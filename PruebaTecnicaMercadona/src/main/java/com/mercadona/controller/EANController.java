package com.mercadona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectEANCode;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.EAN;
import com.mercadona.service.DestinoService;
import com.mercadona.service.EANService;

@RestController
@RequestMapping("/mercadona/ean")
public class EANController {
	private EANService eanService;
	
	
	@Autowired
    public EANController(EANService eanService) {
		this.eanService = eanService;
	}
	
	@GetMapping("/{codigoEAN}")
    public EAN getEAN(@PathVariable Long codigoEAN) throws IncorrectCodeException, IncorrectFormatCodeException, IncorrectEANCode{
        return eanService.getEAN(codigoEAN);
    }
}
