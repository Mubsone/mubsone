package com.example.mubsone.mubsone;

/**
 * Created by andyekko on 24/08/15.
 */

//??an artist can be a user too?? right ??
public class Artist {
    private String Fistname;
    private String Lastname;
    private String Biogtaphy;


    public Artist(String fistname, String lastname, String biogtaphy) {
        Fistname = fistname;
        Lastname = lastname;
        Biogtaphy = biogtaphy;
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

    public String getBiogtaphy() {
        return Biogtaphy;
    }

    public void setBiogtaphy(String biogtaphy) {
        Biogtaphy = biogtaphy;
    }
}
