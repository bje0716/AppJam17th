package com.grapefruit.appjam.datas;

public class UserData {

    private String email;
    private String name;
    private String nickname;
    private String value;

    public UserData(String email, String name, String nickname, String value) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
