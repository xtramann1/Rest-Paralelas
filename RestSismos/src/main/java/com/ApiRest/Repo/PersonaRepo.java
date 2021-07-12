package com.ApiRest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiRest.model.Persona;

public interface PersonaRepo extends JpaRepository<Persona, Long>{
	public Persona findByToken(String token);
	public Persona findByAppIgnoreCase(String app);
}
