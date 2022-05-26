package com.jacaranda.autor;

import java.util.Objects;

public class Autor {

	private String nombre;
	private String nombreArtistico;
	private String codigo;
	private boolean verificado;

	public Autor(String nombre, String nombreArtistico, boolean verificado) throws AutorException {
		super();
		setNombre(nombre);
		setNombreArtistico(nombreArtistico);
		setVerificado(verificado);
		setCodigo();
	}

	private void setCodigo() {
		this.codigo = this.nombre.substring(0, 3) + this.nombreArtistico.substring(0, 3);
	}

	@Override
	public String toString() {
		String cadena = "No es un autor verificado.";
		if (verificado == true) {
			cadena = "Es un autor verificado.";
		}
		return "AUTOR:" + "\n" + "Nombre: " + nombre + "\n" + "Nombre artistico: " + nombreArtistico + "\n"
				+ "Codigo del autor: " + codigo + "\n" + cadena;
	}

	public void setNombreArtistico(String nombreArtistico) throws AutorException {
		if (nombreArtistico.equals("")) {
			throw new AutorException("El nombre no puede estar vacio");
		}
		if (nombreArtistico.length() < 3) {
			throw new AutorException("El nombre artistico del autor debe tener m�s de 3 caracteres");
		}
		this.nombreArtistico = nombreArtistico;
	}

	private void setNombre(String nombre) throws AutorException {
		if (nombre.equals("")) {
			throw new AutorException("El nombre no puede estar vacio");
		}
		if (nombre.length() < 3) {
			throw new AutorException("El nombre del autor debe tener m�s de 3 caracteres");
		}
		this.nombre = nombre;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(codigo, other.codigo);
	}

}