/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helpers.SqlHelper;
import bussiness.Fine;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Administrator PC
 */
public class IRBooks {
    public Books book;    
    public String IRBook_Id;
    public String Mem_Id;
   
    public String Mem_FullName;
    public String IRBook_DueDate;
    public String IRBook_CreateDate;
    public int IRBook_LateDay;
    public Copies copy;
    
    private static String test_col[] = {"No","ISBN","Title","Category","Copy Id"};
    private static String return_col[] = {"","No","ISBN","Title","Copy No","Issue Date","Due Date","Late Day"};
    private static String test_col2[] = {"No","ISBN","Title","Copy No","Book's Price","Late Date", "Pay"};
    public IRBooks(){
        
    }
    
    public static DefaultTableModel getTestIRBookIssue(Books b){
        DefaultTableModel tblM = new DefaultTableModel(return_col, 0);         
        Object[] obj = {1,1,b.Book_ISBN,b.Book_Title ,b.Cat_Name,"00015"};
        tblM.addRow(obj);        
        return tblM;
    }
    
    public static DefaultTableModel getTestIRBookReturn(Books b){
        DefaultTableModel tblM = new DefaultTableModel(return_col, 0){

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Boolean.class;
            } else {
                return String.class;
            }
        }
    }; 
        int lateday = 0;
        Date date;
       
        SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            date = simpledate.parse("31/12/2014");
            Calendar caldue = Calendar.getInstance();
            caldue.setTime(date);
            
            Calendar calcurrent = Calendar.getInstance();
            calcurrent.setTime(new Date());
            while(!caldue.after(calcurrent)){
                caldue.add(Calendar.DATE, 1);
                lateday++;
            }            
            
           // lateday = tempCurrent - tempDue > 7? tempCurrent - tempDue: 0;
        } catch (Exception e) {
            System.out.println("Error");
        }  
        tblM.addRow(new Object[] {Boolean.FALSE,1,b.Book_ISBN,b.Book_Title ,"00016","13/12/2014","20/12/2014", (lateday-1)});
                
        try {
            
            lateday= 0;
            date = simpledate.parse("06/01/2015");
            Calendar caldue = Calendar.getInstance();
            caldue.setTime(date);
            Calendar calcurrent = Calendar.getInstance();
            calcurrent.setTime(new Date());
            while(!caldue.after(calcurrent)){
                caldue.add(Calendar.DATE, 1);
                lateday++;
            } 
            
            //lateday = tempCurrent - tempDue > 7? tempCurrent - tempDue: 0;
        } catch (Exception e) {
            System.out.println("Error");
        }         
        tblM.addRow(new Object[] {Boolean.FALSE,2,b.Book_ISBN,b.Book_Title ,"00015","30/12/2014","06/01/2015",lateday<=0?0:(lateday-1)});
        
        return tblM;
        
        
    }
    
    public static DefaultTableModel getTestIRBookFine(Books b){
        DefaultTableModel tblM = new DefaultTableModel(test_col2, 0); 
        int lateday = 0;
        Date date;
       
        SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            date = simpledate.parse("31/12/2014");
            Calendar caldue = Calendar.getInstance();
            caldue.setTime(date);
            
            Calendar calcurrent = Calendar.getInstance();
            calcurrent.setTime(new Date());
            while(!caldue.after(calcurrent)){
                caldue.add(Calendar.DATE, 1);
                lateday++;
            }            
            
        } catch (Exception e) {
            System.out.println("Error");
        }  
        tblM.addRow(new Object[] {1,b.Book_ISBN,b.Book_Title ,"00016",b.Book_Price, 
                                    (lateday-1),Fine.calculateFine((lateday-1), b.Book_Price)});
         
        return tblM;
        
        
    }
    
    
    public static DefaultTableModel getListBookByFilter(Books b){
        DefaultTableModel tblM = SqlHelper.getDefaultTableModel("Books_getListBookByFilter"
                                            , b.Book_ISBN
                                            , b.Book_Title
                                            , b.Book_Author);
        return tblM;
    }
    public static DefaultTableModel getListBookNotReturnByMemberNo(String Mem_No){
        DefaultTableModel tblM = SqlHelper.getDefaultTableModel("IRBooks_getListBookNotReturnByMemberNo",Mem_No);
        return tblM;
    }
    public static DefaultTableModel getListBookNotReturn(String Mem_No){
        DefaultTableModel tblM = SqlHelper.getDefaultTableModel("IRBooks_getListBookNotReturn",Mem_No);
        return tblM;
    }
    public static DefaultTableModel getListBookNotReturnTest(String Mem_No){
        DefaultTableModel tblM = SqlHelper.getDefaultTableModelWithCheckbox("IRBooks_getListBookNotReturn",Mem_No);
        return tblM;
    }
    public static DefaultTableModel ListByMemberNo(String Mem_No){
        DefaultTableModel tblM = SqlHelper.getDefaultTableModel("Fines_ListByMemberNo",Mem_No);
        return tblM;
    }
    public static int IssueBook(HashMap Cop_No,String Mem_Id){
        return SqlHelper.executeNonQuery("IRBooks_IssueBook", Cop_No,Mem_Id);
    }
    public static int ReturnBook(HashMap IRDetail){
        return SqlHelper.executeNonQuery("IRBooks_ReturnBook", IRDetail);
    }
    
    public static int PaidFine(HashMap fineId){
        return SqlHelper.executeNonQuery("Fines_Paid", fineId);
    }
}
