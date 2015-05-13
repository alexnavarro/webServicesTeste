package br.com.alexandrenavarro.ns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandrenavarro.ns.dao.AddressDAO;
import br.com.alexandrenavarro.ns.model.Address;
import br.com.alexandrenavarro.ns.model.DefaultError;
import br.com.alexandrenavarro.ns.service.ZipCodeService;

@RestController
@RequestMapping("/address")
public class AddressController {

	private AddressDAO dao;
	private ZipCodeService zipCodeService;

	@Autowired
	public AddressController(AddressDAO dao, ZipCodeService zipCodeService) {
		this.dao = dao;
		this.zipCodeService = zipCodeService;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Address address) {
		if (!AddressValidator.validate(address)) {
			return defaultError("Os campos Rua, Numero, Cep, Cidade e Estado são obrigatórios!");
		}
		
		if (zipCodeService.findAddressByCep(address.getZipCode()) == null) {
			return defaultError("CEP invalido");
		}

		return new ResponseEntity<Address>(dao.insert(address),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findByCep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<?> findAddressByCep(@PathVariable String cep) {
		Address address = zipCodeService.findAddressByCep(cep);

		if (address == null) {
			return defaultError("CEP invalido");
		}

		return new ResponseEntity<Address>(address, HttpStatus.OK);

	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAddress(@PathVariable Integer id) {
		Address address = dao.findById(id);

		if (address == null) {
			return defaultError("Id inexistente!");
		}

		return new ResponseEntity<Address>(address, HttpStatus.OK);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Address address) {
		if (!AddressValidator.validate(address)) {
			return defaultError("Os campos Rua, Numero, Cep, Cidade e Estado são obrigatórios!");
		}
		
		if (zipCodeService.findAddressByCep(address.getZipCode()) == null) {
			return defaultError("CEP invalido");
		}

		if (!dao.update(address))
			return defaultError("Id inexistente!");

		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestBody Address address) {
		if (!dao.delete(address.getId()))
			return defaultError("Id inexistente!");

		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

	private ResponseEntity<?> defaultError(String message) {
		DefaultError error = new DefaultError();
		error.setMessage(message);
		return new ResponseEntity<DefaultError>(error, HttpStatus.NOT_FOUND);
	}
}
