package Ferrari;

public class Ferrari implements Car {
    // - driverName: String
    //- model: String
    //+ Ferrari (String)
    //+ brakes() : String
    //+ gas() : String
    //+ toString(): String

    private String driverName;
    private static final String model = "488-Spider";

    public Ferrari(String driverName) {
        this.driverName = driverName;
    }

//    private void setDriverName(String driverName) {
//        for (int i = 0; i < driverName.length(); i++) {
//            char symbol = driverName.charAt(i);
//            if ((symbol >= 65 && symbol <= 90) || (symbol >= 97 && symbol <= 122)) {
//                this.driverName = driverName;
//            } else {
//                throw new IllegalArgumentException();
//            }
//        }
//    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "brum-brum-brum-brrrrr";
    }

    @Override
    public String toString() {
        // "{model}/{brakes}/{gas}/{driver's name}"
        return String.format("%s/%s/%s/%s", this.model, this.brakes(), this.gas(), this.driverName);
    }
}
