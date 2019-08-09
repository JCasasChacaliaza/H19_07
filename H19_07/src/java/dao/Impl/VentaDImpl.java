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
import javax.servlet.http.HttpServletResponse;
import modelo.DetalleVenta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class VentaDImpl extends Conexion implements ICRUD<DetalleVenta> {

    @Override
    public void registrar(DetalleVenta modelo) throws Exception {
        try {
            Conexion();
            String sql = "insert into venta_detalle( ESTVEND , CANTVENTDET ,IDPRO ,IDVENT)VALUES ('A' , ? , ? ,(select DISTINCT ident_current('VENTA')AS ID FROM venta ) )";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getCantVentD());
            ps.setString(2, modelo.getProdVentD());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    @Override
    public void modificar(DetalleVenta modelo) throws Exception {
        try {
            Conexion();
            String sql = "update venta_detalle set cantventdet=?, IDPRO=? WHERE IDTEL=? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getCantVentD());
            ps.setString(2, modelo.getProdVentD());
            ps.setString(3, modelo.getCodVenD());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }

    }

    @Override
    public void eliminar(DetalleVenta modelo) throws Exception {
        try {
            Conexion();
            String sql = "update venta_detalle set ESTVEND='I' WHERE IDTEL=? ";
            PreparedStatement ps = this.getConectar().prepareStatement(sql);
            ps.setString(1, modelo.getCodVenD());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
    }

    public DetalleVenta leerDeta(String detal) throws Exception {
        DetalleVenta leerDeta = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "  select IDTEL,cantventdet, CONCAT(NOMPRO,' ',PREPRO)as Pedido  from venta_detalle det "
                    + " inner join producto pro "
                    + " on pro.idpro = det.idpro "
                    + " where IDTEL = ?";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, detal);
            rs = ps.executeQuery();
            if (rs.next()) {
                leerDeta = new DetalleVenta();
                leerDeta.setCodVenD(rs.getString("IDTEL"));
                leerDeta.setAutoVenta(rs.getString("Pedido"));
                leerDeta.setCantVentD(rs.getString("cantventdet"));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrrar();
        }
        return leerDeta;

    }

    @Override
    public List<DetalleVenta> Listar() throws Exception {
        List<DetalleVenta> listaPed;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select idvent,IDTEL,cantventdet, nompro, prepro , (prepro*cantventdet)as Pedido  from venta_detalle det "
                    + " inner join producto pro "
                    + " on pro.idpro = det.idpro "
                    + " where idvent = (select DISTINCT ident_current('VENTA')AS ID FROM venta ) ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            listaPed = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleVenta detVen = new DetalleVenta();
                detVen.setCodVenD(rs.getString("IDTEL"));
                detVen.setCODVEN(rs.getInt("idvent"));
                detVen.setProdVentD(rs.getString("nompro"));
                detVen.setCantVentD(rs.getString("cantventdet"));
                detVen.setTotal(rs.getString("Pedido"));
                listaPed.add(detVen);
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaPed;
    }

    public String ObtenerCodigoPedido(String asignacion) throws SQLException, Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IDPRO from PRODUCTO WHERE CONCAT(NOMPRO,' ',PREPRO)like ? ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, asignacion);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPRO");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompletePedido(String Consulta) throws SQLException, Exception {
        Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(NOMPRO,' ',PREPRO) AS PEDIDO from PRODUCTO WHERE NOMPRO like ? AND ESTPRO like 'A' ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("PEDIDO"));
            }

            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }
    //METODO REPORTE_PDF_ALUMNO PARA LA DESCARGA

    public void REPORTE_PDF_BOLETA(Map parameters) throws JRException, IOException, Exception {
        this.Conexion();
        System.out.println(parameters.get("CODVEN"));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("vista/Report/BoletaRe/BolVent.jasper"));
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
