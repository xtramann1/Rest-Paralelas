package com.ApiRest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiRest.model.DatosSismos;
public interface DatosSismosRepo extends JpaRepository<DatosSismos, Long>{

}
