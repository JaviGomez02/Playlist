package com.jacaranda.reproduccion;

import java.time.LocalTime;
import java.util.Objects;

import com.jacaranda.autor.Autor;

public abstract class Reproduccion implements Comparable<Reproduccion> {
	private String titulo;
	private int reproducciones;
	private String codigo;
	private LocalTime duracion;
	private Autor autor;

	protected Reproduccion(String titulo, int reproducciones, LocalTime duracion, Autor autor)
			throws ReproduccionException {
		super();
		setCodigo();
		setTitulo(titulo);
		setReproducciones(reproducciones);
		this.duracion = duracion;
		this.autor = autor;
	}

	private void setTitulo(String titulo) throws ReproduccionException {
		if (titulo.equals("")) {
			throw new ReproduccionException("El titulo no puede estar vacio");
		}
		this.titulo = titulo;
	}

	public int getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(int reproducciones) throws ReproduccionException {
		if (reproducciones < 0) {
			throw new ReproduccionException("Las reproducciones no pueden ser menor de 0");
		}
		this.reproducciones = reproducciones;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public Autor getAutor() {
		return autor;
	}

	private void setCodigo() {

		StringBuilder resultado = new StringBuilder("");
		int numRandom;
		for (int i = 0; i < 5; i++) {
			numRandom = (int) (Math.random() * 65) + 57;
			resultado.append((char) numRandom);
		}
		this.codigo = resultado.toString();

	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reproduccion other = (Reproduccion) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public int compareTo(Reproduccion r) {
		int resultado;
		if (this.duracion.isBefore(r.getDuracion())) {
			resultado = -1;
		} else if (this.duracion.equals(r.getDuracion())) {
			resultado = 0;
		} else {
			resultado = 1;
		}
		return resultado;
	}

}