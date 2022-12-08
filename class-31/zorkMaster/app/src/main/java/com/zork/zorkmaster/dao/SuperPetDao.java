package com.zork.zorkmaster.dao;

// TODO Step 7-3 Remove the DAO class
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.zork.zorkmaster.models.SuperPet;
//
//import java.util.List;
//
//// TODO STEP: 4-4 Create a DAO interface for your model
//@Dao // Think of this like a Spring JPA repository, but we have to implement more stuff ourselves
//public interface SuperPetDao {
//  @Insert
//  public void insertASuperPet(SuperPet superPet);
//
//  //find all
//  @Query("SELECT * FROM SuperPet")
//  public List<SuperPet> findAll();
//
//  //findById
//  @Query("SELECT * FROM SuperPet WHERE id = :id")
//  public SuperPet findById(long id);
//
//  // findAllByType
//  @Query("SELECT * FROM SuperPet WHERE type = :type")
//  public List<SuperPet> findAllByType(SuperPet.SuperPetTypeEnum type);
//
//  @Delete
//  public void delete(SuperPet superPet);
//
//  @Update
//  public void update(SuperPet superPet);
//}
