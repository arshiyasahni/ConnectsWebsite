package com.sep.backend.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
public enum UserType {

    EMPLOYER("employer"),

    CANDIDATE("candidate"),

    ADMIN("admin");

    private String userTypeDisplay;

    private static HashMap<String, UserType> userTypeMap = new HashMap<>();

    static {
        for(UserType userType : UserType.values()){
            userTypeMap.put(userType.userTypeDisplay, userType);
        }
    }

    public static UserType getUserType(String userTypeDisplay){
        return userTypeMap.get(userTypeDisplay);
    }
}
