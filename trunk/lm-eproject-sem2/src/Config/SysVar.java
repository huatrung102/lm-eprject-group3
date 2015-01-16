/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 *
 * @author Administrator PC
 */
public class SysVar {
    public static final String file_DbConfig = "dbconfig.properties";
    public static final String file_Lang = "language.properties";
    public static final String driver_msSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String driver_jdts = "net.sourceforge.jtds.jdbc.Driver";
    public static final String patern_email = "^[\\w-\\.]+@([\\w-]+[\\w-]{2,4}$)";
    public static final String value_nullSql = "$null$";
    public static final String image_member_defaut= "/image/noavatar.png";
    public static final String image_staff_defaut= "/image/noavatar.png";
    
    public static final String role_Member = "Member Manage";
    public static final String role_Admin = "Admin";
    public static final String role_IR = "IR Manage";
    public static final String role_Book = "Book Manage";
    public static final String role_Fine = "Fine Manage";
    
}
