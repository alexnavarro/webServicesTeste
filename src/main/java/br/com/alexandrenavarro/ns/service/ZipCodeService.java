package br.com.alexandrenavarro.ns.service;

import org.springframework.stereotype.Service;

import br.com.alexandrenavarro.ns.model.Address;

@Service
public class ZipCodeService {
	
	public Address findAddressByCep(String zipCode) {
		if(zipCode == null)
			return null;
		
		Address value = new Address();
		if ("01508000".equals(zipCode.trim())) {
			value.setCity("São Paulo");
			value.setComplement("");
			value.setNeighborhood("Liberdade");
			value.setNumber("");
			value.setState("SP");
			value.setStreet("Rua São Joaquim");
			value.setZipCode("01508000");
			return value;
		} else if ("01311000".equals(zipCode.trim())) {
			value.setCity("São Paulo");
			value.setComplement("");
			value.setNeighborhood("Bela Vista");
			value.setNumber("");
			value.setState("SP");
			value.setStreet("Av Paulista");
			value.setZipCode("01311000");
			return value;
		} else if ("22333999".equals(zipCode.trim())
				|| "22333990".equals(zipCode.trim())
				|| "22333900".equals(zipCode.trim())) {
			return findAddressByCep(createNewZipCode(zipCode));
		} else if ("22333000".equals(zipCode.trim())) {
			value.setCity("São Paulo");
			value.setComplement("");
			value.setNeighborhood("Brooklin");
			value.setNumber("332");
			value.setState("SP");
			value.setStreet("Av Luis Carlos Berrini");
			value.setZipCode("22333000");
			return value;
		}

		return null;
	}
	
	private String createNewZipCode(String zipCode) {
		int j = zipCode.length();
		boolean changeToZero = true;
		char[] charArray = zipCode.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			j -= 1;
			if (charArray[j] != '0' && changeToZero) {
				changeToZero = false;
				charArray[j] = '0';
				break;
			}
		}

		return new String(charArray);

	}

}
