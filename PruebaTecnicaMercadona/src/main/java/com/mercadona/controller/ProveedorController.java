package com.mercadona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Producto;
import com.mercadona.model.Proveedor;
import com.mercadona.service.ProductoService;
import com.mercadona.service.ProveedorService;

@RestController
@RequestMapping("/mercadona/proveedor")
public class ProveedorController {
	private ProveedorService proveedorService;
	
	
	@Autowired
    public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}



	@GetMapping("/{codigoProveedor}")
    public Proveedor getProveedorByCodigo(@PathVariable Integer codigoProveedor) throws IncorrectCodeException, IncorrectFormatCodeException {
        return proveedorService.getProveedor(codigoProveedor);
    }
}
