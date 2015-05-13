package br.com.alexandrenavarro.ns.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.com.alexandrenavarro.ns.dao.AddressDAO;
import br.com.alexandrenavarro.ns.model.Address;
import br.com.alexandrenavarro.ns.model.DefaultError;
import br.com.alexandrenavarro.ns.service.ZipCodeService;

public class AddressControllerTest {
	
	@Mock
	public AddressDAO dao;
	@Mock
	public ZipCodeService service;
	
	AddressController controller;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		controller = new AddressController(dao, service);
	}
	
	@Test
	public void deveRetornarMesnagemDeErroQuandoTentarInseirEnderecoInvalido() {
		controller.insert(null);
		verify(dao, never()).insert(any(Address.class));
		verify(service, never()).findAddressByCep(anyString());
	}
	
	@Test
	public void deveRetornarMesnagemDeErroQuandoTentarInseirEnderecoECepForInvalido() {
		when(service.findAddressByCep("01508")).thenReturn(null);
		controller.insert(new Address());	
		verify(dao, never()).insert(any(Address.class));
	}
	
	@Test
	public void deveRetornarAddressQuandoEnderecoForCadastrado() {
		Address address = createAddress();		
		when(service.findAddressByCep(anyString())).thenReturn(address);
		controller.insert(address);	
		verify(dao).insert(address);
		verify(service).findAddressByCep(anyString());
	}
	
	@Test
	public void deveRetornarMesnagemDeErroQuandoCeForInvalido() {
		when(service.findAddressByCep(anyString())).thenReturn(null);
		ResponseEntity<?> result = controller.findAddressByCep("444");
	    assertEquals(DefaultError.class, result.getBody().getClass());
	}
	
	@Test
	public void deveRetornarAddressQuandoCepForEncontrado() {
		when(service.findAddressByCep(anyString())).thenReturn(createAddress());
		ResponseEntity<?> result = controller.findAddressByCep("015080000");
	    assertEquals(Address.class, result.getBody().getClass());
	}
	
	@Test
	public void deveRetornarErroQuandoTentarPesquisarEnderecoComIdInexistente() {
		when(dao.findById(anyInt())).thenReturn(null);
		ResponseEntity<?> result = controller.findAddress(1);
		assertEquals(DefaultError.class, result.getBody().getClass());
	    verify(dao).findById(anyInt());
	}
	
	@Test
	public void deveRetornarEnderecoQuandoIdDoEnderecoForValido() {
		when(dao.findById(anyInt())).thenReturn(createAddress());
		ResponseEntity<?> result = controller.findAddress(1);
		assertEquals(Address.class, result.getBody().getClass());
	    verify(dao).findById(anyInt());
	}
	
	@Test
	public void deveRetornarErroQuandoTentarApagarEnderecoComIdInexistente() {
		when(dao.delete(anyInt())).thenReturn(false);
		ResponseEntity<?> result = controller.delete(createAddress());
		assertEquals(DefaultError.class, result.getBody().getClass());
	    verify(dao).delete(anyInt());
	}
	
	@Test
	public void deveRetornarApagarEnderecoComIdEstiverCorreto() {
		when(dao.delete(anyInt())).thenReturn(true);
		ResponseEntity<?> result = controller.delete(createAddress());
		assertEquals(String.class, result.getBody().getClass());
	    verify(dao).delete(anyInt());
	}
	
	@Test
	public void deveRetornarMesnagemDeErroQuandoTentarAtulizarEnderecoInvalido() {
		controller.update(null);
		verify(dao, never()).update(any(Address.class));
		verify(service, never()).findAddressByCep(anyString());
	}
	
	@Test
	public void deveRetornarMesnagemDeErroQuandoTentarAtualizarEnderecoECepForInvalido() {
		when(service.findAddressByCep("01508")).thenReturn(null);
		controller.update(new Address());	
		verify(dao, never()).insert(any(Address.class));
	}
	
	@Test
	public void deveRetornarAddressQuandoEnderecoForAtualizado() {
		Address address = createAddress();		
		when(service.findAddressByCep(anyString())).thenReturn(address);
		when(dao.update(address)).thenReturn(true);
		assertEquals(String.class, controller.update(address).getBody().getClass());
		verify(dao).update(address);
		verify(service).findAddressByCep(anyString());
	}

	private Address createAddress(){
		Address value = new Address();
		value.setCity("São Paulo");
		value.setNeighborhood("Liberdade");
		value.setNumber("544");
		value.setState("SP");
		value.setStreet("Rua São Joaquim");
		value.setZipCode("01508000");
		return value;
	}

}
