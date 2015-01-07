/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import form.book.BookList;

/**
 *
 * @author TraPhucVinh
 */
public class Categories {
    public String Cat_Id;
    public String Cat_Name;
    public boolean Cat_isDelete;
    public String Cat_Description;
    public Categories(){
    
    }
    
    public void getListCategory(){
        DefaultMutableTreeNode rootNode;
        rootNode = new DefaultMutableTreeNode("Categories");
        JTree treCategory;
        treCategory = new JTree(rootNode);
        
        DefaultMutableTreeNode leafNode1;
        leafNode1 = new DefaultMutableTreeNode("Novel");
        
        rootNode.add(leafNode1);
        JScrollPane scpScroller = new JScrollPane(treCategory);
    }
}


