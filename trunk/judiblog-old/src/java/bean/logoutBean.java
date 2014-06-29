/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Welcomes
 */
@ManagedBean(name = "logoutBean")
@SessionScoped
public class logoutBean {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public logoutBean() {
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.jsf";

    }
}
