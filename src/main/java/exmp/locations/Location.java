package exmp.locations;

import java.util.ArrayList;
import java.util.List;

import exmp.entities.BaseEntity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String name;
    private double latitude;
    private double longitude;
    private List<BaseEntity> entities = new ArrayList<>();
    private List<Location> connectedLocations = new ArrayList<>();

    public Location(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<Location> getAvailableLocations() {
        return connectedLocations;
    }

    public void setAvailableLocations(List<Location> locations){
        this.connectedLocations = locations;
    }
}
