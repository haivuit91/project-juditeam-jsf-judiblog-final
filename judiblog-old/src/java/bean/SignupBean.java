/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.dao.RoleDAO;
import model.dao.UserDAO;
import model.dao.service.RoleDAOService;
import model.dao.service.UserDAOService;
import model.entities.Role;
import model.entities.User;

/**
 *
 * @author Khoa
 */
@ManagedBean
@RequestScoped
public class SignupBean {

 public User user = new User();
    private Date birthday;
    UserDAOService USER_SERVICE = UserDAO.getInstance();
    RoleDAOService ROLE_SERVICE = RoleDAO.getInstance();

    public SignupBean() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String createrUser() {

//        Role r = new Role(2, "ds", "ds", 1);
//        user.setRole(r);
        Role role = ROLE_SERVICE.getRoleByID(4);
        user.setRole(role);
        if (USER_SERVICE.createUser(user)) {
            return "home";
        }
        return "registation";
    }
}
