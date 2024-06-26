package com.pattern.task.factory;

import com.pattern.task.model.Plant;

public interface PlantFactory {
    Plant createPlant(String name, double price, String extraAttribute);
}