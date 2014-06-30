/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
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
    public SignupBean() {
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public static Date getBirthday() {
//        return date;
//    }
    public Date getBirthday() {
        return birthday;
    }
    
    

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
    
    public String createrUser(){               
        
//        if(USER_SERVICE.createUser(user)){
//            return "home";
//        }
//        return "registy";
        System.out.println(user.getUserID());
        System.out.println(user.getUserName());
        System.out.println(user.getPwd());
        System.out.println(user.getFullName());
        System.out.println(user.getBirthday());
        System.out.println(user.getGender());
        System.out.println(user.getIdCard());
        System.out.println(user.getAddress());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
        System.out.println(user.getActive());
        return null;
    }
}
