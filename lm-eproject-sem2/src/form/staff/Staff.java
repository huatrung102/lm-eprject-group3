package form.staff;
import form.member.*;
import ExSwing.ClPanelTransparent;
import Helpers.UIHelper;
import Model.Staffs;
import SysController.MessageHandle;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TraPhucVinh
 */
public class Staff extends javax.swing.JFrame {
    Vector row;
    DefaultTableModel tbl;
    /**
     * Creates new form List
     */
    public Staff() {
        initComponents();
        UIHelper.bindBackground(pnlBackground);
        this.setTitle("Staff Manage");
        start();
        
        
        
    }
    private void getList(){
        tblStaffList.setModel(Model.Staffs.getListStaff());
        tblStaffList.getColumnModel().getColumn(0).setMinWidth(0);
        tblStaffList.getColumnModel().getColumn(0).setMaxWidth(0);
        tblStaffList.getColumnModel().getColumn(0).setWidth(0);
        tblStaffList.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblStaffList.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblStaffList.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblStaffList.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblStaffList.getColumnModel().getColumn(5).setPreferredWidth(30);
        tblStaffList.getColumnModel().getColumn(6).setPreferredWidth(30);
    }
    public void start(){
        setNormalMode();
        getList();
        
        
    }
    
    public void setNormalMode(){
        txtID.setEnabled(false);
        txtLogin.setEnabled(false);
        txtPassword.setEnabled(false);
        txtFirstname.setEnabled(false);
        txtLastname.setEnabled(false);
        txtPhone.setEnabled(false);
        txaAddress.setEnabled(false);
        txtEmail.setEnabled(false);
        lblRegdate.setEnabled(false);
        cbStatus.setEnabled(false);
        cbRole.setEnabled(false);
        
        txtID.setEditable(false);
        txtLogin.setEditable(false);
        txtPassword.setEditable(false);
        txtFirstname.setEditable(false);
        txtLastname.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        txaAddress.setEditable(false);
        cbStatus.setEditable(false);
        cbRole.setEditable(false);
        
        txtID.setText(null);
        txtFirstname.setText(null);
        txtLastname.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txaAddress.setText(null);
        lblRegdate.setText(null);
        
        
        btnDelete.setEnabled(false);
        btnChange.setEnabled(true);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(false);
        btnSaveInsert.setEnabled(false);
        btnSaveInsert.setVisible(true);
        btnCancel.setEnabled(false);
        
        tblStaffList.clearSelection();
    }
    
    public void setSelectedMode(){
        txtID.setEnabled(false);
        txtLogin.setEnabled(true);
        txtPassword.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
        cbRole.setEnabled(true);
        
        txtID.setEditable(false);
        txtLogin.setEditable(true);
        txtPassword.setEditable(true);
        txtFirstname.setEditable(false);
        txtLastname.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        txaAddress.setEditable(false);
        cbStatus.setEditable(false);
        cbRole.setEditable(false);
        
        btnDelete.setEnabled(true);
        btnChange.setEnabled(true);
        btnUpdate.setEnabled(true);
        
        btnSaveUpdate.setVisible(false);
        btnSaveInsert.setVisible(true);
        btnSaveInsert.setEnabled(false);
        btnCancel.setEnabled(false);
    }
    
    public void setUpdateMode(){
        txtID.setEnabled(false);
        txtLogin.setEnabled(true);
        txtPassword.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
        cbRole.setEnabled(true);
        
        txtID.setEditable(false);
        txtLogin.setEditable(true);
        txtPassword.setEditable(true);
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txaAddress.setEditable(true);
        cbStatus.setEditable(true);
        cbRole.setEditable(true);
        
        btnDelete.setEnabled(false);
        btnChange.setEnabled(false);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(true);
        btnSaveInsert.setVisible(false);
        btnCancel.setEnabled(true);
    }
    
    public void setAddNewMode(){
        txtID.setEnabled(false);
        txtLogin.setEnabled(true);
        txtPassword.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
        cbRole.setEnabled(true);
        
        txtID.setEditable(false);
        txtLogin.setEditable(true);
        txtPassword.setEditable(true);
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txaAddress.setEditable(true);
        cbStatus.setEditable(true);
        cbRole.setEditable(true);
        
        txtID.setText("Auto Generate");
        txtLogin.setText(null);
        txtPassword.setText(null);
        txtFirstname.setText(null);
        txtLastname.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txaAddress.setText(null);
        lblRegdate.setText(null);
        
        
        btnDelete.setEnabled(false);
        btnChange.setEnabled(false);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(false);
        btnSaveInsert.setVisible(true);
        btnSaveInsert.setEnabled(true);
        btnCancel.setEnabled(true);
        
        tblStaffList.clearSelection();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        pnlBackground = new javax.swing.JPanel();
        jPanel3 = new ClPanelTransparent();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel2 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        txtLastname = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaAddress = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox();
        btnSaveInsert = new javax.swing.JButton();
        btnSaveUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblRegdate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        lblCover = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        jPanel4 = new ClPanelTransparent();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStaffList = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Members Managment");
        setMinimumSize(new java.awt.Dimension(1110, 620));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.yellow);
        jLabel1.setText("Search Staff");

