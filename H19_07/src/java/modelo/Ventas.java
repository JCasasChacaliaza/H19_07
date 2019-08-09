package modelo;

public class Ventas {
    
    private int idVent;
    private String nomCli;
    private String dniCli;
    private String fechVen;
    private int codPer;
    
    private String autPer;

    public int getIdVent() {
        return idVent;
    }

    public void setIdVent(int idVent) {
        this.idVent = idVent;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getDniCli() {
        return dniCli;
    }

    public void setDniCli(String dniCli) {
        this.dniCli = dniCli;
    }

    public String getFechVen() {
        return fechVen;
    }

    public void setFechVen(String fechVen) {
        this.fechVen = fechVen;
    }

    public int getCodPer() {
        return codPer;
    }

    public void setCodPer(int codPer) {
        this.codPer = codPer;
    }

    public String getAutPer() {
        return autPer;
    }

    public void setAutPer(String autPer) {
        this.autPer = autPer;
    }
    
    
    
}
