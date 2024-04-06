package restaurant.core;

import restaurant.common.ConstantMessages;
import restaurant.common.ExceptionMessages;
import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.WaiterRepository;

import java.util.Arrays;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private WaiterRepository waiterRepository;
    private ClientRepository clientRepository;
    private int servedClients = 0;

    public ControllerImpl() {
        this.waiterRepository = new WaiterRepository();
        this.clientRepository = new ClientRepository();
    }

    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter = null;
        if (type.equals("FullTimeWaiter")) {
            waiter = new FullTimeWaiter(waiterName);
        } else if (type.equals("HalfTimeWaiter")) {
            waiter = new HalfTimeWaiter(waiterName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_INVALID_TYPE);
        }

        this.waiterRepository.add(waiter);
        return String.format(ConstantMessages.WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);
        Arrays.stream(orders).forEach(e -> client.getClientOrders().add(e));
        this.clientRepository.add(client);
        return String.format(ConstantMessages.CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        // TODO: този boolean може и да гръмне
        boolean isRemoved = this.waiterRepository.remove(this.waiterRepository.byName(waiterName));
        if (!isRemoved) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.WAITER_DOES_NOT_EXIST, waiterName));
        }
        return String.format(ConstantMessages.WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        Client client = this.clientRepository.byName(clientName);
        if (client == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIENT_DOES_NOT_EXIST, clientName));
        }
        this.clientRepository.remove(client);
        return String.format(ConstantMessages.CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        Client client = this.clientRepository.byName(clientName); // на него ще му приемаме поръчките

        Collection<Waiter> allWaiters = this.waiterRepository.getCollection();
        if (allWaiters.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_WAITERS);
        }

        Working working = new WorkingImpl();
        working.takingOrders(client, allWaiters);
        servedClients++;
        return String.format(ConstantMessages.ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder(String.format(ConstantMessages.FINAL_CLIENTS_COUNT, this.servedClients))
                .append(System.lineSeparator());

        sb.append(ConstantMessages.FINAL_WAITERS_STATISTICS).append(System.lineSeparator());
        for (Waiter waiter : this.waiterRepository.getCollection()) {

            sb.append(String.format(ConstantMessages.FINAL_WAITER_NAME, waiter.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_WAITER_EFFICIENCY, waiter.getEfficiency()))
                    .append(System.lineSeparator());


            String message = "";
            if (waiter.takenOrders().getOrdersList().isEmpty()) {
                message = "None";
            } else {
                message = String.join(ConstantMessages.FINAL_WAITER_ORDERS_DELIMITER, waiter.takenOrders().getOrdersList());
            }

            sb.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS, message)).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
