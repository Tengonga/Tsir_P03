/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import jpa.entity.Editorial;
import jpa.session.EditorialFacade;
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
@Named(value = "editorialController")
@ViewScoped
public class EditorialController implements Serializable {
    
    @EJB
    private EditorialFacade editorialFacade;
    
    private Editorial editorial;
    private List<Editorial> editoriales;
    
    public EditorialController() {
        editorial = new Editorial();
    }
    
    public Editorial getEditorial() {
        return editorial;
    }
    
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    
    public List<Editorial> getEditoriales() {
        if (editoriales == null) {
            editoriales = editorialFacade.findAll();
        }
        return editoriales;
    }
    
    public String prepareCreate() {
        editorial = new Editorial();
        return "editorial_create";
    }
    
    public String create() {
        try {
            editorialFacade.create(editorial);
            editoriales = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Editorial creada exitosamente", ""));
            return "editorial_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la editorial", e.getMessage()));
            return null;
        }
    }
    
    public String prepareEdit(Editorial editorial) {
        this.editorial = editorial;
        return "editorial_edit";
    }
    
    public String update() {
        try {
            editorialFacade.edit(editorial);
            editoriales = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Editorial actualizada exitosamente", ""));
            return "editorial_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar la editorial", e.getMessage()));
            return null;
        }
    }
    
    public String delete(Editorial editorial) {
        try {
            editorialFacade.remove(editorial);
            editoriales = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Editorial eliminada exitosamente", ""));
            return "editorial_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la editorial", e.getMessage()));
            return null;
        }
    }
    
    public String prepareView(Editorial editorial) {
        this.editorial = editorial;
        return "editorial_view";
    }
}
