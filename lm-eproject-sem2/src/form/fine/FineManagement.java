/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.fine;

import ExSwing.CheckBoxHeader;
import ExSwing.CheckBoxTable;
import ExSwing.CheckBoxTableCellEditor;
import ExSwing.ClButtonTransparan;
import ExSwing.ClPanelTransparent;
import ExSwing.GlassPaneProgress;
import ExSwing.SelectAllHeader;
import Helpers.UIHelper;
import Model.Books;
import Model.IRBooks;
import Model.Members;
import SysController.MessageHandle;
import bussiness.Fine;
import form.ir.IssueManagement;
import form.ir.ReturnManagement;
import form.main.Main;
import form.member.MemberSearch;
import form.member.MemberSearch2;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator PC
 */
public class FineManagement extends javax.swing.JFrame {

    /**
     * Creates new form FineManagement
     */
    private HashMap<String, String> ListFine;
    double totalmoney = 0;
    public String Member_No = "";
    private static final int checkBox_Col = 0; //first column
    private static String fine_col[] = //{"","No","ISBN","Title","Copy No","Issue Date","Due Date","Late Day"}
                                        {"","","No","ISBN","Copy No","Title","Money", "CreateDate"};
    private Members selectedMember;
    public FineManagement() {        
        initComponents();
        initForm();
        initTblFine();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
    }
    public void setDataPopUp(String memberNo) {
        this.Member_No = memberNo;
    }
    public String getDataPopUp() {
        return Member_No;
    }
    
    private void bindDataToTableModel(){
        if(selectedMember != null){
        //    int nRow = tblFine.getRowCount(),nCol = tblFine.getColumnCount();
            //Object[][] data = new Object[nRow][nCol];
            DefaultTableModel tbm = IRBooks.ListByMemberNo(selectedMember.Mem_No);
            

            
            tblFine.setModel(tbm);
            UIHelper.hideColumnOfTable(tblFine, 0);
            //column price
            lblTotalPrice.setText(Fine.calculateTotal(tblFine, 5));
//            TableColumn tc = tblFine.getColumnModel().getColumn(checkBox_Col);
//            tc.setHeaderRenderer(new SelectAllHeader(tblFine, checkBox_Col));
//            tc.setCellEditor(tblFine.getDefaultEditor(Boolean.class));  
//            tc.setCellRenderer(tblFine.getDefaultRenderer(Boolean.class));
        }
        
        
    }
    private void initTblFine(){
        DefaultTableModel tblM = //IRBooks.getTestIRBookReturn(Books.getTestBook());
         new DefaultTableModel(fine_col, 0){

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == checkBox_Col) {
                return Boolean.class;
            } else {
                return String.class;
            }
        }
    };
     //   bindTestValue(tblM);
        tblFine.setModel(tblM);
//        TableColumn tc = tblFine.getColumnModel().getColumn(checkBox_Col);
//        tc.setHeaderRenderer(new SelectAllHeader(tblFine, checkBox_Col));
//        tc.setCellEditor(tblFine.getDefaultEditor(Boolean.class));  
//        tc.setCellRenderer(tblFine.getDefaultRenderer(Boolean.class));
        //tc.setCellRenderer(new CheckBoxTableCellEditor(new FineManagement.MyItemListener()));  
