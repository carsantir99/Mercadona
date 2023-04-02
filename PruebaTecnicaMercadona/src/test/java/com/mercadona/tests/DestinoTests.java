package com.mercadona.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.DestinoEnum;
import com.mercadona.model.Producto;
import com.mercadona.repository.ProductoRepository;
import com.mercadona.service.DestinoService;
import com.mercadona.service.ProductoService;

@ExtendWith(MockitoExtension.class)
public class DestinoTests {
	@Mock
    private DestinoService destinoService;
	
	@Test
	public void testConsultaDestinoCorrecta() throws IncorrectCodeException {
		Integer codigoDestino = 1;
		Destino destinoObject = new Destino(codigoDestino, DestinoEnum.ENTRE_1_5.getNombre());
		when(destinoService.getDestino(codigoDestino)).thenReturn(destinoObject);
		
		Destino destinoActual = destinoService.getDestino(codigoDestino);
		
		assertEquals(destinoObject,destinoActual);
		
		Mockito.verify(destinoService).getDestino(codigoDestino);
	}
	
	@Test
	public void testConsultaDestinoNoExisteCodigo() throws IncorrectCodeException {
		Integer codigoDestino = 7;
		destinoService = mock(DestinoService.class);
		
	    doThrow(new IncorrectCodeException()).when(destinoService).getDestino(codigoDestino);
	        
	    assertThrows(IncorrectCodeException.class, () -> destinoService.getDestino(codigoDestino));
	}
}
