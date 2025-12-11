/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import jpa.entity.Autor;
import jpa.session.AutorFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author teovonsu
 */
@Named(value = "autorController")
@RequestScoped
public class AutorController implements Serializable {
    
    @EJB
    private AutorFacade autorFacade;
    
    private Autor autor;
    private List<Autor> autores;
    
    public AutorController() {
        autor = new Autor();
    }
    
    public Autor getAutor() {
        return autor;
    }
    
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public List<Autor> getAutores() {
        if (autores == null) {
            autores = autorFacade.findAll();
        }
        return autores;
    }
    
    public String prepareCreate() {
        autor = new Autor();
        return "autor_create";
    }
    
    public String create() {
        try {
            autorFacade.create(autor);
            autores = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Autor creado exitosamente", ""));
            return "autor_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el autor", e.getMessage()));
            return null;
        }
    }
    
    public String prepareEdit(Autor autor) {
        this.autor = autor;
        return "autor_edit";
    }
    
    public String update() {
        try {
            autorFacade.edit(autor);
            autores = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Autor actualizado exitosamente", ""));
            return "autor_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el autor", e.getMessage()));
            return null;
        }
    }
    
    public String delete(Autor autor) {
        try {
            autorFacade.remove(autor);
            autores = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Autor eliminado exitosamente", ""));
            return "autor_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el autor", e.getMessage()));
            return null;
        }
    }
    
    public String prepareView(Autor autor) {
        this.autor = autor;
        return "autor_view";
    }
}