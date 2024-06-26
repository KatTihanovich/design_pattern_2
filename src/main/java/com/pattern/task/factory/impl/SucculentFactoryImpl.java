package com.pattern.task.factory.impl;

import com.pattern.task.factory.PlantFactory;
import com.pattern.task.model.Plant;
import com.pattern.task.model.Succulent;

public class SucculentFactoryImpl implements PlantFactory {
    @Override
    public Plant createPlant(String name, double price, String type) {
        return new Succulent(name, price, type);
    }
}