package Order;

import java.util.ArrayList;

public class OrderQueue {
    private ArrayList<Order> orders;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public OrderQueue(int capacity){
        this.capacity = capacity;
        this.orders = new ArrayList<Order>();
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order){
        if (size == capacity){
            System.out.println("Order queue is full. Cannot add order.");
            return;
        }
        rear = (rear + 1) % capacity;
        this.orders.set(rear, order);
        size++;
    }

    public Order removeOrder(){
        if (size == 0){
            System.out.println("Order queue is empty.");
            return null;
        }

        Order removedOrder = this.orders.get(front);
        this.orders.set(front, null);
        front = (front + 1) % capacity;
        size--;
        return removedOrder;
        
    }
}
