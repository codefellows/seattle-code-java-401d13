package com.zork.zorkmaster.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zork.zorkmaster.R;

// TODO Step 1-6: Make a Fragment class for the RecyclerView ViewHolders
public class SuperPetFragment extends Fragment {


    public SuperPetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuperPetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuperPetFragment newInstance(String param1, String param2) {
        SuperPetFragment fragment = new SuperPetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_super_pet, container, false);
    }
}
