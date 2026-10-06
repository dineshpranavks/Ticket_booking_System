package model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int theatreId;
    private String name;
    private String location;
    private String city;
    private List<Screen> screens;

    public Theatre(int theatreId, String name, String location, String city) {
        this.theatreId = theatreId;
        this.name = name;
        this.location = location;
        this.city = city;
        this.screens = new ArrayList<>();
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public Screen getScreenById(int screenId) {
        for (Screen screen : screens) {
            if (screen.getScreenId() == screenId) {
                return screen;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void displayTheatre() {
        System.out.println("+--------------------------------------------------+");
        System.out.printf("| Theatre ID  : %-34d |\n", theatreId);
        System.out.printf("| Name        : %-34s |\n", (name.length() > 34 ? name.substring(0, 31) + "..." : name));
        System.out.printf("| Location    : %-34s |\n", location);
        System.out.printf("| City        : %-34s |\n", city);
        System.out.printf("| Screens     : %-34d |\n", screens.size());
        System.out.println("+--------------------------------------------------+");
    }
}