package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private VehicleRepository vehicles = new VehicleRepository();
    private WorkerRepository workers = new WorkerRepository();

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker = null;
        if (type.equals("FirstShift")) {
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }

        this.workers.add(worker);
        return String.format(String.format(ConstantMessages.ADDED_WORKER, type, workerName));
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        this.vehicles.add(new VehicleImpl(vehicleName, strengthRequired));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = findWorkerWithGiveName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        worker.addTool(new ToolImpl(power));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    private Worker findWorkerWithGiveName(String workerName) {
        return this.workers.getWorkers().stream()
                .filter(e -> e.getName().equals(workerName)).findFirst().orElse(null);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        Shop shop = new ShopImpl();
        Vehicle vehicle = this.vehicles.findByName(vehicleName);

        // 1во - Взимаме си само подходящите worker-й за операцията (тези които са със strength над 70)
        List<Worker> goodWorkers = returnWorkersWithStrengthAbove70();
        if (goodWorkers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }

        // 2ро - Започват да работят:
        for (Worker worker : goodWorkers) {
            shop.make(vehicle, worker);
            if (vehicle.reached()) {
                break;
            }
        }

        // 3то - Намираме колко инструменти са станали вече неподходящи (изхабени със сила равна на 0)
        int unfitToolsCount = returnUnfitToolsCount(goodWorkers);

        // 4то - Проверяваме дали превозното средство е ЗАВЪРШЕНО!
        String message = "";
        if (vehicle.reached()) {
            message = "done";
        } else {
            message = "not done";
        }

        return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, message)
                + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, unfitToolsCount);
    }

    private int returnUnfitToolsCount(List<Worker> goodWorkers) {
        int count = 0;
        for (Worker worker : goodWorkers) {
            for (Tool tool : worker.getTools()) {
                if (tool.isUnfit()) {
                    count++;
                }
            }
        }
        return count;
    }

    private List<Worker> returnWorkersWithStrengthAbove70() {
        return this.workers.getWorkers().stream().filter(e -> e.getStrength() > 70).collect(Collectors.toList());
    }

    @Override
    public String statistics() {
        int madedVehiclesCount = this.vehicles.getWorkers().stream()
                .filter(Vehicle::reached).collect(Collectors.toList()).size();;

        StringBuilder result = new StringBuilder(String.format("%d vehicles are ready!", madedVehiclesCount))
                .append(System.lineSeparator());

        result.append("Info for workers:").append(System.lineSeparator());
        for (Worker worker : this.workers.getWorkers()) {
            result.append(String.format("Name: %s, Strength: %d"
                    , worker.getName(), worker.getStrength())).append(System.lineSeparator());

            int validTools = worker.getTools().stream().filter(e -> !e.isUnfit()).collect(Collectors.toList()).size();
            result.append(String.format("Tools: %d fit left", validTools)).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}