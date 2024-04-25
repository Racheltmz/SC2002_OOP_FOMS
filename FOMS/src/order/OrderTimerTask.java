package order;

import io.OrderXlsxHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A task to be run by a Timer when an Order Timer expires.
 */
public class OrderTimerTask extends TimerTask {
    private final Timer timer;
    private Order order;

    /**
     * Constructs an OrderTimerTask with the given timer object and Order.
     *
     * @param timer The timer associated with this task.
     * @param order The Order to be cancelled when the timer expires.
     */
    public OrderTimerTask(Timer timer, Order order) {
        this.timer = timer;
        this.order = order;
    }

    /**
     * The action to be performed by this timer task.
     * Updates the Order's OrderStatus to CANCELLED when the timer expires and updates the Order record in the XLSX file.
     */
    @Override
    public void run() {
        OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
        assert this.order.getStatus() == OrderStatus.READY;
        this.order.setStatus(OrderStatus.CANCELLED);
        orderXlsxHelper.updateXlsx(this.order);
        this.timer.cancel();
    }
}

