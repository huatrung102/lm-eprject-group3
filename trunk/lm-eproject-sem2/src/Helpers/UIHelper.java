/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;

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
}
