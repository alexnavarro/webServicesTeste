package br.com.alexandrenavarro.ns.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import br.com.alexandrenavarro.ns.model.Address;

@Component
public class AddressDAO {

	private Map<Integer, Address> addresses = new HashMap<Integer, Address>();

	public Address insert(Address address) {
		address.setId(addresses.size() + 1);
		addresses.put(address.getId(), address);
		return address;
	}

	public Address findById(int id) {
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
		if (address == null) {
			return false;
		}

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
