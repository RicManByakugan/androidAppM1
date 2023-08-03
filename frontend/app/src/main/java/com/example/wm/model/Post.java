package com.example.wm.model;

public class Post {

    private String _id = null;
    private String image_url = null;

    private String video_url = null;
    private String title = null;
    private String datePost = null;
    private String Lieu = null;

    public Post(String _id, String image_url, String title, String datePost, String lieu) {
        this._id = _id;
        this.image_url = image_url;
        this.video_url = image_url;
        this.title = title;
        this.datePost = datePost;
        Lieu = lieu;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getimage_url() {
        return image_url;
    }

    public void setimage_url(String imageUrl) {
        this.image_url = image_url;
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
