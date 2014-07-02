/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.CategoryDAO;
import model.dao.service.CategoryDAOService;
import model.entities.Category;

/**
 *
 * @author Tuanka
 */
@FacesConverter("converter.CategoryConverter")
public class CategoryConverter implements Converter {

    private final CategoryDAOService categoryService = CategoryDAO.getInstance();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
        //    Category c = categoryService.getCategoryByID(Integer.valueOf(value));
       //      System.out.println(" get object id:" + c.getCatID());
          //  return categoryService.getCategoryByID(Integer.valueOf(value));
            return categoryService.getCategoryByID(Integer.valueOf(value));
        } catch (Exception ex) {
            Logger.getLogger(CategoryConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     //   Category c = ((Category) value);
     //   System.out.println(" get string id:" + c.getCatID());
        return ((Category) value).getCatID()+ "";
    }

}
