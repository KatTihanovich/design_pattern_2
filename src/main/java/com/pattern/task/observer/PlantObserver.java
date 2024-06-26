package com.pattern.task.observer;

import com.pattern.task.model.Plant;

public interface PlantObserver {
    void update(String eventType, Plant plant);
}