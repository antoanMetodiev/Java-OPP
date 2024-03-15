package robotService.entities.services;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseService implements Service {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.robots.size() >= this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        this.robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return this.supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s %s", this.getName(), this.getClass().getSimpleName()));
        sb.append("Robots:");
        if (this.robots.isEmpty()) {
            sb.append(" none").append(System.lineSeparator());
        } else {
            this.robots.forEach(e -> sb.append(" ").append(e.getName()));
            sb.append(System.lineSeparator());
        }

        sb.append("Supplements: ").append(this.supplements.size());
        sb.append("Hardness: ").append(this.sumHardness());
        return sb.toString();
    }
}
