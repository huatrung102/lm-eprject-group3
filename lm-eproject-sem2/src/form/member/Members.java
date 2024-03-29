package form.member;
import ExSwing.ClPanelTransparent;
import Helpers.UIHelper;
import SysController.MessageHandle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Exceptions;

public class Members extends javax.swing.JFrame {
    DefaultTableModel tableModel;
    Vector row;
    Model.Members M ;//= new Members();
    public Members() {
        initComponents();
        lblFileName.setVisible(false);
        M= new Model.Members();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIHelper.bindBackground(pnlBackground);
        this.setTitle("Member Manage");
        start();
        
    }
    
    public void start(){
        setNormalMode();
        getList();   
    }
    
    private void getList(){
        tblMemList.setModel(Model.Members.Mems_getMemberList());
        tblMemList.getColumnModel().getColumn(0).setMinWidth(0);
        tblMemList.getColumnModel().getColumn(0).setMaxWidth(0);
        tblMemList.getColumnModel().getColumn(0).setWidth(0);
        tblMemList.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblMemList.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblMemList.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblMemList.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblMemList.getColumnModel().getColumn(5).setPreferredWidth(30);
        tblMemList.getColumnModel().getColumn(6).setPreferredWidth(30);
    }
    
    public void setNormalMode(){
        txtID.setEnabled(false);
        txtNo.setEnabled(false);
        btnChange.setEnabled(false);
        txtFirstname.setEnabled(false);
        txtLastname.setEnabled(false);
        txtPhone.setEnabled(false);
        txaAddress.setEnabled(false);
        txtEmail.setEnabled(false);
        lblRegdate.setEnabled(false);
        cbStatus.setEnabled(false);
        
        
        txtID.setEditable(false);
        txtNo.setEditable(false);
        txtFirstname.setEditable(false);
        txtLastname.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        txaAddress.setEditable(false);
        cbStatus.setEditable(false);
        
      //  lblFileName.setVisible(true);
        lblFileName.setText(null);
        txtID.setText(null);
        txtNo.setText(null);
        txtFirstname.setText(null);
        txtLastname.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txaAddress.setText(null);
        lblRegdate.setText(null);
        
        lblMemAvatar.setIcon(null);
        btnDelete.setEnabled(false);
        btnChange.setEnabled(false);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(false);
        btnSaveAdd.setEnabled(false);
        btnSaveAdd.setVisible(true);
        btnCancel.setEnabled(false);
        
        tblMemList.clearSelection();
    }
    
    public void setSelectedMode(){
        txtID.setEnabled(false);
        txtNo.setEnabled(false);
        btnChange.setEnabled(false);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
        
        
        txtID.setEditable(false);
        txtNo.setEditable(false);
        txtFirstname.setEditable(false);
        txtLastname.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        txaAddress.setEditable(false);
        cbStatus.setEditable(false);
        cbStatus.setEnabled(false);
        
        
        btnDelete.setEnabled(true);
        btnChange.setEnabled(false);
        btnUpdate.setEnabled(true);
        
        btnSaveUpdate.setVisible(false);
        btnSaveAdd.setVisible(true);
        btnSaveAdd.setEnabled(false);
        btnCancel.setEnabled(false);
    }
    
    public void setUpdateMode(){
        txtID.setEnabled(false);
        txtNo.setEnabled(false);
        btnChange.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
       
        
        txtID.setEditable(false);
        txtNo.setEditable(true);
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txaAddress.setEditable(true);
        cbStatus.setEditable(true);
       
        
        btnDelete.setEnabled(false);
        btnChange.setEnabled(true);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(true);
        btnSaveAdd.setVisible(false);
        btnCancel.setEnabled(true);
    }
    
