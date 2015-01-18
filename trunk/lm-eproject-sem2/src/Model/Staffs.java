/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import SysController.MessageHandle;
import form.staff.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
    public String Staff_ImageFile;
    public boolean Staff_isDeleted;

    
     
    
    public static Staffs getProfile(String Staff_Id){
        Staffs staff = null;
        ResultSet rs = null;
        try {
            rs = SqlHelper.getResultSet("getProfile", Staff_Id);
            
            if(rs.next()){
                staff = Staffs.getObj(rs);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);             
        }
        return staff;
}  

    private static Staffs getObj(ResultSet rs){
        Staffs staff = null;
        try {
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
                staff.Staff_ImageFile = rs.getString("Staff_ImageFile");
        } catch (Exception e) {
            staff = null;
        }
                return staff;        
    }
    public static Staffs Staffs_Login(String Staff_Login,String Staff_Password){
        Staffs staff = null;
        ResultSet rs = null;
        try {
            rs = SqlHelper.getResultSet("Staffs_Login", Staff_Login,Staff_Password);
            if(rs.next()){
                if(rs.getInt("check") == 1){
                staff = Staffs.getObj(rs);
            } else {
                MessageHandle.showMessage(MessageHandle.Obj_Staff, MessageHandle.Action_login, rs.getInt("check"));
            }
            }
               
        } catch (Exception e) {
            staff = null;
        }
        
        return staff;
    }
    
    public static DefaultTableModel getListStaff(){
        DefaultTableModel tbl = SqlHelper.getDefaultTableModel("Staffs_getListStaff");
        return tbl;
    }
    
    public static int Staffs_Insert(Staffs obj){
        return SqlHelper.executeNonQuery("Staffs_Insert", obj.Staff_FirstName, obj.Staff_LastName, obj.Staff_Login,obj.Staff_Password,
            obj.Staff_Phone,obj.Staff_Role,obj.Staff_Address,obj.Staff_Email,obj.Staff_ImageFile,obj.Staff_isDeleted);
        
    }
    
    public static int Staffs_Update(Staffs obj){
        return SqlHelper.executeNonQuery("Staffs_Update"
                ,obj.Staff_Id
                ,obj.Staff_FirstName
                ,obj.Staff_LastName
                ,obj.Staff_Login
                ,obj.Staff_Password
                ,obj.Staff_Phone
                ,obj.Staff_Role
                ,obj.Staff_Address
                ,obj.Staff_Email
                ,obj.Staff_Status
                ,obj.Staff_ImageFile
                ,obj.Staff_isDeleted);
        
    }
    public static Staffs obj;
    public Staffs(){}
    
    
    public static Staffs Staffs_getStaffbyStaffId(String Staff_Id){
        ResultSet rs;
        
        obj = new Staffs();
        try {
            rs = SqlHelper.getResultSet("Staffs_getStaffbyStaffId", Staff_Id);
            if(rs.next()){
                obj.Staff_Id = Staff_Id;
                obj.Staff_FirstName = rs.getString("Staff_FirstName");
                obj.Staff_LastName = rs.getString("Staff_LastName");
                obj.Staff_Login = rs.getString("Staff_Login");
                obj.Staff_Phone = rs.getString("Staff_Phone");
                obj.Staff_Email = rs.getString("Staff_Email");
                obj.Staff_Address = rs.getString("Staff_Address");
                obj.Staff_Role = rs.getString("Staff_Role");
                obj.Staff_Status = rs.getBoolean("Staff_Status");
                obj.Staff_ImageFile = rs.getString("Staff_ImageFile");
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return obj;
    }
    private static DefaultTableModel Staffs_getStaffListbyStaffId(String staffs_getStaffListbyStaffId, String Staff_Id) {
        return null;
    
    }
    public static DefaultTableModel Staffs_getStaffListbyStaffId(String Staff_Id){
        DefaultTableModel tbl = Model.Staffs.Staffs_getStaffListbyStaffId("Staffs_getStaffListbyStaffId",Staff_Id);
        return tbl;
    }
}   


   
 

