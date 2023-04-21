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
public class Manufacturer extends Character {
    private List<Character> employees;

    public Manufacturer(String name, String description, Location location, List<Character> employees) {
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

    public List<Character> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Character> employees) {
        this.employees = employees;
    }
}