    public void setAddNewMode(){
        txtID.setEnabled(false);
        txtNo.setEnabled(false);
        btnChange.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        txaAddress.setEnabled(true);
        lblRegdate.setEnabled(true);
        cbStatus.setEnabled(true);
  
        
        txtID.setEditable(false);
        txtNo.setEditable(true);
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txaAddress.setEditable(true);
        cbStatus.setEnabled(false);
        
                
        txtID.setText("Auto Generate");
        txtNo.setText("Auto Generate");
        txtFirstname.setText(null);
        txtLastname.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txaAddress.setText(null);
        lblRegdate.setText(null);
        
        lblMemAvatar.setIcon(null);
        btnDelete.setEnabled(false);
        btnChange.setEnabled(true);
        btnUpdate.setEnabled(false);
        
        btnSaveUpdate.setVisible(false);
        btnSaveAdd.setVisible(true);
        btnSaveAdd.setEnabled(true);
        btnCancel.setEnabled(true);
        
        tblMemList.clearSelection();
    }
    
    private static void copyFile(File source, File dest) throws IOException {
            InputStream input = null;
            OutputStream output = null;
            try {
                    input = new FileInputStream(source);
                    output = new FileOutputStream(dest);
                    byte[] buf = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buf)) > 0) {
                            output.write(buf, 0, bytesRead);
                    }
            } finally {
                    input.close();
                    output.close();
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        pnlBackground = new javax.swing.JPanel();
        jPanel3 = new ClPanelTransparent();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        pnlUpdate = new ClPanelTransparent();
        pnlProfile = new javax.swing.JPanel();
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
        btnSaveAdd = new javax.swing.JButton();
        btnSaveUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblRegdate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNo = new javax.swing.JTextField();
        lblMemAvatar = new javax.swing.JLabel();
        btnChange = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblFileName = new javax.swing.JLabel();
        jPanel4 = new ClPanelTransparent();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMemList = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Members Managment");
        setMinimumSize(new java.awt.Dimension(1110, 620));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.yellow);
        jLabel1.setText("Search Member");

        jLabel2.setText("Name:");

        jLabel4.setText("Phone");

        jLabel5.setText("Status");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "InActive" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Explore.png"))); // NOI18N
        jButton1.setText("Search");

        jLabel21.setText("Email");

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
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel7.setText("ID");

        jLabel8.setText("First Name");

        jLabel9.setText("Last Name");

        jLabel10.setText("Phone");

        jLabel11.setText("Email");

        jLabel14.setText("Date");

        jLabel15.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        txaAddress.setColumns(20);
        txaAddress.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txaAddress.setLineWrap(true);
        txaAddress.setRows(5);
        txaAddress.setText("\n");
        txaAddress.setWrapStyleWord(true);
        txaAddress.setAutoscrolls(false);
        jScrollPane2.setViewportView(txaAddress);

        jLabel16.setText("Address");

        btnSaveAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveAdd.setText("Save");
        btnSaveAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAddActionPerformed(evt);
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
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel3.setText("No");

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnSaveAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel16))
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbStatus, 0, 180, Short.MAX_VALUE)
                            .addComponent(txtPhone)
                            .addComponent(txtLastname)
                            .addComponent(txtFirstname)
                            .addComponent(txtEmail)
                            .addComponent(jScrollPane2)
                            .addComponent(lblRegdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtNo))))
                .addContainerGap())
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(lblRegdate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveAdd)
                    .addComponent(btnSaveUpdate)
                    .addComponent(btnCancel))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblMemAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/staff.png"))); // NOI18N
        lblMemAvatar.setPreferredSize(new java.awt.Dimension(140, 140));

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Picture.png"))); // NOI18N
        btnChange.setText("Change");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

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

        lblFileName.setText("jLabel6");
        lblFileName.setEnabled(false);

        javax.swing.GroupLayout pnlUpdateLayout = new javax.swing.GroupLayout(pnlUpdate);
        pnlUpdate.setLayout(pnlUpdateLayout);
        pnlUpdateLayout.setHorizontalGroup(
            pnlUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChange, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMemAvatar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblFileName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlUpdateLayout.setVerticalGroup(
            pnlUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblMemAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFileName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlUpdateLayout.createSequentialGroup()
                .addComponent(pnlProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        tblMemList.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMemListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMemList);
        if (tblMemList.getColumnModel().getColumnCount() > 0) {
            tblMemList.getColumnModel().getColumn(0).setResizable(false);
            tblMemList.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblMemList.getColumnModel().getColumn(1).setResizable(false);
            tblMemList.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblMemList.getColumnModel().getColumn(2).setResizable(false);
            tblMemList.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblMemList.getColumnModel().getColumn(3).setResizable(false);
            tblMemList.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblMemList.getColumnModel().getColumn(4).setResizable(false);
            tblMemList.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnAdd.setText("New Member");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(java.awt.Color.yellow);
        jLabel19.setText("Member List");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd)))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(btnAdd))
                .addGap(8, 8, 8)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1135, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(162, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlBackground, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        setAddNewMode();
        txtFirstname.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        setSelectedMode();
        setUpdateMode();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAddActionPerformed
        Model.Members obj;
        obj = new Model.Members();
        
//        obj.Mem_No = txtNo.getText();
        //obj.Mem_Id = txtID.getText();
        obj.Mem_FirstName = txtFirstname.getText();
        obj.Mem_LastName = txtLastname.getText();
        obj.Mem_Phone = txtPhone.getText();
        obj.Mem_Address  = txaAddress.getText();
        obj.Mem_Email = txtEmail.getText();
        
        //Copy file to imgBook folder
        if(lblMemAvatar.getIcon() == null){
            obj.Mem_ImageFile = "imgMem/MemNoAvatar.png";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newfilename = sdf.format(Calendar.getInstance().getTime());
            File labelicon = new File(lblMemAvatar.getIcon().toString());
            File desfile = new File("imgMem\\"+newfilename+"_"+labelicon.getName());
            try {
                copyFile(labelicon, desfile);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        obj.Mem_ImageFile = "imgMem/"+desfile.getName();
        }
        //Ket thuc phan upload Image
        
//        if(cbStatus.getSelectedItem() == "Active"){
//            obj.Mem_Status = true;
//        } else if (cbStatus.getSelectedItem() == "Inactive") {
//            obj.Mem_Status = false;
//        }
        
        int rt = Model.Members.Members_Insert(obj);
        if(rt == 1){
            setAddNewMode();
            getList();
        }else if(rt == 0){
            txtNo.requestFocus();
        }else if(rt == 3){
            txtEmail.requestFocus();
        }
        MessageHandle.showMessage(MessageHandle.Obj_Member, MessageHandle.Action_insert, rt);
        
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
         
               
        if (txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Email do not NULL");
            txtEmail.requestFocus();
            return;
        } else if(txtEmail.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Email do not longer 50 chars");
            txtEmail.requestFocus();
            return;
        }        
        if(cbStatus.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a Status");
            cbStatus.requestFocus();
            return;
        }
      
             
//        String pattern = "(^0[\\d]{9-10})"; //java regex pattern phone number
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(txtPhone.getText());
//        if(txtPhone.getText().isEmpty()){
//            JOptionPane.showMessageDialog(null, "Please input Staff Phone!");
//            txtPhone.requestFocus();
//            return;
//        } else if(!m.find()){
//            JOptionPane.showMessageDialog(null, "Phone must be number, Ex:0xxxxxxxxxx");
//            txtPhone.requestFocus();
//            return;
//        }
        
        if ((String)cbStatus.getSelectedItem() == "Active"){
            obj.Mem_Status = true;
        } else {
            obj.Mem_Status = false;
        }
        
        if(txaAddress.getText().isEmpty()){
            obj.Mem_Address = "";
        }
        
    }//GEN-LAST:event_btnSaveAddActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        //Chọn file hình và lấy đường dẫn
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File sourefile = fc.getSelectedFile();
        
        //Set image vào label cover
        ImageIcon newIcon = new ImageIcon(sourefile.getPath());
        lblMemAvatar.setIcon(newIcon);
        lblFileName.setText(sourefile.getPath());
    }//GEN-LAST:event_btnChangeActionPerformed

    private void tblMemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMemListMouseClicked
        setSelectedMode();
              
        int line = tblMemList.getSelectedRow();
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblMemList.getModel();
        String memid = (String)tbm.getValueAt(line, 0);
        
        Model.Members mem = Model.Members.Members_getMemberByMemberId(memid);
        txtID.setText(mem.Mem_Id);
        txtNo.setText(mem.Mem_No);
        txtFirstname.setText(mem.Mem_FirstName);
        txtLastname.setText(mem.Mem_LastName);
        txtPhone.setText(mem.Mem_Phone);
        txtEmail.setText(mem.Mem_Email);
        txaAddress.setText(mem.Mem_Address);
        lblRegdate.setText(mem.Mem_CreateDate);
        if(mem.Mem_Status == false){
            cbStatus.setSelectedItem("Inactive");
        } else {
            cbStatus.setSelectedItem("Active");
        }
        ImageIcon icon = new ImageIcon(mem.Mem_ImageFile);
        lblMemAvatar.setIcon(icon);
    }//GEN-LAST:event_tblMemListMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setNormalMode();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateActionPerformed
        Model.Members obj;
        obj = new Model.Members();
        
        //obj.Mem_No = txtNo.getText();
        String memid = txtID.getText();
        obj.Mem_FirstName = txtFirstname.getText();
        obj.Mem_LastName = txtLastname.getText();
        obj.Mem_Phone = txtPhone.getText();
        obj.Mem_Address  = txaAddress.getText();
        obj.Mem_Email = txtEmail.getText();
        
        //Copy file to imgBook folder
        
        
        ////  cho nay cho anh Uy
        if(lblFileName.getText() == null){
            obj.Mem_ImageFile = lblMemAvatar.getIcon().toString();
        }else{
            if(lblMemAvatar.getIcon() == null){
            obj.Mem_ImageFile = "imgMem/MemNoAvatar.png";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newfilename = sdf.format(Calendar.getInstance().getTime());
            File labelicon = new File(lblFileName.getText());
            File desfile = new File("imgMem\\"+newfilename+"_"+labelicon.getName());
            try {
                copyFile(labelicon, desfile);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            obj.Mem_ImageFile = "imgMem/" + desfile.getName();
        }
        }
        
        //Ket thuc phan upload Image
        
        if(cbStatus.getSelectedItem() == "Active"){
            obj.Mem_Status = true;
        } else if (cbStatus.getSelectedItem() == "Inactive") {
            obj.Mem_Status = false;
        }
        
        
        
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
         
               
        if (txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Email do not NULL");
            txtEmail.requestFocus();
            return;
        } else if(txtEmail.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Email do not longer 50 chars");
            txtEmail.requestFocus();
            return;
        }        
        if(cbStatus.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a Status");
            cbStatus.requestFocus();
            return;
        }
        obj.Mem_Status = (String)cbStatus.getSelectedItem() == "Active";
        
        if(txaAddress.getText().isEmpty()){
            obj.Mem_Address = "";
        }
        int rt = Model.Members.Members_Update(obj, memid);
        if(rt == 1){
            setNormalMode();
            getList();
        }else if(rt == 0){
            txtNo.requestFocus();
        }else if(rt == 3){
            txtEmail.requestFocus();
        }
        MessageHandle.showMessage(MessageHandle.Obj_Member, MessageHandle.Action_update, rt);
        
    }//GEN-LAST:event_btnSaveUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String Mem_Id = txtID.getText();
        int del = Model.Members.Members_Lock(Mem_Id);
        MessageHandle.showMessage(MessageHandle.Obj_Member,MessageHandle.Action_delete, del);  
        getList();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Members().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSaveAdd;
    private javax.swing.JButton btnSaveUpdate;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblFileName;
    private javax.swing.JLabel lblMemAvatar;
    private javax.swing.JLabel lblRegdate;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlUpdate;
    private javax.swing.JTable tblMemList;
    private javax.swing.JTextArea txaAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
