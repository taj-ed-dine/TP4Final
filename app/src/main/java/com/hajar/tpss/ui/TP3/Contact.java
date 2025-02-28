package com.hajar.tpss.ui.TP3;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private  String Prenom,Name,Phone,Email;

    public Contact(int id, String Prenom, String Name, String Phone, String Email) {
        this.id = id;
        this.Prenom = Prenom;
        this.Name = Name;
        this.Phone = Phone;
        this.Email =Email;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom= Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = Phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Prenom+ " "  +Name ;

    }
}
