package com.pattern.task.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Succulent extends Plant {
    private String type;

    public Succulent() {
        super();
    }

    public Succulent(String name, double price, String type) {
        super(name, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Succulent succulent)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getType(), succulent.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Succulent.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("type='" + type + "'")
                .toString();
    }
}