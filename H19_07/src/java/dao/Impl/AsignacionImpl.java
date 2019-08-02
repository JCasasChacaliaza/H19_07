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
            String sql = "update ASIGNACION set ESTASI ='I' where IDASI=?";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
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
        List<Asignacion> lisAsig;
        ResultSet rs;
        try {
            Conexion();
            String sql = " select IDASI, concat(per.nomper,',', per.apeper)as Jefe , "
                    + " concat(ven.nomper,' ',ven.apeper)as Vendedor, "
                    + " CONCAT(PROSUC,' ',DIRESUC)as Sucursal, "
                    + " gruasig "
                    + " from asignacion asi "
                    + " inner join persona per "
                    + " on per.idper =  asi.idper "
                    + " inner join persona ven "
                    + " on ven.idper = asi.idven "
                    + " inner join sucursal suc "
                    + " on suc.idsuc = asi.idsuc WHERE ESTASI='A' ";
            PreparedStatement ps = getConectar().prepareCall(sql);
            lisAsig = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                Asignacion asig = new Asignacion();
                asig.setIdAsi(rs.getString("IDASI"));
                asig.setAutJef(rs.getString("Jefe"));
                asig.setAutVen(rs.getString("Vendedor"));
                asig.setAutSuc(rs.getString("Sucursal"));
                asig.setGruAsi(rs.getString("gruasig"));
                lisAsig.add(asig);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lisAsig;

    }

    public Asignacion LeerAsig(String idAsig) throws Exception {
        Asignacion asig = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = " select IDASI, CONCAT(per.DNIPER,'||',per.NOMPER,' ', per.APEPER)as Jefe, "
                    + " CONCAT(ven.DNIPER,'||',ven.NOMPER,' ', ven.APEPER)as Vendedor, "
                    + " CONCAT(PROSUC,' ',DIRESUC)as Sucursal, "
                    + " gruasig "
                    + " from asignacion asi "
                    + " inner join persona per "
                    + " on per.idper =  asi.idper "
                    + " inner join persona ven "
                    + " on ven.idper = asi.idven "
                    + " inner join sucursal suc "
                    + " on suc.idsuc = asi.idsuc where IDASI like ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, idAsig);
            rs = ps.executeQuery();
            if (rs.next()) {
                asig = new Asignacion();
                asig.setIdAsi(rs.getString("IDASI"));
                asig.setAutJef(rs.getString("Jefe"));
                asig.setAutVen(rs.getString("Vendedor"));
                asig.setAutSuc(rs.getString("Sucursal"));
                asig.setGruAsi(rs.getString("gruasig"));
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            cerrrar();
        }
        return asig;

    }

    public String ObtenerCodigoJefe(String asignacion) throws SQLException, Exception {
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

    public List<String> autocompleteJefe(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(DNIPER,'||',NOMPER,' ', APEPER)AS Jefe from persona where DNIPER LIKE ? AND ESTPER ='A' AND TIPPER ='2' ";
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

    public String ObtenerCodigoVendedor(String asignacion) throws SQLException, Exception {
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

    public List<String> autocompleteVendedor(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(DNIPER,'||',NOMPER,' ', APEPER)AS vendedor from persona where DNIPER LIKE ? AND ESTPER ='A' AND TIPPER ='3' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("vendedor"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String ObtenerCodigoSucursal(String asignacion) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IDSUC from sucursal WHERE CONCAT(PROSUC,' ',DIRESUC)LIKE ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, asignacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDSUC");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteSucursal(String Consulta) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(PROSUC,' ',DIRESUC)AS SUCURSAL FROM SUCURSAL WHERE DIRESUC LIKE ? and ESTSUC='A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("SUCURSAL"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
