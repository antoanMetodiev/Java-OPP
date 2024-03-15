package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()) {
            return super.getOverallPerformance();
        }

        double averageOvPerformance = this.components.stream().mapToDouble(Component::getOverallPerformance)
                .average().orElse(0);

        return super.getOverallPerformance() + averageOvPerformance;
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public double getPrice() {
        double componentsTotalSum = this.components.stream().mapToDouble(Component::getPrice).sum();
        double peripheralTotalSum = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        return super.getPrice() + componentsTotalSum + peripheralTotalSum;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        for (Component currentComponent : components) {

            String componentName = currentComponent.getClass().getSimpleName();
            if (componentName.equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT
                        , componentName, this.getClass().getSimpleName(), currentComponent.getId()));
            }
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        if (this.components.isEmpty()) {
            // "Component {component type} does not exist in {computer type} with Id {id}."
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT
                    , componentType, this.getClass().getSimpleName(), this.getId()));
        }

        for (Component currentComponent : components) {

            String componentName = currentComponent.getClass().getSimpleName();
            if (componentName.equals(componentType)) {
                this.components.remove(currentComponent);
                return currentComponent;
            }
        }

        throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT
                , componentType, this.getClass().getSimpleName(), this.getId()));
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral currentPeripheral : peripherals) {

            String peripheralName = currentPeripheral.getClass().getSimpleName();
            if (peripheralName.equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL
                        , peripheralName, this.getClass().getSimpleName(), currentPeripheral.getId()));
            }
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (this.components.isEmpty()) {
            // "Component {component type} does not exist in {computer type} with Id {id}."
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL
                    , peripheralType, this.getClass().getSimpleName(), this.getId()));
        }

        for (Peripheral currentPeripheral : peripherals) {

            String componentName = currentPeripheral.getClass().getSimpleName();
            if (componentName.equals(peripheralType)) {
                this.peripherals.remove(currentPeripheral);
                return currentPeripheral;
            }
        }

        throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL
                , peripheralType, this.getClass().getSimpleName(), this.getId()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(OutputMessages.PRODUCT_TO_STRING,
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(), this.getManufacturer(),
                this.getModel(), this.getId()));

        // Components!
        sb.append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, this.components.size()));
        this.components.forEach(e -> sb.append(e.getClass().getSimpleName()));

        // Peripherals!
        double averageOvPerformance = this.peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).average()
                .orElse(0);
        sb.append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(), averageOvPerformance));
        this.peripherals.forEach(e -> sb.append(e.getClass().getSimpleName()));

        return sb.toString();
    }
}
