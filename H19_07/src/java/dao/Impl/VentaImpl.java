package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Ventas;

public class VentaImpl extends Conexion implements ICRUD<Ventas> {

    @Override
    public void registrar(Ventas modelo) throws Exception {
        try {
            Conexion();
            String sql = "INSERT INTO VENTA (NOMCLI, DNICLI, FECVENT, IDPER, ESTVEN)VALUES(?,?,getDate(),?,'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomCli());
            ps.setString(2, modelo.getDniCli());
            ps.setString(3, modelo.getCodPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(Ventas modelo) throws Exception {
        try {
            Conexion();
            String sql = "UPDATE VENTA SET NOMCLI =?, DNICLI=?, IDPER =? WHERE IDVENT=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomCli());
            ps.setString(2, modelo.getDniCli());
            ps.setString(3, modelo.getCodPer());
            ps.setString(4, modelo.getCodPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void eliminar(Ventas modelo) throws Exception {
        try {
            Conexion();
            String sql = "UPDATE VENTA SET ESTVEN ='I', WHERE IDVENT=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getCodPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public List<Ventas> Listar() throws Exception {
        return null;

    }

    public String ObtenerCodigoPersonal(String asignacion) throws SQLException, Exception {
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

    public List<String> autocompletePersonal(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(DNIPER,'||',NOMPER,' ', APEPER)AS Vendedor from persona where DNIPER LIKE ? AND ESTPER ='A' AND TIPPER ='3' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("Vendedor"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
