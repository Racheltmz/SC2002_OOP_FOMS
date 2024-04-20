package order;

import io.OrderXlsxHelper;

import java.util.Timer;
import java.util.TimerTask;

public class OrderTimerTask extends TimerTask {
    private final Timer timer;
    private Order order;

    public OrderTimerTask(Timer timer, Order order) {
        this.timer = timer;
        this.order = order;
    }

    @Override
    public void run() {
        OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
        assert this.order.getStatus() == OrderStatus.READY;
        this.order.setStatus(OrderStatus.CANCELLED);
        orderXlsxHelper.updateXlsx(this.order);
        this.timer.cancel();
    }
}

