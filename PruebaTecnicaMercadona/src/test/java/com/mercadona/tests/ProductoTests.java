package com.mercadona.tests;

import static org.junit.Assert.assertEquals;
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
	public void testConsultaProductoCorrecta() {
		Integer codigoProducto = 12345;
		Producto productoObject = new Producto(codigoProducto, "Margarina");
		when(productoService.getProducto(codigoProducto)).thenReturn(productoObject);
		
		Producto productoActual = productoService.getProducto(12345);
		
		assertEquals(productoObject,productoActual);
		
		Mockito.verify(productoRepository).getProducto(codigoProducto);
	}
}
