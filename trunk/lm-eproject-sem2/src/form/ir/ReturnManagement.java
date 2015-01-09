/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.ir;

import ExSwing.ClButtonTransparan;
import ExSwing.ClPanelTransparent;
import Helpers.UIHelper;
import Model.Books;
import Model.IRBooks;
import Model.Member;
import form.main.Main;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Administrator PC
 */
public class ReturnManagement extends javax.swing.JFrame {

    /**
     * Creates new form ReturnManagement
     */
    Books glBook;
    public ReturnManagement() {
        initComponents();
         btSearchMem.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/Explore.png")));
         btReturn.setIcon(new ImageIcon(ReturnManagement.class
                        .getResource("/image/return.png")));
        
        UIHelper.bindBackground(pnlReturn);
        loadBook();
        loadIRBook();
        loadMember();
    }
    private void loadBook(){
        glBook = Books.getTestBook();
    }
    private void loadIRBook(){
        tblReturn.setModel(IRBooks.getTestIRBookReturn(glBook));
    }
    private void loadMember(){
        Member mem = Member.getTestMember();
        lblFullname.setText(mem.Mem_FirstName + " " + mem.Mem_LastName);
        lblPhone.setText(mem.Mem_Phone);
        lblStatusMem.setText(mem.Mem_Status?"Active" : "Inactive");
        lblRegisterDate.setText(mem.Mem_CreateDate);
        //load image member
        lblImgMember.setIcon(new ImageIcon(Main.class
                        .getResource(mem.Mem_ImageFile)));        
        lblImgMember.setBounds(0, 0, 140, 140);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlReturn = new javax.swing.JPanel();
        pnlBackground = new ClPanelTransparent();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmailMem = new javax.swing.JTextField();
        btSearchMem = new ClButtonTransparan("Search");
        jPanel10 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        btReturn = new ClButtonTransparan("Return");
        javax.swing.JPanel jPanel4 = new ClPanelTransparent();
        jPanel3 = new javax.swing.JPanel();
        pnlImgMember = new javax.swing.JPanel();
        lblImgMember = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblRegisterDate = new javax.swing.JLabel();
        lblStatusMem = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblStatusMem1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(1190, 480));
        setResizable(false);

        pnlReturn.setPreferredSize(new java.awt.Dimension(1100, 400));

        pnlBackground.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.pnlBackground.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        pnlBackground.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel1.text")); // NOI18N

        txtEmailMem.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.txtEmailMem.text")); // NOI18N

        btSearchMem.setForeground(java.awt.Color.darkGray);
        btSearchMem.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.btSearchMem.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtEmailMem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearchMem)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmailMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchMem))
                .addGap(12, 12, 12))
        );

        pnlBackground.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel10.setLayout(new java.awt.BorderLayout());

        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblReturn);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel10.add(jPanel5, java.awt.BorderLayout.CENTER);

        btReturn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReturn.setForeground(java.awt.Color.yellow);
        btReturn.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.btReturn.text")); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 176));
        jPanel4.setLayout(new java.awt.BorderLayout());

        pnlImgMember.setPreferredSize(new java.awt.Dimension(140, 140));

        lblImgMember.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblImgMember.text")); // NOI18N
        lblImgMember.setAlignmentX(0.5F);

        javax.swing.GroupLayout pnlImgMemberLayout = new javax.swing.GroupLayout(pnlImgMember);
        pnlImgMember.setLayout(pnlImgMemberLayout);
        pnlImgMemberLayout.setHorizontalGroup(
            pnlImgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgMember)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        pnlImgMemberLayout.setVerticalGroup(
            pnlImgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgMember)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel5.text")); // NOI18N

        lblFullname.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblFullname.text")); // NOI18N

        lblPhone.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblPhone.text")); // NOI18N
        lblPhone.setPreferredSize(new java.awt.Dimension(40, 15));

        lblRegisterDate.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblRegisterDate.text")); // NOI18N

        lblStatusMem.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblStatusMem.text")); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel16.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel16.text")); // NOI18N

        lblStatusMem1.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.lblStatusMem1.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblRegisterDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblStatusMem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblStatusMem1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lblFullname))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblRegisterDate))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(lblStatusMem))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(lblStatusMem1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(pnlImgMember, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel11.setForeground(java.awt.Color.yellow);
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText(org.openide.util.NbBundle.getMessage(ReturnManagement.class, "ReturnManagement.jLabel11.text")); // NOI18N

        javax.swing.GroupLayout pnlReturnLayout = new javax.swing.GroupLayout(pnlReturn);
        pnlReturn.setLayout(pnlReturnLayout);
        pnlReturnLayout.setHorizontalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReturnLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btReturn)
                    .addGroup(pnlReturnLayout.createSequentialGroup()
                        .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlReturnLayout.setVerticalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReturnLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btReturn)
                .addGap(12, 12, 12))
        );

        getContentPane().add(pnlReturn, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ReturnManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReturn;
    private javax.swing.JButton btSearchMem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblImgMember;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblRegisterDate;
    private javax.swing.JLabel lblStatusMem;
    private javax.swing.JLabel lblStatusMem1;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlImgMember;
    private javax.swing.JPanel pnlReturn;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTextField txtEmailMem;
    // End of variables declaration//GEN-END:variables
}
