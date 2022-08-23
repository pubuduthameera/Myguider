package com.example.myguider;

import android.net.Uri;

public class Firebase_database {
    String usern,email,password,country,mobile,desription, imgUrl;

    public Firebase_database() {
    }

    public Firebase_database(String usern, String email, String password, String country, String mobile, String desription, String imgUrl) {
        this.usern = usern;
        this.email = email;
        this.password = password;
        this.country = country;
        this.mobile = mobile;
        this.imgUrl = imgUrl;
        this.desription=desription;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUri(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Firebase_database{" +
                "usern='" + usern + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", mobile='" + mobile + '\'' +
                ", desription='" + desription + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
