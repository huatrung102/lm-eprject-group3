/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Helpers.SqlHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.openide.util.Exceptions;

/**
 *
 * @author TraPhucVinh
 */
public class Categories {    
    public String Cat_Id;
    public String Cat_Name;
    public boolean Cat_isDelete;
    public String Cat_Description;
    public Categories(){
    
    }

    
    public static DefaultTableModel Categories_getCategoryListWithBookNumber(){
        DefaultTableModel tbl = SqlHelper.getDefaultTableModel("Categories_getCategoryListWithBookNumber");
        return tbl;
    }
    
    public static int Categories_Insert(Categories obj){
        return SqlHelper.executeNonQuery("Categories_Insert","Lala",0,"asda");
    }
    
    public static int Categories_findCategoryByCateName(String catename){
        ResultSet rs;
        int isExist = 1; //1 là có tồn tại CateName
        rs = SqlHelper.getResultSet("Categories_findCategoryByCateName", catename);
        try {
            if(rs.next()){
                isExist = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return isExist;
    }
    
    public static int Categories_Update(Categories obj){
        return 0;
//       return SqlHelper.executeNonQuery(sqlUpdateAccount,obj.password
//                            , obj.fullname,obj.age,obj.mail,obj.username);
        
    }
    public static int Categories_Lock(String catid){
       return 0;
//       return SqlHelper.executeNonQuery(sqlDeleteAccount,username);
    }
}


