package com.example.bakeryapp;

public class Model {
    public String name;
    public String rupees;
    public int image;

    public Model(String name, String rupees, int image) {
        this.name = name;
        this.rupees = rupees;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
