/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
import model.entities.User;

/**
 *
 * @author HAI VU
 */
@ManagedBean(name = "userManager", eager = true)
@RequestScoped
public class UserManagerBean implements Serializable {

    private String searchbyUser;
    private final HttpServletRequest request;
    private final FacesContext fc;
    private final HttpSession session;
    private FacesMessage facesMessage;
    private final Map<String, String> params;

    UserDAOService USER_SERVICE = UserDAO.getInstance();

    public UserManagerBean() {
        fc = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        params = fc.getExternalContext().getRequestParameterMap();
        session = request.getSession(true);
    }

    public List<User> getAllUser() {
        List<User> userList = USER_SERVICE.getAllUser();
        return userList;
    }

    public List<User> search() {
        
        List<User> userList = USER_SERVICE.findUserByUserName(searchbyUser);
        return userList;
    }

    /**
     * @return the searchbyUsername
     */
    public String searchbyUser() {
        return searchbyUser;
    }

    /**
     * @param searchbyUsername the searchbyUsername to set
     */
    public void setSearchbyUsername(String searchbyUser) {
        this.searchbyUser = searchbyUser;
    }

    
  
}
