package com.zork.zorkmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.SuperOwner;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.amplifyframework.datastore.generated.model.SuperPetTypeEnum;
import com.zork.zorkmaster.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddASuperPetActivity extends AppCompatActivity {
  public final static String TAG = "AddASuperPetActivity";
  Spinner superPetTypeSpinner;
  Spinner superOwnerSpinner;
  CompletableFuture<List<SuperOwner>> superOwnersFuture = new CompletableFuture<>();
  // TODO Step 3-3 setup the activityResultLauncher
  ActivityResultLauncher<Intent> activityResultLauncher; // at top of class
  private String s3ImageKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
      // TODO Step 3-3 setup the activityResultLauncher
        activityResultLauncher = getImagePickingActivityResultLauncher();
        superPetTypeSpinner = findViewById(R.id.AddASuperPetSpinnerType);
        superOwnerSpinner = findViewById(R.id.AddASuperPetSpinnerSuperOwner);

      Amplify.API.query(
        ModelQuery.list(SuperOwner.class),
        success -> {
          Log.i(TAG, "Read Super Owners Successfully");
          ArrayList<String> ownerNames = new ArrayList<>();
          ArrayList<SuperOwner> superOwners = new ArrayList<>();
          for (SuperOwner superOwner: success.getData()) {
            ownerNames.add(superOwner.getName());
            superOwners.add(superOwner);
          }
          // Todo Step: 1-4 implement CompleteableFuture
          superOwnersFuture.complete(superOwners);
          runOnUiThread(() -> {
            // Todo Step: 1-3 setup owner spinner
            setupSuperOwnerSpinner(ownerNames);
          });
        },
        failure -> {
          // Todo Step: 1-4 implement CompleteableFuture
          superOwnersFuture.complete(null);
          Log.w(TAG, "Failed to read SuperOwners from Database");
        }
      );
        setupAddImageBttn();
        setupTypeSpinner();
        setupSaveBttn();
    }

    public void setupSuperOwnerSpinner(ArrayList<String> ownerNames){
      superOwnerSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        ownerNames
      ));
    }

    public void setupTypeSpinner(){
      superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        SuperPetTypeEnum.values()
      ));
    }

    // TODO refactor move logic to other method -> saveSuperPet
    public void setupSaveBttn() {
    Button saveBttn = findViewById(R.id.AddASuperPetSaveBttn);
    saveBttn.setOnClickListener(v -> {
      saveSuperPet(); // Singe use responsibility
    });
  }

  // TODO 3-2 setupAddImageBttn
    private void setupAddImageBttn(){
      findViewById(R.id.AddASuperPetBttnAddImage).setOnClickListener(v -> {
        launchImageSelectionIntent();
      });
    }

    // Todo Step 3-4 create launchImageSelectionIntent
    private void launchImageSelectionIntent(){
      Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT); // one of several file picking activities built into Android
      //TODO play around with setTypes
      imageFilePickingIntent.setType("*/*");  // only allow one kind or category of file; if you don't have this, you get a very cryptic error about "No activity found to handle Intent
      imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"}); // only pick JPEG and PNG images

      // Launch Android's built-in file picking activity using our newly created ActivityResultLauncher below
      activityResultLauncher.launch(imageFilePickingIntent);
    }

    // TODO Step 3-5 getImagePickingActivityResultLauncher
    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
      ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
        registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            Uri pickedImageFileUri = result.getData().getData();
            try{
              // take in the file URI and turn it into a inputStream
              InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
//              String pickedImageFilename = getFileNameFromUri(pickedImageFileUri);
              String pickedImageFilename = DocumentFile.fromSingleUri(this, pickedImageFileUri).getName();
              Log.i(TAG, "Succeeded in getting input stream from file on phone! Filename is: " + pickedImageFilename);
                uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
            } catch (FileNotFoundException fnfe){
              Log.e(TAG, "Could not get file from file picker" + fnfe.getMessage());
            }
          }
        );
        return imagePickingActivityResultLauncher;
    }

    // TODO Step 3-6 uploadInputStreamToS3
    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFilename, Uri pickedImageFileUri){
      Amplify.Storage.uploadInputStream(
        pickedImageFilename,
        pickedImageInputStream,
        success -> {
          Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
          s3ImageKey = success.getKey();
          // TODO:
//          saveSuperPet();
          ImageView superPetImage = findViewById(R.id.ADdAASuperPetImageViewImage);
          InputStream pickedImageInputStreamCopy = null; // need to make a copy because InputStreams cannot be reused!
          try {
            pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
          } catch (FileNotFoundException fnfe) {
            Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
          }
          superPetImage.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
        },
        failure -> Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFilename + " with error: " + failure.getMessage())
      );
    }

    // TODO 3-7 saveSuperPet
    private void saveSuperPet(){
      String selectedSuperOwnerString = superOwnerSpinner.getSelectedItem().toString();
      List<SuperOwner> superOwners = null;
      try{
        superOwners = superOwnersFuture.get();
      }
      catch (InterruptedException ie) {
        Log.e(TAG, "InterruptedException while getting Super OWners");
        Thread.currentThread().interrupt();
      } catch (ExecutionException ee) {
        Log.e(TAG, "ExecutionException while getting Super Owners");
      }
      SuperOwner selectedOwner = superOwners.stream().filter(owner -> owner.getName().equals(selectedSuperOwnerString)).findAny().orElseThrow(RuntimeException::new);

      SuperPet newSuperPet = SuperPet.builder()
        .name(((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString())
        .type((SuperPetTypeEnum)superPetTypeSpinner.getSelectedItem())
        .birthDate(new Temporal.DateTime(new Date(), 0))
        .height(Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()))
        .superOwner(selectedOwner)
        .s3ImageKey(s3ImageKey)
        .build();

      Amplify.API.mutate(
        ModelMutation.create(newSuperPet),
        success -> Log.i(TAG, "AddASuperPetActivity.onCreate(): made a super pet successfully!"),
        failure -> Log.w(TAG, "AddASuperPetActivity.onCreate(): failed to make a super pet", failure)
      );
      Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
    }

    // OUTDATED!!!!
    //TODO read through this helper function
    // TODO consider other easy to get filename from URI -> STRETCH GOAL
  // Taken from https://stackoverflow.com/a/25005243/16889809
  @SuppressLint("Range")
  public String getFileNameFromUri(Uri uri) {
    String result = null;
    if (uri.getScheme().equals("content")) {
      Cursor cursor = getContentResolver().query(uri, null, null, null, null);
      try {
        if (cursor != null && cursor.moveToFirst()) {
          result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
      } finally {
        cursor.close();
      }
    }
    if (result == null) {
      result = uri.getPath();
      int cut = result.lastIndexOf('/');
      if (cut != -1) {
        result = result.substring(cut + 1);
      }
    }
    return result;
  }


  // TODO possible refactor
//    public String getFileNameFromURI(URI uri) throws MalformedURLException {
//      URL url = uri.toURL();
//      String filePath = url.getFile();
//      String[] segments = filePath.split("/");
//      return segments[segments.length - 1];
//    }
}
