package order;

import java.util.Timer;
import java.util.TimerTask;

public class OrderTimerTask extends TimerTask {
    private final Timer timer;
    private Order order;

    public OrderTimerTask(Timer timer, Order order){
        this.timer = timer;
        this.order = order;
    }

    @Override
    public void run(){
        assert this.order.getStatus() == OrderStatus.READY;
        this.order.setStatus(OrderStatus.CANCELLED);
        System.out.println(this.order.getOrderID() + "Order " + this.order.getStatus());
        this.timer.cancel();
    }
    
}
