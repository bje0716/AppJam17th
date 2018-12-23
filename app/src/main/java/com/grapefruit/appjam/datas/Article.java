package com.grapefruit.appjam.datas;

import android.net.Uri;

public class Article {

    private Uri profile_img;
    private String title;
    private String content;
    private Uri img;

    public Article(Uri profile_img, String title, String content, Uri img) {
        this.profile_img = profile_img;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public Uri getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Uri profile_img) {
        this.profile_img = profile_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}
