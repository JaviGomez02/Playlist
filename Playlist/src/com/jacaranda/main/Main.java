package com.jacaranda.main;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.jacaranda.Playlist.Playlist;
import com.jacaranda.Playlist.PlaylistException;
import com.jacaranda.autor.Autor;
import com.jacaranda.autor.AutorException;

public class Main {

	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println(mostrarBienvenida(leerCadena("Introduce tu nombre: ")));
		try {
			Playlist p = new Playlist(leerCadena("Introduce el nombre que le darias a tu PlayList: "));
			System.out.println(mostrarMenu());
			int opcion;
			Set<Autor> autores = new HashSet<>();
			boolean salir = false;
			while (!salir) {
				opcion = leerEntero("Introduce la opcion que deseas realizar: ");

				switch (opcion) {
				case 1: {
					String nombre = leerCadena("Introduce el nombre del autor: ");
					String nombreArtistico = leerCadena("Introduce el nombre artistico del autor: ");
					char charVerificado = leerCaracter("¿Es un autor verificado? Introduce S/N: ");
					boolean verificado = false;
					if (charVerificado == 'S') {
						verificado = true;
					}
					autores.add(new Autor(nombre, nombreArtistico, verificado));
					System.out.println("Autor annadido correctamente." + "\n");
					break;
				}
				case 2: {
					StringBuilder cadenaAutores = new StringBuilder("AUTORES: " + "\n");
					Iterator<Autor> iterador = autores.iterator();
					while (iterador.hasNext()) {
						Autor a = iterador.next();
						cadenaAutores.append(a.getNombre() + "\n");

					}
					System.out.println(cadenaAutores.toString());
					break;
				}
				case 3: {
					String nombre = leerCadena("Introduce el nombre del autor del que quieres ver la informacion: ");
					String cadena = "Autor no encontrado.";
					boolean encontrado = false;
					Iterator<Autor> iterador = autores.iterator();
					while (iterador.hasNext() && !encontrado) {
						Autor a = iterador.next();
						if (a.getNombre().equalsIgnoreCase(nombre)) {
							cadena = a.toString();
							encontrado = true;
						}

					}
					System.out.println(cadena);

					break;
				}
				case 4: {
					String titulo = leerCadena("Introduce el titulo de la cancion: ");
					int reproducciones = leerEntero("Introduce el numero de reproducciones: ");
					System.out.println("¿Cuanto dura la cancion?");
					int minutos = leerEntero("Minutos: ");
					int segundos = leerEntero("Segundos: ");
					int horas = 0;
					while (segundos >= 60) {
						segundos -= 60;
						minutos++;
					}
					while (minutos >= 60) {
						minutos -= 60;
						horas++;
					}
					int puntuacion = leerEntero("Introduce la puntuacion de la cancion. (1/10)");
					String genero = leerCadena("Introduce el genero de la cancion: ");
					String nombre = leerCadena(
							"Introduce el nombre del autor(El autor tiene que estar registrado anteriormente): ");
					boolean encontrado = false;
					Autor autor = null;
					Iterator<Autor> iterador = autores.iterator();
					while (iterador.hasNext() && !encontrado) {
						Autor a = iterador.next();
						if (a.getNombre().equalsIgnoreCase(nombre)) {
							encontrado = true;
							autor = a;
						}

					}
					if (encontrado) {
						p.annadirCancion(titulo, reproducciones, LocalTime.of(horas, minutos, segundos), autor,
								puntuacion, genero);
						System.out.println("Cancion annadida");
					} else {
						System.out.println("Autor no encontrado, cancion no annadida");
					}

					break;
				}
				case 5: {
					String titulo = leerCadena("Introduce el titulo del PodCast: ");
					int reproducciones = leerEntero("Introduce el numero de reproducciones: ");
					System.out.println("¿Cuanto dura el PodCast?");
					int minutos = leerEntero("Minutos: ");
					int segundos = leerEntero("Segundos: ");
					int horas = 0;
					while (segundos >= 60) {
						segundos -= 60;
						minutos++;
					}
					while (minutos >= 60) {
						minutos -= 60;
						horas++;
					}
					String tema = leerCadena("Introduce el tema del PodCast: ");
					String nombre = leerCadena(
							"Introduce el nombre del autor(El autor tiene que estar registrado anteriormente): ");
					boolean encontrado = false;
					Autor autor = null;
					Iterator<Autor> iterador = autores.iterator();
					while (iterador.hasNext() && !encontrado) {
						Autor a = iterador.next();
						if (a.getNombre().equalsIgnoreCase(nombre)) {
							encontrado = true;
							autor = a;
						}

					}
					if (encontrado) {
						p.annadirPodcast(titulo, reproducciones, LocalTime.of(horas, minutos, segundos), autor, tema);
						System.out.println("PodCast annadido");
					} else {
						System.out.println("Autor no encontrado, PodCast no annadido.");
					}

					break;

				}
				case 6: {
					System.out.println(p.mostrarPlaylist());
					break;
				}
				case 7: {
					System.out.println(p.reproducirReproduccioAleatoria());
					break;
				}
				case 8: {
					String titulo = leerCadena("Introduce el titulo de la cancion a escuchar: ");
					System.out.println("\n" + p.reproducirReproduccion(titulo));

					break;
				}
				case 9: {
					p.ordenarPlaylist();
					System.out.println("PlayList ordenada correctamente.");
					break;
				}
				case 10: {
					System.out.println(p.toString());
					break;
				}
				case 11: {
					String titulo = leerCadena("Introduce el titulo de la reproduccion para ver su informacion: ");
					System.out.println(p.mostrarReproduccion(titulo));
					break;
				}

				case 12: {
					salir = true;
					System.out.println("Saliendo...");
					break;
				}
				default:
					System.out.println("Introduce una opcion entre 1 y 7");
				}

			}
		} catch (PlaylistException | AutorException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String mostrarBienvenida(String nombre) {
		return "Buenas, " + nombre + ". Bienvenido/a a nuestra aplicacion de musica." + "\n"
				+ "¿Que te parece si empezamos por crear nuestra Playlist?";
	}

	public static String mostrarMenu() {
		return "\n" + "MENU:" + "\n" + "1. Annadir un nuevo autor." + "\n" + "2. Mostrar autores." + "\n"
				+ "3. Ver informacion de autor." + "\n" + "4. Annadir cancion a nuestra PlayList." + "\n"
				+ "5. Annadir PodCast a nuestra PlayList." + "\n" + "6. Mostrar PlayList." + "\n"
				+ "7. Reproducir aleatorio." + "\n" + "8. Reproducir cancion." + "\n"
				+ "9. Ordenar PlayList(Se ordena por duracion)." + "\n" + "10. Mostrar información de la PlayList."
				+ "\n" + "11. Mostrar informacion de reproduccion" + "\n" + "12. Salir de la aplicacion.";
	}

	public static String leerCadena(String texto) {
		System.out.println(texto);
		return teclado.nextLine();
	}

	public static int leerEntero(String texto) {
		System.out.println(texto);
		return Integer.parseInt(teclado.nextLine());
	}

	public static char leerCaracter(String texto) {
		System.out.println(texto);
		return teclado.nextLine().charAt(0);
	}

}
