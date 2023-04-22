package exmp.locations;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    private String name;
    private List<Character> characters;
    private List<Location> availableLocations = new ArrayList<>();

    public Location(String name, List<Character> characters){
        setName(name);
        setCharacters(characters);
    }
}
