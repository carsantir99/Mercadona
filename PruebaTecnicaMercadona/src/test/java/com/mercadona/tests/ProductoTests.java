package com.mercadona.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Producto;
import com.mercadona.repository.ProductoRepository;
import com.mercadona.service.ProductoService;

@ExtendWith(MockitoExtension.class)
public class ProductoTests {

	@Mock
    private ProductoRepository productoRepository;
	@InjectMocks
    private ProductoService productoService;
    
	@Test
	public void testConsultaProductoCorrecta() throws IncorrectCodeException, IncorrectFormatCodeException {
		Integer codigoProducto = 12345;
		Producto productoObject = new Producto(codigoProducto, "Margarina");
		when(productoRepository.getProducto(codigoProducto)).thenReturn(productoObject);
		
		Producto productoActual = productoService.getProducto(12345);
		
		assertEquals(productoObject,productoActual);
		
		Mockito.verify(productoRepository).getProducto(codigoProducto);
	}
	
	@Test
	public void testConsultaProductoNoExisteCodigo() throws IncorrectCodeException, IncorrectFormatCodeException {
		Integer codigoProducto = 12346;
		productoService = mock(ProductoService.class);
		
	    doThrow(new IncorrectCodeException()).when(productoService).getProducto(codigoProducto);
	        
	    assertThrows(IncorrectCodeException.class, () -> productoService.getProducto(codigoProducto));
	}
	
	@Test
	public void testConsultaProductoFormatoCodigoIncorrecto() throws IncorrectCodeException, IncorrectFormatCodeException {
		Integer codigoProducto = 12345698;
		productoService = mock(ProductoService.class);
		
	    doThrow(new IncorrectFormatCodeException()).when(productoService).getProducto(codigoProducto);
	        
	    assertThrows(IncorrectFormatCodeException.class, () -> productoService.getProducto(codigoProducto));
	}
}
