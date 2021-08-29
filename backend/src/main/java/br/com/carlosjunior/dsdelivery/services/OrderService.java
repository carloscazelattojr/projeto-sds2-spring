package br.com.carlosjunior.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlosjunior.dsdelivery.dto.OrderDTO;
import br.com.carlosjunior.dsdelivery.dto.ProductDTO;
import br.com.carlosjunior.dsdelivery.entities.Order;
import br.com.carlosjunior.dsdelivery.entities.OrderStatus;
import br.com.carlosjunior.dsdelivery.entities.Product;
import br.com.carlosjunior.dsdelivery.repositories.OrderRepository;
import br.com.carlosjunior.dsdelivery.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		return orderRepository.findOrdersWithItems().stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	
	@Transactional
	public OrderDTO insert( OrderDTO newOrder) {
		Order order = new Order(null, newOrder.getAddress(), newOrder.getLatitude(), newOrder.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : newOrder.getItemsOrder()) {
			Product prod = productRepository.getById(p.getId());
			order.getProducts().add(prod);
		}
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}	
	
}
