package com.mercadona.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Producto;
import com.mercadona.repository.ProductoRepository;

@Service
public class ProductoService {
	
	  private final ProductoRepository productoRepository;
	  
	  public ProductoService(ProductoRepository productoRepository) {
	    this.productoRepository = productoRepository;
	  }
	  
  @Cacheable
  public Producto getProducto(Integer codigo) throws IncorrectCodeException, IncorrectFormatCodeException {
	  if(((int) Math.log10(codigo) + 1)==5) {
	  Producto producto =  productoRepository.getProducto(codigo);
	  if(producto==null) {
		  throw new IncorrectCodeException();
	  }else {
		  return producto;
	  }
	  }else {
		  throw new IncorrectFormatCodeException();
	  }
	  }
  
  
}
