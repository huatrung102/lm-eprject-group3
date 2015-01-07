/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator PC
 */
public class Copies {
    public String Cop_Id;
    public String Book_ISBN;
    public boolean Cop_Status;
    //public boolean Cop_isDeleted;
    private static String test_col[] = {"ISBN","Title"};
    private static String test_ISBN = "900123456789";
    
    public Copies(String Cop_Id, String Book_ISBN, boolean Cop_Status) {
        this.Cop_Id = Cop_Id;
        this.Book_ISBN = Book_ISBN;
        this.Cop_Status = Cop_Status;
    }
    
    public static DefaultTableModel getTestCopyByISBN(){
        DefaultTableModel tblM = new DefaultTableModel(test_col, 0);
        
        for(int i = 0 ; i< 10; i++){            
            tblM.addRow(new Object[] {test_ISBN+i,"Title 00"+i});
        }
        return tblM;
    }
    
    
}
