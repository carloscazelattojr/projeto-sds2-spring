package br.com.carlosjunior.dsdelivery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.carlosjunior.dsdelivery.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM tb_order o inner join tb_order_product op on op.order_id=o.id inner join tb_product p on p.id=op.product_id WHERE o.status = 0 ORDER BY o.moment ASC", nativeQuery = true)
	List<Order> findOrdersWithItems();
	
}
