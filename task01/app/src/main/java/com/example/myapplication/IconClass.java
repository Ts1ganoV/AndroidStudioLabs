package com.example.myapplication;

public class IconClass {
    private String iconPrefix;
    private static IconClass instance;

    private IconClass(){
        this.iconPrefix = "animal";
    }

    public String getIconPrefix() {
        return iconPrefix;
    }
    public static synchronized IconClass getInstance() {
        if (instance == null) {
            instance = new IconClass();
        }
        return instance;
    }

    public void setIconPrefix(String prefix){
        this.iconPrefix = prefix;
    }
}
