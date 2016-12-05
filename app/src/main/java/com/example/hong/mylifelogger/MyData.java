package com.example.hong.mylifelogger;

/**
 * Created by admin on 2016-11-26.
 */
public class MyData {
    private int id;
    private String date;
    private String time;
    private String address;
    private String type;
    private String title;
    private String detail;
    private double latitude;
    private double longitude;
    private String picturekey;


    public MyData(int i, String d, String t, String ad, double latitude, double longitude,  String ty, String ti,
           String de, String pk){
        id = i;
        date = d;
        time = t;
        address = ad;
        type = ty;
        title = ti;
        detail = de;
        this.latitude = latitude;
        this.longitude = longitude;
        picturekey = pk;
    }
    public String getDate() {
        return date;
    }
    public int getId(){return id;}
    public String getTime() {
        return time;
    }
    public String getAddress() {
        return address;
    }
    public String getType() { return type;}

    public void setText(String text) {
        this.title = text ;
    }

    public String getTitle() {return this.title;}
    public String getDetail() { return detail;}
    public double getLatitude(){  return latitude;    }
    public double getLongitude(){
        return longitude;
    }
    public String getPicturekey(){return picturekey; }

    public String getPrint() {
        return "ID: "+ id+ "\n"+
                date + " " + time +"\n" +
                "위도: "+ latitude+ "\n경도: "+ longitude+"\n"+
                "주소: "+getAddress() + "\n" +
                "일상 종류 : " +  type+"\n" +
                "내용:" +  detail;
    }
}