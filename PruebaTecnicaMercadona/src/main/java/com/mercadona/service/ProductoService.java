package com.mercadona.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
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
  
  public Producto creaProducto(Integer codigo, String nombre) throws DuplicatedCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException {
	  Producto producto = new Producto(codigo,nombre);
	  Producto productoExistente =  productoRepository.getProducto(producto.getCodigo());
	  if(productoExistente==null) {
		  return productoRepository.save(producto);
	  }else {
		  throw new DuplicatedCodeException();
	  }
	 
  }
  
  public Producto actualizarProducto(Integer codigo, String nombre) throws IncorrectCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException {
	  Producto producto = new Producto(codigo,nombre);
	  Producto productoExistente =  productoRepository.getProducto(producto.getCodigo());
	  if(productoExistente==null) {
		  throw new IncorrectCodeException();
	  }else {
		  productoExistente.setNombre(producto.getNombre());
		  return productoRepository.save(productoExistente);  
	  }

	 
  }
  
}
