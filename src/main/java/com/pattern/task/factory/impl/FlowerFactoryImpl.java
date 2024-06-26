package com.pattern.task.factory.impl;

import com.pattern.task.factory.PlantFactory;
import com.pattern.task.model.Plant;
import com.pattern.task.model.Flower;

public class FlowerFactoryImpl implements PlantFactory {

    @Override
    public Plant createPlant(String name, double price, String colour) {
        return new Flower(name, price, colour);
    }
}