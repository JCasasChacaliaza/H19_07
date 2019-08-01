package controlador;

import dao.Impl.SucursalImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import modelo.Sucursal;

@Named(value = "sucursalC")
@SessionScoped
public class SucursalC implements Serializable {

    private Sucursal sucursal;
    private SucursalImpl dao;
    private List<Sucursal> lisSuc;

    @PostConstruct
    public void init() {
        try {
            listado();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

    public SucursalC() {
        sucursal = new Sucursal();
        dao = new SucursalImpl();
        lisSuc = new ArrayList();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(sucursal);
            limpiar();
            listado();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(sucursal);
            limpiar();
            listado();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(sucursal);
            limpiar();
            listado();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listado() throws Exception {
        try {
            lisSuc = dao.Listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        sucursal = new Sucursal();
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public SucursalImpl getDao() {
        return dao;
    }

    public void setDao(SucursalImpl dao) {
        this.dao = dao;
    }

    public List<Sucursal> getLisSuc() {
        return lisSuc;
    }

    public void setLisSuc(List<Sucursal> lisSuc) {
        this.lisSuc = lisSuc;
    }

}
