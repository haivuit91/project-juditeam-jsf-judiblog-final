/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.dao.PostDAO;
import model.dao.service.PostDAOService;
import model.entities.Post;

/**
 *
 * @author Tuanka
 */
@ManagedBean
@RequestScoped
public class PostManagerBean {

    /**
     * Creates a new instance of PostManagerBean
     */
    private final PostDAOService postService = PostDAO.getInstance();
    private final FacesContext facesContext;

    public PostManagerBean() {
        this.facesContext = FacesContext.getCurrentInstance();
    }

    public void detete(int postID) {
        try {
            if (postService.deletePost(postID)) {
                System.out.println("ok");
            } else {
                System.out.println("fail");
            }
        } catch (Exception ex) {
            Logger.getLogger(PostManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void active(int postID, boolean isActive) {
        FacesMessage mes;
        try {
            if (postService.activePost(isActive, postID)) {
                mes = new FacesMessage("Success!");

            } else {
                mes = new FacesMessage("Faile!");
            }
            facesContext.addMessage("result", mes);
        } catch (Exception ex) {
            Logger.getLogger(PostManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Post> getListPost() {
        try {
            List<Post> listPost = postService.getListPost();
            return listPost;
        } catch (Exception ex) {
            Logger.getLogger(PostManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
