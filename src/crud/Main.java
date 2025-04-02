package crud;

import java.util.Scanner;

public class Main {
	private static final String URL = "jdbc:mysql://localhost:3306/pelis";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main (String [] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("------Menu------");
			System.out.println("1. Insertar Actor");
			System.out.println("2. Insertar Pelicula");
			System.out.println("3. Insertar Pais");
			System.out.println("4. Listar Actores con Pais");
			System.out.println("5. Actualizar Nombre de Actor");
			System.out.println("6. Eliminar Actor por ID");
			System.out.println("7. Listar Peliculas con  Género y Director");
			System.out.println("8. Eliminar Pais por ID");
			System.out.println("9. Salir");
			
			opcion = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcion) {
			case 1: 
				ActorDAO.agregarActor(scanner);
				break;
			case 2: 
				PeliculaDAO.agregarPelicula(scanner);
				break;
			case 3:
				PaisDAO.agregarPais(scanner);
				break;
			case 4: 
				ActorDAO.listarActor();
				break;
			case 5: 
				ActorDAO.actualizarActor(scanner);
				break;
			case 6: 
				ActorDAO.eliminarActor(scanner);
				break;
			case 7: 
				PeliculaDAO.listarPelicula();
				break;
			case 8:
				PaisDAO.eliminarPais(scanner);
				break;
			case 9: 
				System.out.println("Saliendo...");
				break;
			default: 
				System.out.println("Opcion no valida");
			
			}
			
		}while (opcion != 5);
			scanner.close();
		}

}
