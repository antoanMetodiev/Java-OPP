package BorderControl;

public class Robot implements Identifiable {

    // - id: String
    //- model: String
    //+ Robot(String, String)
    //+ getId(): String
    //+ getModel(): String

    private String model;
    private String id;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }
}
