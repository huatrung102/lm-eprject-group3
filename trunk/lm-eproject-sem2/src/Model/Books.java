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
 * @author Administrator PC
 */
public class Books {
    public String Book_ISBN;
    public String Book_Title;
    public String Book_Publisher;
    public String Book_Author;
    public double Book_Price;
    public String Book_Content;
    public String Cat_Id;
    public String Book_Language;
    public String Book_ImageFile;
    public String Book_CreateDate;
    public boolean Book_isDeleted;
    public String Cat_Name;
    public int Book_Count;
    public Books(){
    
    }    
    
    public static Books getByISBN(String ISBN){
        Books obj = null;
        ResultSet rs = null;
        try {
            rs = SqlHelper.getResultSet("Books_getByISBN", ISBN);
            if(rs.next()){
                obj = new Books();
                obj.Book_Author = rs.getString("Book_Author");                
                obj.Book_ISBN = rs.getString("Book_ISBN");
                obj.Book_ImageFile = rs.getString("Book_ImageFile");
                obj.Book_Language = rs.getString("Book_Language");                
                obj.Book_Publisher = rs.getString("Book_Publisher");
                obj.Book_Title = rs.getString("Book_Title");
                obj.Cat_Name = rs.getString("Cat_Name");
                obj.Book_Count = rs.getInt("Book_Count");
            }
        } catch (Exception e) {
            SqlHelper.closeConnection(rs);
            obj = null;
        }
        return obj;  
        
    }
    
}
