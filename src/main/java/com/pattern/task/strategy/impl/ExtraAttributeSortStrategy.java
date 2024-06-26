package com.pattern.task.strategy.impl;

import com.pattern.task.model.Flower;
import com.pattern.task.model.Plant;
import com.pattern.task.model.Succulent;
import com.pattern.task.strategy.PlantSortStrategy;

import java.util.Comparator;
import java.util.List;

public class ExtraAttributeSortStrategy implements PlantSortStrategy {
    @Override
    public void sort(List<Plant> plants) {
        plants.sort(Comparator.comparing(plant -> {
            if (plant instanceof Succulent) {
                return ((Succulent) plant).getType();
            } else if (plant instanceof Flower) {
                return ((Flower) plant).getColour();
            }
            return "";
        }));
    }
}