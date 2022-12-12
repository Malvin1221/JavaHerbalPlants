package com.jhp.javaherbalplants;

public class Users {

    private String Uid;
    private String Nama;
    private String Email;
    private String Password;
    private int usertype;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public Users(String uid, String nama, String email, String password, int usertype) {
        Uid = uid;
        Nama = nama;
        Email = email;
        Password = password;
        usertype=usertype;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
