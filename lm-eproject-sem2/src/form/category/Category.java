package form.category;
import ExSwing.ClPanelTransparent;
import Helpers.UIHelper;
import SysController.MessageHandle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Category extends javax.swing.JFrame {
    DefaultTableModel tableModel;
    int line;
    String cateid, catename, catedesc;
    int isSuccess;
 
    public Category() {
        initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIHelper.bindBackground(pnlBackground);       
        start();      
    }

    private Category(String text, String text0) {
        
    }
    
    public void start(){
        setNormalMode();
        
        tblListCate.setModel(Model.Categories.Categories_getCategoryListWithBookNumber());
        tblListCate.getColumnModel().getColumn(0).setMinWidth(0);
        tblListCate.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListCate.getColumnModel().getColumn(0).setWidth(0);
        tblListCate.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblListCate.getColumnModel().getColumn(2).setPreferredWidth(5);
    }
    
    public void setNormalMode(){
        txtCateID.setEnabled(false);
        txtCateName.setEnabled(false);
        txtCateDescription.setEnabled(false);
        
        txtCateID.setEditable(false);
        txtCateName.setEditable(false);
        txtCateDescription.setEditable(false);
        
        txtCateID.setText(null);
        txtCateName.setText(null);
        txtCateDescription.setText(null);
        
        btnDeleteCate.setEnabled(false);
        btnNewCate.setEnabled(true);
        btnUpdateCate.setEnabled(false);
        
        btnSaveUpdateCate.setVisible(false);
        btnSaveCate.setEnabled(false);
        btnSaveCate.setVisible(true);
        btnCancel.setEnabled(false);
        
        tblListCate.clearSelection();
    }
    
    public void setSelectedMode(){
        txtCateID.setEnabled(false);
        txtCateName.setEnabled(true);
        txtCateDescription.setEnabled(true);
        
        txtCateID.setEditable(false);
        txtCateName.setEditable(false);
        txtCateDescription.setEditable(false);
        
        btnDeleteCate.setEnabled(true);
        btnNewCate.setEnabled(true);
        btnUpdateCate.setEnabled(true);
        
        btnSaveUpdateCate.setVisible(false);
        btnSaveCate.setVisible(true);
        btnSaveCate.setEnabled(false);
        btnCancel.setEnabled(false);
    }
    
    public void setUpdateMode(){
        txtCateID.setEnabled(false);
        txtCateName.setEnabled(true);
        txtCateDescription.setEnabled(true);
        
        txtCateID.setEditable(false);
        txtCateName.setEditable(true);
        txtCateDescription.setEditable(true);
        
        btnDeleteCate.setEnabled(false);
        btnNewCate.setEnabled(false);
        btnUpdateCate.setEnabled(false);
        
        btnSaveUpdateCate.setVisible(true);
        btnSaveCate.setVisible(false);
        btnCancel.setEnabled(true);
    }
    
    public void setAddNewMode(){
        txtCateID.setEnabled(false);
        txtCateName.setEnabled(true);
        txtCateDescription.setEnabled(true);
        
        txtCateID.setEditable(false);
        txtCateName.setEditable(true);
        txtCateDescription.setEditable(true);
        
        txtCateID.setText("Auto Generate");
        txtCateName.setText(null);
        txtCateDescription.setText(null);
        
        btnDeleteCate.setEnabled(false);
        btnNewCate.setEnabled(false);
        btnUpdateCate.setEnabled(false);
        
        btnSaveUpdateCate.setVisible(false);
        btnSaveCate.setVisible(true);
        btnSaveCate.setEnabled(true);
        btnCancel.setEnabled(true);
        
        tblListCate.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        jPanel9 = new ClPanelTransparent();
        jLabel19 = new javax.swing.JLabel();
        btnNewCate = new javax.swing.JButton();
        btnDeleteCate = new javax.swing.JButton();
        btnUpdateCate = new javax.swing.JButton();
        jPanel10 = new ClPanelTransparent();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListCate = new javax.swing.JTable();
        jPanel6 = new ClPanelTransparent();
        jLabel20 = new javax.swing.JLabel();
        txtCateName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCateID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCateDescription = new javax.swing.JTextArea();
        btnSaveCate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSaveUpdateCate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Managment");
        setMinimumSize(new java.awt.Dimension(820, 515));
        setResizable(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 0));
        jLabel19.setText("Category");

        btnNewCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnNewCate.setText("New");
        btnNewCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCateActionPerformed(evt);
            }
        });

        btnDeleteCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnDeleteCate.setText("Delete");
        btnDeleteCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCateActionPerformed(evt);
            }
        });

        btnUpdateCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Update.png"))); // NOI18N
        btnUpdateCate.setText("Update");
        btnUpdateCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnUpdateCate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteCate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCate)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(btnNewCate)
                    .addComponent(btnDeleteCate)
                    .addComponent(btnUpdateCate))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tblListCate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title", "Author"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListCate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListCateMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListCate);
        if (tblListCate.getColumnModel().getColumnCount() > 0) {
            tblListCate.getColumnModel().getColumn(0).setResizable(false);
            tblListCate.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblListCate.getColumnModel().getColumn(1).setResizable(false);
            tblListCate.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setText("Category Name");

        jLabel21.setText("Category ID");

        jLabel1.setText("Description");

        txtCateDescription.setColumns(20);
        txtCateDescription.setRows(5);
        jScrollPane2.setViewportView(txtCateDescription);

        btnSaveCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveCate.setText("Save");
        btnSaveCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCateActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSaveUpdateCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveUpdateCate.setText("Save");
        btnSaveUpdateCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdateCateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSaveCate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveUpdateCate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(txtCateName)
                        .addComponent(txtCateID, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCateID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveCate)
                    .addComponent(btnCancel)
                    .addComponent(btnSaveUpdateCate))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCateActionPerformed
        setAddNewMode(); 
    }//GEN-LAST:event_btnNewCateActionPerformed

    private void btnSaveCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCateActionPerformed
        catename = txtCateName.getText();
        catedesc = txtCateDescription.getText();
         
        Model.Categories obj;
        if(catename.isEmpty()){
            JOptionPane.showMessageDialog(null, "Category Name not be blank!");
            txtCateName.requestFocus();
            return;
        } else {
            obj = new Model.Categories(catename, false, catedesc);
            isSuccess = Model.Categories.Categories_Insert(obj);
            MessageHandle.showMessage(MessageHandle.Obj_Category, MessageHandle.Action_insert, isSuccess);
            txtCateName.requestFocus();
        }
        setAddNewMode();
        tblListCate.setModel(Model.Categories.Categories_getCategoryListWithBookNumber());
        tblListCate.getColumnModel().getColumn(0).setMinWidth(0);
        tblListCate.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListCate.getColumnModel().getColumn(0).setWidth(0);
        tblListCate.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblListCate.getColumnModel().getColumn(2).setPreferredWidth(5);
    }//GEN-LAST:event_btnSaveCateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setNormalMode();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblListCateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListCateMouseClicked
        setSelectedMode();
        line = tblListCate.getSelectedRow();
        
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblListCate.getModel();
        cateid = (String)tbm.getValueAt(line, 0);
        catename = (String)tbm.getValueAt(line, 1);
        catedesc = Model.Categories.Categories_getCategoryByCateId(cateid).Cat_Description;
     
        txtCateID.setText(cateid);
        txtCateName.setText(catename);
        txtCateDescription.setText(catedesc);
    }//GEN-LAST:event_tblListCateMouseClicked

    private void btnDeleteCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCateActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete this Category?", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (dialogResult == JOptionPane.OK_OPTION){
            isSuccess = Model.Categories.Categories_Lock(cateid);
            MessageHandle.showMessage(MessageHandle.Obj_Category, MessageHandle.Action_delete, isSuccess);
            setNormalMode();
            tblListCate.setModel(Model.Categories.Categories_getCategoryListWithBookNumber());
            tblListCate.getColumnModel().getColumn(0).setMinWidth(0);
            tblListCate.getColumnModel().getColumn(0).setMaxWidth(0);
            tblListCate.getColumnModel().getColumn(0).setWidth(0);
            tblListCate.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblListCate.getColumnModel().getColumn(2).setPreferredWidth(5);
        }
    }//GEN-LAST:event_btnDeleteCateActionPerformed

    private void btnUpdateCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCateActionPerformed
        setUpdateMode();
    }//GEN-LAST:event_btnUpdateCateActionPerformed

    private void btnSaveUpdateCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateCateActionPerformed
        catename = txtCateName.getText();
        catedesc = txtCateDescription.getText();
        cateid = txtCateID.getText();
         
        Model.Categories obj;
        if(catename.isEmpty()){
            JOptionPane.showMessageDialog(null, "Category Name not be blank!");
            txtCateName.requestFocus();
            return;
        } else {
            obj = new Model.Categories(catename, false, catedesc);
            isSuccess = Model.Categories.Categories_Update(obj, cateid);
            MessageHandle.showMessage(MessageHandle.Obj_Category, MessageHandle.Action_update, isSuccess);
            txtCateName.requestFocus();
        }
        setAddNewMode();
        tblListCate.setModel(Model.Categories.Categories_getCategoryListWithBookNumber());
        tblListCate.getColumnModel().getColumn(0).setMinWidth(0);
        tblListCate.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListCate.getColumnModel().getColumn(0).setWidth(0);
        tblListCate.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblListCate.getColumnModel().getColumn(2).setPreferredWidth(5);
    }//GEN-LAST:event_btnSaveUpdateCateActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDeleteCate;
    private javax.swing.JButton btnNewCate;
    private javax.swing.JButton btnSaveCate;
    private javax.swing.JButton btnSaveUpdateCate;
    private javax.swing.JButton btnUpdateCate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTable tblListCate;
    private javax.swing.JTextArea txtCateDescription;
    private javax.swing.JTextField txtCateID;
    private javax.swing.JTextField txtCateName;
    // End of variables declaration//GEN-END:variables
}
