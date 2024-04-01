import java.util.ArrayList;
import java.util.List;

public class OrderQueue {
    private List<Order> orders;

    public OrderQueue(int capacity){
        this.orders = new ArrayList<>();
        
    }

    public String getStatusByOrderID(String orderID){
        for (Order order : orders){
            if (order.getOrderId().equals(orderID)){
                return order.getStatus();
            }
        }
        return "Order not found";
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(String orderID){
        Order orderToRemove = null;
        for (Order order : orders){
            if (order.getOrderId().equals(orderID)){
                orderToRemove = order;
                break;
            }
        }
        if (orderToRemove != null){
            orders.remove(orderToRemove);
            System.out.println("Order with ID " + orderID + " removed successfully.");
        }
        else{
            System.out.println("Order with ID " + orderID + " not found.");
        }
    }
}
