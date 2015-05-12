package br.com.alexandrenavarro.ns.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import br.com.alexandrenavarro.ns.model.Address;

@Component
public class AddressDAO {

	private Map<Integer, Address> addresses = new HashMap<Integer, Address>();

	public Address findAddressByCep(String zipCode) {
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

	public Address insert(Address address) {
		address.setId(addresses.size() + 1);
		addresses.put(address.getId(), address);
		return address;
	}

	public Address findById(int id) throws Error {
		for (Entry<Integer, Address> entry : addresses.entrySet()) {
			Integer key = entry.getKey();
			Address value = entry.getValue();
			if (key.equals(id)) {
				return value;
			}
		}

		return null;
	}

	public boolean delete(int id) {
		for (Entry<Integer, Address> entry : addresses.entrySet()) {
			Integer key = entry.getKey();
			Address value = entry.getValue();
			if (key.equals(id)) {
				return addresses.remove(key, value);
			}
		}
		return false;
	}

	public boolean update(Address address) {
		for (Entry<Integer, Address> entry : addresses.entrySet()) {
			Integer key = entry.getKey();
			Address value = entry.getValue();
			if (key.equals(address.getId())) {
				value.setCity(address.getCity());
				value.setComplement(address.getComplement());
				value.setNeighborhood(address.getNeighborhood());
				value.setNumber(address.getNumber());
				value.setState(address.getState());
				value.setStreet(address.getStreet());
				value.setZipCode(address.getZipCode());
				return true;
			}
		}

		return false;
	}

}
