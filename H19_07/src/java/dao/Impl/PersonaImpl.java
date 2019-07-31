package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaImpl extends Conexion implements ICRUD<Persona> {

    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "insert into MAESTRA.PERSONA (NOMPER, APEPER, DNIPER, SEXPER, CELPER, TIPPER, USUPER, PASSPER, ESTPER) "
                    + "VALUES (?,?,?,?,?,?,?,?,'A')";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomPer());
            ps.setString(2, modelo.getApePer());
            ps.setString(3, modelo.getDniPer());
            ps.setString(4, modelo.getSexPer());
            ps.setString(5, modelo.getCelPer());
            ps.setString(6, modelo.getTipPer());
            ps.setString(7, modelo.getUsuPer());
            ps.setString(8, modelo.getPasPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(Persona modelo) throws Exception {
        try {
            Conexion();
            String sql = "UPDATE MAESTRA.PERSONA SET NOMPER =?, APEPER=?, DNIPER=?, SEXPER=?, CELPER=?, TIPPER=?, USUPER=?, PASSPER =? WHERE IDPER=?";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getNomPer());
            ps.setString(2, modelo.getApePer());
            ps.setString(3, modelo.getDniPer());
            ps.setString(4, modelo.getSexPer());
            ps.setString(5, modelo.getCelPer());
            ps.setString(6, modelo.getTipPer());
            ps.setString(7, modelo.getUsuPer());
            ps.setString(8, modelo.getPasPer());
            ps.setString(9, modelo.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            Conexion();
            String sql = "UPDATE MAESTRA.PERSONA SET ESTPER='I' WHERE IDPER=?";
            PreparedStatement ps = getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getIdPer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public List<Persona> Listar() throws Exception {
        List<Persona> lisPer;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "select * from maestra.persona WHERE ESTPER='A'";            
            PreparedStatement ps = this.getConectar().prepareCall(sql);           
            lisPer = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona pers = new Persona();
                pers.setIdPer(rs.getString("IDPER"));
                pers.setNomPer(rs.getString("NOMPER"));
                pers.setApePer(rs.getString("APEPER"));
                pers.setDniPer(rs.getString("DNIPER"));
                pers.setCelPer(rs.getString("CELPER"));                
                pers.setSexPer(rs.getString("SEXPER"));
                pers.setPasPer(rs.getString("PASSPER"));
                pers.setUsuPer(rs.getString("USUPER"));
                pers.setTipPer(rs.getString("TIPPER"));
                pers.setEstPer(rs.getString("ESTPER"));
                lisPer.add(pers);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lisPer;
    }

}
