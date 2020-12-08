package com.corsanhub.spring.service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Calculadora {
	private static Logger logger = LoggerFactory.getLogger(Calculadora.class);

	public BigDecimal calcula(String marca, Integer modelo) {
		logger.info("Executing calcula. marca: " + marca + ", modelo: " + modelo + "...");
		int intPart = ThreadLocalRandom.current().nextInt(100, 200 + 1);
		int decimalPart = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		String value = intPart + "." + decimalPart;

		BigDecimal result = new BigDecimal(value);
		logger.info("calcula result: " + result);

		return result;

	}

}
