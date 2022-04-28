package com.jacaranda.autor;

import java.util.Objects;

public class Autor {

	private String nombre;
	private String nombreArtistico;
	private String codigo;
	private boolean verificado;
	public Autor(String nombre, String nombreArtistico, boolean verificado) {
		super();
		this.nombre = nombre;
		this.nombreArtistico = nombreArtistico;
		this.verificado = verificado;
		setCodigo();
	}
	
	private void setCodigo() {
		String resultado=this.nombre.substring(0,3)+this.nombreArtistico.substring(0,3);
		this.codigo=resultado;
	}

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", nombreArtistico=" + nombreArtistico + ", codigo=" + codigo
				+ ", verificado=" + verificado + "]";
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
