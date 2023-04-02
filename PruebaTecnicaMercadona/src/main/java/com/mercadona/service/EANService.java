package com.mercadona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectEANCode;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.DestinoEnum;
import com.mercadona.model.EAN;
import com.mercadona.model.Producto;
import com.mercadona.model.Proveedor;
import com.mercadona.repository.ProductoRepository;
import com.mercadona.repository.ProveedorRepository;

@Service
public class EANService {
	
	 private final ProductoService productoService;
	 private final ProveedorService proveedorService;
	 private final DestinoService destinoService;
	  
	  @Autowired
	  public EANService(ProductoService productoService,ProveedorService proveedorService,DestinoService destinoService) {
	    this.productoService = productoService;
	    this.proveedorService = proveedorService;
	    this.destinoService = destinoService;
	  }
	  
	  @Cacheable
	  public EAN getEAN(Long codigoEAN) throws IncorrectCodeException, IncorrectFormatCodeException, IncorrectEANCode {
		  
		  if(Long.toString(codigoEAN).length()==13){
		  
		  Integer codigoProducto = Integer.parseInt(String.valueOf(codigoEAN).substring(7, 12));
		  Producto producto = productoService.getProducto(codigoProducto);
		  
		  Integer codigoProveedor = Integer.parseInt(String.valueOf(codigoEAN).substring(0, 7));
		  Proveedor proveedor = proveedorService.getProveedor(codigoProveedor);
		  
		  Integer codigoDestino = Integer.parseInt(String.valueOf(codigoEAN).substring(12, 13));
		  Destino destino = destinoService.getDestino(codigoDestino);
		  
		  return new EAN(producto,proveedor,destino);
		  
		  }else {
			  throw new IncorrectEANCode();
		  }
		  
		  
	}
}
