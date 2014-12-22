/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoTestFunction;


import Helpers.SqlHelper;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator PC
 */
public class User {
    public String username;
    public String password;
    public String fullname;
    public int age;
    public String mail;
    private static String sqlFindByID = "select * from Account where username = ?";
   private static String sqlUpdateAccount = "update Account set password = ?,fullname = ?,age =?,email = ? where username = ?";
   private static String sqlDeleteAccount = "delete Account where username = ?"; 
   private static String sqlInsertAccount = "insert into Account values (?,?,?,?,?)";
    public User(String username, String password, String fullname, int age, String mail) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
        this.mail = mail;
    }
    public static int insertAccount(User obj){
       return SqlHelper.executeNonQuery(sqlInsertAccount,obj.username ,obj.password
                            , obj.fullname,obj.age,obj.mail);
        
    }
    public static int updateAccount(User obj){
       return SqlHelper.executeNonQuery(sqlUpdateAccount,obj.password
                            , obj.fullname,obj.age,obj.mail,obj.username);
        
    }
    public static int deleteAccount(String username){
       return SqlHelper.executeNonQuery(sqlDeleteAccount,username);
        
    }
    public static User getAccountById(String username){
        
        User obj = null;
        ResultSet rs = null;
        try {            
            rs = SqlHelper.getResultSet("getAccountById", username);
            if(rs.next()){
                obj = new User(rs.getString(1)
                                    , rs.getString(2)
                                    , rs.getString(3)
                                    , rs.getInt(4)
                                    , rs.getString(5));
            }
            return obj;
        } catch (Exception e) {            
            return null;
        }finally{
            SqlHelper.closeConnection(rs);
        }
    }
    
    public static DefaultComboBoxModel getListComboBox(){
        DefaultComboBoxModel cbm = SqlHelper.getDefaultComboBoxModel("getListAccount");
        return cbm;
    }
    public static DefaultTableModel getListTable(){
        DefaultTableModel tbm = SqlHelper.getDefaultTableModel("getListAccount");
        return tbm;
    }
}
