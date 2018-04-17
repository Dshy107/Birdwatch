package com.example.mathias.birdwatch;




import java.io.Serializable;

public class Bird implements Serializable {

    private int Id;
    private String NameEnglish;
    private String NameDanish;
    private String PhotoUrl;
    private String UserId;
    private String Created;

    public Bird(){

    }

    public Bird(int id, String nameEnglish, String nameDanish, String photoUrl,
                String userId, String created){
        this.Id = id;
        this.NameEnglish = nameEnglish;
        this.NameDanish = nameDanish;
        this.PhotoUrl = photoUrl;
        this.UserId = userId;
        this.Created = created;
    }
    public void setId(int id){
        this.Id = id;
    }
    public void setNameEnglish(String nameEnglish){
        this.NameEnglish = nameEnglish;
    }
    public void setNameDanish(String nameDanish){
        this.NameDanish = nameDanish;
    }
    public void setPhotoUrl(String photoUrl){
        this.PhotoUrl = photoUrl;
    }
    public void setUserId(String userId){
        this.UserId = userId;
    }
    public void setCreated(String created){
        this.Created = created;
    }
    public int getId(){
        return Id;
    }
    public String getNameEnglish(){
        return NameEnglish;
    }
    public String getNameDanish(){
        return NameDanish;
    }
    public String getPhotoUrl(){
        return PhotoUrl;
    }
    public String getUserId(){
        return UserId;
    }
    public String getCreated(){
        return Created;
    }
    @Override
    public String toString(){
        return NameEnglish;
    }

}
