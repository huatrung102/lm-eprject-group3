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
    public static final String writeFileError = "Can not write file";
    public static final String openFileError = "Can not open file";
    public static void showError(String s){
        JOptionPane.showMessageDialog(null, s);
    }
}
