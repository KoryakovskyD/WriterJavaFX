package ru.avalon.javapp.devj140.writerjavafx.DataBase;

public class Car {
    Integer num;
    String model;

    public Car(Integer num, String model) {
        this.num = num;
        this.model = model;
    }

    public Integer getNum() {
        return num;
    }

    public String getModel() {
        return model;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
