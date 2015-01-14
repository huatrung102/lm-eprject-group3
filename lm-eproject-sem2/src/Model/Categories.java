package Model;

import Helpers.SqlHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.openide.util.Exceptions;

public class Categories {    
    public String Cat_Id;
    public String Cat_Name;
    public boolean Cat_isDelete;
    public String Cat_Description;
    public Categories(){
    
    }
    
    public Categories(String Cat_Name, boolean Cat_isDelete, String Cat_Description){
        this.Cat_Id = Cat_Id;
        this.Cat_Name = Cat_Name; 
        this.Cat_isDelete = Cat_isDelete;
        this.Cat_Description = Cat_Description;
    }
    
    public static DefaultTableModel Categories_getCategoryListWithBookNumber(){
        DefaultTableModel tbl = SqlHelper.getDefaultTableModel("Categories_getCategoryListWithBookNumber");
        return tbl;
    }
    
    public static int Categories_Insert(Categories obj){
        return SqlHelper.executeNonQuery("Categories_Insert", obj.Cat_Name, obj.Cat_isDelete, obj.Cat_Description);
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
//      return SqlHelper.executeNonQuery(sqlUpdateAccount,obj.password
//                            , obj.fullname,obj.age,obj.mail,obj.username);
        
    }
    public static int Categories_Lock(String catid){
       return 0;
//       return SqlHelper.executeNonQuery(sqlDeleteAccount,username);
    } 
}


