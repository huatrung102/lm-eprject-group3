/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.ir;

import Config.SysVar;
import ExSwing.ClButtonTransparan;
import ExSwing.ClPanelTransparent;
import ExSwing.CheckBoxHeader;
import ExSwing.GlassPaneProgress;
import ExSwing.SelectAllHeader;
import Helpers.UIHelper;
import Model.Books;
import Model.IRBooks;
import Model.Members;
import SysController.MessageHandle;
import form.main.Main;
import form.member.MemberSearch;
import form.member.MemberSearch1;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator PC
 */
public class ReturnManagement1 extends javax.swing.JFrame {

    /**
     * Creates new form ReturnManagement
     */
    Books glBook;
    public String Member_No;
    private HashMap<String, String> Cop_IRDetail_Return;
    
    private static String return_col[] = {"","No","ISBN","Title","Copy No","IssueDate","DueDate","Late Day"};
    DefaultTableModel tblDef = new DefaultTableModel(return_col,0);    
    private GlassPaneProgress glasspane;
    private Members selectedMember;    
    int minBook = 0;
    int maxBook = 5;
    public ReturnManagement1() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initForm();
         btSearchMem.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/Explore.png")));
         btReturn.setIcon(new ImageIcon(ReturnManagement1.class
                        .getResource("/image/return.png")));
        
