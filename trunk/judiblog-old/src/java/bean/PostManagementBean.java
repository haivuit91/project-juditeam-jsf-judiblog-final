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
public class PostManagementBean {

    /**
     * Creates a new instance of PostManagementBean
     */
    private final PostDAOService postService = PostDAO.getInstance();
    private final FacesContext facesContext;
    private Post post;
    public PostManagementBean() {
        this.facesContext = FacesContext.getCurrentInstance();
    }
    
    public String preEdit(Post post){
        System.out.println(post.getTitle());
        this.post = post;
        return "new-post.jsf";
    }
    public void detete(int postID) {
        FacesMessage mess;
        try {
            if (postService.deletePost(postID)) {
                mess = new FacesMessage("Success!");
            } else {
               mess = new FacesMessage("fail!");
            }
             facesContext.addMessage("result", mess);
        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Post> getListPost() {
        try {
            List<Post> listPost = postService.getListPost();
            return listPost;
        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
}
