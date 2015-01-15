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
    public static Books getTestBook(){
        Books book = new Books();
        book.Book_Author = "Cornelia Funke";
        book.Book_Content = "One cruel night, Meggie's father reads aloud from a book called \"Inkheart,\" and an evil ruler escapes the boundaries of fiction and lands in their living room. Suddenly, Meggie is smack in the middle of the kind of adventure she has only read about in books";
        book.Book_ISBN = "9780439709101";
        book.Book_ImageFile = "/imgBook/book (6).JPG";
        book.Book_Language = "English";
        book.Book_Price = 80;
        book.Book_Publisher = "Scholastic Paperbacks";
        book.Book_Title = "Inkheart";
        book.Cat_Name = "Fiction";
        return book;
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
