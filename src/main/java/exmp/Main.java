package exmp;

import exmp.entities.Manufacturer;
import exmp.entities.BaseCharacter;
import exmp.entities.SpecialCharacter;
import exmp.locations.Location;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Location lumberMill = new Location("Лесопилка", new ArrayList<>());
        Location tavern = new Location("Таверна", new ArrayList<>());
        Location forest = new Location("Лес", new ArrayList<>());
        Location moon = new Location("Луна", new ArrayList<>());
        Location city = new Location("Цветочный город", new ArrayList<>());

        lumberMill.setAvailableLocations(List.of(city, tavern));
        tavern.setAvailableLocations(List.of(forest, lumberMill, city));
        forest.setAvailableLocations(List.of(lumberMill, city));
        moon.setAvailableLocations(List.of(city));
        city.setAvailableLocations(List.of(moon, forest, tavern, lumberMill));

        Manufacturer dube = new Manufacturer("Дубе", "Владелец лесопильных заводов", lumberMill);

        List<String> sprutsDialogLines = List.of("Работа должна быть выполнена!");
        SpecialCharacter spruts = new SpecialCharacter("Спрутс", "Бывший миллиардер и хозяин мануфактуры, выпускающей сахар, чай и ткани.", lumberMill, sprutsDialogLines);

        List<String> miguDialogLines = List.of("Привет!", "Поговори со мной еще!", "Я вам больше не помешаю.");
        SpecialCharacter migu = new SpecialCharacter("Мига", "Житель Луны, бывший приятель Жулио.", moon, miguDialogLines);

        List<String> julioDialogLines = List.of("Привет!", "Я лишь продаю свои товары и никого не трогаю.");
        SpecialCharacter julio = new SpecialCharacter("Жулио", "Житель Луны, бывший приятель Миги и бывший владелец магазина разнокалиберных товаров. Один из организаторов Общества Гигантских Растений.", moon, julioDialogLines);

        List<String> noNameDialogLines = List.of("Прости, но кажется я об этом ничего не знаю...");
        SpecialCharacter noName = new SpecialCharacter("Незнайка", "Веселый шалун и фантазер, коротышка. Путешественник, житель Цветочного города.", city, noNameDialogLines);

        List<String> goatDialogLines = List.of("Я не знаю, что может говорить Козлик...");
        SpecialCharacter goat = new SpecialCharacter("Козлик", "Тот еще козел.", forest, goatDialogLines);

        List<SpecialCharacter> specialCharacters = new ArrayList<>(List.of(migu, julio, noName, goat));
        List<BaseCharacter> baseCharacters = new ArrayList<>(List.of(dube, spruts));
        List<Location> locations = List.of(lumberMill, tavern, forest, city, moon);

        int employeesCount = 15;
        List<BaseCharacter> employees = new ArrayList<>();
        for (int i = 1; i <= employeesCount; i++) {
            String name = "Сотрудник " + i;
            String description = "Сотрудник лесопильного завода номер " + i;

            BaseCharacter employee;
            if (Math.random() < .2)
                employee = new BaseCharacter(name, description, tavern);
            else
                employee = new BaseCharacter(name, description, lumberMill);

            employees.add(employee);
        }

        dube.setEmployees(employees);
        baseCharacters.addAll(employees);
        baseCharacters.addAll(specialCharacters);

        Game game = new Game(dube, baseCharacters, locations);
        game.start();
    }
}
