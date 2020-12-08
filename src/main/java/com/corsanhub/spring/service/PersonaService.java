package com.corsanhub.spring.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsanhub.spring.dto.Persona;
import com.corsanhub.spring.repository.PersonaRepository;

@Service
public class PersonaService {
	private static Logger logger = LoggerFactory.getLogger(PersonaService.class);
	@Autowired
	private PersonaRepository personaRepository;

	private String[] nombres = { "Héctor", "Helena", "Yandel", "Penélope", "Ulises", "Zeus", "Alejandro", "Álvaro", "Ana", "Emma", "Lucía", "Manuel", "Mariana",
			"Martín", "Gabriel", "Isabel", "Martha", "Margarita", "Shakira", "Arianna" };

	private String[] apellidos = { "Alzate", "Becerril", "Chavira", "Donato", "Escamilla", "Fraga", "Galindo", "Heredia", "Ivañez", "Jara", "Linares",
			"Montecinos", "Novoa", "Oviedo", "Patiño", "Quijano", "Romero", "Silva", "Talavera", "Uribe" };

	private int generateRandom(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max debe ser mayor que min [" + max + "<=" + min + "] :S");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	private Persona createRandomPersona() {
		String uuid = UUID.randomUUID().toString();
		String nombre = nombres[generateRandom(0, 19)];
		String apellidoPaterno = apellidos[generateRandom(0, nombres.length - 1)];
		String apellidoMaterno = apellidos[generateRandom(0, apellidos.length - 1)];
		Persona persona = new Persona(uuid, nombre, apellidoPaterno, apellidoMaterno);
		return persona;
	}

	public void testMongo() {

//		personaRepository.deleteAll();

		// save a couple of personas
		personaRepository.save(createRandomPersona());

		// fetch all personas
		logger.info("Personas found with findAll():");
		logger.info("-------------------------------");
		for (Persona persona : personaRepository.findAll()) {
			logger.info("persona: " + persona);
		}

//		// fetch an individual persona
//		logger.info("Persona found with findByFirstName('Alice'):");
//		logger.info("--------------------------------");
//		logger.info("found: " + personaRepository.findByFirstName("Alice"));
//
//		logger.info("Personas found with findByLastName('Smith'):");
//		logger.info("--------------------------------");
//		for (Persona persona : personaRepository.findByLastName("Smith")) {
//			logger.info("persona (2): " + persona);
//		}
	}

}
