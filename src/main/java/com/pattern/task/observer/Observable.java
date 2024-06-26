package com.pattern.task.observer;

import com.pattern.task.model.Plant;

public interface Observable {
    void attach();
    void detach();

    void attach(PlantObserver observer);

    void detach(PlantObserver observer);

    void notifyObservers(String eventType, Plant plant);
}