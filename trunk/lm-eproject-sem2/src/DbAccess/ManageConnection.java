/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbAccess;

import Config.SysVar;
import Model.Db;
import SysController.MessageHandle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator PC
 */
public class ManageConnection {
   
    public static Db getInfoDB(){
        Properties p = new Properties();
        Db db = null;
        File file = new File(SysVar.file_DbConfig);
        if(file.exists())
        {
            try (
                   FileInputStream fis = new FileInputStream(file); 
                 ){                
                p.load(fis);
                db = new Db(p.getProperty("server"),
                        p.getProperty("port"), 
                        p.getProperty("database"),
                        p.getProperty("instance"),
                        p.getProperty("username"), 
                        p.getProperty("password"));
            } catch(Exception ex) {
                MessageHandle.showError(MessageHandle.openFileError);
               // Logger.getLogger(ManageConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }
    public static boolean SetInfoDB(Db db){
        Properties p = new Properties();
        try(
            FileOutputStream file = new FileOutputStream(SysVar.file_DbConfig);
            )
        {            
            p.setProperty("server", db.getServer());
            p.setProperty("port", db.getPort());
            p.setProperty("database", db.getDatabase());
            p.setProperty("instance", db.getInstance());
            p.setProperty("username", db.getUsername());
            p.setProperty("password", db.getPassword());
            p.store(file, "Group 3-Fpt Aptech");
            
        }catch(IOException ex)
        {
            MessageHandle.showError(MessageHandle.openFileError);
            return false;
        }
        return true;
    }
    private static String connectionString(String driver){
        StringBuilder sb = new StringBuilder();
        Db db =  getInfoDB();
        if(driver.equalsIgnoreCase(SysVar.driver_msSQL))
            sb.append("jdbc:sqlserver://");
        else    
            sb.append("jdbc:jtds:sqlserver://");        
         sb.append(db.getServer())
        .append(":").append(db.getPort());
         if(driver.equalsIgnoreCase(SysVar.driver_msSQL))
            sb.append(";DatabaseName=").append(db.getDatabase());
         else            sb.append("/").append(db.getDatabase());
        if(!db.getInstance().isEmpty()) 
            sb.append(";instance=").append(db.getInstance());
        sb.append(";User=").append(db.getUsername())
        .append(";Password=").append(db.getPassword());
        return sb.toString();
                
    }
   
    public static Connection getConnection(String driver){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(connectionString(driver));
             
        } catch (SQLException ex) {
           Logger.getLogger(ManageConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(ManageConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
    



}
