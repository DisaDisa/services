package com.order.repository;

import com.order.OrderItem;
import com.order.OrderItemKey;
import com.order.types.OrderItemId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {
}
