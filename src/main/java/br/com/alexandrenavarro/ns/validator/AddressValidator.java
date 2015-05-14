package br.com.alexandrenavarro.ns.validator;

import br.com.alexandrenavarro.ns.model.Address;

public class AddressValidator {

	public static boolean validate(Address address) {
		if(address == null){
			return false;
		}
		
		if (address.getNumber() == null || address.getCity().isEmpty()) {
			return false;
		}

		if (address.getZipCode() == null || address.getZipCode().isEmpty()) {
			return false;
		}

		if (address.getCity() == null || address.getCity().isEmpty()) {
			return false;
		}

		if (address.getState() == null || address.getState().isEmpty()) {
			return false;
		}

		if (address.getStreet() == null || address.getStreet().isEmpty()) {
			return false;
		}

		return true;
	}

}
