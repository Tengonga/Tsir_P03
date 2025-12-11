package jsf;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private boolean sesionIniciada = false;
    
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }
    
    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }
    
    public boolean isSesionActiva() {
        return sesionIniciada;
    }
    
    // Método para login
    public String login() {
        this.sesionIniciada = true;
        
        FacesContext.getCurrentInstance().addMessage(null,
            new javax.faces.application.FacesMessage(
                javax.faces.application.FacesMessage.SEVERITY_INFO,
                "Sesión iniciada",
                "Bienvenido, "
            ));
        
        return "index?faces-redirect=true";
    }
    
    // MÉTODO LOGOUT CORREGIDO (SOLO UNO)
    public String logout() {
        this.sesionIniciada = false;
        
        FacesContext.getCurrentInstance().addMessage(null,
            new javax.faces.application.FacesMessage(
                javax.faces.application.FacesMessage.SEVERITY_INFO,
                "Sesión cerrada",
                "Has cerrado sesión correctamente"
            ));
        
        // NO invalidar la sesión aquí, solo limpiar los datos
        return "/index?faces-redirect=true";
    }
    
    // Cálculos de pago CORREGIDOS
    
    public String irAIndex() {
        return "index?faces-redirect=true";
    }
    
}