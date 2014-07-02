/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.RoleDAO;
import model.dao.UserDAO;
import model.dao.service.RoleDAOService;
import model.dao.service.UserDAOService;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author HAI VU
 */
@ManagedBean(name = "userManager", eager = true)
@RequestScoped
public class UserManagerBean implements Serializable {

    private User user;
    private String searchbyUser;
    private final HttpServletRequest request;
    private final FacesContext fc;
    private final HttpSession session;
    private FacesMessage facesMessage;
    private final Map<String, String> params;

    UserDAOService USER_SERVICE = UserDAO.getInstance();
    RoleDAOService ROLE_SERVICE = RoleDAO.getInstance();

    public UserManagerBean() {
        fc = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        params = fc.getExternalContext().getRequestParameterMap();
        session = request.getSession(true);
        this.user = new User();
    }
    
    public String update1(User user1){
        this.user = user1;
        return "edit_user";
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
        String username = getUser().getUserName();
        return searchbyUser;
    }

    /**
     * @param searchbyUsername the searchbyUsername to set
     */
    public void searchbyUser(String searchbyUser) {
        this.searchbyUser = searchbyUser;
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
     * @return the role
     */
    public List<Role> getListRole() {
        return ROLE_SERVICE.getRoles();

    }

    public String edituser() {
//        String msg = "";
        
        int userID = getUser().getUserID();
        String username = getUser().getUserName();
        String fullname = getUser().getFullName();
        String newpass = getUser().getPwd();
        System.out.println(newpass);
        String address = getUser().getAddress();
        String email = getUser().getEmail();
        String phone = getUser().getPhone();
        int role = getUser().getRole().getRoleID();
        Role roleID = ROLE_SERVICE.getRoleByID(role);
        Date birthday = getUser().getBirthday();
        java.sql.Date date = new java.sql.Date(birthday.getTime());
        int gender = getUser().getGender();
        String idcard = getUser().getIdCard();
        
        User user = new User(userID, username, newpass, fullname, date, gender, idcard, address, email, phone, null, roleID, null, 1);
         if (USER_SERVICE.updateProfile(user)){
//          msg += " Successfully";
             System.out.println(username);
            
             return "users_manager";
            
        } else {
//            msg += " Failed";
             return "edit_user";
        }
//        FacesMessage message = new FacesMessage(msg, "Message!");
//
//        FacesContext.getCurrentInstance()
//                .addMessage(null, message);
//       

    }

    public void adduser() {
        String msg = "";
        String username = getUser().getUserName();
        String fullname = getUser().getFullName();
        String newpass = getUser().getPwd();
        String address = getUser().getAddress();
        String email = getUser().getEmail();
        String phone = getUser().getPhone();
        int role = getUser().getRole().getRoleID();
        Role roleID = ROLE_SERVICE.getRoleByID(role);
        Date birthday = getUser().getBirthday();
        java.sql.Date date = new java.sql.Date(birthday.getTime());
        int gender = getUser().getGender();
        String idcard = getUser().getIdCard();

        User user = new User(1, username, newpass, fullname, birthday, gender, idcard, address, email, phone, null, roleID, null, 1);
        if (USER_SERVICE.createUser(user)) {
            msg += " Successfully";

        } else {
            msg += " Failed";
        }
        FacesMessage message = new FacesMessage(msg, "Message!");

        FacesContext.getCurrentInstance()
                .addMessage(null, message);

    }

    /**
     * @return the DeleteUser
     */
    public void detete(int userID) {
        FacesMessage mess;
        try {
            if (USER_SERVICE.deleteUser(userID)) {
                mess = new FacesMessage("Success!");
            } else {
                mess = new FacesMessage("fail!");
            }
            FacesContext.getCurrentInstance().addMessage("result", mess);
        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
