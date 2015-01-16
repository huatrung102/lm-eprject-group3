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
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator PC
 */
public class CheckBoxTable extends JCheckBox  
    implements TableCellRenderer, MouseListener {  
  
  protected int column;  
  protected boolean mousePressed = false;  
  public CheckBoxTable(ItemListener itemListener) {
     
    this.addItemListener(itemListener);  
  }  
  public Component getTableCellRendererComponent(  
      JTable table, Object value,  
      boolean isSelected, boolean hasFocus, int row, int column) {  
    if (table != null) {  
      
        this.setBackground(isSelected? UIManager.getColor("Table.selectionBackground") : 
                                    UIManager.getColor("Table.background"));  
        
        this.setSelected(Boolean.TRUE.equals(value));
        this.addMouseListener(this);  
        
    }  
    return this;  
  }  
  protected void setColumn(int column) {  
    this.column = column;  
  }  
  public int getColumn() {  
    return column;  
  }  
  protected void handleClickEvent(MouseEvent e) {  
    if (mousePressed) {  
      mousePressed=false;      
      JTable tableView = (JTable)(e.getSource());  
      TableColumnModel columnModel = tableView.getColumnModel();  
      int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
      int column = tableView.convertColumnIndexToModel(viewColumn);  
   
      if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {  
        doClick();  
      }  
    }  
  }  
  public void mouseClicked(MouseEvent e) {  
    handleClickEvent(e);  
    ((JTable)e.getSource()).repaint();  
  }  
  public void mousePressed(MouseEvent e) {  
    mousePressed = true;  
  }  
  public void mouseReleased(MouseEvent e) {  
  }  
  public void mouseEntered(MouseEvent e) {  
  }  
  public void mouseExited(MouseEvent e) {  
  }  
}


