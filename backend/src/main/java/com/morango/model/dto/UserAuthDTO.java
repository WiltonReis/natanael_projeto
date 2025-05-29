package com.morango.model.dto;

public class UserAuthDTO {
    private String userName;
    private String password;

    public UserAuthDTO() {}

    public UserAuthDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }
}

