package br.com.alexandrenavarro.ns.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alexandrenavarro.ns.dao.AddressDAO;
import br.com.alexandrenavarro.ns.model.Address;
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
		Address address = Mockito.mock(Address.class, Mockito.RETURNS_DEEP_STUBS);		
		when(service.findAddressByCep(anyString())).thenReturn(any(Address.class));
		controller.insert(eq(address));	
		verify(dao).insert(any(Address.class));
		verify(service).findAddressByCep(anyString());
	}

}
