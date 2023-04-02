package com.mercadona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Proveedor;
import com.mercadona.model.Proveedor;
import com.mercadona.service.ProveedorService;
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
	
	@PostMapping("/create")
    public Proveedor crearProveedor(@RequestParam Integer codigo, @RequestParam String nombre) throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
		return proveedorService.creaProveedor(codigo,nombre);
    }
	
	@PutMapping("/edit")
    public Proveedor editarProveedor(@RequestParam Integer codigo, @RequestParam String nombre) throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException, IncorrectCodeException {
		return proveedorService.actualizarProveedor(codigo,nombre);
    }
}
