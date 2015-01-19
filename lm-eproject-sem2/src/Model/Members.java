/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import SysController.MessageHandle;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator PC
 */
public class Members {
    public String Mem_Id;
    public String Mem_FirstName;
    public String Mem_LastName;   
    public String Mem_No;
    public String Mem_Phone;
    public String Mem_Address;
    public String Mem_Email;
    public boolean Mem_Status;
    public String Mem_CreateDate;
    public String Mem_ImageFile;
    public boolean Mem_isDelete;
    
    
    
    public Members(){
    }
     
    //trunghth
    public static Members getIRCountInformation(String mem_No){
        Members mem = null;
        ResultSet rs = null;
        try {
            rs = SqlHelper.getResultSet("Members_GetIRCountInformation", mem_No);
            if(rs.next()){
                mem = new Members();
                mem.Mem_Address = rs.getString("Mem_Address");
                mem.Mem_CreateDate = rs.getString("Mem_CreateDate");
                mem.Mem_Email = rs.getString("Mem_Email");
                mem.Mem_FirstName = rs.getString("Mem_FirstName");
                mem.Mem_Id = rs.getString("Mem_Id");
                mem.Mem_ImageFile = rs.getString("Mem_ImageFile");
                mem.Mem_LastName = rs.getString("Mem_LastName");
                mem.Mem_No = rs.getString("Mem_No");
                mem.Mem_Phone = rs.getString("Mem_Phone");
                mem.Mem_Status = rs.getBoolean("Mem_Status");                
            }
        } catch (Exception e) {
            SqlHelper.closeConnection(rs);
            mem = null;
        }
        return mem;        
    }
    
    public static Members Members_getMemberByMemberId(String memid){
        Members mem = null;
        ResultSet rs = null;
        try{
            rs = SqlHelper.getResultSet("Members_getMemberByMemberId", memid);
            if(rs.next()){
                mem = new Members();
                mem.Mem_Address = rs.getString("Mem_Address");
                mem.Mem_CreateDate = rs.getString("Mem_CreateDate");
                mem.Mem_Email = rs.getString("Mem_Email");
                mem.Mem_FirstName = rs.getString("Mem_FirstName");
                mem.Mem_Id = rs.getString("Mem_Id");
                mem.Mem_ImageFile = rs.getString("Mem_ImageFile");
                mem.Mem_LastName = rs.getString("Mem_LastName");
                mem.Mem_No = rs.getString("Mem_No");
                mem.Mem_Phone = rs.getString("Mem_Phone");
                mem.Mem_Status = rs.getBoolean("Mem_Status"); 
            }
        } catch (Exception e) {
            SqlHelper.closeConnection(rs);
            mem = null;
        }
        return mem;
    }
    
    public static int Members_Insert(Members obj){
         return SqlHelper.executeNonQuery("Members_Insert"
                 ,obj.Mem_FirstName
                 ,obj.Mem_LastName
                 ,obj.Mem_Phone
                 ,obj.Mem_Address
                 ,obj.Mem_Email
                 ,obj.Mem_ImageFile);
    }
    
    public static int Members_Update(Members obj,String memid){
         return SqlHelper.executeNonQuery("Members_Update"
                 ,memid
                 ,obj.Mem_FirstName
                 ,obj.Mem_LastName
                 ,obj.Mem_Phone
                 ,obj.Mem_Address
                 ,obj.Mem_Email
                 ,obj.Mem_ImageFile
                 ,obj.Mem_Status);
    }
    
    public static DefaultTableModel Mems_getMemberList(){
        DefaultTableModel tbl = SqlHelper.getDefaultTableModel("Mems_getMemberList");
        return tbl;
    }
}
