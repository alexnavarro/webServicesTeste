package br.com.alexandrenavarro.ns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandrenavarro.ns.dao.AddressDAO;

@RestController
@RequestMapping("/findAddress")
public class AddressController {

	private AddressDAO dao;

	@Autowired
	public AddressController(AddressDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value ="/{zipCode}")
	public Result findAddress(@PathVariable String zipCode) {
		String adddress = dao.findAddressByCep(zipCode);

		if (adddress.isEmpty()) {
			int j = zipCode.length();

			String zipCodeChanged = zipCode;

			for (int i = 0; i < zipCode.length(); i++) {
				j -= 1;
				String newZipCode = "";
				for (int o = 0; o < zipCode.length(); o++) {
					newZipCode += j == o ? "0" : zipCodeChanged.charAt(o);
				}

				zipCodeChanged = newZipCode;
				String zip = dao.findAddressByCep(newZipCode);
				if (!zip.isEmpty()) {
					return new Result(zip);
				}
			}
		}

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
