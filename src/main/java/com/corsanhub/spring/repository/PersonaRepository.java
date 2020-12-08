package com.corsanhub.spring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.corsanhub.spring.dto.Persona;

public interface PersonaRepository extends MongoRepository<Persona, String> {

	public List<Persona> findByUuid(String uuid);

	public Persona findByNombre(String nombre);

	public List<Persona> findByApellidoPaterno(String apellidoPaterno);

	public List<Persona> findByApellidoMaterno(String apellidoMaterno);

}