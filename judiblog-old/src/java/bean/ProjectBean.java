/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.dao.ProjectDAO;
import model.dao.ProjectTypeDAO;
import model.dao.ProjectUserDAO;
import model.dao.UserDAO;
import model.dao.service.ProjectDAOService;
import model.dao.service.ProjectTypeDAOService;
import model.dao.service.ProjectUserDAOService;
import model.dao.service.UserDAOService;

/**
 *
 * @author windows 8.1
 */
@ManagedBean
@RequestScoped
public class ProjectBean {

    ProjectDAOService PROJECT_SERVICE = ProjectDAO.getInstance();
    ProjectTypeDAOService TYPE_SERVICE = ProjectTypeDAO.getInstance();
    ProjectUserDAOService PU_SERVICE = ProjectUserDAO.getInstance();
    UserDAOService USER_SERVICE = UserDAO.getInstance();
    /**
     * Creates a new instance of projectBean
     */
    public ProjectBean() {
    }
    
}
