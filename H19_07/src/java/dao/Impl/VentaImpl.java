package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRException;;
import javax.servlet.http.HttpServletResponse;
import modelo.Ventas;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA
    public void REPORTE_PDF_BOLETA(Map parameters) throws JRException, IOException, Exception {
        Conexion();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("vista\\Report\\BoletaRe\\BolVent.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getConectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Boleta de Compra.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

}
