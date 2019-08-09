
package modelo;

public class DetalleVenta {

    private String codVenD;
    private String cantVentD;
    private int CODVEN;
    private String prodVentD;
    private String vendVentD;
    
    private String total;
    private String autoVenta;

    public int getCODVEN() {
        return CODVEN;
    }

    public void setCODVEN(int CODVEN) {
        this.CODVEN = CODVEN;
    }

    
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAutoVenta() {
        return autoVenta;
    }

    public void setAutoVenta(String autoVenta) {
        this.autoVenta = autoVenta;
    }

    
    
    public String getCodVenD() {
        return codVenD;
    }

    public void setCodVenD(String codVenD) {
        this.codVenD = codVenD;
    }

    public String getCantVentD() {
        return cantVentD;
    }

    public void setCantVentD(String cantVentD) {
        this.cantVentD = cantVentD;
    }

    public String getProdVentD() {
        return prodVentD;
    }

    public void setProdVentD(String prodVentD) {
        this.prodVentD = prodVentD;
    }

    public String getVendVentD() {
        return vendVentD;
    }

    public void setVendVentD(String vendVentD) {
        this.vendVentD = vendVentD;
    }
    
}
