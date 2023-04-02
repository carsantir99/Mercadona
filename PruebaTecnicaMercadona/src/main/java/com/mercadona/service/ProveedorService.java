package com.mercadona.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Proveedor;
import com.mercadona.model.Proveedor;
import com.mercadona.repository.ProveedorRepository;


@Service
public class ProveedorService {
	  private final ProveedorRepository proveedorRepository;
	  
	  @Autowired
	  public ProveedorService(ProveedorRepository proveedorRepository) {
	    this.proveedorRepository = proveedorRepository;
	  }
	  
	  @Cacheable
	  public Proveedor getProveedor(Integer codigo) throws IncorrectCodeException, IncorrectFormatCodeException {
		  if(((int) Math.log10(codigo) + 1)==7) {
			  Proveedor proveedor =  proveedorRepository.getProveedor(codigo);
		  if(proveedor==null) {
			  throw new IncorrectCodeException();
		  }else {
			  return proveedor;
		  }
		  }else {
			  throw new IncorrectFormatCodeException();
		  }
		  }
	  
	  public Proveedor creaProveedor(Integer codigo, String nombre) throws DuplicatedCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException {
		  Proveedor proveedor = new Proveedor(codigo,nombre);
		  Proveedor proveedorExistente =  proveedorRepository.getProveedor(proveedor.getCodigo());
		  if(proveedorExistente==null) {
			  return proveedorRepository.save(proveedor);
		  }else {
			  throw new DuplicatedCodeException();
		  }
		 
	  }
	  
	 
}
