package com.mvo.crud.model;

import java.util.Objects;

public class Lable {
    private int id;
    private String name;

    public Lable(String name) {
        this.name = name;
    }

    public Lable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lable lable = (Lable) o;
        return id == lable.id && Objects.equals(name, lable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
