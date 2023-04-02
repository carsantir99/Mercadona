package com.mercadona.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadona.exceptions.DuplicatedCodeException;
import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectCodeException;
import com.mercadona.exceptions.IncorrectFormatCodeException;
import com.mercadona.model.Proveedor;
import com.mercadona.model.Proveedor;
import com.mercadona.repository.ProveedorRepository;
import com.mercadona.service.ProveedorService;
import com.mercadona.service.ProveedorService;


@ExtendWith(MockitoExtension.class)
public class ProveedorTests {
	@Mock
    private ProveedorRepository proveedorRepository;
	@InjectMocks
    private ProveedorService proveedorService;
	
	@Test
	public void testConsultaProveedorCorrecta() throws IncorrectCodeException, IncorrectFormatCodeException, EmptyCodeException, EmptyNameException {
		Integer codigoProveedor = 8437008;
		Proveedor proveedorObject = new Proveedor(codigoProveedor, "Hacendado");
		when(proveedorRepository.getProveedor(codigoProveedor)).thenReturn(proveedorObject);
		
		Proveedor proveedorActual = proveedorService.getProveedor(codigoProveedor);
		
		assertEquals(proveedorObject,proveedorActual);
		
		Mockito.verify(proveedorRepository).getProveedor(codigoProveedor);
	}
	
	@Test
	public void testConsultaProveedorNoExisteCodigo() throws IncorrectCodeException, IncorrectFormatCodeException {
		Integer codigoProveedor = 8437009;
		proveedorService = mock(ProveedorService.class);
		
	    doThrow(new IncorrectCodeException()).when(proveedorService).getProveedor(codigoProveedor);
	        
	    assertThrows(IncorrectCodeException.class, () -> proveedorService.getProveedor(codigoProveedor));
	}
	
	@Test
	public void testConsultaproveedorFormatoCodigoIncorrecto() throws IncorrectCodeException, IncorrectFormatCodeException {
		Integer codigoProveedor = 12345698;
		proveedorService = mock(ProveedorService.class);
		
	    doThrow(new IncorrectFormatCodeException()).when(proveedorService).getProveedor(codigoProveedor);
	        
	    assertThrows(IncorrectFormatCodeException.class, () -> proveedorService.getProveedor(codigoProveedor));
	}
	
	 @Test
		public void testCrearProveedorCorrecto() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
			Proveedor proveedor = new Proveedor(1234567,"Proveedor externo");
			proveedorService = mock(ProveedorService.class);
			
			when(proveedorService.creaProveedor(proveedor.getCodigo(), proveedor.getNombre())).thenReturn(proveedor);
			
			Proveedor proveedorCreado = proveedorService.creaProveedor(proveedor.getCodigo(), proveedor.getNombre());
			
			assertEquals(proveedor,proveedorCreado);
		}
		
		@Test
		public void testCrearProveedorCodigoDuplicado() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, DuplicatedCodeException {
			Proveedor proveedor = new Proveedor(1234567,"Proveedor externo");
			proveedorService = mock(ProveedorService.class);
			
			when(proveedorService.creaProveedor(proveedor.getCodigo(), proveedor.getNombre())).thenThrow(new DuplicatedCodeException());
			
			
			 assertThrows(DuplicatedCodeException.class, () -> proveedorService.creaProveedor(proveedor.getCodigo(), proveedor.getNombre()));
		}
		
		@Test
		public void testProveedorFormatoIncorrecto() throws IncorrectFormatCodeException {
			
			assertThrows(IncorrectFormatCodeException.class,() -> new Proveedor(12345677,"Proveedor externo"));
		}
		
		@Test
		public void testProveedorCampoCodigoVacio() throws EmptyCodeException {
			assertThrows(EmptyCodeException.class,() -> new Proveedor(null,"Proveedor externo"));
		}
		
		@Test
		public void testProveedorCampoNombreVacio() throws EmptyNameException {
			assertThrows(EmptyNameException.class,() -> new Proveedor(1234567,""));
		}
		
		@Test
		public void testEditarProveedorCorrecto() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, IncorrectCodeException {
			Proveedor proveedorActual = new Proveedor(1234567, "Proveedor externo 1");

			when(proveedorRepository.getProveedor(1234567)).thenReturn(proveedorActual);

			Proveedor proveedorNuevo = new Proveedor(1234567, "Proveedor externo 2");

	        proveedorService.actualizarProveedor(proveedorNuevo.getCodigo(), proveedorNuevo.getNombre());

	        proveedorRepository.save(proveedorActual);
	        assertEquals(proveedorNuevo.getCodigo(), proveedorActual.getCodigo());
	        assertEquals(proveedorNuevo.getNombre(),proveedorActual.getNombre());
		}
		
		@Test
		public void testEditarProveedorCodigoNoExistente() throws IncorrectFormatCodeException, EmptyCodeException, EmptyNameException, IncorrectCodeException {
			Proveedor proveedor = new Proveedor(1234567, "Galletas");
			proveedorService = mock(ProveedorService.class);
			
		    doThrow(new IncorrectCodeException()).when(proveedorService).actualizarProveedor(proveedor.getCodigo(), proveedor.getNombre());
		        
		    assertThrows(IncorrectCodeException.class, () -> proveedorService.actualizarProveedor(proveedor.getCodigo(), proveedor.getNombre()));
		}
}
