package com.example.congratsapp;
public class ToDo {
    String name;
    int value;

    ToDo(String name, int value) {
        this.name= name;
        this.value= value;

    }
    public String getName() {
        return this.name;
    }
    public int getValue() {
        return this.value;
    }
    public void setValue(int val) { this.value = val; }
}

