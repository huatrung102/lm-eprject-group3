/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import form.main.Main;
import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JToggleButton;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Administrator PC
 */
public class UIHelper {
    public static void resetForm(Container cont){
        for(Component c: cont.getComponents()){
            if(c instanceof JTextComponent){
                ((JTextComponent)c).setText("");
            }else if(c instanceof JToggleButton){
                ((JToggleButton)c).setSelected(false); //setSelectedIndex(0);
            }else if(c instanceof Container){
                resetForm((Container)c);
            }
        }
    }
    public static void bindDataFromObj(Class obj,Container cont){
        Field f;
        
        
        //Object value = field.get(object);
        
        
        for(Component c: cont.getComponents()){
            if(c instanceof JTextComponent){
                try {
                f = obj.getDeclaredField(c.getName());
                    if(f != null){
                   //     ((JTextComponent)c).setText(f.get(value));
                    }
                } catch (Exception e) {
                }
                
            }else if(c instanceof JToggleButton){
                ((JToggleButton)c).setSelected(false); //setSelectedIndex(0);
            }else if(c instanceof Container){
                resetForm((Container)c);
            }
        }
    }
    public static void bindBackground(JPanel pnl){
         JLabel label_5 = new JLabel("");
        label_5.setIcon(new ImageIcon(Main.class
                        .getResource("/image/wall3.jpg")));        
        label_5.setBounds(0, 0, 2000, 1000);
        pnl.add(label_5);
    }
}
