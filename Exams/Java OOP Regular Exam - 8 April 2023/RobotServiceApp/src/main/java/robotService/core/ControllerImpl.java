package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Collection<Service> serviceCollection;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.serviceCollection = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service = null;
        if (type.equals("MainService")) {
            service = new MainService(name);
        } else if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }

        this.serviceCollection.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement = null;
        if (type.equals("PlasticArmor")) {
            supplement = new PlasticArmor();
        } else if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplementRepository.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, supplement.getClass().getSimpleName());
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Service service = findFirst(serviceName);
        Supplement suplement = this.supplementRepository.findFirst(supplementType);
        if (suplement == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }
        this.supplementRepository.removeSupplement(suplement);
        service.addSupplement(suplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Service service = findFirst(serviceName);

        Robot robot = null;
        if (robotType.equals("MaleRobot")) {
            robot = new MaleRobot(robotName, robotKind, price);
        } else if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName, robotKind, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }

        String message = "";
        if (robotType.equals("MaleRobot") && service.getClass().getSimpleName().equals("MainService")) {
            service.addRobot(robot);
            message = String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        } else if (robotType.equals("FemaleRobot") && service.getClass().getSimpleName().equals("SecondaryService")) {
            service.addRobot(robot);
            message = String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        } else {
            message = ConstantMessages.UNSUITABLE_SERVICE;
        }
        return message;
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = findFirst(serviceName);

        int count = 0;
        if (service != null) {
            count = service.getRobots().size();
            service.getRobots().forEach(Robot::eating);
        }
        return String.format(ConstantMessages.FEEDING_ROBOT, count);
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = findFirst(serviceName);
        double price = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
        price += service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();
        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, price);
    }

    private Service findFirst(String name) {
        return this.serviceCollection.stream().filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.serviceCollection.forEach(e -> sb.append(e.getStatistics()));
        return sb.toString().trim();
    }
}
