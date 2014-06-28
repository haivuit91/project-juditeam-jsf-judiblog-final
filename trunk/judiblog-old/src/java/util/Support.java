/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Support {

    /**
     * Convert three args day month year to type java.sql.Date
     * @param day 
     * @param month
     * @param year
     * @return Date type: yyyy/MM/dd
     */
    public static Date convertToDate(String day, String month, String year) {
        Date date = null;
        try {
            String bd = year + "/" + month + "/" + day;
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date utilBD = df.parse(bd);
            date = new Date(utilBD.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
