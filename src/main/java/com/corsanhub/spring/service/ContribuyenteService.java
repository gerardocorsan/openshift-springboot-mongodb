package com.corsanhub.spring.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsanhub.spring.repository.PersonaRepository;

@Service
public class ContribuyenteService {
	private static Logger logger = LoggerFactory.getLogger(ContribuyenteService.class);

	@Autowired
	private Calculadora calculadora;


	@Autowired
	public ContribuyenteService(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public BigDecimal calcula(String marca, Integer modelo) {
		logger.info("Calculating ...");
		BigDecimal value = calculadora.calcula(marca, modelo);

		return value;
	}

}
