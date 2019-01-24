package id.gdplabs.hellosts.repository;

import id.gdplabs.hellosts.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
}
