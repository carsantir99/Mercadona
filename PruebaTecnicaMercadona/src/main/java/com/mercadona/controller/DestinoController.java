package com.mercadona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.Producto;
import com.mercadona.service.DestinoService;



@RestController
@RequestMapping("/mercadona/destino")
public class DestinoController {
	private DestinoService destinoService;
	
	
	@Autowired
    public DestinoController(DestinoService destinoService) {
		this.destinoService = destinoService;
	}
	
	@GetMapping("/{codigoDestino}")
    public Destino getDestino(@PathVariable Integer codigoDestino) throws IncorrectCodeException{
        return destinoService.getDestino(codigoDestino);
    }
}
