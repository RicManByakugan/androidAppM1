package com.example.wm.model;

public class Notification {

    private String _id = null;
    private String message = null;
    private String postID = null;
    private String typePost = null;
    private String dateNotif = null;

    public Notification(String _id, String message, String postID, String typePost, String dateNotif) {
        this._id = _id;
        this.message = message;
        this.postID = postID;
        this.typePost = typePost;
        this.dateNotif = dateNotif;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getTypePost() {
        return typePost;
    }

    public void setTypePost(String typePost) {
        this.typePost = typePost;
    }

    public String getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(String dateNotif) {
        this.dateNotif = dateNotif;
    }
}
