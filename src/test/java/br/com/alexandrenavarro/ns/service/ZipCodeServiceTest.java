package br.com.alexandrenavarro.ns.service;

import org.junit.Assert;
import org.junit.Test;

import br.com.alexandrenavarro.ns.service.ZipCodeService;

public class ZipCodeServiceTest {

	private static final String INVALID_ZIPCODE = "14920";
	private static final String VALID_ZIPCODE = "01311000";
	private static final String NOT_EXISTENT_VALID_ZIPCODE = "22333999";
	private static final String VALID_ZIPCODE_FOUND = "22333000";

	@Test
	public void quandoCepForInvalidoDeveRetornarNull() {
		ZipCodeService service = new ZipCodeService();
		Assert.assertNull(service.findAddressByCep(INVALID_ZIPCODE));
	}

	@Test
	public void quandoCepForValidoDeveRetornarEndereco() {
		ZipCodeService service = new ZipCodeService();
		Assert.assertEquals(VALID_ZIPCODE,
				service.findAddressByCep(VALID_ZIPCODE).getZipCode());
	}

	@Test
	public void quandoCepForPertencenteAoRangeDeCepMasForEnderecoInesistenteDeveRealizarOutraConsultaColocandoZeroNoFinal() {
		ZipCodeService service = new ZipCodeService();
		Assert.assertEquals(VALID_ZIPCODE_FOUND,
				service.findAddressByCep(NOT_EXISTENT_VALID_ZIPCODE).getZipCode());
	}
}
