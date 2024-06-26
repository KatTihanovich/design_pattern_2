package com.pattern.task.strategy;

import com.pattern.task.model.Plant;

import java.util.List;

public interface PlantSortStrategy {
    public void sort(List<Plant> plants);
}