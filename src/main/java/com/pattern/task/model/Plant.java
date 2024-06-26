package com.pattern.task.model;

import com.pattern.task.util.IdGenerator;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class Plant {
    private final int plantId;
    private String name;
    private double price;

    public Plant() {
        this.plantId = IdGenerator.increment();
    }

    public Plant(String name, double price) {
        this.plantId = IdGenerator.increment();
        this.name = name;
        this.price = price;
    }

    public int getPlantId() {
        return plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant plant)) return false;
        return getPlantId() == plant.getPlantId() && Double.compare(getPrice(), plant.getPrice()) == 0 && Objects.equals(getName(), plant.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlantId(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plant.class.getSimpleName() + "[", "]")
                .add("plantId=" + plantId)
                .add("name='" + name + "'")
                .add("price=" + price)
                .toString();
    }
}