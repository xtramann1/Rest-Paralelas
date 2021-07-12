package com.ApiRest.Manager;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiRest.Repo.DatosSismosRepo;
import com.ApiRest.model.DatosSismos;
import scrapt.scrapting;

@Service
public class DatosSismosManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient DatosSismosRepo Sismosrepo;
	
	public void extraerdatos() throws IOException, ParseException{
		List<DatosSismos> sismito = scrapting.extraerDatos();
		for(DatosSismos S: sismito) {

			Sismosrepo.save(S);
		}
	}
	
	public List<DatosSismos> getAllSismos(){
		return Sismosrepo.findAll();
	}
	
	public Optional<DatosSismos> create(Long id) {
		return Sismosrepo.findById(id);
	}
	
}
