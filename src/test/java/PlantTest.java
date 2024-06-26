import com.pattern.task.factory.impl.FlowerFactoryImpl;
import com.pattern.task.factory.impl.SucculentFactoryImpl;
import com.pattern.task.model.Flower;
import com.pattern.task.model.Plant;
import com.pattern.task.model.Succulent;
import com.pattern.task.reader.PlantFileReader;
import com.pattern.task.repository.impl.PlantRepositoryImpl;
import com.pattern.task.strategy.impl.ExtraAttributeSortStrategy;
import com.pattern.task.strategy.impl.IdSortStrategy;
import com.pattern.task.strategy.impl.NameSortStrategy;
import com.pattern.task.strategy.impl.PriceSortStrategy;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    @Test
    void shouldAddPlantToList() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant = new Succulent("Aloe Vera", 10.0, "Leafy");

        repository.add(plant);
        assertEquals(1, repository.findAll().size());
        assertEquals(plant, repository.findAll().get(0));
    }

    @Test
    void shouldRemovePlantFromList() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant = new Succulent("Aloe Vera", 10.0, "Leafy");
        repository.add(plant);

        repository.remove(plant);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void shouldReturnEmptyIfPlantNotFound() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Optional<Plant> foundPlant = repository.findById(1);
        assertFalse(foundPlant.isPresent());
    }

    @Test
    void shouldReturnAllPlants() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Aloe Vera", 10.0, "Leafy");
        Plant plant2 = new Flower("Rose", 5.0, "Red");
        repository.add(plant1);
        repository.add(plant2);

        List<Plant> plants = repository.findAll();
        assertEquals(2, plants.size());
        assertTrue(plants.contains(plant1));
        assertTrue(plants.contains(plant2));
    }

    @Test
    void shouldFindPlantsByName() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant = new Succulent("Aloe Vera", 10.0, "Leafy");
        repository.add(plant);

        List<Plant> plantsByName = repository.findByName("Aloe Vera");
        assertEquals(1, plantsByName.size());
        assertEquals(plant, plantsByName.get(0));
    }

    @Test
    void shouldReturnEmptyIfNoPlantsFoundByName() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        List<Plant> plantsByName = repository.findByName("Nonexistent");
        assertEquals(0, plantsByName.size());
    }

    @Test
    void shouldFindPlantsByPriceRange() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Aloe Vera", 10.0, "Leafy");
        Plant plant2 = new Flower("Rose", 5.0, "Red");
        repository.add(plant1);
        repository.add(plant2);

        List<Plant> plantsByPriceRange = repository.findByPriceRange(1.0, 10.0);
        assertEquals(2, plantsByPriceRange.size());
        assertTrue(plantsByPriceRange.contains(plant1));
        assertTrue(plantsByPriceRange.contains(plant2));
    }

    @Test
    void shouldReturnEmptyIfNoPlantsFoundByPriceRange() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        List<Plant> plantsByPriceRange = repository.findByPriceRange(20.0, 30.0);
        assertEquals(0, plantsByPriceRange.size());
    }

    @Test
    void shouldFindPlantsByExtraAttribute() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant = new Flower("Rose", 5.0, "Red");
        repository.add(plant);

        List<Plant> plantsByExtraAttribute = repository.findByExtraAttribute("Red");
        assertEquals(1, plantsByExtraAttribute.size());
        assertEquals(plant, plantsByExtraAttribute.get(0));
    }

    @Test
    void shouldReturnEmptyIfNoPlantsFoundByExtraAttribute() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        List<Plant> plantsByExtraAttribute = repository.findByExtraAttribute("Nonexistent");
        assertEquals(0, plantsByExtraAttribute.size());
    }

    @Test
    void shouldSortPlantsByPrice() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Aloe Vera", 10.0, "Leafy");
        Plant plant2 = new Flower("Rose", 5.0, "Red");
        repository.add(plant1);
        repository.add(plant2);

        repository.sort(new PriceSortStrategy());
        List<Plant> sortedPlants = repository.findAll();
        assertEquals(plant2, sortedPlants.get(0));
        assertEquals(plant1, sortedPlants.get(1));
    }

    @Test
    void shouldSortPlantsByName() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Aloe Vera", 10.0, "Leafy");
        Plant plant2 = new Flower("Rose", 5.0, "Red");
        repository.add(plant2);
        repository.add(plant1);

        repository.sort(new NameSortStrategy());
        List<Plant> sortedPlants = repository.findAll();
        assertEquals(plant1, sortedPlants.get(0));
        assertEquals(plant2, sortedPlants.get(1));
    }

    @Test
    void shouldSortPlantsById() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Aloe Vera", 10.0, "Leafy");
        Plant plant2 = new Flower("Rose", 5.0, "Red");
        repository.add(plant1);
        repository.add(plant2);

        repository.sort(new IdSortStrategy());
        List<Plant> sortedPlants = repository.findAll();
        assertEquals(plant1, sortedPlants.get(0));
        assertEquals(plant2, sortedPlants.get(1));
    }

    @Test
    void shouldSortPlantsByExtraAttribute() {
        PlantRepositoryImpl repository = new PlantRepositoryImpl();
        Plant plant1 = new Succulent("Cactus", 15.0, "Stem");
        Plant plant2 = new Succulent("Aloe Vera", 10.0, "Leafy");
        repository.add(plant1);
        repository.add(plant2);

        repository.sort(new ExtraAttributeSortStrategy());
        List<Plant> sortedPlants = repository.findAll();
        assertEquals(plant2, sortedPlants.get(0));
        assertEquals(plant1, sortedPlants.get(1));
    }

    @Test
    void shouldReadPlantsFromValidFile() throws FileNotFoundException {
        String filePath = "src/test/resources/validPlants.txt";
        List<Plant> plants = PlantFileReader.readPlant(filePath);
        assertEquals(3, plants.size());
    }

    @Test
    void shouldHandleInvalidData() throws FileNotFoundException {
        String filePath = "src/test/resources/invalidPlants.txt";
        List<Plant> plants = PlantFileReader.readPlant(filePath);
        assertEquals(1, plants.size());
    }

    @Test
    void shouldHandleUnknownTags() throws FileNotFoundException {
        String filePath = "src/test/resources/unknownTagsPlants.txt";
        List<Plant> plants = PlantFileReader.readPlant(filePath);
        assertEquals(2, plants.size());
    }

    @Test
    void shouldThrowFileNotFoundExceptionIfFileNotFound() {
        String filePath = "src/test/resources/validPla.txt";
        assertThrows(FileNotFoundException.class, () -> PlantFileReader.readPlant(filePath));
    }

    @Test
    void shouldCreateSucculentWithCorrectValues() {
        SucculentFactoryImpl factory = new SucculentFactoryImpl();
        String name = "Aloe Vera";
        double price = 10.0;
        String type = "TypeA";

        Succulent succulent = (Succulent) factory.createPlant(name, price, type);

        assertEquals(name, succulent.getName());
        assertEquals(price, succulent.getPrice());
        assertEquals(type, succulent.getType());
        assertTrue(succulent instanceof Succulent);
    }

    @Test
    void shouldCreateFlowerWithCorrectValues() {
        FlowerFactoryImpl factory = new FlowerFactoryImpl();
        String name = "Rose";
        double price = 5.0;
        String color = "Red";

        Flower flower = (Flower) factory.createPlant(name, price, color);

        assertEquals(name, flower.getName());
        assertEquals(price, flower.getPrice());
        assertEquals(color, flower.getColour());
        assertTrue(flower instanceof Flower);
    }
}