//       tblFine.getModel().addTableModelListener(new TableModelListener() {
//
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int x = tblFine.getModel().getColumnCount();
//                totalmoney = 0;
//                for(int i= 0 ; i < tblFine.getModel().getRowCount();i++){
//                    if((Boolean) tblFine.getModel().getValueAt(i, 0)){
//                        String temp = tblFine.getModel().getValueAt(i,x-1).toString();
//                        totalmoney +=   Double.parseDouble(temp);
//                    }
//                }
//                 lblTotalPrice.setText(String.valueOf(totalmoney));
//            }
//        });
       // tblReturn.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
    }
  /*
  class MyItemListener implements ItemListener  
  {  
    public void itemStateChanged(ItemEvent e) {  
      Object source = e.getSource();  
      if (source instanceof AbstractButton == false) return;  
      boolean checked = e.getStateChange() == ItemEvent.SELECTED;        
       tblFine.setValueAt(checked,tblFine.getSelectedRow(),0);
       totalmoney = 0;
        for(int row = 0;row < tblFine.getRowCount();row++){
            checked = e.getStateChange() == ItemEvent.SELECTED;     
            if(checked){
               totalmoney += Double.valueOf((String)tblFine.getModel().getValueAt(row, tblFine.getColumnCount()-1));
            }
        }
      
      lblTotalPrice.setText(String.valueOf(totalmoney));
    }  
  } 
    */
   
    /*
    private void loadTblFine(){
        tblFine.setModel(IRBooks.ListByMemberNo(selectedMember.Mem_No));
    }
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFine = new javax.swing.JPanel();
        pnlBackground = new ClPanelTransparent();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMemberNo = new javax.swing.JTextField();
        btSearchMem = new ClButtonTransparan("Search");
        jPanel10 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFine = new javax.swing.JTable(){

            public boolean isCellEditable(int row,int column){
                return column == 0;
            };
        };
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        btFine = new ClButtonTransparan("Return");
        jPanel4 = new ClPanelTransparent();
        jPanel3 = new javax.swing.JPanel();
        pnlImgMember = new javax.swing.JPanel();
        lblImgMember = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblRegisterDate = new javax.swing.JLabel();
        lblStatusMem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(890, 615));

        pnlFine.setPreferredSize(new java.awt.Dimension(700, 595));

        pnlBackground.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.pnlBackground.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        pnlBackground.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel1.text")); // NOI18N

        txtMemberNo.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.txtMemberNo.text")); // NOI18N

        btSearchMem.setForeground(java.awt.Color.darkGray);
        btSearchMem.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.btSearchMem.text")); // NOI18N
        btSearchMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchMemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearchMem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btSearchMem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBackground.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel10.setLayout(new java.awt.BorderLayout());

        tblFine.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblFine);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel10.add(jPanel5, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel3.text")); // NOI18N

        lblTotalPrice.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTotalPrice.setForeground(java.awt.Color.red);
        lblTotalPrice.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblTotalPrice.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(lblTotalPrice)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalPrice))
                .addContainerGap())
        );

        btFine.setForeground(java.awt.Color.darkGray);
        btFine.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.btFine.text")); // NOI18N
        btFine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFineActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 176));
        jPanel4.setLayout(new java.awt.BorderLayout());

        pnlImgMember.setPreferredSize(new java.awt.Dimension(140, 140));

        lblImgMember.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblImgMember.text")); // NOI18N
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
        jLabel2.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel2.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel5.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.jLabel6.text")); // NOI18N

        lblFullname.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblFullname.text")); // NOI18N

        lblPhone.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblPhone.text")); // NOI18N
        lblPhone.setPreferredSize(new java.awt.Dimension(40, 15));

        lblRegisterDate.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblRegisterDate.text")); // NOI18N

        lblStatusMem.setText(org.openide.util.NbBundle.getMessage(FineManagement.class, "FineManagement.lblStatusMem.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRegisterDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatusMem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(254, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFullname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblRegisterDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblStatusMem))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlFineLayout = new javax.swing.GroupLayout(pnlFine);
        pnlFine.setLayout(pnlFineLayout);
        pnlFineLayout.setHorizontalGroup(
            pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFineLayout.createSequentialGroup()
                            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlFineLayout.createSequentialGroup()
                        .addGroup(pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btFine)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFineLayout.setVerticalGroup(
            pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFineLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btFine)
                .addContainerGap())
        );

        getContentPane().add(pnlFine, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void loadMember(){
        String mem_No = txtMemberNo.getText();
        Members mem = Members.getIRCountInformation(mem_No);
        
        if(mem != null){
            selectedMember = mem;
            
            lblFullname.setText(mem.Mem_FirstName + " " + mem.Mem_LastName);
            lblPhone.setText(mem.Mem_Phone);
            
            lblStatusMem.setText(mem.Mem_Status?"Active" : "Inactive");
            lblRegisterDate.setText(mem.Mem_CreateDate);            
            
            //load image member
            lblImgMember.setIcon(new ImageIcon(Main.class
                            .getResource(mem.Mem_ImageFile)));        
            lblImgMember.setBounds(0, 0, 140, 140);
            //rebind data tbl issued
            
        }else{
            MessageHandle.showError("Can not find Member with No: " + mem_No);
        }
        
    }
    
    private void btSearchMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchMemActionPerformed
        // TODO add your handling code here:
       // loadTblFine();
        MemberSearch2 memberSearchBox = new MemberSearch2(this, true);
        memberSearchBox.setVisible(true);
        txtMemberNo.setText(memberSearchBox.getPopUpData());   
        loadMember();
        bindDataToTableModel();
    }//GEN-LAST:event_btSearchMemActionPerformed

    private void btFineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFineActionPerformed
        // TODO add your handling code here:
        int row = tblFine.getRowCount();
        if(row> 0 ){
            for(int i =0;i <row;i++){
                String fineid = String.valueOf(tblFine.getModel().getValueAt(i, 0)) ;
                ListFine.put(fineid, fineid);
            }
            int result = IRBooks.PaidFine(ListFine);
            MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_return, result);
            if(result == 1) ((DefaultTableModel)tblFine.getModel()).setNumRows(0);
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
            MessageHandle.showError("Member dont have Fee fine to pay!");
        }
        
    }//GEN-LAST:event_btFineActionPerformed
    private void initForm(){
        ListFine = new HashMap<>(5);
        /*
        glasspane  = new GlassPaneProgress();
        setGlassPane(glasspane);
        glasspane.setMinimum(minBook);
        glasspane.setMaximum(maxBook);
        glasspane.setValue(minBook);
        glasspane.setStringPainted(true);
        */
        btSearchMem.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/Explore.png")));
         btFine.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/pay.png")));
         UIHelper.bindBackground(pnlFine);
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
        tblFine.getTableHeader().setReorderingAllowed(false);
    }
   /*
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FineManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FineManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FineManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FineManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FineManagement().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFine;
    private javax.swing.JButton btSearchMem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblImgMember;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblRegisterDate;
    private javax.swing.JLabel lblStatusMem;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlFine;
    private javax.swing.JPanel pnlImgMember;
    protected javax.swing.JTable tblFine;
    private javax.swing.JTextField txtMemberNo;
    // End of variables declaration//GEN-END:variables
}
