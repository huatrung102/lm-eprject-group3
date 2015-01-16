/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExSwing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator PC
 */
public class CheckBoxTableCellEditor extends JCheckBox  
    implements TableCellRenderer, MouseListener{
    
    protected boolean mousePressed = false;  
    protected CheckBoxTableCellEditor renderControl;
    public CheckBoxTableCellEditor(ItemListener itemListener){
            renderControl = this;
            renderControl.addItemListener(itemListener);  
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (mousePressed) {  
      mousePressed=false;  
      
      JTable tableView = (JTable)(e.getSource());  
      TableColumnModel columnModel = tableView.getColumnModel();  
      int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
      int column = tableView.convertColumnIndexToModel(viewColumn);  
   
      if (e.getClickCount() == 1 && column != -1) {  
        doClick();  
        }  
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         mousePressed = true;  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
      }  
    } 
        
    

    
    

