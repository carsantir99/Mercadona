package com.mercadona.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Validator;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
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
	private Validator validator;
    
	@Test
	public void testConsultaProductoCorrecta() throws IncorrectCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException {
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
	
	@Test
	public void testCrearProductoCorrecto() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
		Producto producto = new Producto(12346,"Leche");
		productoService = mock(ProductoService.class);
		
		when(productoService.creaProducto(producto)).thenReturn(producto);
		
		Producto productoCreado = productoService.creaProducto(producto);
		
		assertEquals(producto,productoCreado);
	}
	
	@Test
	public void testCrearProductoCodigoDuplicado() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
		Producto producto = new Producto(12346,"Leche");
		productoService = mock(ProductoService.class);
		
		when(productoService.creaProducto(producto)).thenThrow(new DuplicatedCodeException());
		
		
		 assertThrows(DuplicatedCodeException.class, () -> productoService.creaProducto(producto));
	}
	
	@Test
	public void testCrearProductoFormatoIncorrecto() throws IncorrectFormatCodeException {
		
		assertThrows(IncorrectFormatCodeException.class,() -> new Producto(123466,"Leche"));
	}
	
	@Test
	public void testCrearProductoCampoCodigoVacio() throws EmptyCodeException {
		assertThrows(EmptyCodeException.class,() -> new Producto(null,"Leche"));
	}
	
	@Test
	public void testCrearProductoCampoNombreVacio() throws EmptyNameException {
		assertThrows(EmptyNameException.class,() -> new Producto(12347,""));
	}
	
}
