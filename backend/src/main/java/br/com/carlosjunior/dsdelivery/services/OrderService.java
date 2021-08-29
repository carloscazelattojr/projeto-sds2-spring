package br.com.carlosjunior.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlosjunior.dsdelivery.dto.OrderDTO;
import br.com.carlosjunior.dsdelivery.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		return repository.findOrdersWithItems().stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

}
