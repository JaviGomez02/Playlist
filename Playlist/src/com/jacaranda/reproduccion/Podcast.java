package com.jacaranda.reproduccion;

import java.time.LocalTime;

import com.jacaranda.autor.Autor;

public class Podcast extends Reproduccion {

	private String tema;

	public Podcast(String titulo, int reproducciones, LocalTime duracion, Autor autor, String tema)
			throws ReproduccionException {
		super(titulo, reproducciones, duracion, autor);
		setTema(tema);
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) throws ReproduccionException {
		if (tema == null) {
			throw new ReproduccionException("El tema no puede ser nulo");
		}
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "PODCAST" + "\n" + "Titulo: " + super.getTitulo() + "\n" + "Autor: " + super.getAutor().toString() + "\n"
				+ "Numero de reproducciones: " + super.getReproducciones() + "\n" + "Duracion: " + super.getDuracion()
				+ "\n" + "Tema: " + this.getTema() + "\n" + "Codigo: " + super.getCodigo();
		
	}
}