        UIHelper.bindBackground(pnlReturn);
        initTblReturn();
        initMember();
      
    }
    private void initMember(){
        lblMemberNo.setText("");
        lblFullname.setText("");
        lblPhone.setText("");        
        lblRegisterDate.setText("");
        lblStatusMem.setText("");
        
        //load image member
        lblImgMember.setIcon(new ImageIcon(IssueManagement.class
                        .getResource(SysVar.image_member_defaut)));        
        lblImgMember.setBounds(0, 0, 140, 140);
    }
    private void initTblReturn(){
        DefaultTableModel tblM = new DefaultTableModel(return_col, 0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                } else {
                    return Object.class;
                }
            }
        };
        tblReturn.setModel(tblM);         
        renderTableColumnCheckbox();
        
      //  UIHelper.hideColumnOfTable(tblReturn,0);
       // tblReturn.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
    }
    private void renderTableColumnCheckbox(){
        TableColumn tc = tblReturn.getColumnModel().getColumn(0);  
        tc.setHeaderRenderer(new SelectAllHeader(tblReturn, 0));
        tc.setCellEditor(tblReturn.getDefaultEditor(Boolean.class));  
        tc.setCellRenderer(tblReturn.getDefaultRenderer(Boolean.class)); 
    }
    private void loadTblReturn(){
        if(selectedMember != null){
            DefaultTableModel tblM = IRBooks.getListBookNotReturnTest(selectedMember.Mem_No);
            tblReturn.setModel(tblM);
            renderTableColumnCheckbox();        
            UIHelper.unhideColumnOfTable(tblReturn,0,70);
            UIHelper.hideColumnOfTable(tblReturn,1);

            Cop_IRDetail_Return.clear();
        }
        
    }
    private void initForm(){
        Cop_IRDetail_Return = new HashMap<>(5);
        glasspane  = new GlassPaneProgress();
        setGlassPane(glasspane);
        glasspane.setMinimum(minBook);
        glasspane.setMaximum(maxBook);
        glasspane.setValue(minBook);
        glasspane.setStringPainted(true);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnManagement1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        tblReturn.getTableHeader().setReorderingAllowed(false);
         btClose.setIcon(new ImageIcon(ReturnManagement1.class
                        .getResource("/image/CloseForm.png")));
    }
    
    private void loadMember(){
        String mem_No = txtMemberNo.getText();
        Members mem = Members.getIRCountInformation(mem_No);
        
        if(mem != null){
            selectedMember = mem;
            lblMemberNo.setText(mem.Mem_No);
            lblFullname.setText(mem.Mem_FirstName + " " + mem.Mem_LastName);
            lblPhone.setText(mem.Mem_Phone);
            
            lblStatusMem.setText(mem.Mem_Status?"Active" : "Inactive");
            lblRegisterDate.setText(mem.Mem_CreateDate);            
           
            //load image member
            ImageIcon icon = new ImageIcon(mem.Mem_ImageFile);
            lblImgMember.setIcon(icon);      
            lblImgMember.setBounds(0, 0, 140, 140);
            //rebind data tbl issued
        }else{
            MessageHandle.showError("Can not find Member with No: " + mem_No);
        }
        
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
        txtMemberNo = new javax.swing.JTextField();
        btSearchMem = new ClButtonTransparan("Search");
        jPanel10 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable(){

            public boolean isCellEditable(int row,int column){
                return column == 0;
            };
        };
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
        jLabel6 = new javax.swing.JLabel();
        lblMemberNo = new javax.swing.JLabel();
        btClose = new ClButtonTransparan("Return");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.title")); // NOI18N
        setResizable(false);

        pnlReturn.setPreferredSize(new java.awt.Dimension(1100, 400));

        pnlBackground.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.pnlBackground.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        pnlBackground.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel1.text")); // NOI18N

        txtMemberNo.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.txtMemberNo.text")); // NOI18N

        btSearchMem.setForeground(java.awt.Color.darkGray);
        btSearchMem.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.btSearchMem.text")); // NOI18N
        btSearchMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchMemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearchMem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchMem))
                .addGap(12, 12, 12))
        );

        pnlBackground.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
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
        btReturn.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.btReturn.text")); // NOI18N
        btReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturnActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 176));
        jPanel4.setLayout(new java.awt.BorderLayout());

        pnlImgMember.setPreferredSize(new java.awt.Dimension(140, 140));

        lblImgMember.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblImgMember.text")); // NOI18N
        lblImgMember.setAlignmentX(0.5F);

        javax.swing.GroupLayout pnlImgMemberLayout = new javax.swing.GroupLayout(pnlImgMember);
        pnlImgMember.setLayout(pnlImgMemberLayout);
        pnlImgMemberLayout.setHorizontalGroup(
            pnlImgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgMember)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        pnlImgMemberLayout.setVerticalGroup(
            pnlImgMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgMember)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel5.text")); // NOI18N

        lblFullname.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblFullname.text")); // NOI18N

        lblPhone.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblPhone.text")); // NOI18N
        lblPhone.setPreferredSize(new java.awt.Dimension(40, 15));

        lblRegisterDate.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblRegisterDate.text")); // NOI18N

        lblStatusMem.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblStatusMem.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.jLabel6.text")); // NOI18N

        lblMemberNo.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.lblMemberNo.text")); // NOI18N
        lblMemberNo.setPreferredSize(new java.awt.Dimension(40, 15));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMemberNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRegisterDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(lblStatusMem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        btClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btClose.setForeground(java.awt.Color.yellow);
        btClose.setText(org.openide.util.NbBundle.getMessage(ReturnManagement1.class, "ReturnManagement1.btClose.text")); // NOI18N
        btClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlReturnLayout = new javax.swing.GroupLayout(pnlReturn);
        pnlReturn.setLayout(pnlReturnLayout);
        pnlReturnLayout.setHorizontalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReturnLayout.createSequentialGroup()
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReturnLayout.createSequentialGroup()
                        .addGap(907, 907, 907)
                        .addComponent(btReturn)
                        .addGap(52, 52, 52)
                        .addComponent(btClose))
                    .addGroup(pnlReturnLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlReturnLayout.setVerticalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReturnLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReturn)
                    .addComponent(btClose))
                .addGap(12, 12, 12))
        );

        getContentPane().add(pnlReturn, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSearchMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchMemActionPerformed
        // TODO add your handling code here:
        MemberSearch1 memberSearchBox = new MemberSearch1(this, true);
        memberSearchBox.setVisible(true);
        txtMemberNo.setText(memberSearchBox.getPopUpData1());      
        loadMember();
        loadTblReturn();
    }//GEN-LAST:event_btSearchMemActionPerformed

    private void btReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturnActionPerformed
        // TODO add your handling code here:
        int row = tblReturn.getRowCount();
        if(row> 0 ){
            for(int i =0;i <row;i++){
                if(Boolean.valueOf(tblReturn.getModel().getValueAt(i, 0).toString()) == true){
                    String IRDetail = String.valueOf(tblReturn.getModel().getValueAt(i, 1)) ;
                    Cop_IRDetail_Return.put(IRDetail, IRDetail);
                }
            }
            int result = IRBooks.ReturnBook(Cop_IRDetail_Return);
            MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_return, result);
            if(result == 1){
                loadTblReturn();
              //  ((DefaultTableModel)tblReturn.getModel()).setNumRows(0);
              //  Cop_IRDetail_Return.clear();
            } 
            /*
            for(int i =0;i <row;i++){
                boolean check = Boolean.valueOf(String.valueOf(tblReturn.getModel().getValueAt(i, 0))) ;
                if(check){
                    String IrDetail = String.valueOf(tblReturn.getModel().getValueAt(i, 1));
                   // String 
                   // IRBooks.ReturnBook(Member_No, TOP_ALIGNMENT, maxBook)
                }
            }
            */
        }else{
            MessageHandle.showError("Member dont have book return!");
        }
        
        /*
        boolean flag;
        for(int i = 0; i< tblReturn.getRowCount(); i ++){
            flag = Boolean.valueOf(String.valueOf(tblReturn.getValueAt(i, 0)));
            if(flag){
                new Thread(new Runnable() {

        public void run() {
            glasspane.setShowProgress(true);
            for (int i = minBook; i < maxBook; i++) {
                try {
                    glasspane.setValue(i);
                    glasspane.setString("DONE " + i + " OF " + maxBook + " TASK");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    MessageHandle.showError("nooooooooo");
                }
            }
            glasspane.setShowProgress(false);
        }
    }).start();
            }
        }
        */
    }//GEN-LAST:event_btReturnActionPerformed

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCloseActionPerformed
    public void setDataPopUp(String memberNo) {
        this.Member_No = memberNo;
    }

    public String getDataPopUp() {
        return Member_No;
    }
    
    
  /*
    public static void main(String args[]) {
        
        
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnManagement().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClose;
    private javax.swing.JButton btReturn;
    private javax.swing.JButton btSearchMem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblImgMember;
    private javax.swing.JLabel lblMemberNo;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblRegisterDate;
    private javax.swing.JLabel lblStatusMem;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlImgMember;
    private javax.swing.JPanel pnlReturn;
    protected javax.swing.JTable tblReturn;
    private javax.swing.JTextField txtMemberNo;
    // End of variables declaration//GEN-END:variables
}

