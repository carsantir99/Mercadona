package com.mercadona.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.DestinoEnum;


@Service
public class DestinoService {
	
	
	
	  @Cacheable
	  public Destino getDestino(Integer destinoNumber) throws IncorrectCodeException {
		  Destino destino = new Destino();
		  if(destinoNumber>0 && destinoNumber<=5) {
			  destino.setCodigo(destinoNumber);
			  destino.setDestino(DestinoEnum.ENTRE_1_5.getNombre());
		  }else if(destinoNumber==6) {
			  destino.setCodigo(destinoNumber);
			  destino.setDestino(DestinoEnum.SEIS.getNombre());
		  }else if(destinoNumber==8) {
			  destino.setCodigo(destinoNumber);
			  destino.setDestino(DestinoEnum.OCHO.getNombre());
		  }else if(destinoNumber==9) {
			  destino.setCodigo(destinoNumber);
			  destino.setDestino(DestinoEnum.NUEVE.getNombre());
		  }else if(destinoNumber==0) {
			  destino.setCodigo(destinoNumber);
			  destino.setDestino(DestinoEnum.CERO.getNombre());
		  }else {
			  throw new IncorrectCodeException();
		  }
		  
		  return destino;
	}
}
