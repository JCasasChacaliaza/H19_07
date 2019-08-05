package controlador;

import dao.Impl.VentaDImpl;
import dao.Impl.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.DetalleVenta;
import modelo.Ventas;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private Ventas venta;
    private VentaImpl dao;

    private VentaDImpl daoD;
    private DetalleVenta detalleVenta;

    private List<DetalleVenta> listD;

    @PostConstruct
    public void init() {
        try {
            listarD();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public VentaC() {
        listD = new ArrayList();
        detalleVenta = new DetalleVenta();
        daoD = new VentaDImpl();
        dao = new VentaImpl();
        venta = new Ventas();
    }

    public void registraD() throws Exception {
        try {
            detalleVenta.setProdVentD(daoD.ObtenerCodigoPedido(detalleVenta.getAutoVenta()));
            daoD.registrar(detalleVenta);
            limpiarD();
            listarD();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correcto del Pedido", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarD() throws Exception {
        try {
            detalleVenta.setProdVentD(daoD.ObtenerCodigoPedido(detalleVenta.getAutoVenta()));
            daoD.modificar(detalleVenta);
            limpiarD();
            listarD();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correcto del Pedido", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarD() throws Exception {
        try {

            daoD.eliminar(detalleVenta);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correcto del Pedido", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarD() throws Exception {
        try {
            listD = daoD.Listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiarD() throws Exception {
        try {
            detalleVenta = new DetalleVenta();
        } catch (Exception e) {
            throw e;
        }

    }

    public void registrar() throws Exception {
        try {
            venta.setCodPer(dao.ObtenerCodigoPersonal(venta.getAutPer()));
            dao.registrar(venta);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(venta);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(venta);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            venta = new Ventas();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextidVen(String query) throws SQLException, Exception {
        VentaImpl Conexion = new VentaImpl();
        return Conexion.autocompletePersonal(query);
    }

    public List<String> completeTextidVenD(String query) throws SQLException, Exception {
        VentaDImpl Conexion = new VentaDImpl();
        return Conexion.autocompletePedido(query);
    }

    public VentaDImpl getDaoD() {
        return daoD;
    }

    public void setDaoD(VentaDImpl daoD) {
        this.daoD = daoD;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public List<DetalleVenta> getListD() {
        return listD;
    }

    public void setListD(List<DetalleVenta> listD) {
        this.listD = listD;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public VentaImpl getDao() {
        return dao;
    }

    public void setDao(VentaImpl dao) {
        this.dao = dao;
    }

}
