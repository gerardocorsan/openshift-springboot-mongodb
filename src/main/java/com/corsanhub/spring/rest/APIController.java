package com.corsanhub.spring.rest;

import static net.logstash.logback.marker.Markers.append;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.corsanhub.spring.dto.StatusDTO;
import com.corsanhub.spring.service.ContribuyenteService;
import com.corsanhub.spring.service.PersonaService;
import com.corsanhub.spring.util.LogUtils;

@RestController
public class APIController {
	private static Logger logger = LoggerFactory.getLogger(APIController.class);

	@Autowired
	private PersonaService personaService;

	@Autowired
	private ContribuyenteService contribuyenteService;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@RequestMapping(value = "/api/ping", method = RequestMethod.GET)
	public String ping() {
		Marker marker = append("field1", "value1").and(append("field2", "value2"));
		logger.info(marker, "Pinging server ...");
		return "pongX";
	}

	@RequestMapping(value = "/api/health/{key}", method = RequestMethod.GET)
	public ResponseEntity<String> health(@PathVariable(value = "key") String key) {
		StatusDTO status = new StatusDTO(key, "OK");
		logger.info(LogUtils.marker(status), "Verifying health ...");

		String result = "{'action':'verifyHealth', 'key':'" + key + "', 'status':'OK'}";
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/api/sayHi/{name}", method = RequestMethod.GET)
	public ResponseEntity<String> sayHi(@PathVariable(value = "name") String name) {
		logger.info(LogUtils.marker("{'name': '" + name + "'}"), "Saying Hi! ...");

		String result = "Hello there " + name + "!";
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/api/dbTest", method = RequestMethod.GET)
	public ResponseEntity<String> testMongo() {
		logger.info("Testing Mongo DB ...");
		personaService.testMongo();
		ResponseEntity<String> entity = new ResponseEntity<String>("{'result': 'success'}", HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/api/calcula/{marca}/{modelo}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> calcula(@PathVariable(value = "marca") String marca, @PathVariable(value = "modelo") String modeloStr) {
		logger.info("Executing calcula function ...");

		Integer modelo = Integer.valueOf(modeloStr);
		BigDecimal result = contribuyenteService.calcula(marca, modelo);

		ResponseEntity<BigDecimal> entity = new ResponseEntity<BigDecimal>(result, HttpStatus.OK);
		return entity;
	}

//	@RequestMapping(value = "/api/createCode/{code}", method = RequestMethod.POST)
//	public ResponseEntity<BigDecimal> createCode(@PathVariable(value = "code") String code) {
//		logger.info("Executing calcula function ...");
//
//		Integer modelo = Integer.valueOf(code);
//		BigDecimal result = contribuyenteService.calcula(marca, modelo);
//
//		ResponseEntity<BigDecimal> entity = new ResponseEntity<BigDecimal>(result, HttpStatus.OK);
//		return entity;
//	}

	@PostConstruct
	private void postConstruct() {
		logger.info("############# mappings:" + requestMappingHandlerMapping.getHandlerMethods().keySet());
	}

}
