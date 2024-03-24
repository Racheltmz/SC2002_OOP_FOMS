public class OrderQueue {
    private Order[] orders;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public OrderQueue(int capacity){
        this.capacity = capacity;
        this.orders = new Order[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void addOrder(Order order){
        if (size == capacity){
            System.out.println("Order queue is full. Cannot add order.");
            return;
        }
        rear = (rear + 1) % capacity;
        orders[rear] = order;
        size++;
    }

    public Order removeOrder(){
        if (size == 0){
            System.out.println("Order queue is empty.");
            return null;
        }

        Order removedOrder = orders[front];
        orders[front] = null;
        front = (front + 1) % capacity;
        size--;
        return removedOrder;
        
    }
}
