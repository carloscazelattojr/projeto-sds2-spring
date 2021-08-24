package br.com.carlosjunior.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlosjunior.dsdelivery.dto.ProductDTO;
import br.com.carlosjunior.dsdelivery.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return repository.findAllByOrderByNameAsc().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

}
