package controlador;

import dao.Impl.ProductoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import modelo.Producto;

@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    private Producto productos;
    private ProductoImpl dao;
    private List<Producto> lisProduc;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public ProductoC() {
        productos = new Producto();
        dao = new ProductoImpl();
        lisProduc = new ArrayList<>();
    }

    public void ingresar() throws Exception {
        try {
            dao.registrar(productos);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(productos);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(productos);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correcto", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            lisProduc = dao.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiar() throws Exception {
        try {
            productos = new Producto();
        } catch (Exception e) {
            throw e;
        }

    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }

    public ProductoImpl getDao() {
        return dao;
    }

    public void setDao(ProductoImpl dao) {
        this.dao = dao;
    }

    public List<Producto> getLisProduc() {
        return lisProduc;
    }

    public void setLisProduc(List<Producto> lisProduc) {
        this.lisProduc = lisProduc;
    }

}
