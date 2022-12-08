package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the SuperPet type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SuperPets", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class SuperPet implements Model {
  public static final QueryField ID = field("SuperPet", "id");
  public static final QueryField NAME = field("SuperPet", "name");
  public static final QueryField TYPE = field("SuperPet", "type");
  public static final QueryField BIRTH_DATE = field("SuperPet", "birthDate");
  public static final QueryField HEIGHT = field("SuperPet", "height");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="SuperPetTypeEnum") SuperPetTypeEnum type;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime birthDate;
  private final @ModelField(targetType="Int") Integer height;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public SuperPetTypeEnum getType() {
      return type;
  }
  
  public Temporal.DateTime getBirthDate() {
      return birthDate;
  }
  
  public Integer getHeight() {
      return height;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private SuperPet(String id, String name, SuperPetTypeEnum type, Temporal.DateTime birthDate, Integer height) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.birthDate = birthDate;
    this.height = height;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      SuperPet superPet = (SuperPet) obj;
      return ObjectsCompat.equals(getId(), superPet.getId()) &&
              ObjectsCompat.equals(getName(), superPet.getName()) &&
              ObjectsCompat.equals(getType(), superPet.getType()) &&
              ObjectsCompat.equals(getBirthDate(), superPet.getBirthDate()) &&
              ObjectsCompat.equals(getHeight(), superPet.getHeight()) &&
              ObjectsCompat.equals(getCreatedAt(), superPet.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), superPet.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getType())
      .append(getBirthDate())
      .append(getHeight())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SuperPet {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("birthDate=" + String.valueOf(getBirthDate()) + ", ")
      .append("height=" + String.valueOf(getHeight()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static SuperPet justId(String id) {
    return new SuperPet(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      type,
      birthDate,
      height);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    SuperPet build();
    BuildStep id(String id);
    BuildStep type(SuperPetTypeEnum type);
    BuildStep birthDate(Temporal.DateTime birthDate);
    BuildStep height(Integer height);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private SuperPetTypeEnum type;
    private Temporal.DateTime birthDate;
    private Integer height;
    @Override
     public SuperPet build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SuperPet(
          id,
          name,
          type,
          birthDate,
          height);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep type(SuperPetTypeEnum type) {
        this.type = type;
        return this;
    }
    
    @Override
     public BuildStep birthDate(Temporal.DateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    
    @Override
     public BuildStep height(Integer height) {
        this.height = height;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, SuperPetTypeEnum type, Temporal.DateTime birthDate, Integer height) {
      super.id(id);
      super.name(name)
        .type(type)
        .birthDate(birthDate)
        .height(height);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder type(SuperPetTypeEnum type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder birthDate(Temporal.DateTime birthDate) {
      return (CopyOfBuilder) super.birthDate(birthDate);
    }
    
    @Override
     public CopyOfBuilder height(Integer height) {
      return (CopyOfBuilder) super.height(height);
    }
  }
  
}
