/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
import model.entities.User;

/**
 *
 * @author Tuanka
 */
@FacesConverter("UserConverter")
public class UserConverter  implements Converter{

    private final UserDAOService userServer = UserDAO.getInstance();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return userServer.getUserByID(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((User)value).getUserID()+"";
    }
    
}
