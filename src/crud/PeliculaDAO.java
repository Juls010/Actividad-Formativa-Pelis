package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PeliculaDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/pelis";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";


		public static void listarPelicula() {
		String sql = "SELECT idPelicula, tituloPelicula, directorPelicula, generoPelicula FROM pelicula";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			System.out.println("\n----- Lista de Películas -----");

			while (rs.next()) {
				int id = rs.getInt("idPelicula");
				String titulo = rs.getString("tituloPelicula");
				int director = rs.getInt("directorPelicula");
				int genero = rs.getInt("generoPelicula");
			
				System.out.println("ID: " + id);
				System.out.println("Título: " + titulo);
				System.out.println("Director ID: " + director);
				System.out.println("Género ID: " + genero);
				System.out.println("-----------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void agregarPelicula(Scanner scanner) {
		System.out.println("Dame el título de la peli");
		String titulo = scanner.nextLine();
		System.out.println("Dame el ID del director");
		int directorID = scanner.nextInt();
		System.out.println("Dame el ID del genero");
		int generoID = scanner.nextInt();
		System.out.println("Dime el año de la pelicula");
		int anio = scanner.nextInt();
		System.out.println("Dime la duración de la peli en minutos: ");
		int duracion = scanner.nextInt();

		String sql = "INSERT INTO pelicula (tituloPelicula, directorPelicula, generoPelicula, anyoPelicula, duracionPelicula) VALUES (?,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, titulo);
			pstmt.setInt(2, directorID);
			pstmt.setInt(3, generoID);
			pstmt.setInt(4, anio);
			pstmt.setInt(5, duracion);
			pstmt.executeUpdate();
			System.out.println("Pelicula agregadada correctamente");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void actualizarPeliculas(Scanner scanner) {
		System.out.println("Ingrese ID de la pelicula a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese nuevo titulo: ");
		String titulo = scanner.nextLine();

		String sql = "UPDATE pelicula SET tituloPelicula = ? WHERE idPelicula = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, titulo);
			pstmt.setInt(2, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("pelicula actualizada correctamente.");
			} else {
				System.out.println("No se encontró la pelicula con ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void eliminarPeliculas(Scanner scanner) {
		System.out.println("Ingrese ID de la pelicula a eliminar: ");
		int id = scanner.nextInt();

		String deleteInterpretacionesSQL = "DELETE FROM interpretacion WHERE idPelicula = ?";
		String deletePeliculaSQL = "DELETE FROM pelicula WHERE idPelicula = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmtInterpretaciones = conn.prepareStatement(deleteInterpretacionesSQL);
				PreparedStatement pstmtPelicula = conn.prepareStatement(deletePeliculaSQL)) {

			pstmtInterpretaciones.setInt(1, id);
			pstmtInterpretaciones.executeUpdate();

			pstmtPelicula.setInt(1, id);
			int rowsDeleted = pstmtPelicula.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Pelicula eliminada correctamente");
			} else {
				System.out.println("No se encontro la pelicula con ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	

	}
	
}

