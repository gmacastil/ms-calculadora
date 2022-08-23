package com.mauricio.clase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoMicroApplicationTests {
	
	@Autowired
	private ControlCalculadora control;
		
	@Test
	public void sumar1() {
		Respuesta res = control.sumar(1, 10);
		assertThat(res.getResultado()).isEqualTo(11);
	}

	@Test
	public void didvirCero() {
		Respuesta res = control.dividir(1, 0);
		assertThat(res.getError()).isNotNull();
		assertThat(res.getResultado()).isEqualTo(0);
	}

}
