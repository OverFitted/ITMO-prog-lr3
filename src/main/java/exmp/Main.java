package exmp;

import exmp.entities.*;
import exmp.locations.Location;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Location sawmill = new Location("Лесопилка", 51.5074, 0.1278);
        Location tavern = new Location("Таверна", 48.8566, 2.3522);
        Location forest = new Location("Лес", 40.7128, 74.0060);
        Location moon = new Location("Луна", 28.538336, -81.379234);
        Location flowerCity = new Location("Цветочный город", 37.7749, 122.4194);

        sawmill.setAvailableLocations(List.of(flowerCity, tavern));
        tavern.setAvailableLocations(List.of(forest, sawmill, flowerCity));
        forest.setAvailableLocations(List.of(sawmill, flowerCity));
        moon.setAvailableLocations(List.of(flowerCity));
        flowerCity.setAvailableLocations(List.of(moon, forest, tavern, sawmill));

        Manufacturer dube = new Manufacturer("Дубе", "Владелец лесопильных заводов", sawmill);

        List<String> miguDialogLines = List.of("Привет!", "Поговори со мной еще!", "Я вам больше не помешаю.");
        SpecialCharacter migu = new SpecialCharacter("Мига", "Житель Луны, бывший приятель Жулио.", moon, miguDialogLines);

        List<String> julioDialogLines = List.of("Привет!", "Я лишь продаю свои товары и никого не трогаю.");
        SpecialCharacter julio = new SpecialCharacter("Жулио", "Житель Луны, бывший приятель Миги и бывший владелец магазина разнокалиберных товаров. Один из организаторов Общества Гигантских Растений.", moon, julioDialogLines);

        List<String> noNameDialogLines = List.of("Прости, но кажется я об этом ничего не знаю...");
        SpecialCharacter noName = new SpecialCharacter("Незнайка", "Веселый шалун и фантазер, коротышка. Путешественник, житель Цветочного города.", flowerCity, noNameDialogLines);

        List<String> goatDialogLines = List.of("Что может говорить Козлик... ну, типа, это же буквально животное, они не говорят же, да?");
        SpecialCharacter goat = new SpecialCharacter("Козлик", "Тот еще козел.", forest, goatDialogLines);

        Council cheeseCouncil = new Council("Сырный бредлам", "Бредлам владельцев сыроваренных фабрик", new ArrayList<>());
        Council sugarCouncil = new Council("Сахарный бредлам", "Бредлам всех сахарозаводчиков", new ArrayList<>());
        Council coalCouncil = new Council("Угольный бредлам", "Бредлам владельцев угольных шахт", new ArrayList<>());
        Council bigCouncil = new Council("Большой бредлам", "Главный бредлам господина Спрутса", new ArrayList<>());

        RichPerson spruts = new RichPerson("Спрутс", "Председатель большого бредлама", flowerCity, bigCouncil);
        RichPerson richPerson2 = new RichPerson("Сырный богач", "Владелец сыроваренной фабрики", moon, cheeseCouncil);
        RichPerson richPerson3 = new RichPerson("Сахарный богач", "Владелец сахарозавода", moon, sugarCouncil);
        RichPerson richPerson4 = new RichPerson("Угольный богач", "Владелец угольной шахты", moon, coalCouncil);

        bigCouncil.getMembers().add(spruts);
        cheeseCouncil.getMembers().add(richPerson2);
        sugarCouncil.getMembers().add(richPerson3);
        coalCouncil.getMembers().add(richPerson4);

        flowerCity.getEntities().add(spruts);
        moon.getEntities().addAll(Arrays.asList(richPerson2, richPerson3, richPerson4));

        List<SpecialCharacter> specialCharacters = new ArrayList<>(List.of(migu, julio, noName, goat));
        List<BaseCharacter> baseCharacters = new ArrayList<>(List.of(dube, spruts));
        List<Location> locations = List.of(sawmill, tavern, forest, flowerCity, moon);

        int employeesCount = 15;
        List<BaseCharacter> employees = new ArrayList<>();
        for (int i = 1; i <= employeesCount; i++) {
            String name = "Сотрудник " + i;
            String description = "Сотрудник лесопильного завода номер " + i;

            BaseCharacter employee;
            if (Math.random() < .2)
                employee = new BaseCharacter(name, description, tavern);
            else
                employee = new BaseCharacter(name, description, sawmill);

            employees.add(employee);
        }

        dube.setEmployees(employees);
        baseCharacters.addAll(employees);
        baseCharacters.addAll(specialCharacters);

        Game game = new Game(dube, baseCharacters, locations);
        game.start();
    }
}
