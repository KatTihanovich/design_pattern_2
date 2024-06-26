package com.pattern.task.strategy.impl;

import com.pattern.task.model.Plant;
import com.pattern.task.strategy.PlantSortStrategy;

import java.util.Comparator;
import java.util.List;

public class IdSortStrategy implements PlantSortStrategy {
    @Override
    public void sort(List<Plant> plants) {
        plants.sort(Comparator.comparingInt(Plant::getPlantId));
    }
}