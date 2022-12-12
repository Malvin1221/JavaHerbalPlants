package com.jhp.javaherbalplants;

public class DataClassMinuman {
    private String dataTitleMinuman;
    private String dataDescMinuman;
    private String dataLangMinuman;
    private String dataImageMinuman;
    private String key;

    public String getKey(){return key;}

    public void setKey(String key){this.key = key;}
    public String getDataTitleMinuman(){
        return dataTitleMinuman;
    }
    public String getDataDescMinuman(){
        return dataDescMinuman;
    }
    public String getDataLangMinuman(){
        return dataLangMinuman;
    }
    public String getDataImageMinuman(){
        return dataImageMinuman;
    }

    public DataClassMinuman(String dataTitle, String dataDesc, String dataLang, String dataImage) {
        this.dataTitleMinuman = dataTitle;
        this.dataDescMinuman = dataDesc;
        this.dataLangMinuman = dataLang;
        this.dataImageMinuman = dataImage;
    }
    public DataClassMinuman(){
    }
}
