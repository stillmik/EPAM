package park;

import plants.Plant;

import java.util.ArrayList;

public class ParkObject {

    ParkObject(String place){
        this.place = place;
    }

    private String place;
    public ArrayList<Plant> plants = new ArrayList<>();
    public String getPlace(){
        return place;
    }

    void setPlace(String place){
        this.place = place;
    }
}
