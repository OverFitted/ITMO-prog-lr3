package exmp;

import exmp.entities.*;
import exmp.enums.ActionType;
import exmp.exceptions.GameLoadException;
import exmp.exceptions.InvalidActionException;
import exmp.exceptions.InvalidCharacterException;
import exmp.exceptions.InvalidLocationException;
import exmp.locations.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private final Manufacturer player;
    private final List<BaseCharacter> baseCharacters;
    private List<Location> locations;

    public Game(Manufacturer player, List<BaseCharacter> baseCharacters, List<Location> locations) {
        this.player = player;
        this.baseCharacters = baseCharacters;
        this.locations = locations;
    }

    public void start() {
        try {
            openingScene();
            gameLoop();
        } catch (GameLoadException e) {
            System.err.println(e.getMessage());
        }
    }

    private void openingScene() throws GameLoadException {
        try {
            RichPerson spruts = (RichPerson) findCharacterByName("Спрутс");
            RichPerson.Money sprutsMoney = spruts.new Money(127541);
            System.out.println("Кат-сцена: Диалог между Дубе и господином Спрутсом\n");
            dialog(spruts, player, "Мистер Дубе, есть дело... надо убрать с дороги Мигу и Жулио, а заодно и Незнайку с Козликом. Дело надо убить в зародыше!");
            dialog(spruts, player, "Готов заплатить за это Вам... скажем " + Math.round(sprutsMoney.getAmount() * .001));
            dialog(player, spruts, "Я могу предложить двух талантливых личностей, которые помогут нам в этом деле.");
            dialog(spruts, player, "Господин Дубе, вы, видимо, меня не поняли. Убить в зародыше - это не в буквальном смысле.");
        } catch (Exception e){
            throw new GameLoadException(e.getMessage());
        }
    }

    class ItemPrinter {
        void printItem(Item item) {
            System.out.println(item.getName() + ": " + item.getDescription());
        }
    }

    private void dialog(BaseCharacter speaker, BaseCharacter listener, String message) {
        System.out.println(speaker.getName() + " говорит " + listener.getName() + ": " + message.toLowerCase());
    }

    private void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nТекущая локация: " + player.getLocation().getName());

            ItemPrinter itemPrinter = new ItemPrinter();
            System.out.println("Предметы в инвентаре:");
            for (Item item : player.getInventory().getItems()) {
                itemPrinter.printItem(item);
            }

            System.out.println("Выберите действие:");
            for (ActionType action : ActionType.values()) {
                System.out.println((action.ordinal() + 1) + ". " + action.getDescription());
            }
            boolean containsSpecialCharacter = baseCharacters.stream()
                    .anyMatch(character -> character instanceof SpecialCharacter);

            if (!containsSpecialCharacter) {
                BaseCharacter spruts = findCharacterByName("Спрутс");
                dialog(spruts, player, "Я же говорил, надо аккуратнее как-то, а ты...");
                dialog(spruts, player, "...");
                dialog(spruts, player, "Проблема решена, мистер Дубе, спасибо за работу.");
                endGame(scanner);
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice < 1 || choice > ActionType.values().length) {
                    throw new InvalidActionException("Неверный выбор. Пожалуйста, выберите один из предложенных вариантов.");
                }
            } catch (InvalidActionException e) {
                System.err.println(e.getMessage());
                continue;
            }

            ActionType actionType = ActionType.values()[choice - 1];
            switch (actionType) {
                case MOVE -> {
                    List<Location> availableLocations = player.getLocation().getAvailableLocations();
                    System.out.println("Выберите локацию:");
                    for (int i = 0; i < availableLocations.size(); i++) {
                        System.out.println((i + 1) + ". " + availableLocations.get(i).getName());
                    }
                    int locationIndex = scanner.nextInt() - 1;
                    if (locationIndex < availableLocations.size()) {
                        player.setLocation(availableLocations.get(locationIndex));
                    } else {
                        throw new InvalidLocationException("Неверный выбор. Пожалуйста, выберите один из предложенных вариантов.");
                    }
                }
                case ATTACK -> {
                    System.out.println("Выберите персонажа, которого вы хотите атаковать:");

                    BaseCharacter otherCharacter = chooseYourCharacter(scanner);
                    if (otherCharacter != null && otherCharacter.getLocation().equals(player.getLocation())) {
                        dialog(otherCharacter, player, "Нееееееееееет...");
                        this.baseCharacters.remove(otherCharacter);
                        System.out.println(otherCharacter.getName() + " погибает.");
                    } else {
                        throw new InvalidCharacterException("Неверный выбор. Пожалуйста, выберите один из предложенных вариантов.");
                    }
                }
                case TALK -> {
                    System.out.println("Выберите персонажа, с которым хотите поговорить:");

                    BaseCharacter otherCharacter = chooseYourCharacter(scanner);
                    if (otherCharacter != null && otherCharacter.getLocation().equals(player.getLocation())) {
                        String dialogText = "Привет, " + player.getName() + "!";
                        if (otherCharacter instanceof SpecialCharacter)
                            dialogText = ((SpecialCharacter) otherCharacter).interact();

                        dialog(otherCharacter, player, dialogText);
                    } else {
                        throw new InvalidCharacterException("Неверный выбор. Пожалуйста, выберите один из предложенных вариантов.");
                    }
                }
                case QUIT -> {
                    endGame(scanner);
                }
            }
        }
    }

    private BaseCharacter chooseYourCharacter(Scanner scanner) {
        Object[] characters = findCharactersByLocation(player.getLocation());
        if (characters.length == 0)
            System.out.println("Нет персонажей в этой локации.");
        else {
            Arrays.stream(characters)
                    .map(character -> (BaseCharacter) character)
                    .forEach(baseCharacter -> {
                        if (!baseCharacter.getName().equals(player.getName())) {
                            System.out.println(baseCharacter.getName() + " - " + baseCharacter.getDescription());
                        }
                    });
        }

        String name = scanner.nextLine();
        return findCharacterByName(name);
    }

    private void endGame(Scanner scanner) {
        System.out.println("Спасибо за игру!");
        scanner.close();
        exit(0);
    }


    private BaseCharacter findCharacterByName(String name) {
        return baseCharacters.stream()
                .filter(baseCharacter -> baseCharacter.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private Object[] findCharactersByLocation(Location location) {
        return baseCharacters.stream()
                .filter(baseCharacter -> baseCharacter.getLocation().equals(location))
                .toArray();
    }
}
