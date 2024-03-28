package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

public class ShopImpl implements Shop {

    @Override
    public void make(Vehicle vehicle, Worker worker) {
        //  Работникът започва да произвежда превозното средство.
        //  Това е възможно само ако работникът има сила и инструмент, който не е повреден.

        // TODO:
        while (worker.canWork() && checkWorkerHaveTool(worker) && !vehicle.reached()) {
            worker.working();
            Tool tool = worker.getTools().stream().filter(e -> !e.isUnfit()).findFirst().orElse(null);
            tool.decreasesPower();
            vehicle.making();
        }

        //⦁ Продължавайте да работите, докато превозното средство не
        // бъде направено или работникът има сила (и инструменти за използване).
    }

    private boolean checkWorkerHaveTool(Worker worker) {
        Tool tool = worker.getTools().stream().filter(e -> !e.isUnfit()).findFirst().orElse(null);
        if (tool == null) {
            return false;
        }
        return true;
    }
}
