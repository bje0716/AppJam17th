package com.grapefruit.appjam.datas;

import android.net.Uri;

public class Comment {

    private Uri img;
    private String nickname;
    private String content;
    private Long date;

    public Comment(Uri img, String nickname, String content, Long date) {
        this.img = img;
        this.nickname = nickname;
        this.content = content;
        this.date = date;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
