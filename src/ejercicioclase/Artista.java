/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioclase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anfeg
 */
public class Artista {

    private static void finAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nombre;
    private String genero;

    public Artista(int id, String nombre, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public static List<Artista> findAll() throws SQLException {
        List<Artista> artistas = null;
        String query = "SELECT * FROM artista";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            String genero = null;

            while (rs.next()) {
                if (artistas == null) {
                    artistas = new ArrayList<Artista>();
                }

                Artista registro = new Artista(id, nombre, genero);
                id = rs.getInt("id");
                registro.setId(id);

                nombre = rs.getString("nombre");
                registro.setNombre(nombre);

                genero = rs.getString("genero");
                registro.setGenero(genero);

                artistas.add(registro);
            }

            for (int i = 0; i < artistas.size(); i++) {
                System.out.println(artistas.get(i).getId() + " " + artistas.get(i).getNombre() + " " + artistas.get(i).getGenero());
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Artitas");
            e.printStackTrace();
        }

        return artistas;
    }

    public static boolean insert(Artista t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = "insert into artista (id,nombre,genero) values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
            preparedStmt.setString(2, t.getNombre());
            preparedStmt.setString(3, t.getGenero());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {
        Artista x = new Artista(7, "Gibran", "Rock");

        insert(x);
        findAll();

    }

}
