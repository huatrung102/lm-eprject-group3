/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import java.sql.ResultSet;

/**
 *
 * @author Thient60330
 */
public class Staffs {
    String Staff_Id ;
    String Staff_FirstName;
    String Staff_LastName;
    String Staff_Login;
    String Staff_Password;
    String Staff_Phone;
    String Staff_Role;
    String Staff_Address;
    String Staff_Email;
    String Staff_Status;
    String Staff_CreateDate;
    
    public static ResultSet getProfile(String Staff_Id)
    {
        ResultSet rs = SqlHelper.getResultSet("getProfile", Staff_Id);
        return rs;
    }
    
    
    
}
