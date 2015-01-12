/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysController;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrator PC
 */
public class MessageHandle {

    //public static final String writeFileError = "Can not write file";
    //public static final String openFileError = "Can not open file";

    public static final String Obj_Copy = "Copy";
    public static final String Obj_Book = "Book";
    public static final String Obj_Member = "Member";
    public static final String Obj_Staff = "Staff";
    public static final String Obj_Category = "Category";
    public static final String Obj_Fine = "Fine";
    
    
    public static final String Obj_File = "File";    
    public static final String Obj_System = "System";
    
    
    public static final String Action_insert = "Insert";
    public static final String Action_update = "Update";
    public static final String Action_delete = "Delete";
    public static final String Action_login = "Login";
    public static final String Action_issue = "Issue";
    public static final String Action_return = "Return";
    public static final String Action_pay = "Pay";
    
    public static final String Action_open = "Open";
    public static final String Action_save = "Save";
    public static final String Action_pass = "Assign";
    
   
    
    
    
    private static final String result_success = "successfully";
    private static final String result_unsuccess = "unsuccessfully";    
    private static final String result_duplicate = "duplicate";  
    private static final String result_notExist = "not exits";
    private static final String result_notAllowed = "not allowed issue total book more than 5";
    private static final String result_cannotFindCopy = "can not find copy for iussing";
    
    
    private static final String colUnique_loginName = "Login name";
    private static final String colUnique_Email = "Email";
    private static final String colUnique_Member_No = "Member No";
    private static final String colUnique_categoryName = "Category name";
    
    
    private static final String colUnique_nullParameter = "Parameter null";

    public static void showError(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
    
    public static void showMessage(String Obj, String action, int result){
        JOptionPane.showMessageDialog(null, getMessage(Obj,action,result));
    }
    
    public static String getMessage(String Obj, String action, int result) {
        return getMessageByResult(Obj,action,result);
    }
    
    private static String getMessageByResult(String Obj, String action,int result){
        StringBuilder sb = new StringBuilder();
        //insert,/update/lock/login User/Book/... successfull!   
        sb.append(action).append(" ")
           .append(Obj).append(" ");
           
        switch(Obj){
            // <editor-fold defaultstate="collapsed" desc="Obj_Copy">
            case Obj_Copy: 
                switch(action){
                    case Action_insert:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;                   
                    case Action_delete:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                }
            break;
                // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Obj_Book">
            case Obj_Book:     
                switch(action){
                    case Action_issue:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(" ")
                              .append(result_notAllowed).append(" ");                              
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;                            
                        }
                    break;
                    case Action_return:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(" ")
                              .append(result_cannotFindCopy).append(" ");                              
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;                            
                        }
                    break;    
                    case Action_insert:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                    case Action_update:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                    case Action_delete:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                }
            break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Member">
            case Obj_Member:                
                switch(action){
                    case Action_insert:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_Member_No).append(" ")
                              .append(result_duplicate);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            case 3:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_Email).append(" ")
                              .append(result_duplicate);
                            break;
                                
                        }
                    break;
                    case Action_update:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            case 3:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_Email).append(" ")
                              .append(result_duplicate);
                            break;
                        }
                    break;
                    case Action_delete:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                }
            break;
            // </editor-fold>       
            // <editor-fold defaultstate="collapsed" desc="Staff">
            case Obj_Staff:                
                switch(action){
                    case Action_login:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_loginName).append(" ")
                              .append(result_notExist);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb = new StringBuilder();
                              sb.append(action).append(" ")
                                .append(result_unsuccess);
                            break;
                            
                        }
                    break;
                    case Action_insert:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_loginName).append(" ")
                              .append(result_duplicate);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            case 3:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_Email).append(" ")
                              .append(result_duplicate);
                            break;
                                
                        }
                    break;
                    case Action_update:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_loginName).append(" ")
                              .append(result_duplicate);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            case 3:
                            sb.append(result_unsuccess).append(", ")
                              .append(colUnique_Email).append(" ")
                              .append(result_duplicate);
                            break;
                        }
                    break;
                    case Action_delete:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                }
            break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Category">
            case Obj_Category:     
                switch(action){
                    case Action_insert:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(" ")
                              .append(colUnique_categoryName).append(" ")
                              .append(result_duplicate);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                    case Action_update:
                        switch(result){
                            case 0:
                            sb.append(result_unsuccess).append(" ")
                              .append(colUnique_categoryName).append(" ")
                              .append(result_duplicate);
                            break;
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                    case Action_delete:
                        switch(result){
                            case 1:
                            sb.append(result_success);
                            break;
                            case 2:
                            sb.append(result_unsuccess);
                            break;
                            
                        }
                    break;
                }
            break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="My Fold">
            
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="My Fold">

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="My Fold">

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="My Fold">

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="My Fold">

            // </editor-fold>
            
        }
        return sb.toString();
    }
}
