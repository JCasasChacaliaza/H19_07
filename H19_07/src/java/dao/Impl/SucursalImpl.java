package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Sucursal;

public class SucursalImpl extends Conexion implements ICRUD<Sucursal> {

    @Override
    public void registrar(Sucursal modelo) throws Exception {
        try {
            Conexion();
            String sql = "insert into SUCURSAL (TELSUC , DIRESUC, PROSUC ,ESTSUC ) VALUES (?,?,?,'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getTelSuc());
            ps.setString(2, modelo.getDirSuc());
            ps.setString(3, modelo.getProSuc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(Sucursal modelo) throws Exception {
        try {
            Conexion();
            String sql = "update  SUCURSAL set TELSUC=? ,DIRESUC=?,PROSUC=? where IDSUC=? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getTelSuc());
            ps.setString(2, modelo.getDirSuc());
            ps.setString(3, modelo.getProSuc());
            ps.setString(4, modelo.getIdSuc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void eliminar(Sucursal modelo) throws Exception {
        try {
            Conexion();
            String sql = "update  SUCURSAL set ESTSUC='I' where IDSUC=? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdSuc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public List<Sucursal> Listar() throws Exception {
        List<Sucursal> lisSuc;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select * from sucursal where estsuc='A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            lisSuc = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                Sucursal suc = new Sucursal();
                suc.setIdSuc(rs.getString("IDSUC"));
                suc.setTelSuc(rs.getString("TELSUC"));
                suc.setDirSuc(rs.getString("DIRESUC"));
                suc.setProSuc(rs.getString("PROSUC"));
                lisSuc.add(suc);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lisSuc;

    }
}
