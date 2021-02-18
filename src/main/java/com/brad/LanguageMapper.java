package com.brad;

public class LanguageMapper {

    public static String map(String language){
        if( language.equalsIgnoreCase("english")){
            return "en";
        }
        else if( language.equalsIgnoreCase("russian")){
            return "ru";
        }
        else{
            throw new IllegalArgumentException(language);
        }
    }
}
