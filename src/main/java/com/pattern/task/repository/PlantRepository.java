package com.pattern.task.repository;

import com.pattern.task.strategy.PlantSortStrategy;

import java.util.List;
import java.util.Optional;

public interface PlantRepository<T> {
    public void add(T item);

    public void remove(T item);

    public Optional<T> findById(int id);

    public List<T> findAll();

    public List<T> findByName(String name);

    public List<T> findByPriceRange(double minPrice, double maxPrice);

    public List<T> findByExtraAttribute(String extraAttribute);

    public void sort(PlantSortStrategy strategy);
}