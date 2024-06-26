package com.pattern.task.repository.impl;

import com.pattern.task.model.Flower;
import com.pattern.task.model.Plant;
import com.pattern.task.model.Succulent;
import com.pattern.task.observer.PlantObserver;
import com.pattern.task.observer.Observable;
import com.pattern.task.repository.PlantRepository;
import com.pattern.task.strategy.PlantSortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlantRepositoryImpl implements PlantRepository<Plant>, Observable {
    private final List<Plant> plants = new ArrayList<>();
    private final List<PlantObserver> observers = new ArrayList<>();

    @Override
    public void add(Plant plant) {
        plants.add(plant);
        notifyObservers("Plant Added", plant);
    }

    @Override
    public void remove(Plant plant) {
        plants.remove(plant);
        notifyObservers("Plant Removed", plant);
    }

    @Override
    public Optional<Plant> findById(int id) {
        return plants.stream().filter(plant -> plant.getPlantId() == id).findFirst();
    }

    @Override
    public List<Plant> findAll() {
        return new ArrayList<>(plants);
    }

    @Override
    public List<Plant> findByName(String name) {
        return plants.stream()
                .filter(plant -> plant.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Plant> findByPriceRange(double minPrice, double maxPrice) {
        return plants.stream()
                .filter(plant -> plant.getPrice() >= minPrice && plant.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Plant> findByExtraAttribute(String extraAttribute) {
        return plants.stream()
                .filter(plant -> {
                    if (plant instanceof Succulent) {
                        return ((Succulent) plant).getType().equalsIgnoreCase(extraAttribute);
                    } else if (plant instanceof Flower) {
                        return ((Flower) plant).getColour().equalsIgnoreCase(extraAttribute);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void attach() {
    }

    @Override
    public void detach() {
    }

    @Override
    public void attach(PlantObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(PlantObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String eventType, Plant plant) {
        for (PlantObserver observer : observers) {
            observer.update(eventType, plant);
        }
    }

    @Override
    public void sort(PlantSortStrategy strategy) {
        strategy.sort(plants);
        notifyObservers("Plants Sorted", null);
    }
}