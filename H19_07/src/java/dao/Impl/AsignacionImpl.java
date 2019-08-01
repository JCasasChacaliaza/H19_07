package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Asignacion;

public class AsignacionImpl extends Conexion implements ICRUD<Asignacion> {

    @Override
    public void registrar(Asignacion modelo) throws Exception {
        try {
            Conexion();
            String sql = "INSERT INTO ASIGNACION (IDPER, IDSUC, IDVEN, GRUASIG, ESTASI)VALUES (?,?,?,?,'A') ";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdPer());
            ps.setString(2, modelo.getIdSuc());
            ps.setString(3, modelo.getIdVen());
            ps.setString(4, modelo.getGruAsi());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(Asignacion modelo) throws Exception {
        try {
            Conexion();
            String sql = "update ASIGNACION set IDPER=?, IDSUC=?, IDVEN=?, GRUASIG=? where IDASI=?";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdPer());
            ps.setString(2, modelo.getIdSuc());
            ps.setString(3, modelo.getIdVen());
            ps.setString(4, modelo.getGruAsi());
            ps.setString(5, modelo.getIdAsi());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void eliminar(Asignacion modelo) throws Exception {
        try {
            Conexion();
            String sql = "update ASIGNACION set ESTASI = ? where IDASI=?";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdAsi());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public List<Asignacion> Listar() throws Exception {
        return null;

    }

    public String ObtenerCodigoEstudiante(String asignacion) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE CONCAT(DNIPER,'||',NOMPER,' ', APEPER) LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, asignacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPER");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteEstudiante(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(DNIPER,' || '  , APEPER ,' ', NOMPER)AS Jefe from persona where DNIPER LIKE '%' AND ESTPER ='A' AND TIPPER ='2' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("Jefe"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
