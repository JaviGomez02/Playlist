package com.jacaranda.Playlist;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.jacaranda.autor.Autor;
import com.jacaranda.reproduccion.Cancion;
import com.jacaranda.reproduccion.Podcast;
import com.jacaranda.reproduccion.Reproduccion;
import com.jacaranda.reproduccion.ReproduccionException;

public class Playlist {
	private List<Reproduccion> lista;
	private String codigo;
	private String titulo;
	private int numReproducciones;
	private LocalTime duracionTotal;

	public Playlist(String titulo) throws PlaylistException {
		super();
		setTitulo(titulo);
		this.numReproducciones = 0;
		this.lista = new ArrayList<>();
		this.duracionTotal = LocalTime.of(0, 0, 0);
		setCodigo();
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

	public void setTitulo(String titulo) throws PlaylistException {
		if (titulo.equals("")) {
			throw new PlaylistException("El titulo no puede estar vacio");
		}
		this.titulo = titulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public LocalTime getDuracion() {
		return this.duracionTotal;
	}

	public void setDuracion() {
		int horas = 0;
		int minutos = 0;
		int segundos = 0;
		Iterator<Reproduccion> iterador = this.lista.iterator();
		while (iterador.hasNext()) {
			Reproduccion r = iterador.next();
			segundos += r.getDuracion().getSecond();
			minutos += r.getDuracion().getMinute();
			segundos += r.getDuracion().getSecond();
		}
		while (segundos >= 60) {
			segundos -= 60;
			minutos++;
		}
		while (minutos >= 60) {
			minutos -= 60;
			horas++;
		}
		this.duracionTotal = LocalTime.of(horas, minutos, segundos);
	}

	public void annadirCancion(String titulo, int reproducciones, LocalTime duracion, Autor autor, int puntuacion,
			String genero) throws PlaylistException {
		try {
			this.lista.add(new Cancion(titulo, reproducciones, duracion, autor, puntuacion, genero));
			setDuracion();
		} catch (ReproduccionException e) {
			throw new PlaylistException("Introduce datos correctos");
		}
	}

	public void annadirPodcast(String titulo, int reproducciones, LocalTime duracion, Autor autor, String tema)
			throws PlaylistException {
		try {
			this.lista.add(new Podcast(titulo, reproducciones, duracion, autor, tema));
			setDuracion();
		} catch (ReproduccionException e) {
			throw new PlaylistException("Introduce datos correctos");
		}
	}

	public boolean borrarReproduccion(String titulo) {
		boolean salir = false;
		Iterator<Reproduccion> iterador = lista.iterator();
		while (iterador.hasNext() && !salir) {
			Reproduccion r = iterador.next();
			if (r.getTitulo().equals(titulo)) {
				salir = true;
				this.lista.remove(r);
				setDuracion();
			}
		}
		return salir;
	}

	public String mostrarPlaylist() {
		StringBuilder resultado = new StringBuilder("PLAYLIST: " + this.getTitulo() + "\n");
		Iterator<Reproduccion> iterador = lista.iterator();
		int contador = 1;
		while (iterador.hasNext()) {
			Reproduccion r = iterador.next();
			resultado.append(contador + ". " + r.getTitulo() + " " + r.getDuracion() + "\n");
			contador++;
		}
		return resultado.toString();
	}

	@Override
	public String toString() {
		return "PLAYLIST" + "\n" + "Codigo de la PlayList: " + codigo + "\n" + "Nombre de la PlayList: " + titulo + "\n"
				+ "Numero de reproducciones totales: " + numReproducciones + "\n" + "Duracion total de la PlayList: "
				+ duracionTotal.getHour() + " horas, " + duracionTotal.getMinute() + " minutos y "
				+ duracionTotal.getSecond() + " segundos.";
	}

	public String reproducirReproduccion(String titulo) {
		boolean salir = false;
		String resultado = "Cancion no encontrada";
		Iterator<Reproduccion> iterador = lista.iterator();
		while (iterador.hasNext() && !salir) {
			Reproduccion r = iterador.next();
			if (r.getTitulo().equals(titulo)) {
				salir = true;
				resultado = "~~Sonando~~" + "\n" + r.getTitulo() + "\n" + "~~Sonando~~" + "\n";
				this.numReproducciones++;
			}
		}
		return resultado;
	}

	public String reproducirReproduccioAleatoria() {
		String resultado;
		if (this.lista.isEmpty()) {
			resultado = "No hay ninguna canción para reproducir.";
		} else {
			Random random = new Random();
			int num = random.nextInt(this.lista.size());
			resultado = "~~Sonando~~" + "\n" + this.lista.get(num).getTitulo() + "\n" + "~~Sonando~~" + "\n";
			this.numReproducciones++;
		}

		return resultado;
	}

	public String mostrarReproduccion(String titulo) {
		boolean salir = false;
		String resultado = "Cancion no encontrada";
		Iterator<Reproduccion> iterador = lista.iterator();
		while (iterador.hasNext() && !salir) {
			Reproduccion r = iterador.next();
			if (r.getTitulo().equals(titulo)) {
				salir = true;
				resultado = r.toString();
			}
		}
		return resultado;

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
		Playlist other = (Playlist) obj;
		return Objects.equals(codigo, other.codigo);
	}

	public void ordenarPlaylist() {
		Collections.sort(lista);
	}

}
