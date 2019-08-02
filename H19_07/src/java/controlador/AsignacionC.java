package controlador;

import dao.Impl.AsignacionImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Asignacion;

@Named(value = "asignacionC")
@SessionScoped
public class AsignacionC implements Serializable {

    private Asignacion asignacion;
    private AsignacionImpl dao;
    private List<Asignacion> lisAsig;

    @PostConstruct

    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public AsignacionC() {
        asignacion = new Asignacion();
        dao = new AsignacionImpl();
        lisAsig = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            asignacion.setIdPer(dao.ObtenerCodigoJefe(asignacion.getAutJef()));
            asignacion.setIdVen(dao.ObtenerCodigoVendedor(asignacion.getAutVen()));
            asignacion.setIdSuc(dao.ObtenerCodigoSucursal(asignacion.getAutSuc()));
            dao.registrar(asignacion);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            asignacion.setIdPer(dao.ObtenerCodigoJefe(asignacion.getAutJef()));
            asignacion.setIdVen(dao.ObtenerCodigoVendedor(asignacion.getAutVen()));
            asignacion.setIdSuc(dao.ObtenerCodigoSucursal(asignacion.getAutSuc()));
            dao.modificar(asignacion);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerAsi(String idiAsi) throws Exception {
        try {
            asignacion = dao.LeerAsig(idiAsi);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Asignacion asign) throws Exception {
        try {
         
            dao.eliminar(asign);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextidJef(String query) throws SQLException, Exception {
        AsignacionImpl Conexion = new AsignacionImpl();
        return Conexion.autocompleteJefe(query);
    }

    public List<String> completeTextidVen(String query) throws SQLException, Exception {
        AsignacionImpl Conexion = new AsignacionImpl();
        return Conexion.autocompleteVendedor(query);
    }

    public List<String> completeTextidSuc(String query) throws SQLException, Exception {
        AsignacionImpl Conexion = new AsignacionImpl();
        return Conexion.autocompleteSucursal(query);
    }

    public void listar() throws Exception {
        try {
            lisAsig = dao.Listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        asignacion = new Asignacion();
    }

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    public AsignacionImpl getDao() {
        return dao;
    }

    public void setDao(AsignacionImpl dao) {
        this.dao = dao;
    }

    public List<Asignacion> getLisAsig() {
        return lisAsig;
    }

    public void setLisAsig(List<Asignacion> lisAsig) {
        this.lisAsig = lisAsig;
    }

}
