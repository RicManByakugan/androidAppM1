package com.example.wm.model;

public class Post {

    private String _id = null;
    private String imageUrl = null;
    private String title = null;
    private String datePost = null;
    private String Lieu = null;

    public Post(String _id, String imageUrl, String title, String datePost, String lieu) {
        this._id = _id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.datePost = datePost;
        Lieu = lieu;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }
}
