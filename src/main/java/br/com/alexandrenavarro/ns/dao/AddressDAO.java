package br.com.alexandrenavarro.ns.dao;

import org.springframework.stereotype.Component;

@Component
public class AddressDAO {

  public String findAddressByCep(String zipCode){
	  if("01508000".equals(zipCode.trim())){
		  return "Rua São Joaquim, 580 Liberdade - São Paulo 01508-000";
	  }else if("01311000".equals(zipCode.trim())){
		  return "Av Paulista, 287 Bela Vista - São Paulo 01311-000";
	  }else if("22333999".equals(zipCode.trim()) ||  "22333990".equals(zipCode.trim()) || "22333900".equals(zipCode.trim())){
		  return "";
	  }else if("22333000".equals(zipCode.trim())){
		  return "Av Luis Carlos Berrini, 287 Brooklin - São Paulo 22333-000";
	  }
	  
	  return "CEP invalido";
  }

}
