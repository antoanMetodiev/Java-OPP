package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;
import java.util.Collection;

public class WorkingImpl implements Working{

    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {
        for (Waiter waiter : waiters) {
            while (waiter.canWork() && !client.getClientOrders().isEmpty()) {
                waiter.work();
                String order = client.getClientOrders().iterator().next();
                waiter.takenOrders().getOrdersList().add(order); 
                client.getClientOrders().remove(order);
            }
        }
    }
}
