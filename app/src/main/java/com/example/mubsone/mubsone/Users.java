package com.example.mubsone.mubsone;

import android.media.Image;

import org.w3c.dom.Text;

/**
 * Created by andyekko on 24/08/15.
 */
//The user interface like in the database made

public class Users {

    private long Id_User;
    private String Fistname;
    private String Lastname;
    private String Username;
    private String Password;
    private int Rating;   // out of the 5 starts
    private int Fans;     // the number of the fans for the users
    private Text Biography;  //text type ??
    private boolean isBanned;
    private String Email;
    private Image Avatar;
    private boolean isAdmin;
    private boolean isPremium;

    //??The user can be an artist right ??


    public Users(long id_User, String fistname, String lastname, String username, String password, int rating, int fans, Text biography, boolean isBanned, String email, Image avatar, boolean isAdmin, boolean isPremium) {
        Id_User = id_User;
        Fistname = fistname;
        Lastname = lastname;
        Username = username;
        Password = password;
        Rating = rating;
        Fans = fans;
        Biography = biography;
        this.isBanned = isBanned;
        Email = email;
        Avatar = avatar;
        this.isAdmin = isAdmin;
        this.isPremium = isPremium;
    }

    public long getId_User() {
        return Id_User;
    }

    public void setId_User(long id_User) {
        Id_User = id_User;
    }

    public String getFistname() {
        return Fistname;
    }

    public void setFistname(String fistname) {
        Fistname = fistname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getFans() {
        return Fans;
    }

    public void setFans(int fans) {
        Fans = fans;
    }

    public Text getBiography() {
        return Biography;
    }

    public void setBiography(Text biography) {
        Biography = biography;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Image getAvatar() {
        return Avatar;
    }

    public void setAvatar(Image avatar) {
        Avatar = avatar;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
}
