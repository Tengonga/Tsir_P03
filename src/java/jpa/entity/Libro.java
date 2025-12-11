/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author teovonsu
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByIdLibro", query = "SELECT l FROM Libro l WHERE l.idLibro = :idLibro")
    , @NamedQuery(name = "Libro.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn")
    , @NamedQuery(name = "Libro.findByAnioPublicacion", query = "SELECT l FROM Libro l WHERE l.anioPublicacion = :anioPublicacion")
    , @NamedQuery(name = "Libro.findByIdioma", query = "SELECT l FROM Libro l WHERE l.idioma = :idioma")
    , @NamedQuery(name = "Libro.findByNumPaginas", query = "SELECT l FROM Libro l WHERE l.numPaginas = :numPaginas")
    , @NamedQuery(name = "Libro.findByPortada", query = "SELECT l FROM Libro l WHERE l.portada = :portada")
    , @NamedQuery(name = "Libro.findByFechaAdquisicion", query = "SELECT l FROM Libro l WHERE l.fechaAdquisicion = :fechaAdquisicion")
    , @NamedQuery(name = "Libro.findByEstadoLectura", query = "SELECT l FROM Libro l WHERE l.estadoLectura = :estadoLectura")
    , @NamedQuery(name = "Libro.findByValoracion", query = "SELECT l FROM Libro l WHERE l.valoracion = :valoracion")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Basic(optional = false)
    ////////@NotNull
    //////@Size(min = 1, max = 200)
    @Column(name = "titulo")
    private String titulo;
    //////@Size(max = 20)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;
    //////@Size(max = 30)
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "num_paginas")
    private Integer numPaginas;
    @Lob
    //////@Size(max = 65535)
    @Column(name = "sinopsis")
    private String sinopsis;
    //////@Size(max = 255)
    @Column(name = "portada")
    private String portada;
    @Column(name = "fecha_adquisicion")
    @Temporal(TemporalType.DATE)
    private Date fechaAdquisicion;
    //////@Size(max = 9)
    @Column(name = "estado_lectura")
    private String estadoLectura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valoracion")
    private BigDecimal valoracion;
    @JoinTable(name = "libro_genero", joinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")})
    @ManyToMany
    private Collection<Genero> generoCollection;
    @JoinTable(name = "libro_autor", joinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")})
    @ManyToMany
    private Collection<Autor> autorCollection;
    @JoinColumn(name = "id_editorial", referencedColumnName = "id_editorial")
    @ManyToOne
    private Editorial idEditorial;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Libro(Integer idLibro, String titulo) {
        this.idLibro = idLibro;
        this.titulo = titulo;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getEstadoLectura() {
        return estadoLectura;
    }

    public void setEstadoLectura(String estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public BigDecimal getValoracion() {
        return valoracion;
    }

    public void setValoracion(BigDecimal valoracion) {
        this.valoracion = valoracion;
    }

    @XmlTransient
    public Collection<Genero> getGeneroCollection() {
        return generoCollection;
    }

    public void setGeneroCollection(Collection<Genero> generoCollection) {
        this.generoCollection = generoCollection;
    }

    @XmlTransient
    public Collection<Autor> getAutorCollection() {
        return autorCollection;
    }

    public void setAutorCollection(Collection<Autor> autorCollection) {
        this.autorCollection = autorCollection;
    }

    public Editorial getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Editorial idEditorial) {
        this.idEditorial = idEditorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entity.Libro[ idLibro=" + idLibro + " ]";
    }
    
}
