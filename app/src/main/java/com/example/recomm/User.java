package com.example.recomm;

import java.io.Serializable;

public class User implements Serializable {
    public String email;
    public String name;
    private String pass;
    private String birth;

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPass() {
        return pass;
    }

    public String getBirth() {
        return birth;
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
}
