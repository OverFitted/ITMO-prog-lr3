package exmp;

import exmp.entities.Manufacturer;
import exmp.entities.Character;
import exmp.locations.Location;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Location lumberMill = new Location("Лесопилка", new ArrayList<>());
        Location cityMarket = new Location("Городской рынок", new ArrayList<>());
        Location tavern = new Location("Таверна", new ArrayList<>());
        Location forest = new Location("Лес", new ArrayList<>());
        Location castle = new Location("Замок", new ArrayList<>());
        Manufacturer dube = new Manufacturer("Дубе", "Владелец лесопильных заводов", lumberMill);
        Character spruts = new Character("Спрутс", "Господин Спрутс", lumberMill);

        List<Character> characters = new ArrayList<>(Arrays.asList(dube, spruts));
        List<Location> locations = Arrays.asList(lumberMill, cityMarket, tavern, forest, castle);

        int employeesCount = 15;
        List<Character> employees = new ArrayList<>();
        for (int i = 1; i <= employeesCount; i++) {
            String name = "Сотрудник " + i;
            String description = "Сотрудник лесопильного завода номер " + i;

            Character employee;
            if (Math.random() < .2)
                employee = new Character(name, description, tavern);
            else
                employee = new Character(name, description, lumberMill);

            employees.add(employee);
        }

        dube.setEmployees(employees);
        characters.addAll(employees);

        Game game = new Game(dube, characters, locations);
        game.start();
    }
}
