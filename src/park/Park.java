package park;

import plants.Plant;

import java.util.ArrayList;
import java.util.Iterator;

public class Park {

    private ArrayList<Plant> plants;
    private ArrayList<String> places = new ArrayList<>();
    private ArrayList<ParkObject> parkObjects = new ArrayList<>();

    private int quantOfPlants = 0;
    private double totalHeight = 0;

    public Park(ArrayList<Plant> plants) {
        this.plants = plants;
        fillPlace();
        fillParkObjects();
        plantPark();
    }

    private void fillPlace() {
        for (Plant plant : plants) {
            if (places.size() == 0) {
                places.add(plant.getPlace());
            } else {
                boolean flag = false;
                for (String place : places) {
                    if (plant.getPlace().equals(place)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    places.add(plant.getPlace());
            }
        }
    }

    private void fillParkObjects() {
        for (String place : places) {
            parkObjects.add(new ParkObject(place));
        }
    }

    private void plantPark() {

        for (ParkObject parkObject : parkObjects) {
            for (Plant plant : plants) {
                plantEqualsParkObject(parkObject,plant);
            }
        }
    }

    private void plantEqualsParkObject(ParkObject parkObject, Plant plant){
        if (parkObject.getPlace().equals(plant.getPlace())) {
            parkObject.plants.add(plant);
            quantOfPlants++;
            totalHeight = totalHeight + Double.parseDouble(plant.getHeight());
        }
    }

    public ArrayList<ParkObject> getParkObjects() {
        return parkObjects;
    }

}
