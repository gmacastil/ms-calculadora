package com.mauricio.clase;

public class Respuesta {
	private int a;
	private int b;
	private int resultado;
	private String error = "NO";

	public Respuesta() {
	}
	
	public Respuesta(int x, int y, int z) {
		this.a = x;
		this.b = y;
		this.resultado = z;
	}

	public Respuesta(int x, int y, String msj) {
		this.a = x;
		this.b = y;
		this.error = msj;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
