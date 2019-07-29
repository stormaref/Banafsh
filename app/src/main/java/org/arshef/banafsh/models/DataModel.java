package org.arshef.banafsh.models;

public class DataModel {
    public int Id;
    public int Position;
    public String Title;
    public String University;
    public int Code;

    public DataModel(int id,int position, String title, String university, int code) {
        Id = id;
        Position = position;
        Title = title;
        University = university;
        Code = code;
    }
}
