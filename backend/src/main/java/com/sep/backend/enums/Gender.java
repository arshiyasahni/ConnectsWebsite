package com.sep.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * The enum Gender.
 */
@AllArgsConstructor
@Getter
public enum Gender {
     
     /**
      * Male gender.
      */
     MALE(0, "male"),
     /**
      * Female gender.
      */
     FEMALE(1, "female");

private Integer genderNumber;
private String genderDisplay;

private static HashMap<String, Gender> genderMap = new HashMap<>();

static {
     for(Gender gender : Gender.values()){
          genderMap.put(gender.genderDisplay, gender);
     }
}

/**
 * Get gender gender.
 *
 * @param gender the gender
 * @return the gender
 */
public static Gender getGender(String gender){
     return genderMap.get(gender);
}

}
