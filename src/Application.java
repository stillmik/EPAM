import park.Park;

class Application {

    Application(){
        Park park = new Park(XMLInputOutput.getInfFromXML());
        XMLInputOutput.getPlantedParkXML(
                park.getParkObjects(),park.getQuantOfPlants(),park.getTotalHeight());
    }
}
