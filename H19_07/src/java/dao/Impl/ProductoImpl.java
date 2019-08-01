package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class ProductoImpl extends Conexion implements ICRUD<Producto> {

    @Override
    public void registrar(Producto modelo) throws Exception {
        try {
            Conexion();
            String sql = "insert into producto (NOMPRO, PREPRO, CATPRO,  CANTPRO, FECHINGPRO,ESTPRO) VALUES (?,?,?,?,getdate(),'A')";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomProc());
            ps.setString(2, modelo.getPreProc());
            ps.setString(3, modelo.getCatProc());
            ps.setString(4, modelo.getCantProc());            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(Producto modelo) throws Exception {
        try {
            Conexion();
            String sql = "update producto set NOMPRO=?, PREPRO=?, CATPRO=?, CANTPRO=? where IDPRO=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomProc());
            ps.setString(2, modelo.getPreProc());
            ps.setString(3, modelo.getCatProc());
            ps.setString(4, modelo.getCantProc());            
            ps.setString(5, modelo.getIdProc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void eliminar(Producto modelo) throws Exception {
        try {
            Conexion();
            String sql = "update producto set ESTPRO='I' where IDPRO=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdProc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public List<Producto> Listar() throws Exception {
        List<Producto> listProc;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select * from producto where ESTPRO = 'A'";
            PreparedStatement ps = getConectar().prepareCall(sql);
            listProc = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProc(rs.getString("idpro"));
                prod.setNomProc(rs.getString("NOMPRO"));
                prod.setPreProc(rs.getString("PREPRO"));
                prod.setCatProc(rs.getString("CATPRO"));
                prod.setCantProc(rs.getString("CANTPRO"));
                prod.setFechIngProc(rs.getString("FECHINGPRO"));
                listProc.add(prod);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listProc;

    }

}
