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
		this.codigo= this.nombre.substring(0, 3) + this.nombreArtistico.substring(0, 3);
	}

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", nombreArtistico=" + nombreArtistico + ", codigo=" + codigo
				+ ", verificado=" + verificado + "]";
	}

	public void setNombreArtistico(String nombreArtistico) throws AutorException {
		if (nombreArtistico == null) {
			throw new AutorException("El nombre no puede estar vacio");
		}
		this.nombreArtistico = nombreArtistico;
	}

	private void setNombre(String nombre) throws AutorException {
		if (nombre == null) {
			throw new AutorException("El nombre no puede estar vacio");
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