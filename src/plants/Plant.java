package plants;

public class Plant {

    private String name;
    private String place;
    private String group;
    private String height;

    public Plant(String name, String place, String group, String height) {
        this.name = name;
        this.place = place;
        this.group = group;
        this.height = height;
    }

    public Plant(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
