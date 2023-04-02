package com.mercadona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Producto;
import com.mercadona.service.ProductoService;

@RestController
@RequestMapping("/mercadona/producto")
public class ProductoController {
	
	private ProductoService productoService;
	
	
	@Autowired
    public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}



	@GetMapping("/{codigoProducto}")
    public Producto getProductoByCodigo(@PathVariable Integer codigoProducto) throws IncorrectCodeException, IncorrectFormatCodeException {
        return productoService.getProducto(codigoProducto);
    }
	
	@PostMapping("/create")
    public Producto crearProducto(@RequestParam Integer codigo, @RequestParam String nombre) throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
		Producto producto = new Producto(codigo,nombre);
		return productoService.creaProducto(producto);
    }
}
