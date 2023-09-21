package com.example.myapplication;

public class PoleSize {

    private static PoleSize instance;
    private String size;

    private PoleSize() {
        this.size = "4";
    }
    public static synchronized PoleSize getInstance() {
        if (instance == null) {
            instance = new PoleSize();
        }
        return instance;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIntSize(){
        int c = size.charAt(0);
        if(c<=47 || c>=58){
            return 4;
        }else{
            int s = Integer.parseInt(size);
            if(s < 2 || s >6){
                return 4;
            }else{
                return s;
            }
        }
    }
}
