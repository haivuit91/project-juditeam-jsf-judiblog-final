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
import javax.servlet.http.HttpServletRequest;
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
public class loginBean {

    private String userName;
    private String pwd;
    private User user;
    private final HttpServletRequest httpServletRequest;
    private  FacesContext facesContext;
    private FacesMessage facesMessage;

    UserDAOService USER_SERVICE = UserDAO.getInstance();

    public loginBean() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public String login() {
        if (USER_SERVICE.checkLogin(userName, pwd)) {
            facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            session.setAttribute("current","1"); // làm tạm cho nó chạy ai làm phần nay sửa lại cho đúng
            
//            User user = USER_SERVICE.getUserByUserName(userName);
//            httpServletRequest.getSession().setAttribute(util.Constants.CURRENT_USER, user);
//            
            return "./info-user.jsf";
        } else {
//            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", null);
//            facesContext.addMessage(null, facesMessage);
//            httpServletRequest.getSession().setAttribute("error", "Login error");
            return "./home.jsf";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the user
     */
    public User getUser() {
        user = USER_SERVICE.getUserByUserName(userName);
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
