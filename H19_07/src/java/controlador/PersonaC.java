package controlador;

import dao.Impl.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persona;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona persona;
    private PersonaImpl dao;
    private List<Persona> lisper;

    @PostConstruct
    public void init() {
        try {
            listado();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

    public void limpiar() throws Exception {
        try {
            persona = new Persona();
        } catch (Exception e) {
        }

    }

    public PersonaC() {
        try {
            dao = new PersonaImpl();
            persona = new Persona();
            lisper = new ArrayList<>();
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(persona);

            limpiar();
            listado();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completado"));
        } catch (Exception e) {
            throw e;

        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(persona);
            listado();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Completado"));
        } catch (Exception e) {
            throw e;

        }
    }

    public void eliminado() throws Exception {
        try {
            dao.eliminar(persona);
            listado();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Completado"));
        } catch (Exception e) {
            throw e;

        }
    }

    public void listado() throws Exception {
        try {
            lisper = dao.Listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public List<Persona> getLisper() {
        return lisper;
    }

    public void setLisper(List<Persona> lisper) {
        this.lisper = lisper;
    }

}
