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
    
    private static String test_col[] = {"Member No","Fullname","Email","Phone"};
    
    public Members(){
    }
     public static DefaultTableModel getTestMemberList(){
        DefaultTableModel tblM = new DefaultTableModel(test_col, 0);         
        
        
        tblM.addRow(new Object[]{"M0000001","Hua Tran Huu Trung","trunghth@gmail.com","0934399664"});
        tblM.addRow(new Object[]{"M0000002","Ngo Thanh Dat","trunghth1@gmail.com",""});
        tblM.addRow(new Object[]{"M0000003","Tra Phuc Vinh Uy","trunghth2@gmail.com","0123456789"});
        return tblM;
        
    }
    public static Members getTestMember(){
        Members mem = new Members();
        mem.Mem_Address = "Address ABC";
        mem.Mem_CreateDate = "30/12/2014";
        mem.Mem_Email = "huatrung102@gmail.com";
        mem.Mem_FirstName = "Hua Tran";
        mem.Mem_LastName = "Huu Trung";
        mem.Mem_ImageFile = "/imgMem/anh (11).jpg";
        mem.Mem_Phone = "0934399664";
        mem.Mem_Status = true;
        mem.Mem_isDelete = false;
        return mem;
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
}
