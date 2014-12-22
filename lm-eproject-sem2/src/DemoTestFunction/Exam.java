/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoTestFunction;

import java.awt.Component;
import java.awt.Container;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;


/**
 *
 * @author Administrator PC
 */
public class Exam extends javax.swing.JFrame {

    /**
     * Creates new form Exam
     */
    public Exam() {
        initComponents();
        initForm();
        loadComboBox();
        loadTable();
    }
    
    private void initForm(){
        enableComponents(pnlInsert,false);
        enableComponents(pnlEdit,false);
        actionBtReset(this.getContentPane());
    }
    
    private void loadComboBox(){
        jComboBox1.setModel(User.getListComboBox());
    }
    private void loadTable(){
        jTable1.setModel(User.getListTable());
    }
    private boolean validateForm(){
        Pattern regex = 
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher matcher = regex.matcher(txtEmail.getText());
        if(txtUsername.getText().isEmpty()){            
            JOptionPane.showMessageDialog(this, "UserName can not be blank,check again");
            txtUsername.requestFocus();
            return false;
        }else if(String.valueOf(txtPass.getPassword()).isEmpty()){
            txtPass.requestFocus();
            JOptionPane.showMessageDialog(this, "Password can not be blank,check again");
            return false;
        }else if(txtFullname.getText().isEmpty()){            
            JOptionPane.showMessageDialog(this, "Fullname can not be blank,check again");
            txtFullname.requestFocus();
            return false;
        }else if(!txtAge.getText().isEmpty() &&
                (Integer.parseInt(txtAge.getText())  < 1 || Integer.parseInt(txtAge.getText())  > 200)){
            txtAge.requestFocus();
            JOptionPane.showMessageDialog(this, "Age can not be blank and must be between 1 and 200,check again");
            return false;
        }else if(!(matcher.matches())){
            txtEmail.requestFocus();
            JOptionPane.showMessageDialog(this, "Email is not right,check again");
            return false;
        }
          return true;  
    }
    private void actionBtReset(Container cont){
        for(Component c: cont.getComponents()){
            if(c instanceof JTextComponent){
                ((JTextComponent)c).setText("");            
            }
        }
    }
    private void enableComponents(Container cont,boolean enable){
        for(Component c: cont.getComponents()){
            c.setEnabled(enable);
            if(c instanceof Container){
                enableComponents((Container)c,enable);
            }
        }
    
    }
    private User getObjByForm(){
        User obj = null;
       // String username, String password, String fullname, String age, String mail
        try {
            obj = new User(txtUsername.getText(),String.valueOf(txtPass.getPassword()) 
                                    , txtFullname.getText(),Integer.parseInt(txtAge.getText()),txtEmail.getText());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!!!");
            return null;
        }
        return obj;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlInsert = new javax.swing.JPanel();
        btinsert = new javax.swing.JButton();
        pnlEdit = new javax.swing.JPanel();
        btupdate = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();
        btreset = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practical Exam");

        jLabel1.setText("Username");

        txtUsername.setColumns(20);
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel2.setText("Password");

        jLabel3.setText("Fullname");

        txtFullname.setColumns(20);

        jLabel4.setText("Age");

        txtAge.setColumns(20);

        jLabel5.setText("Email");

        txtEmail.setColumns(20);

        btinsert.setText("Insert");
        btinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInsertLayout = new javax.swing.GroupLayout(pnlInsert);
        pnlInsert.setLayout(pnlInsertLayout);
        pnlInsertLayout.setHorizontalGroup(
            pnlInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInsertLayout.createSequentialGroup()
                .addComponent(btinsert)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlInsertLayout.setVerticalGroup(
            pnlInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btinsert)
        );

        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btdelete.setText("Delete");
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btupdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btdelete, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addComponent(btupdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btdelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btreset.setText("Reset");
        btreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btresetActionPerformed(evt);
            }
        });

        txtPass.setColumns(20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(51, 51, 51)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btreset))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(25, 25, 25)
                                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(59, 59, 59)
                                .addComponent(pnlInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1))
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2))
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btreset))
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btresetActionPerformed
        // TODO add your handling code here:
        actionBtReset(this.getContentPane());
        txtUsername.setEnabled(true);
    }//GEN-LAST:event_btresetActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        // TODO add your handling code here:
        String username = txtUsername.getText();
        if(JOptionPane.showConfirmDialog(this, "Do you want to delete " + username, "Warning Delete", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
        if(User.deleteAccount(username) > 0){
            JOptionPane.showMessageDialog(this, "Delete successfull!");
            initForm();
            txtUsername.setEnabled(true);
        }else
            JOptionPane.showMessageDialog(this, "Delete failed!");
    }//GEN-LAST:event_btdeleteActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
            User obj = getObjByForm();
        if(obj != null){
            if(User.updateAccount(obj) > 0) {
                JOptionPane.showMessageDialog(this, "Update successfull!");
                initForm();
                txtUsername.setEnabled(true);
            }else
                JOptionPane.showMessageDialog(this, "Update failed!");
        }else
            JOptionPane.showMessageDialog(this, "Can not load data");
        }
    }//GEN-LAST:event_btupdateActionPerformed

    private void btinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
            User obj = getObjByForm();
        if(obj != null){
            if(User.insertAccount(obj) > 0) {
                JOptionPane.showMessageDialog(this, "Insert successfull!");
                initForm();
            }else
                JOptionPane.showMessageDialog(this, "Insert failed!");
        }else
            JOptionPane.showMessageDialog(this, "Can not load data");
        }
    }//GEN-LAST:event_btinsertActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
        
        String username = txtUsername.getText();
        if(!username.isEmpty()){
            User obj = User.getAccountById(username);
            if(obj != null){
                bindObjToForm(obj);
                enableComponents(pnlEdit,true);
                txtUsername.setEnabled(false);
            }else{
                enableComponents(pnlInsert,true);
            }
              //  JOptionPane.showConfirmDialog(this, "Can not find username: " + username);
        }else{
            JOptionPane.showMessageDialog(this, "Please enter username");
        }
    }//GEN-LAST:event_txtUsernameActionPerformed
    private void bindObjToForm(User obj){
        txtUsername.setText(obj.username);
        txtPass.setText(obj.password);
        txtFullname.setText(obj.fullname);
        txtAge.setText(String.valueOf(obj.age));
        txtEmail.setText(obj.mail);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btinsert;
    private javax.swing.JButton btreset;
    private javax.swing.JButton btupdate;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlInsert;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
