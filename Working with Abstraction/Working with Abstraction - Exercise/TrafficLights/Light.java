package TrafficLights;

public enum Light {
    RED("GREEN"),
    GREEN("YELLOW"),
    YELLOW("RED");

    private String lightValue;

    Light(String value) {
        this.lightValue = value;
    }

    public String getLightValue() {
        return lightValue;
    }
}
