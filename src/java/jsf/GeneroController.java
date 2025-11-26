/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import jpa.entity.Genero;
import jpa.session.GeneroFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author teovonsu
 */
@Named(value = "generoController")
@ViewScoped
public class GeneroController implements Serializable {
    
    @EJB
    private GeneroFacade generoFacade;
    
    private Genero genero;
    private List<Genero> generos;
    
    public GeneroController() {
        genero = new Genero();
    }
    
    public Genero getGenero() {
        return genero;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    public List<Genero> getGeneros() {
        if (generos == null) {
            generos = generoFacade.findAll();
        }
        return generos;
    }
    
    public String prepareCreate() {
        genero = new Genero();
        return "genero_create";
    }
    
    public String create() {
        try {
            generoFacade.create(genero);
            generos = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Género creado exitosamente", ""));
            return "genero_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el género", e.getMessage()));
            return null;
        }
    }
    
    public String prepareEdit(Genero genero) {
        this.genero = genero;
        return "genero_edit";
    }
    
    public String update() {
        try {
            generoFacade.edit(genero);
            generos = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Género actualizado exitosamente", ""));
            return "genero_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el género", e.getMessage()));
            return null;
        }
    }
    
    public String delete(Genero genero) {
        try {
            generoFacade.remove(genero);
            generos = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Género eliminado exitosamente", ""));
            return "genero_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el género", e.getMessage()));
            return null;
        }
    }
    
    public String prepareView(Genero genero) {
        this.genero = genero;
        return "genero_view";
    }
}