        jLabel2.setText("Name:");

        jTextField1.setText("Trà Phúc Vĩnh");

        jLabel5.setText("Status");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Explore.png"))); // NOI18N
        btnSearch.setText("Search");

        jLabel21.setText("Email");

        jTextField9.setText("lengochue711603@gmail.com.vn");

        jLabel12.setText("Role");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        txtID.setText("M00001");

        jLabel7.setText("ID");

        txtFirstname.setText("Trà Phúc Vĩnh");

        txtLastname.setText("Uy");

        txtPhone.setText("01223131459");

        jLabel8.setText("First Name");

        jLabel9.setText("Last Name");

        jLabel10.setText("Phone");

        jLabel11.setText("Email");

        jLabel14.setText("Reg.Date");

        jLabel15.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txaAddress.setColumns(20);
        txaAddress.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txaAddress.setLineWrap(true);
        txaAddress.setRows(5);
        txaAddress.setText("Nhà số 1 đường Nguyễn Huệ, P. Bến Nghé Quận 1, TPHCM, Việt Nam");
        txaAddress.setWrapStyleWord(true);
        txaAddress.setAutoscrolls(false);
        jScrollPane2.setViewportView(txaAddress);

        jLabel16.setText("Address");

        txtEmail.setText("pigbanguy@gmail.com");

        jLabel3.setText("Role");

        cbRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSaveInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveInsert.setText("Save");
        btnSaveInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInsertActionPerformed(evt);
            }
        });

        btnSaveUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveUpdate.setText("Save");
        btnSaveUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdateActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel.png"))); // NOI18N
        btnCancel.setText("Cancel");

        jLabel4.setText("Login");

        jLabel13.setText("Password");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel16))
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhone)
                            .addComponent(txtLastname)
                            .addComponent(txtFirstname)
                            .addComponent(txtID)
                            .addComponent(txtEmail)
                            .addComponent(jScrollPane2)
                            .addComponent(lblRegdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword)
                            .addComponent(txtLogin))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel15))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnSaveInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(lblRegdate, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveInsert)
                    .addComponent(btnSaveUpdate)
                    .addComponent(btnCancel))
                .addGap(23, 23, 23))
        );

        lblCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/staff.png"))); // NOI18N
        lblCover.setPreferredSize(new java.awt.Dimension(140, 140));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Picture.png"))); // NOI18N
        btnChange.setText("Change");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChange, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCover, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnChange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblStaffList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "First Name", "Last Name", "Phone", "Email", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaffList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblStaffList);
        if (tblStaffList.getColumnModel().getColumnCount() > 0) {
            tblStaffList.getColumnModel().getColumn(0).setResizable(false);
            tblStaffList.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblStaffList.getColumnModel().getColumn(1).setResizable(false);
            tblStaffList.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblStaffList.getColumnModel().getColumn(2).setResizable(false);
            tblStaffList.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblStaffList.getColumnModel().getColumn(3).setResizable(false);
            tblStaffList.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblStaffList.getColumnModel().getColumn(4).setResizable(false);
            tblStaffList.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/first.png"))); // NOI18N
        jPanel8.add(btnFirst);

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pre.png"))); // NOI18N
        jPanel8.add(btnPre);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/next.png"))); // NOI18N
        jPanel8.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/last.png"))); // NOI18N
        jPanel8.add(btnLast);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(java.awt.Color.yellow);
        jLabel19.setText("Staff List");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnAdd.setText("Add New");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(36, 36, 36))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(btnAdd))
                .addGap(9, 9, 9)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnlBackground, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        
    }//GEN-LAST:event_btnChangeActionPerformed

    private void btnSaveInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInsertActionPerformed
                 
        Model.Staffs obj;
        obj = new Model.Staffs();
        obj.Staff_Address  = txaAddress.getText();
        obj.Staff_Email = txtEmail.getText();
        obj.Staff_FirstName = txtFirstname.getText();
        obj.Staff_ImageFile = "";
        obj.Staff_LastName = txtLastname.getText();
        obj.Staff_Login = txtLogin.getText();
        obj.Staff_Password = String.valueOf(txtPassword.getPassword());
        obj.Staff_Phone = txtPhone.getText();
        obj.Staff_Role = String.valueOf(cbRole.getSelectedItem());
        int rt = Model.Staffs.Staffs_Insert(obj);
        if(rt == 1){
            setAddNewMode();
            getList();
        }else if(rt == 0){
            txtLogin.requestFocus();
        }else if(rt == 3){
            txtEmail.requestFocus();
        }
        MessageHandle.showMessage(MessageHandle.Obj_Staff, MessageHandle.Action_insert, rt);
        
        
        
    }//GEN-LAST:event_btnSaveInsertActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        setAddNewMode();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        setSelectedMode();
        setUpdateMode();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateActionPerformed
        Staffs obj = new Staffs();
        String pattern; 
        Pattern r;
        Matcher m;
        //Validate
        
        if (txtFirstname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First Name do not NULL");
            txtFirstname.requestFocus();
            return;
        } else if(txtFirstname.getText().length()>30){
            JOptionPane.showMessageDialog(null, "First Name do not longer 30 chars");
            txtFirstname.requestFocus();
            return;
        }
        
         if (txtLastname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Last Name do not NULL");
            txtLastname.requestFocus();
            return;
        } else if(txtLastname.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Last Name do not longer 50 chars");
            txtLastname.requestFocus();
            return;
        }
         
        if (txtLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Login do not NULL");
            txtLogin.requestFocus();
            return;
        } else if(txtLogin.getText().length()>25){
            JOptionPane.showMessageDialog(null, "Login do not longer 25 chars");
            txtLogin.requestFocus();
            return;
        }
        
        if (txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Email do not NULL");
            txtEmail.requestFocus();
            return;
        } else if(txtEmail.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Email do not longer 50 chars");
            txtEmail.requestFocus();
            return;
        }
        
