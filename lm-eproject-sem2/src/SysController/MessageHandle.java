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
    public static final String Obj_Connection = "Connection";
    public static final String Obj_Parameter = "Parameter";
    
    public static final String Action_insert = "insert";
    public static final String Action_update = "update";
    public static final String Action_delete = "delete";
    public static final String Action_login = "login";
    public static final String Action_issue = "issue";
    public static final String Action_return = "return";
    public static final String Action_pay = "pay";
    
    public static final String Action_open = "open";
    public static final String Action_save = "save";
    public static final String Action_pass = "pass";
    
    public static final int result_saveConfig_err = 1003;
    public static final int result_openConfig_err = 1002;
    public static final int result_saveConfig_ok = 1001;
    
    public static final int result_commonAction_ok = 1;
    public static final int result_commonAction_err = 2;
    
    public static final int result_connection_err = -1;
    public static final int result_parameter_err = -2;
    
    
    
    private static final String result_success = "Successfully";
    private static final String result_unsuccess = "Unsuccessfully";
    private static final String result_null = "Null";
    private static final String result_incorrect = "Incorrect";
    
    
    
    public static void showError(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    public static String getMessage(String Obj, String action, int result) {
        return getMessageByResult(Obj,action,result);
    }
    
    private static String getMessageByResult(String Obj, String action,int result){
        StringBuilder sb = new StringBuilder();
        //insert,/update/lock/login User/Book/... successfull!   
        sb.append(action).append(" ")
           .append(Obj).append(" ");
           
        switch(result){
            case result_connection_err:
                sb.append(result_null);
                break;
            case result_parameter_err:
                sb.append(result_incorrect);
                break;
            case result_commonAction_ok://action successful
                sb.append(result_success);
                break;
            case result_commonAction_err://action unsuccessful
                sb.append(result_unsuccess);
                break;
            case result_saveConfig_ok://save config ok
                sb.append(result_success);
                break;
            case result_openConfig_err://open file error
                sb.append(result_unsuccess);
                break;
            case result_saveConfig_err://save file error
                sb.append(result_unsuccess);
                break;
            default:
                sb = new StringBuilder();
                break;
        }        
        return sb.toString();
    }
}
