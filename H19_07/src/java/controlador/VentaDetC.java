package controlador;

import dao.Impl.VentaDImpl;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.DetalleVenta;

@ManagedBean
@SessionScoped
public class VentaDetC implements Serializable {

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

    public VentaDetC() {
        detalleVenta = new DetalleVenta();
        daoD = new VentaDImpl();
        listD = new ArrayList<>();
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
            listarD();
            limpiarD();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correcto del Pedido", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarD(DetalleVenta deta) throws Exception {
        try {

            daoD.eliminar(deta);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correcto del Pedido", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void lerrD(String idDeta) throws Exception {
        try {
            detalleVenta = daoD.leerDeta(idDeta);
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

        detalleVenta = new DetalleVenta();

    }

    public List<String> completeTextidVenD(String query) throws SQLException, Exception {
        VentaDImpl demo = new VentaDImpl();
        return demo.autocompletePedido(query);
    }

    //DESCARGAR REPORTE DE VENTA
    public void REPORTE_PDF_VENTA(int CodigoVent) throws Exception {
        System.out.println("aSDASDSA");
        System.out.println(CodigoVent);
        VentaDImpl ventaDImpl = new VentaDImpl();
        try {
            HashMap parameters = new HashMap(); // Libro de parametros  
            parameters.put("CODVEN" , CodigoVent); //Insertamos un parametro
            ventaDImpl.REPORTE_PDF_BOLETA(parameters); //Pido exportar Reporte con los parametros
        } catch (Exception e) {
            throw e;
        }
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

}
