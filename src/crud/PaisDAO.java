package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PaisDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/pelis";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	// Mostrar lista de paises
	public static void listarPais() {
		String sql = "SELECT * FROM pais";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			System.out.println("Listado de paises: ");
			while (rs.next()) {
				int id = rs.getInt("idPais");
				String nombre = rs.getString("nombrePais");

				System.out.println("ID: " + id);
				System.out.println("Pais: " + nombre);
				System.out.println("-------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Actualizar un nombre de la lista
	public static void actualizarPais(Scanner scanner) {
		System.out.println("Ingrese el ID del pais a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese el nombre del pais: ");
		String nombreNuevo = scanner.nextLine();

		String sql = "UPDATE pais SET nombrePais = ? WHERE idPais = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.setString(2, nombreNuevo);
			int rowsUpdate = stmt.executeUpdate();
			if (rowsUpdate > 0) {
				System.out.println("pais actualizado correctamente");
			} else {
				System.out.println("No se encontró pais con el id: " + id);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Eliminar pais
	public static void eliminarPais(Scanner scanner) {
		System.out.println("Ingrese el ID del pais a eliminar:");
		int id = scanner.nextInt();
		scanner.nextLine();

		String deletePais = "DELETE FROM pais WHERE idPais = ? ";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(deletePais)) {

			stmt.setInt(1, id);
			int rowDeleted = stmt.executeUpdate();
			if (rowDeleted > 0) {
				System.out.println("El pais se ha eliminado correctamente");
			} else {
				System.out.println("No se ha encontrado ningun pais con ID: " + id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Agregar el nombre de un pais nuevo
	public static void agregarPais(Scanner scanner) {
		String nombre = scanner.nextLine();
		String sql = "INSERT INTO pais (nombrePais) VALUES (?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			System.out.println("Nombre del pais añadido correctamente");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
