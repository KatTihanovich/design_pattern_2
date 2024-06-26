package com.pattern.task.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Flower extends Plant {
    private String colour;

    public Flower() {
        super();
    }

    public Flower(String name, double price, String colour) {
        super(name, price);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower flower)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getColour(), flower.getColour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColour());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Flower.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("colour=='" + colour + "'")
                .toString();
    }
}