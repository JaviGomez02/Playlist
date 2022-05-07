package com.jacaranda.main;

import java.time.LocalTime;
import java.util.Iterator;

import com.jacaranda.Playlist.Playlist;
import com.jacaranda.Playlist.PlaylistException;
import com.jacaranda.autor.Autor;
import com.jacaranda.autor.AutorException;
import com.jacaranda.reproduccion.Cancion;
import com.jacaranda.reproduccion.Podcast;
import com.jacaranda.reproduccion.Reproduccion;
import com.jacaranda.reproduccion.ReproduccionException;

public class Main {

	public static void main(String[] args) throws AutorException, ReproduccionException, PlaylistException {


		Autor a1=new Autor("Juan", "Juanini", true);
		System.out.println(a1.toString());
		LocalTime.of(0, 3);
		
//		Reproduccion r1=new Cancion("The london",6546547,LocalTime.of(0, 3, 32),a1,9,"Hip Hop"); 
//		Reproduccion r2=new Podcast("Juegos",12334,LocalTime.of(3, 3, 32),a1,"Videojuegos y anime");
//		System.out.println(r1.toString());
//		System.out.println(r2.toString());
		
		Playlist p1=new Playlist("Mi playlist");
		p1.annadirCancion("Cancion1", 0, LocalTime.of(0, 4,23), a1, 6, "rock");
		p1.annadirCancion("Cancion2", 0, LocalTime.of(0, 3,23), a1, 8, "jazz");
		
		System.out.println(p1.mostrarPlaylist());
		
		p1.ordenarPlaylist();
		
		System.out.println(p1.mostrarPlaylist());

	
	
	
	}


}
