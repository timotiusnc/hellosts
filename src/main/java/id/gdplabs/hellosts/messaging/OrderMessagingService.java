package id.gdplabs.hellosts.messaging;

import id.gdplabs.hellosts.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}
