/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Helpers.SqlHelper;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    
    public static DefaultTableModel getCategoryListWithBookNumber(){
        DefaultTableModel tbl = SqlHelper.getDefaultTableModel("getCategoryListWithBookNumber");
        return tbl;
    }
}


