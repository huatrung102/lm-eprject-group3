/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Thient60330
 */
public class Staffs {
    public String Staff_Id ;
    public String Staff_FirstName;
    public String Staff_LastName;
    public String Staff_Login;
    public String Staff_Password;
    public String Staff_Phone;
    public String Staff_Role;
    public String Staff_Address;
    public String Staff_Email;
    public boolean Staff_Status;
    public String Staff_CreateDate;

    
     
    
    public static Staffs getProfile(String Staff_Id){
        Staffs staff = null;
        ResultSet rs = null;
        try {
            rs = SqlHelper.getResultSet("getProfile", Staff_Id);
            if(rs.next()){
                staff = new Staffs();
                staff.Staff_Id = rs.getString("Staff_Id");
                staff.Staff_Address = rs.getString("Staff_Address");
                staff.Staff_CreateDate = rs.getString("Staff_CreateDate");
                staff.Staff_Email = rs.getString("Staff_Email");
                staff.Staff_FirstName = rs.getString("Staff_FirstName");
                staff.Staff_LastName = rs.getString("Staff_LastName");
                staff.Staff_Login = rs.getString("Staff_Login");
                staff.Staff_Password = rs.getString("Staff_Password");
                staff.Staff_Role = rs.getString("Staff_Role");
                staff.Staff_Phone = rs.getString("Staff_Phone");
                staff.Staff_Status = rs.getBoolean("Staff_Status");
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);             
        }
        return staff;
}   
}
    

