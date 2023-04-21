package exmp;

import exmp.entities.SpecialCharacter;
import exmp.enums.ActionType;
import exmp.locations.Location;
import exmp.entities.Character;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Character player;
    private List<Character> characters;
    private List<Location> locations;

    public Game(Character player, List<Character> characters, List<Location> locations) {
        this.player = player;
        this.characters = characters;
        this.locations = locations;
    }

    public void start() {
        openingScene();
        gameLoop();
    }

    private void openingScene() {
        Character spruts = findCharacterByName("Спрутс");
        System.out.println("Кат-сцена: Диалог между Дубе и господином Спрутсом\n");
        dialog(player, spruts, "Я могу предложить двух талантливых личностей, которые помогут нам в этом деле.");
        dialog(spruts, player, "Господин Дубе, вы, видимо, меня не поняли. Убить в зародыше - это не в буквальном смысле.");
    }

    private void dialog(Character speaker, Character listener, String message) {
        System.out.println(speaker.getName() + " говорит " + listener.getName() + ": " + message);
    }

    private void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nТекущая локация: " + player.getLocation().getName());
            System.out.println("Выберите действие:");
            for (ActionType action : ActionType.values()) {
                System.out.println((action.ordinal() + 1) + ". " + action.getDescription());
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > ActionType.values().length) {
                System.out.println("Неверный выбор. Пожалуйста, выберите один из предложенных вариантов.");
                continue;
            }

            ActionType actionType = ActionType.values()[choice - 1];
            switch (actionType) {
                case MOVE -> {
                    System.out.println("Выберите локацию:");
                    for (int i = 0; i < locations.size(); i++) {
                        System.out.println((i + 1) + ". " + locations.get(i).getName());
                    }
                    int locationIndex = scanner.nextInt() - 1;
                    player.setLocation(locations.get(locationIndex));
                }
                case TALK -> {
                    System.out.println("Выберите персонажа, с которым хотите поговорить:");

                    Object[] characters = findCharactersByLocation(player.getLocation());
                    if (characters.length == 0)
                        System.out.println("Нет персонажей в этой локации.");
                    else
                        Arrays.stream(characters)
                                .map(character -> (Character) character)
                                .forEach(character -> System.out.println(character.getName() + " - " + character.getDescription()));

                    String name = scanner.nextLine();
                    Character character = findCharacterByName(name);
                    if (character != null && character.getLocation().equals(player.getLocation())) {
                        if (character instanceof SpecialCharacter) {
                            ((SpecialCharacter) character).interact(player);
                        } else {
                            System.out.println(character.getName() + ": Привет, " + player.getName() + "!");
                        }
                    } else {
                        System.out.println("Персонаж не найден или находится в другой локации.");
                    }
                }
                case QUIT -> {
                    System.out.println("Спасибо за игру!");
                    scanner.close();
                    return;
                }
            }
        }
    }


    private Character findCharacterByName(String name) {
        return characters.stream()
                .filter(character -> character.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private Object[] findCharactersByLocation(Location location) {
        return characters.stream()
                .filter(character -> character.getLocation().equals(location))
                .toArray();
    }
}
