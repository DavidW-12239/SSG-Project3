package com.ssg.team.domain;

public class NoteBook implements Equipment{
    private double price;
    private String model;

    public NoteBook() {
    }

    public NoteBook(double price, String model) {
        this.price = price;
        this.model = model;
    }

    @Override
    public String getDescription() {
        return model + "(" + price + ")";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
