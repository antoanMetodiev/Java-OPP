package onlineShop.models.products.peripherals;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {
    private String connectionType;

    public BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        return String.format(OutputMessages.PRODUCT_TO_STRING + OutputMessages.PERIPHERAL_TO_STRING,
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(), this.getManufacturer(),
                this.getModel(), this.getId(), this.connectionType);
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }
}