//        if (txaAddress.getText().isEmpty()){
//            JOptionPane.showMessageDialog(null, "Address do not NULL");
//            txaAddress.requestFocus();
//            return;
//        } else if(txaAddress.getText().length()>200){
//            JOptionPane.showMessageDialog(null, "Address do not longer 200 chars");
//            txaAddress.requestFocus();
//            return;
//        }
        
            
        if(cbStatus.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a Status");
            cbStatus.requestFocus();
            return;
        }
        
        if(cbRole.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a Role");
            cbRole.requestFocus();
            return;
        }
        
        pattern = "(\\d)";
        r = Pattern.compile(pattern);
        m = r.matcher(txtPhone.getText());
        if(txtPhone.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please input Staff Phone!");
            txtPhone.requestFocus();
            return;
        } else if(!m.find()){
            JOptionPane.showMessageDialog(null, "Phone must be number!");
            txtPhone.requestFocus();
            return;
        }
//        } else if (txtPhone.getText().startsWith("")){
//            JOptionPane.showConfirmDialog(null, "Book price must be larger 0");
//            txtPrice.requestFocus();
//            return;
//        }
        
                
        if(txaAddress.getText().isEmpty()){
            obj.Staff_Address = "";
        }
        
        //Get data from form and add to Object
        obj.Staff_Id = txtID.getText();
        obj.Staff_Address = txaAddress.getText();
        obj.Staff_FirstName = txtFirstname.getText();
        obj.Staff_LastName = txtLastname.getText();       
        obj.Staff_Phone = txtPhone.getText();
        obj.Staff_Login = txtLogin.getText();
        obj.Staff_Role =String.valueOf(cbRole.getSelectedItem());//Model.Staffs.Staffs_getStaffbyStaffId(String)instanceof cbRole.getSelectedItem().Staff_Id;
        obj.Staff_Status = (boolean) cbStatus.getSelectedItem(); 
        //obj.Book_ImageFile = lblCover.getIcon().toString();
        obj.Staff_ImageFile = "/imgBook/Nocover.JPG";
        if ((String)cbStatus.getSelectedItem() == "Active"){
            obj.Staff_isDeleted = false;
        } else {
            obj.Staff_isDeleted = true;
        }
        
        setNormalMode();
        int line = tblStaffList.getSelectedRow();
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblStaffList.getModel();
        String Staff_Id = (String)tbm.getValueAt(line, 0);
        tblStaffList.setModel(Model.Staffs.Staffs_getStaffListbyStaffId(Staff_Id));
        getList();
    }//GEN-LAST:event_btnSaveUpdateActionPerformed

    private void tblStaffListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffListMouseClicked
        setSelectedMode();
              
        int line = tblStaffList.getSelectedRow();
        
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblStaffList.getModel();
        String Staff_Id = (String)tbm.getValueAt(line, 0);
        
        Staffs obj = Staffs.Staffs_getStaffbyStaffId(Staff_Id);
        
        txtID.setText("Auto Generate");
        txtLogin.setText(obj.Staff_Login);
        txtFirstname.setText(obj.Staff_FirstName);
        txtLastname.setText(obj.Staff_LastName);
        txtEmail.setText(obj.Staff_Email);
        txtPhone.setText(obj.Staff_Phone);
        txaAddress.setText(obj.Staff_Address);
        lblRegdate.setText(obj.Staff_CreateDate);
        cbStatus.setSelectedItem(obj.Staff_Status);
        cbRole.setSelectedItem(obj.Staff_Role);
        boolean status = false;
        if(status == true){
            cbStatus.setSelectedIndex(1);
        } else {
            cbStatus.setSelectedIndex(0);
        }
        
        ImageIcon icon = new ImageIcon(obj.Staff_ImageFile);
        lblCover.setIcon(icon);
    }//GEN-LAST:event_tblStaffListMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        txtID.setText("");
        txtLogin.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txaAddress.setText("");
        lblRegdate.setText("");
        cbStatus.setSelectedItem(-1);
        cbRole.setSelectedItem(-1);
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSaveInsert;
    private javax.swing.JButton btnSaveUpdate;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cbRole;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblCover;
    private javax.swing.JLabel lblRegdate;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTable tblStaffList;
    private javax.swing.JTextArea txaAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}