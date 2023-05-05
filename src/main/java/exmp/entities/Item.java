package exmp.entities;

public class Item {
    String name;
    String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    };
    public String getDescription(){
        return this.description;
    };
}
