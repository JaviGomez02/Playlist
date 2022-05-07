package com.jacaranda.reproduccion;

import java.time.LocalTime;

import com.jacaranda.autor.Autor;

public class Cancion extends Reproduccion {
	private int puntuacion;
	private String genero;

	public Cancion(String titulo, int reproducciones, LocalTime duracion, Autor autor, int puntuacion, String genero)
			throws ReproduccionException {
		super(titulo, reproducciones, duracion, autor);
		this.puntuacion = puntuacion;
		setGenero(genero);
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) throws ReproduccionException {
		if (puntuacion < 0 || puntuacion > 10) {
			throw new ReproduccionException("");
		}
		this.puntuacion = puntuacion;
	}

	public String getGenero() {
		return genero;
	}

	private void setGenero(String genero) throws ReproduccionException {
		if (genero == null) {
			throw new ReproduccionException("El genero no puede ser nulo");
		}
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "CANCION" + "\n" + "Titulo: " + super.getTitulo() + "\n" + "Autor: " + super.getAutor().toString() + "\n"
				+ "Numero de reproducciones: " + super.getReproducciones() + "\n" + "Duracion: " + super.getDuracion()
				+ "\n" + "Puntuacion: " + this.getPuntuacion() + "/10" + "\n" + "Genero: " + this.getGenero() + "\n"
				+ "Codigo: " + super.getCodigo();
	}

	

}
