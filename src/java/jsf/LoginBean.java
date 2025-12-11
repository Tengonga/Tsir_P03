package jsf;

import java.io.Serializable;
import java.security.Principal;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn = false;
    private String userRole;

    public LoginBean() {
    }

    /**
     * Método para login manual (si quieres manejar el login desde JSF)
     * Pero en tu caso usas j_security_check
     */
    public String login() {
        try {
            // Como usas j_security_check, este método puede usarse para lógica adicional
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            
            // Aquí puedes agregar lógica adicional después del login exitoso
            // Por ejemplo, redireccionar según el rol
            Principal principal = request.getUserPrincipal();
            
            if (principal != null) {
                loggedIn = true;
                username = principal.getName();
                
                // Determinar el rol del usuario
                if (request.isUserInRole("adminUser")) {
                    userRole = "admin";
                    return "/admin/dashboard?faces-redirect=true";
                } else if (request.isUserInRole("clientUser")) {
                    userRole = "client";
                    return "/client/dashboard?faces-redirect=true";
                }
            }
            
            // Si llegamos aquí, el login no fue exitoso
            return "/loginError?faces-redirect=true";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/loginError?faces-redirect=true";
        }
    }

    /**
     * Método para logout
     */
    public String logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            
            // Invalidar la sesión
            request.getSession().invalidate();
            
            // Resetear variables
            loggedIn = false;
            username = null;
            userRole = null;
            
            // Redireccionar a login
            return "/login?faces-redirect=true";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/login?faces-redirect=true";
        }
    }

    /**
     * Verificar si el usuario está autenticado
     */
    public boolean isLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getUserPrincipal() != null;
    }

    /**
     * Obtener el nombre del usuario actual
     */
    public String getCurrentUsername() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        return principal != null ? principal.getName() : "";
    }

    /**
     * Verificar si el usuario tiene un rol específico
     */
    public boolean hasRole(String role) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole(role);
    }

    /**
     * Verificar si el usuario es administrador
     */
    public boolean isAdmin() {
        return hasRole("adminUser");
    }

    /**
     * Verificar si el usuario es cliente
     */
    public boolean isClient() {
        return hasRole("clientUser");
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}