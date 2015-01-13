/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator PC
 */
public class Copies {
    public String Cop_Id;
    public String Book_ISBN;
    public boolean Cop_Status;
    public boolean Cop_isDeleted;
    
    
    public Copies(String Cop_Id, String Book_ISBN, boolean Cop_Status) {
        this.Cop_Id = Cop_Id;
        this.Book_ISBN = Book_ISBN;
        this.Cop_Status = Cop_Status;
    }
    
    public static String getLastestIsFree(String ISBN){
        return String.valueOf(SqlHelper.execScalar("Copies_getLastestIsFree", ISBN)) ;        
    }
    
}
