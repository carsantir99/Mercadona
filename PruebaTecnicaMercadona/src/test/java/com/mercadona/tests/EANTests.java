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
import com.mercadona.exceptions.IncorrectEANCode;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Destino;
import com.mercadona.model.DestinoEnum;
import com.mercadona.model.EAN;
import com.mercadona.model.Producto;
import com.mercadona.model.Proveedor;
import com.mercadona.service.DestinoService;
import com.mercadona.service.EANService;
import com.mercadona.service.ProductoService;
import com.mercadona.service.ProveedorService;

@ExtendWith(MockitoExtension.class)
public class EANTests {
	@Mock
    private EANService eanService;
	
	@Mock
    private ProductoService productoService;
	
	@Mock
    private ProveedorService proveedorService;
	
	@Mock
    private DestinoService destinoService;
	
	@Test
	public void testConsultaEANCorrecta() throws IncorrectCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, IncorrectEANCode {
		Long codigoEAN = 1234567123451L;
		Producto producto = new Producto(12345, "Leche");
		Proveedor proveedor = new Proveedor(1234567, "Proveedor externo 1");
		Destino destino = new Destino(1, "España");
		
		EAN eanObject = new EAN(producto, proveedor, destino);
		
		when(eanService.getEAN(codigoEAN)).thenReturn(eanObject);
		
		EAN eanActual = eanService.getEAN(codigoEAN);
		
		assertEquals(eanObject,eanActual);
		
		Mockito.verify(eanService).getEAN(codigoEAN);
	}
	
	@Test
	public void testConsultaEANCodigoIncorrecto() throws IncorrectCodeException, IncorrectFormatCodeException, IncorrectEANCode {
		Long codigoEAN = 12345671234512L;
		eanService = mock(EANService.class);
		
	    doThrow(new IncorrectEANCode()).when(eanService).getEAN(codigoEAN);
	        
	    assertThrows(IncorrectEANCode.class, () -> eanService.getEAN(codigoEAN));
	}
}
