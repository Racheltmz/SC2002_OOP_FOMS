package Order;

import java.util.ArrayList;

public class OrderQueue {
    public ArrayList<Order> orders;
    private int size;
    private int capacity;

    public OrderQueue(int capacity){
        this.capacity = capacity;
        this.orders = new ArrayList<>();
        this.size = 0;
    }

    public void addOrder(Order order){
        if (this.size == this.capacity){
            System.out.println("Order queue is full. Cannot add order.");
            return;
        }
        orders.add(order);
        this.size++;
    }

    public Order removeOrder(){
        if (size == 0){
            System.out.println("Order queue is empty.");
            return null;
        }
        Order removedOrder = orders.get(0);
        orders.remove(0);
        this.size--;
        return removedOrder;
        
    }

    public void displayOrders(String branch)
    {
        if (this.size != 0) {
            System.out.println("Orders in the queue:");
            for (Order order : orders) {
                if (order.getStatus() == "NEW" && order.getBranch() == branch)
                {
                    System.out.println(order);
                }
            }
        } else {
            System.out.println("Queue is empty. No orders to display.");
        }
    }
}