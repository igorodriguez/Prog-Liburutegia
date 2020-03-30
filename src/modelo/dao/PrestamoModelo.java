package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Conector;
import modelo.bean.Libro;
import modelo.bean.Prestamo;
import modelo.bean.Socio;

public class PrestamoModelo extends Conector {
	public PrestamoModelo() {
		super();
	}
	
	public ArrayList<Prestamo> selectAll(){
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT prestamos.*, libros.titulo, libros.autor, libros.num_pag, socios.nombre, socios.apellido, socios.dni, socios.direccion, socios.poblacion, socios.provincia "
					+ "FROM (prestamos join libros on prestamos.id_libro=libros.id) join socios on prestamos.id_socio=socios.id");
			
			while(rs.next()) {
				Prestamo prestamo= new Prestamo();
				prestamo.setFecha(rs.getDate("fecha"));
				prestamo.setDevuelto(rs.getBoolean("devuelto"));
				
				Libro libro = new Libro();
				libro.setId(rs.getInt("id_libro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNum_pag(rs.getInt("num_pag"));
				prestamo.setLibro(libro);
				
				Socio socio = new Socio();
				socio.setId(rs.getInt("id_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setApellido(rs.getString("apellido"));
				socio.setDni(rs.getString("dni"));
				socio.setDireccion(rs.getString("direccion"));
				socio.setPoblacion(rs.getString("poblacion"));
				socio.setProvincia(rs.getString("provincia"));
				prestamo.setSocio(socio);
				
				prestamos.add(prestamo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
		
	}
	
	public ArrayList<Prestamo> selectConPrestamos(int idLibro){
		ArrayList<Prestamo> prestamos= new ArrayList<Prestamo>();
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement("SELECT prestamos.*, "
					+ "libros.titulo, libros.autor, libros.num_pag, "
					+ "socios.nombre, socios.apellido, socios.dni, socios.direccion, socios.poblacion, socios.provincia "
					+ "FROM (prestamos join libros on prestamos.id_libro=libros.id) "
					+ "join socios on prestamos.id_socio=socios.id "
					+ "where libros.id= ?");
			pst.setInt(1, idLibro);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setFecha(rs.getDate("fecha"));
				prestamo.setDevuelto(rs.getBoolean("devuelto"));
				
				Libro libro = new Libro();
				libro.setId(rs.getInt("id_libro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNum_pag(rs.getInt("num_pag"));
				prestamo.setLibro(libro);
				
				Socio socio = new Socio();
				socio.setId(rs.getInt("id_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setApellido(rs.getString("apellido"));
				socio.setDni(rs.getString("dni"));
				socio.setDireccion(rs.getString("direccion"));
				socio.setPoblacion(rs.getString("poblacion"));
				socio.setProvincia(rs.getString("provincia"));
				prestamo.setSocio(socio);
				
				prestamos.add(prestamo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
		
	}
	
	public ArrayList<Prestamo> selecConPrestamos(int idSocio) {
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
				
				try {
					PreparedStatement pst = super.conexion.prepareStatement("SELECT prestamos.*, "
							+ "libros.titulo, libros.autor, libros.num_pag, "
							+ "socios.nombre, socios.apellido, socios.dni, socios.direccion, socios.poblacion, socios.provincia "
							+ "FROM (prestamos join libros on prestamos.id_libro=libros.id) "
							+ "join socios on prestamos.id_socio=socios.id "
							+ "where socios.id= ?");
					pst.setInt(1, idSocio);
					
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
						Prestamo prestamo = new Prestamo();
						prestamo.setFecha(rs.getDate("fecha"));
						prestamo.setDevuelto(rs.getBoolean("devuelto"));
						
						Libro libro = new Libro();
						libro.setId(rs.getInt("id_libro"));
						libro.setTitulo(rs.getString("titulo"));
						libro.setAutor(rs.getString("autor"));
						libro.setNum_pag(rs.getInt("num_pag"));
						prestamo.setLibro(libro);
						
						Socio socio = new Socio();
						socio.setId(rs.getInt("id_socio"));
						socio.setNombre(rs.getString("nombre"));
						socio.setApellido(rs.getString("apellido"));
						socio.setDni(rs.getString("dni"));
						socio.setDireccion(rs.getString("direccion"));
						socio.setPoblacion(rs.getString("poblacion"));
						socio.setProvincia(rs.getString("provincia"));
						prestamo.setSocio(socio);
						
						prestamos.add(prestamo);
					}
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return prestamos;
			}
	
	
	
}
