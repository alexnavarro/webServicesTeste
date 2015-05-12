package br.com.alexandrenavarro.ns.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alexandrenavarro.ns.controller.AddressController.Result;
import br.com.alexandrenavarro.ns.dao.AddressDAO;

public class AddressControllerTest {
	private static final String INVALID_ZIPCODE = "14920";
	private static final String INVALID_ADDRESS = "CEP invalido";
	
	private static final String VALID_ZIPCODE = "01311000";
	private static final String VALID_ADDRESS = "Av Paulista, 287 Bela Vista - São Paulo 01311-000";
	
	private static final String NOT_EXISTENT_VALID_ZIPCODE = "22333999";
	private static final String VALID_ADDRESS_FOUND = "Av Luis Carlos Berrini, 287 Brooklin - São Paulo 22333-000";
	
	@Mock
	private AddressDAO dao;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	  } 
	
	@Test
	public void quandoCepForInvalidoDeveRetornarMensagemInformando(){
		AddressController controller = new AddressController(dao);
		Mockito.when(dao.findAddressByCep(INVALID_ZIPCODE)).thenReturn(INVALID_ADDRESS);		
		Assert.assertEquals(INVALID_ADDRESS, controller.findAddress(INVALID_ZIPCODE).getResult());		
		Mockito.verify(dao).findAddressByCep(INVALID_ZIPCODE);
	}
	
	@Test
	public void quandoCepForValidoDeveRetornarEndereco(){
		AddressController controller = new AddressController(dao);
		Mockito.when(dao.findAddressByCep(VALID_ZIPCODE)).thenReturn(VALID_ADDRESS);
		Result findAddress = controller.findAddress(VALID_ZIPCODE);
		Assert.assertEquals(VALID_ADDRESS, findAddress.getResult());	
		Mockito.verify(dao).findAddressByCep(VALID_ZIPCODE);
	}
	
	@Test
	public void quandoCepForPertencenteAoRangeDeCepMasForEnderecoInesistenteDeveRealizarOutraConsultaColocandoZeroNoFinal(){
		AddressController controller = new AddressController(dao);
		Mockito.when(dao.findAddressByCep(Mockito.anyString())).thenReturn("" , VALID_ADDRESS_FOUND);
		Assert.assertEquals(VALID_ADDRESS_FOUND, controller.findAddress(NOT_EXISTENT_VALID_ZIPCODE).getResult());
		
	}

}
