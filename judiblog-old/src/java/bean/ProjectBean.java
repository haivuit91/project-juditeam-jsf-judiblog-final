/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.dao.ProjectDAO;
import model.dao.ProjectTypeDAO;
import model.dao.ProjectUserDAO;
import model.dao.UserDAO;
import model.dao.service.ProjectDAOService;
import model.dao.service.ProjectTypeDAOService;
import model.dao.service.ProjectUserDAOService;
import model.dao.service.UserDAOService;
import model.entities.Project;
import model.entities.ProjectType;
import model.entities.ProjectUserDetails;
import model.entities.User;

/**
 *
 * @author windows 8.1
 */
@ManagedBean
@RequestScoped
public class ProjectBean {

    private final ProjectDAOService PROJECT_SERVICE = ProjectDAO.getInstance();
    private final ProjectUserDAOService PROJECT_USER_SERVICE = ProjectUserDAO.getInstance();
    private final ProjectTypeDAOService TYPE_SERVICE = ProjectTypeDAO.getInstance();
    private final ProjectUserDAOService PU_SERVICE = ProjectUserDAO.getInstance();
    private final UserDAOService USER_SERVICE = UserDAO.getInstance();

    /**
     * Creates a new instance of projectBean
     */
    public ProjectBean() {
    }

    private Project project = new Project();
    private ProjectType projectType;
    private List<ProjectType> types;
    private List<Project> projects;
    private String typeName;

    public String createProject() {
        String msg = "";
        String projectName = getProject().getProjectName();
        String description = getProject().getDescription();
        Date startDate = getProject().getStartDate();
        java.sql.Date date = new java.sql.Date(startDate.getTime());
        int duration = getProject().getDuration();
        ProjectType type = this.projectType;
        Project p = new Project(1, projectName, description, date, duration, type, 1);
        if (PROJECT_SERVICE.createProject(p)) {
            User user = util.Support.getCurrentUser();
            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!", "Please try again!"));
            } else {
                List<Project> projectList = PROJECT_SERVICE.getProjectByName(projectName);
                Project pro = projectList.get(projectList.size() - 1);
                ProjectUserDetails pud = new ProjectUserDetails(1, user, pro, 1);
                if (PU_SERVICE.createPUD(pud)) {
//                    msg += "Project created by: " + user.getFullName();
                    return "project-manager";
                } else {
//                    msg += "Create creater for project failed. \n";
                    return "add-project";
                }
            }
//            msg += "Post project Successfully";
        } else {
//            msg += "Post project Failed";
        }
        FacesMessage message = new FacesMessage(msg, "Message!");

        FacesContext.getCurrentInstance()
                .addMessage(null, message);
        return "/login";
    }

    public String deleteProject(int projectID) {
        if (PROJECT_USER_SERVICE.deleteUserJoinedProject(projectID) && PROJECT_SERVICE.deleteProject(projectID)) {
//            return "project-manager";
            System.out.println("Thanh cong");
        }
        return null;
    }

    public String updateProject(Project project1) {
        this.project = project1;
        return "update-project";
    }

    public String saveProject() {
        int projectID = getProject().getProjectID();
        String projectName = getProject().getProjectName();
        String description = getProject().getDescription();
        Date startDate = getProject().getStartDate();
        java.sql.Date date = new java.sql.Date(startDate.getTime());
        int duration = getProject().getDuration();
        ProjectType type = this.projectType;
        Project pro = new Project(projectID, projectName, description, date, duration, type, 1);
        if(PROJECT_SERVICE.updateProject(pro)){
            return "project-manager";
        }
        return "update-project";
//        System.out.println("thanh cong");
////        }
//        return null;
    }

    /**
     * @return the projectType
     */
    public ProjectType getProjectType() {
        return projectType;
    }

    /**
     * @param projectType the projectType to set
     */
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    /**
     * @return the types
     */
    public List<ProjectType> getTypes() {
        types = TYPE_SERVICE.getTypes();
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<ProjectType> types) {
        this.types = types;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    public List<Project> getAllProject() {
        List<Project> projectList = PROJECT_SERVICE.getProjects();
        return projectList;
    }
}
