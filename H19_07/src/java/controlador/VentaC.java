package controlador;

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
import modelo.Ventas;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private Ventas venta;
    private VentaImpl dao;
    private List<Ventas> lisVent;

    @PostConstruct
    public void init() {
        try {

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void limpiar() throws Exception {
        venta = new Ventas();
    }

    public VentaC() {
        lisVent = new ArrayList<>();
        dao = new VentaImpl();
        venta = new Ventas();
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
            venta.setCodPer(dao.ObtenerCodigoPersonal(venta.getAutPer()));
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

    public List<String> completeTextidVen(String query) throws SQLException, Exception {
        VentaImpl Conexion = new VentaImpl();
        return Conexion.autocompletePersonal(query);
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
