package com.mauricio.clase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class ControlCalculadora {
	
	@Value("${app.message.error}")
	private String mensajeError;
		
	@Value("${db.server}")
	private String server;
	
	@Value("${db.user}")
	private String user;
	

	@GetMapping("/suma/{a}/{b}")
	public Respuesta sumar(@PathVariable int a, @PathVariable int b) {
		Respuesta res = new Respuesta();
		res.setA(a);
		res.setB(b);
		res.setResultado(a + b);
		return res;
	}

	@GetMapping("/resta/{a}/{b}")
	public Respuesta restar(@PathVariable int b, @PathVariable int a) {
		Respuesta res = new Respuesta(a, b, a - b);
		return res;
	}

	@GetMapping("/div/{a}/{b}")
	public Respuesta dividir(@PathVariable int a, @PathVariable int b) {
		if(b == 0) {
			return new Respuesta(a, b, mensajeError);
		}
		return new Respuesta(a, b, a / b);
	}
	
	@GetMapping("/getdb")
	public String getInfo() {
		return user + "," + server;
	}
	

}
