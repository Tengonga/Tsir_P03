/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author teovonsu
 */
@Named(value = "aptController")
@RequestScoped
public class AppController {

    /**
     * Creates a new instance of AppController
     */
    public AppController() {
    }
    
}
