package com.jacaranda.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.jacaranda.Playlist.Playlist;
import com.jacaranda.Playlist.PlaylistException;
import com.jacaranda.autor.Autor;
import com.jacaranda.autor.AutorException;
import com.jacaranda.reproduccion.Cancion;
import com.jacaranda.reproduccion.Reproduccion;
import com.jacaranda.reproduccion.ReproduccionException;

public class Main {

	public static Scanner teclado = new Scanner(System.in);
	public static Set<Autor> autores = new HashSet<>();

	
	public static void main(String[] args) {
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain", "dummy",
					"dummy");
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		borrarTodo(conexion);
		
		
		System.out.println(mostrarBienvenida(leerCadena("Introduce tu nombre: ")));
		Playlist p = null;
		try {
			p = new Playlist(leerCadena("Introduce el titulo de la playlist: "));
		} catch (PlaylistException e1) {
			System.out.println(e1.getMessage());
		}
		annadirPlaylist(conexion, p);
		System.out.println("1. Acceder a la base de datos.\n2. Acceder a la aplicacion\nPulse otra tecla para salir");
		
		int opcionInicial = leerEntero("Introduce la opcion: ");
		if (opcionInicial == 1) {
			boolean salirBucle = false;
			while (!salirBucle) {
				int opcion = leerEntero(
						" 1. Introducir autor\n 2. Ver autores\n 3. Introducir reproduccion\n 4. Ver reproduccion\n 5.Borrar un autor\n Cualquier otra tecla para salir");
				switch (opcion) {
				case 1: {
					annadirAutor(conexion);
					break;
				}
				case 2: {
					consultaAutor(conexion);

					break;
				}
				case 3: {
					annadirCancion(conexion,p);
					break;
				}
				case 4: {
					consultaCancion(conexion);
					break;
				}
				case 5: {
					borrarAutor(conexion);
					break;
				}
				
				default:
					salirBucle = true;
					System.out.println("Saliendo...");
					try {
						conexion.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		} else if (opcionInicial == 2) {

			
			try {
				
				System.out.println(mostrarMenu());
				int opcion;
				boolean salir = false;
				while (!salir) {
					opcion = leerEntero("Introduce la opcion que deseas realizar: ");

					switch (opcion) {
					case 1: {
						String nombre = leerCadena("Introduce el nombre del autor: ");
						String nombreArtistico = leerCadena("Introduce el nombre artistico del autor: ");
						char charVerificado = leerCaracter("�Es un autor verificado? Introduce S/N: ");
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
						String nombre = leerCadena(
								"Introduce el nombre del autor del que quieres ver la informacion: ");
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
						System.out.println("�Cuanto dura la cancion?");
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
						System.out.println("�Cuanto dura el PodCast?");
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
							p.annadirPodcast(titulo, reproducciones, LocalTime.of(horas, minutos, segundos), autor,
									tema);
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
		} else {
			System.out.println("Saliendo...");
		}
	}

	public static String mostrarBienvenida(String nombre) {
		return "Buenas, " + nombre + ". Bienvenido/a a nuestra aplicacion de musica." + "\n"
				+ "�Que te parece si empezamos por crear nuestra Playlist?";
	}

	public static String mostrarMenu() {
		return "\n" + "MENU:" + "\n" + "1. Annadir un nuevo autor." + "\n" + "2. Mostrar autores." + "\n"
				+ "3. Ver informacion de autor." + "\n" + "4. Annadir cancion a nuestra PlayList." + "\n"
				+ "5. Annadir PodCast a nuestra PlayList." + "\n" + "6. Mostrar PlayList." + "\n"
				+ "7. Reproducir aleatorio." + "\n" + "8. Reproducir cancion." + "\n"
				+ "9. Ordenar PlayList(Se ordena por duracion)." + "\n" + "10. Mostrar informacion de la PlayList."
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



	public static void anadirAutoresColeccion(Connection conexion) {
		try {

			// Como hacer una consulta:

			Statement instruccion = conexion.createStatement();
			String query = "SELECT * FROM AUTOR";

			ResultSet resultado = instruccion.executeQuery(query);

			while (resultado.next()) {
				Boolean verificado = false;
				if (resultado.getString("Verificado").equals("1")) {
					verificado = true;
				}

				Autor a = new Autor(resultado.getString("nombre"), resultado.getString("nombreArtistico"), verificado);
				autores.add(a);
			}

			conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void borrarTodo(Connection conexion) {
		Statement instruccion = null;
		try {
			instruccion = conexion.createStatement();
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
		String query1 = "DELETE CANCION";
		String query2 = "DELETE AUTOR";
		String query3 = "DELETE PLAYLIST";
		try {
			instruccion.executeQuery(query1);
			instruccion.executeQuery(query2);
			instruccion.executeQuery(query3);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		
	}
	public static void consultaAutor(Connection conexion) {
		try {

			// Como hacer una consulta:

			Statement instruccion = conexion.createStatement();
			String query = "SELECT * FROM AUTOR";

			ResultSet resultado = instruccion.executeQuery(query);

			while (resultado.next()) {
				String cadena = "No está verificado";
				if (resultado.getString("Verificado").equals("1")) {
					cadena = "Está verificado";
				}
				System.out.println("Nombre del autor: " + resultado.getString("nombre"));
				System.out.println("Nombre artístico del autor: " + resultado.getString("nombreArtistico"));
				System.out.println("Codigo del autor: " + resultado.getString("codigo"));
				System.out.println(cadena);
				System.out.println("******************************************");

			}

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void consultaCancion(Connection conexion) {
		try {

			// Como hacer una consulta:

			Statement instruccion = conexion.createStatement();
			String query = "SELECT * FROM CANCION";

			ResultSet resultado = instruccion.executeQuery(query);

			while (resultado.next()) {
				
				System.out.println("Titulo de la cancion: " + resultado.getString("TITULO"));
				System.out.println("Genero de la cancion: " + resultado.getString("GENERO"));
				System.out.println("Codigo del autor: " + resultado.getString("CODIGO_AUTOR"));
				System.out.println("******************************************");

			}

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void annadirCancion(Connection conexion, Playlist p) { //TERMINAR
		try {
			Statement instruccion = conexion.createStatement();

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
			String titulo=leerCadena("Introduce el titulo de la canción: ");
			int reproducciones=leerEntero("Introduce el numero de reproducciones: ");
			int puntuacion=leerEntero("Introduce la puntuacion del autor: ");
			String genero=leerCadena("Introduce el genero de la cancion: ");
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
			if (encontrado) {
				Cancion c=new Cancion(titulo, reproducciones, LocalTime.of(horas, minutos, segundos), autor,
						puntuacion, genero);

				String query = "insert into CANCION values('" + p.getCodigo() + "','" + autor.getCodigo() + "','" + c.getCodigo() +"','" + c.getTitulo() +"','" + c.getDuracion().getMinute() + "','" + c.getPuntuacion() +"','" + c.getGenero() +"')";
				if (instruccion.execute(query)) {
					System.out.println("Error en la sentencia: " + query);
				} else {
					System.out.println("Registro insertado");
				}
			} else {
				System.out.println("Autor no encontrado, cancion no annadida");
			}
			

			
		} catch (SQLException | ReproduccionException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void annadirAutor(Connection conexion) {
		try {
			Statement instruccion = conexion.createStatement();
			String nombre, nombreArtistico;
			char verificado;
			int verificadoNum = 0;
			boolean verificadoBoolean = false;
			nombre = leerCadena("Introduce el nombre del autor: ");
			nombreArtistico = leerCadena("Introduce el nombre artístico del autor: ");
			verificado = leerCaracter("¿El autor está verificado? Introduce S/N");
			if (verificado == 'S') {
				verificadoNum = 1;
				verificadoBoolean = true;
			}
			Autor a = new Autor(nombre, nombreArtistico, verificadoBoolean);
			autores.add(a);

			String query = "insert into autor values('" + a.getCodigo() + "','" + a.getNombre() + "','"
					+ a.getNombreArtistico() + "','" + verificadoNum + "')";

			if (instruccion.execute(query)) {
				System.out.println("Error en la sentencia: " + query);
			} else {
				System.out.println("Registro insertado");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (AutorException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void annadirPlaylist(Connection conexion, Playlist p) {
		try {
			Statement instruccion = conexion.createStatement();
			String query = "insert into PLAYLIST values('" + p.getCodigo() + "','" + p.getTitulo() + "','"
					+ p.getNumReproducciones() + "','" + p.getDuracion().getMinute() + "')";

			if (instruccion.execute(query)) {
				System.out.println("Error en la sentencia: " + query);
			} else {
				System.out.println("Registro insertado");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void borrarAutor(Connection conexion) {
		Statement instruccion;
		try {
			instruccion = conexion.createStatement();
			

			
			
			String nombre = leerCadena(
					"Introduce el nombre del autor a borrar: ");
			boolean encontrado = false;
			Autor autor = null;
			Iterator<Autor> iterador = autores.iterator();
			while (iterador.hasNext() && !encontrado) {
				Autor a = iterador.next();
				if (a.getNombre().equalsIgnoreCase(nombre)) {
					encontrado = true;
					autor = a;
					autores.remove(autor);
				}
			}
			if (encontrado) {
				String query = "DELETE AUTOR WHERE NOMBRE="+"'"+autor.getNombre()+"'";
				instruccion.executeQuery(query);
			}
			else {
				System.out.println("Autor no encontrado");
			}
			

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}

}
