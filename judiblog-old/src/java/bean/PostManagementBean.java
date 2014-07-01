/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.UserDAO;
import model.dao.service.CategoryDAOService;
import model.dao.service.PostDAOService;
import model.dao.service.UserDAOService;
import model.entities.Category;
import model.entities.Post;
import model.entities.User;
import util.Support;

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
    private final CategoryDAOService categoryService = CategoryDAO.getInstance();
    private final UserDAOService userService = UserDAO.getInstance();
    private final FacesContext facesContext;
    private Post post;
    private Part image;
    private String action = "Publish";

    public PostManagementBean() {
        this.post = new Post();
        this.facesContext = FacesContext.getCurrentInstance();
    }

    public String preEditPost(Post post) {
        this.post = post;
        this.action = "Save";
        return "new-post";
    }

    public String submitPost() {
        boolean isSuccess = false;
        try {

//            post.setUser(Support.getCurrentUser());
//            System.out.println("usser:" + post.getUser());
//            System.out.println("cat:" + post.getCategory());
//            System.out.println("title:" + post.getTitle());
//            System.out.println("img:" + post.getImagePath());
//            System.out.println("id:" + post.getPostID());
            if (action.equals("Save")) {
                System.out.println("save:");
                if (post.getImagePath() == null) {
                    post.setImagePath(saveImage());
                }
                if (postService.updatePost(post)) {
                    return "post-manager";
                } else {
                    action = "Save";
                    return null;
                }

            } else {
                post.setImagePath(saveImage());
                post.setPostDate(Support.getCurrentDate());
                if (postService.insertPost(post)) {
                    return "post-manager";
                } else {
                    return null;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    private String saveImage() {
        String fileName = "default.png";
        try {
            if (image == null) {
                return fileName;
            }
            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
            String path = context.getRealPath("").replace("build\\web", "web\\img\\post\\");
            String arr[] = getFilename(image).split("\\.");
            String type = arr[arr.length - 1];
            if (type.equals("PNG") || type.equals("png") || type.equals("jpg") || type.equals("JPG")) {
                fileName = Support.randomCode(8) + "." + type;
                image.write(path + fileName);
                post.setImagePath(fileName);
            }
        } catch (IOException ex) {
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "default.png";
        }
        return fileName;
    }

    public List<Category> getListCategory() {
        return categoryService.getList();
    }

    public List<User> getListUser() {
        return userService.getAllUser();
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
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Post> getListPost() {
        try {
            List<Post> listPost = postService.getListPost();
            return listPost;

        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
