package com.mercadona.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.model.Producto;
import com.mercadona.repository.ProductoRepository;

@Service
public class ProductoService {
	
	  private final ProductoRepository productoRepository;
	  
	  public ProductoService(ProductoRepository productoRepository) {
	    this.productoRepository = productoRepository;
	  }
	  
  @Cacheable
  public Producto getProducto(Integer codigo) {
	  return productoRepository.getProducto(codigo);
	  }
  
  
}
