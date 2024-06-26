package com.pattern.task.observer.impl;

import com.pattern.task.model.Plant;
import com.pattern.task.observer.PlantObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlantObserverImpl implements PlantObserver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void update(String eventType, Plant plant) {
        logger.info("Event: " + eventType + " - " + (plant != null ? plant.toString() : "null"));
    }
}