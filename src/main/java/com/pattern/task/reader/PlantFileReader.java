package com.pattern.task.reader;

import com.pattern.task.exception.UnknownDataException;
import com.pattern.task.factory.PlantFactory;
import com.pattern.task.factory.impl.FlowerFactoryImpl;
import com.pattern.task.factory.impl.SucculentFactoryImpl;
import com.pattern.task.model.Plant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlantFileReader {
    private static final int NUMBER_OF_ELEMENTS = 4;
    private static final String DELIMITER = "; ";

    private static final Logger logger = LogManager.getLogger();

    public static List<Plant> readPlant(String filePath) throws FileNotFoundException {
        List<Plant> plantsList = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                String[] stringData = line.split(DELIMITER);
                if (stringData.length == NUMBER_OF_ELEMENTS) {
                    try {
                        Plant plant = writePlant(stringData);
                        plantsList.add(plant);
                    } catch (NumberFormatException e) {
                        logger.error("Error parsing number for line: " + line, e);
                    } catch (UnknownDataException e) {
                        logger.error("Unknown plant tag for line: " + line, e);
                    }
                } else {
                    logger.warn("Incorrect number of elements for line: " + line);
                }
            });
        } catch (IOException e) {
            logger.error("Error reading file: " + filePath, e);
            throw new FileNotFoundException();
        }
        return plantsList;
    }

    private static Plant writePlant(String[] stringData) throws UnknownDataException {
        String tag = stringData[0];
        String name = stringData[1];
        double price = Double.parseDouble(stringData[2]);
        String extraAttribute = stringData[3];

        PlantFactory factory;
        if ("Succulent".equalsIgnoreCase(tag)) {
            factory = new SucculentFactoryImpl();
        } else if ("Flower".equalsIgnoreCase(tag)) {
            factory = new FlowerFactoryImpl();
        } else {
            throw new UnknownDataException("Unknown plant tag, remove it: " + tag);
        }

        return factory.createPlant(name, price, extraAttribute);
    }
}