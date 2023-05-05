package exmp.entities;

import exmp.locations.Location;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Manufacturer extends BaseCharacter {
    private List<BaseCharacter> employees;
    private Inventory inventory;

    public Manufacturer(String name, String description, Location location, List<BaseCharacter> employees) {
        this.name = name;
        this.description = description;
        this.setLocation(location);
        this.employees = employees;
    }

    public Manufacturer(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.setLocation(location);
    }

    public List<BaseCharacter> getEmployees() {
        return employees;
    }

    public void setEmployees(List<BaseCharacter> employees) {
        this.employees = employees;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return this.inventory;
    }
}