package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

public class ShopImpl implements Shop {

    @Override
    public void make(Vehicle vehicle, Worker worker) {
        while (worker.canWork() && checkWorkerHaveTool(worker) && !vehicle.reached()) {
            worker.working();
            Tool tool = worker.getTools().stream().filter(e -> !e.isUnfit()).findFirst().orElse(null);
            tool.decreasesPower();
            vehicle.making();
        }
    }

    private boolean checkWorkerHaveTool(Worker worker) {
        Tool tool = worker.getTools().stream().filter(e -> !e.isUnfit()).findFirst().orElse(null);
        if (tool == null) {
            return false;
        }
        return true;
    }
}
