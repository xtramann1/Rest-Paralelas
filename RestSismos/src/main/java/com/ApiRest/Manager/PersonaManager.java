package com.ApiRest.Manager;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ApiRest.Repo.PersonaRepo;
import com.ApiRest.model.Persona;
import java.util.List;


@Service
public class PersonaManager implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient PersonaRepo personarepo;
	
	public Persona getPersona(final Long id) {
		Persona persona = null;
		if(id != null) {
			Optional<Persona> finded = personarepo.findById(id);
			if(finded.isPresent()) {
				persona = finded.get();
			}
		}
		return persona;
	}
	
	public Persona getPersona(final String token) {
		Persona persona = null;
		if(StringUtils.isNotBlank(token)) {
			persona = personarepo.findByToken(token);
		}
		return persona;
	}
	
	public List<Persona> getPersonas(){
		return personarepo.findAll(Sort.by(Sort.Direction.ASC, "created"));
	}
	
    public Persona getPersonaByApp(final String app) {
        Persona persona = null;
        if (StringUtils.isNotBlank(app)) {
            persona = personarepo.findByAppIgnoreCase(app);
        }
        return persona;
    }
	
	@Transactional
	public Persona save(Persona persona) {
		Persona saved = null;
		if(persona != null) {
			saved = personarepo.save(persona);
		}
		return saved;
	}
	
	@Transactional
	public boolean delete(Persona persona) {
		boolean ok = false;
		if(persona !=null) {
			personarepo.delete(persona);
			ok = false;
		}
		return ok;
	}
	
	public boolean authenticate(final String app, final String password) {
		boolean ok = false;
		if(StringUtils.isNotBlank(app) && StringUtils.isNotBlank(password)) {
			Persona persona = personarepo.findByAppIgnoreCase(app);
			if(persona != null && persona.isActive()) {
				ok = StringUtils.equals(persona.getPassword(), password);
			}
		}
		return ok;
	}
}
