package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Conector;
import modelo.bean.Libro;

public class LibroModelo extends Conector{
	public LibroModelo() {
		super();
	}
	
	public Libro select(int idLibro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where id = ?");
			pst.setInt(1, idLibro);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNum_pag(rs.getInt("num_pag"));
				
				return libro;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean exist(int idLibro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where id = ?");
			pst.setInt(1, idLibro);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public ArrayList<Libro> selectAll(){
		ArrayList <Libro> libros = new ArrayList<Libro>();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from libros");
			while(rs.next()){
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNum_pag(rs.getInt("num_pag"));
				
				libros.add(libro);
			}
			return libros;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return libros;
		}
	
	public ArrayList<Libro> selectAllConPrestamos(){
		ArrayList<Libro> libros = this.selectAll();
		PrestamoModelo prestamoModelo = new PrestamoModelo();
		
		Iterator<Libro> i = libros.iterator();
		while(i.hasNext()) {
			Libro libro = i.next();
			libro.setPrestamos(prestamoModelo.selectConPrestamos(libro.getId()));
		}
		
		return libros;
		
	}
	
	public void update(int idLibro,String titulo) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("update libros set titulo = ? where id = ?");
			pst.setString(1, titulo);
			pst.setInt(2, idLibro);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update2(int idLibro,String autor) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("update libros set autor = ? where id = ?");
			pst.setString(1, autor);
			pst.setInt(2, idLibro);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(int idLibro,int numPaginas) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("update libros set num_pag = ? where id = ?");
			pst.setInt(1, numPaginas);
			pst.setInt(2, idLibro);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Libro libro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("update libros set titulo= ?, autor = ?, num_pag = ? where id = ?");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.setInt(3, libro.getNum_pag());
			pst.setInt(4, libro.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int idLibro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("delete from libros where id = ?");
			pst.setInt(1, idLibro);
			pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Libro libro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("delete from libros where id = ?");
			pst.setInt(1, libro.getId());
			pst.setString(2, libro.getTitulo());
			pst.setString(3, libro.getAutor());
			pst.setInt(4, libro.getNum_pag());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert (Libro libro) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("insert into libros (titulo, autor, num_pag) VALUES (?, ?, ?)");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.setInt(3, libro.getNum_pag());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
