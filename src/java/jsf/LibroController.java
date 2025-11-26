/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import jpa.entity.Libro;
import jpa.entity.Autor;
import jpa.entity.Genero;
import jpa.entity.Editorial;
import jpa.session.LibroFacade;
import jpa.session.AutorFacade;
import jpa.session.GeneroFacade;
import jpa.session.EditorialFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "libroController")
@ViewScoped
public class LibroController implements Serializable {
    
    @EJB
    private LibroFacade libroFacade;
    @EJB
    private AutorFacade autorFacade;
    @EJB
    private GeneroFacade generoFacade;
    @EJB
    private EditorialFacade editorialFacade;
    
    private Libro libro;
    private List<Libro> libros;
    private Integer[] selectedAutores;
    private Integer[] selectedGeneros;
    
    public LibroController() {
        libro = new Libro();
    }
    
    // Getters y setters
    public Libro getLibro() {
        return libro;
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public List<Libro> getLibros() {
        if (libros == null) {
            libros = libroFacade.findAll();
        }
        return libros;
    }
    
    public Integer[] getSelectedAutores() {
        return selectedAutores;
    }
    
    public void setSelectedAutores(Integer[] selectedAutores) {
        this.selectedAutores = selectedAutores;
    }
    
    public Integer[] getSelectedGeneros() {
        return selectedGeneros;
    }
    
    public void setSelectedGeneros(Integer[] selectedGeneros) {
        this.selectedGeneros = selectedGeneros;
    }
    
    public List<Autor> getAutores() {
        return autorFacade.findAll();
    }
    
    public List<Genero> getGeneros() {
        return generoFacade.findAll();
    }
    
    public List<Editorial> getEditoriales() {
        return editorialFacade.findAll();
    }
    
    // Métodos CRUD
    public String prepareCreate() {
        libro = new Libro();
        selectedAutores = null;
        selectedGeneros = null;
        return "libro_create";
    }
    
    public String create() {
        try {
            // Asociar autores seleccionados
            if (selectedAutores != null && selectedAutores.length > 0) {
                List<Autor> autores = new ArrayList<>();
                for (Integer id : selectedAutores) {
                    autores.add(autorFacade.find(id));
                }
                libro.setAutorCollection(autores);
            }
            
            // Asociar géneros seleccionados
            if (selectedGeneros != null && selectedGeneros.length > 0) {
                List<Genero> generos = new ArrayList<>();
                for (Integer id : selectedGeneros) {
                    generos.add(generoFacade.find(id));
                }
                libro.setGeneroCollection(generos);
            }
            
            libroFacade.create(libro);
            libros = null; // Refrescar lista
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro creado exitosamente", ""));
            return "libro_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el libro", e.getMessage()));
            return null;
        }
    }
    
    public String prepareEdit(Libro libro) {
        this.libro = libro;
        
        // Cargar autores seleccionados
        if (libro.getAutorCollection() != null && !libro.getAutorCollection().isEmpty()) {
            selectedAutores = new Integer[libro.getAutorCollection().size()];
            int i = 0;
            for (Autor a : libro.getAutorCollection()) {
                selectedAutores[i++] = a.getIdAutor();
            }
        }
        
        // Cargar géneros seleccionados
        if (libro.getGeneroCollection() != null && !libro.getGeneroCollection().isEmpty()) {
            selectedGeneros = new Integer[libro.getGeneroCollection().size()];
            int i = 0;
            for (Genero g : libro.getGeneroCollection()) {
                selectedGeneros[i++] = g.getIdGenero();
            }
        }
        
        return "libro_edit";
    }
    
    public String update() {
        try {
            // Actualizar autores
            if (selectedAutores != null && selectedAutores.length > 0) {
                List<Autor> autores = new ArrayList<>();
                for (Integer id : selectedAutores) {
                    autores.add(autorFacade.find(id));
                }
                libro.setAutorCollection(autores);
            } else {
                libro.setAutorCollection(new ArrayList<>());
            }
            
            // Actualizar géneros
            if (selectedGeneros != null && selectedGeneros.length > 0) {
                List<Genero> generos = new ArrayList<>();
                for (Integer id : selectedGeneros) {
                    generos.add(generoFacade.find(id));
                }
                libro.setGeneroCollection(generos);
            } else {
                libro.setGeneroCollection(new ArrayList<>());
            }
            
            libroFacade.edit(libro);
            libros = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro actualizado exitosamente", ""));
            return "libro_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el libro", e.getMessage()));
            return null;
        }
    }
    
    public String delete(Libro libro) {
        try {
            libroFacade.remove(libro);
            libros = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro eliminado exitosamente", ""));
            return "libro_list";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el libro", e.getMessage()));
            return null;
        }
    }
    
    public String prepareView(Libro libro) {
        this.libro = libro;
        return "libro_view";
    }
    
    public String getAutoresAsString(Libro libro) {
        if (libro.getAutorCollection() == null || libro.getAutorCollection().isEmpty()) {
            return "Sin autor";
        }
        StringBuilder sb = new StringBuilder();
        for (Autor autor : libro.getAutorCollection()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(autor.getNombre()).append(" ").append(autor.getApellidos());
        }
        return sb.toString();
    }
    
    public String getGenerosAsString(Libro libro) {
        if (libro.getGeneroCollection() == null || libro.getGeneroCollection().isEmpty()) {
            return "Sin género";
        }
        StringBuilder sb = new StringBuilder();
        for (Genero genero : libro.getGeneroCollection()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(genero.getNombre());
        }
        return sb.toString();
    }
}
