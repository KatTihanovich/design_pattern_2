package com.pattern.task.main;

import com.pattern.task.observer.impl.PlantObserverImpl;
import com.pattern.task.reader.PlantFileReader;
import com.pattern.task.model.Plant;
import com.pattern.task.repository.impl.PlantRepositoryImpl;
import com.pattern.task.strategy.PlantSortStrategy;
import com.pattern.task.strategy.impl.ExtraAttributeSortStrategy;
import com.pattern.task.strategy.impl.IdSortStrategy;
import com.pattern.task.strategy.impl.NameSortStrategy;
import com.pattern.task.strategy.impl.PriceSortStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_PATH = "src/main/resources/listOfProducts.txt";

    public static void main(String[] args) throws FileNotFoundException {
        logger.info("Reading plant data from file...");
        List<Plant> plants = PlantFileReader.readPlant(FILE_PATH);
        printPlants(plants);

        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        PlantObserverImpl plantObserver = new PlantObserverImpl();
        repository.attach(plantObserver);

        plants.forEach(repository::add);

        logger.info("Sorting plants by price...");
        PlantSortStrategy priceSortStrategy = new PriceSortStrategy();
        repository.sort(priceSortStrategy);
        printPlants(repository.findAll());

        logger.info("Sorting plants by name...");
        PlantSortStrategy nameSortStrategy = new NameSortStrategy();
        repository.sort(nameSortStrategy);
        printPlants(repository.findAll());

        logger.info("Sorting plants by ID...");
        PlantSortStrategy idSortStrategy = new IdSortStrategy();
        repository.sort(idSortStrategy);
        printPlants(repository.findAll());

        logger.info("Sorting plants by extra attribute...");
        PlantSortStrategy extraAttributeSortStrategy = new ExtraAttributeSortStrategy();
        repository.sort(extraAttributeSortStrategy);
        printPlants(repository.findAll());

        logger.info("Finding plant with ID 1...");
        Optional<Plant> plantById = repository.findById(1);
        plantById.ifPresent(plant -> logger.info("Found: " + plant));

        logger.info("Finding plants with name 'Aloe Vera'...");
        List<Plant> plantsByName = repository.findByName("Aloe Vera");
        printPlants(plantsByName);

        logger.info("Finding plants with price between 1.0 and 5.0...");
        List<Plant> plantsByPriceRange = repository.findByPriceRange(1.0, 5.0);
        printPlants(plantsByPriceRange);

        logger.info("Finding plants with extra attribute 'Red'...");
        List<Plant> plantsByExtraAttribute = repository.findByExtraAttribute("Red");
        printPlants(plantsByExtraAttribute);

        if (!plants.isEmpty()) {
            Plant plantToRemove = plants.get(0);
            logger.info("Removing plant: " + plantToRemove);
            repository.remove(plantToRemove);
            logger.info("Plant removed. Current list:");
            printPlants(repository.findAll());
        }
    }

    private static void printPlants(List<Plant> plants) {
        for (Plant plant : plants) {
            logger.info(plant);
        }
    }
}