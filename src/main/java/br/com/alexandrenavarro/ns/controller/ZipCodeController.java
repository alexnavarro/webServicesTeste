package br.com.alexandrenavarro.ns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandrenavarro.ns.dao.AddressDAO;

@RestController
@RequestMapping("/findAddress")
public class ZipCodeController {

	private AddressDAO dao;

	@Autowired
	public ZipCodeController(AddressDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/{zipCode}")
	public Result findAddress(@PathVariable String zipCode) {
		String adddress = null;



		return new Result(adddress);
	}

	public class Result {
		private String result;

		public Result(String result) {
			this.result = result;
		}

		public String getResult() {
			return result;
		}

	}

}
