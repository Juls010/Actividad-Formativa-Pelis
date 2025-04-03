package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class ActorDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/pelis";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	// Agregar el nombre de un actor nuevo
	public static void agregarActor(Scanner scanner) {
		System.out.println("Dime el nombre del actor a agregar: ");
		String nombreActor = scanner.nextLine();
		System.out.println("Dime la nacionalidad del actor: ");
		int nacionalidadActor  = scanner.nextInt();
		String sql = "INSERT INTO actor (nombreActor, nacionalidadActor) VALUES (?,?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nombreActor);
			stmt.setInt(2, nacionalidadActor);
			stmt.executeUpdate();
			System.out.println("Nombre del actor añadido correctamente");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Mostrar la lista de actores
	public static void listarActor() {
		String sql = "SELECT * FROM actor";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			System.out.println("Listado de actores: \n");
			while (rs.next()) {
				int id = rs.getInt("idActor");
				String nombre = rs.getString("nombreActor");
				String nacionalidad = rs.getString("nacionalidadActor");

				System.out.println("ID: " + id);
				System.out.println("Nombre: " + nombre);
				System.out.println("Nacionalidad: " + nacionalidad);
				System.out.println("--------------------");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Actualizar un nombre de la lista
	public static void actualizarActor(Scanner scanner) {
		System.out.println("Ingrese el ID del actor a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese el nombre del actor: ");
		String nombreNuevo = scanner.nextLine();

		String sql = "UPDATE actor SET nombreActor = ? WHERE idActor = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.setString(2, nombreNuevo);
			int rowsUpdate = stmt.executeUpdate();
			if (rowsUpdate > 0) {
				System.out.println("Actor actualizado correctamente");
			} else {
				System.out.println("No se encontró actor con el id: " + id);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Eliminar actor
	public static void eliminarActor(Scanner scanner) {
		System.out.println("Ingrese el ID del actor a eliminar:");
		int id = scanner.nextInt();
		scanner.nextLine();

		String deleteInter = "DELETE FROM interpretacion WHERE idActor = ? ";
		String deleteActor = "DELETE FROM actor WHERE idActor = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmtInterpre = conn.prepareStatement(deleteInter);
				PreparedStatement stmtActor = conn.prepareStatement(deleteActor)) {

			stmtInterpre.setInt(1, id);
			stmtInterpre.executeUpdate();

			stmtActor.setInt(1, id);
			int rowDeleted = stmtActor.executeUpdate();
			if (rowDeleted > 0) {
				System.out.println("El actor se ha eliminado correctamente");
			} else {
				System.out.println("No se ha encontrado ningun actor con ID: " + id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
