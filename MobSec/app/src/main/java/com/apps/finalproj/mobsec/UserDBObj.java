package com.apps.finalproj.mobsec;

/**
 * Created by Poornima on 12/6/15.
 */
public class UserDBObj {
    String userName;
    String password;
    String emergContact;
    String location;
    String email;

    public UserDBObj(String un, String pass, String cont, String loc, String em) {
        this.userName = un;
        this.password = pass;
        this.emergContact = cont;
        this.location = loc;
        this.email = em;
    }

    public String getName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
