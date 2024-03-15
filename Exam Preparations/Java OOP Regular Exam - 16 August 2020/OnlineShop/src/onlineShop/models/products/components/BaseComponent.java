package onlineShop.models.products.components;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {
    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        // "Overall Performance: {overall performance}. Price: {price} -
        // {product type}: {manufacturer} {model} (Id: {id}) Generation: {generation}"
        return String.format(OutputMessages.PRODUCT_TO_STRING  + OutputMessages.COMPONENT_TO_STRING,
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(), this.getManufacturer(),
                this.getModel(), this.getId(), this.generation);
    }
}
