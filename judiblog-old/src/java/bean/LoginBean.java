/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
import model.entities.User;

/**
 *
 * @author Khoa
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private User user = new User();
    private String message = "";

    
    public String checkLogin(){
        if(USER_SERVICE.checkLogin(getUser().getUserName(), getUser().getPwd())){
            HttpSession session = util.Support.getSession();
            User u = USER_SERVICE.getUserByUserName(getUser().getUserName());
            session.setAttribute(util.Constants.CURRENT_USER, u);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("curUser", u);
            return "home?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!", "Please try again!"));
            return "login";
        }
    }
    
    public String logout(){
        HttpSession session = util.Support.getSession();
        session.invalidate();
        return "login";
    }
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
