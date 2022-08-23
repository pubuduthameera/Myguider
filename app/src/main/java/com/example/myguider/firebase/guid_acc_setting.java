package com.example.myguider.firebase;

public class guid_acc_setting {
    String username,displayname,country,desription, propic;
    long rating,posts;

    public guid_acc_setting() {
    }

    public guid_acc_setting(String username, String displayname, String country, String desription, String propic, long rating, long posts) {
        this.username = username;
        this.displayname = displayname;
        this.country = country;
        this.desription = desription;
        this.propic = propic;
        this.rating = rating;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic)
    {
        this.propic = propic;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "guid_acc_setting{" +
                "username='" + username + '\'' +
                ", displayname='" + displayname + '\'' +
                ", country='" + country + '\'' +
                ", desription='" + desription + '\'' +
                ", propic='" + propic + '\'' +
                ", rating=" + rating +
                ", posts=" + posts +
                '}';
    }


}
