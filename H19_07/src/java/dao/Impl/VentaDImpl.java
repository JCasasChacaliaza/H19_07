package dao.Impl;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleVenta;

public class VentaDImpl extends Conexion implements ICRUD<DetalleVenta> {

    @Override
    public void registrar(DetalleVenta modelo) throws Exception {
        try {
            Conexion();
            String sql = "insert into venta_detalle( ESTVEND ,CANTVENTDET,IDPRO,idvent)VALUES ('A' , ? , ? ,(select DISTINCT ident_current('VENTA')AS ID FROM venta ))";
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
            String sql = "update venta_detalle set CANTVENTDET=?, IDPRO=? WHERE IDTEL=? ";
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

    @Override
    public List<DetalleVenta> Listar() throws Exception {
        List<DetalleVenta> listaPed;
        ResultSet rs;
        try {
            Conexion();
            String sql = "select IDTEL,cantventdet, concat(nompro,' ', prepro)as Producto, (prepro*cantventdet)as Pedido  from venta_detalle det "
                    + " inner join producto pro "
                    + " on pro.idpro = det.idpro "
                    + " where idvent = (select DISTINCT ident_current('VENTA')AS ID FROM venta ) "
                    + " group by IDTEL,cantventdet, nompro,prepro ";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            listaPed = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleVenta detVen = new DetalleVenta();
                detVen.setCodVenD(rs.getString("IDTEL"));
                detVen.setCantVentD(rs.getString("Producto"));
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
            String sql = "select IDPRO from PRODUCTO WHERE CONCAT (NOMPRO, PREPRO)like ? ";
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
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(NOMPRO, PREPRO) AS PEDIDO from PRODUCTO WHERE IDPRO like ? AND ESTPRO like 'A'";
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

}
