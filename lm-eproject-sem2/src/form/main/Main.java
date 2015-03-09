/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.main;

import Config.SysVar;
import DemoTestFunction.mainForm;
import ExSwing.*;
import Helpers.UIHelper;
import Model.Staffs;
import SysController.MessageHandle;
import form.book.BookList;
import form.category.Category;
import form.changepass.ChangePassword;
import form.copy.Copies;
import form.fine.FineManagement;
import form.fine.FineManagement1;
import form.ir.IssueManagement;
import form.ir.ReturnManagement;
import form.ir.ReturnManagement1;
import form.member.Members;
import form.staff.Profile;
import form.staff.Staff;

import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.Timer;

/**
 *
 * @author Administrator PC
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private String[] imageFile={"home.png","member_management.png","book_management.png"
            , "staff_management.png","ir_management.png","fine_management.png"};
    private boolean[] role_Admin = {Boolean.TRUE,Boolean.TRUE
                                    ,Boolean.TRUE,Boolean.TRUE
                                    ,Boolean.TRUE,Boolean.TRUE};
    private boolean[] role_IR = {Boolean.TRUE,Boolean.FALSE
                                    ,Boolean.FALSE,Boolean.FALSE
                                    ,Boolean.TRUE,Boolean.FALSE};
    private boolean[] role_Fine = {Boolean.TRUE,Boolean.FALSE
                                    ,Boolean.FALSE,Boolean.FALSE
                                    ,Boolean.FALSE,Boolean.TRUE};
    private boolean[] role_Member = {Boolean.TRUE,Boolean.TRUE
                                    ,Boolean.FALSE,Boolean.FALSE
                                    ,Boolean.FALSE,Boolean.FALSE};
    private boolean[] role_Book = {Boolean.TRUE,Boolean.FALSE
                                    ,Boolean.TRUE,Boolean.FALSE
                                    ,Boolean.FALSE,Boolean.FALSE};
    public Staffs staffLogin;
    SimpleDateFormat simpleDate = new SimpleDateFormat("EEE, dd/MM/yyyy");
    SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm:ss");
    private String[] strDays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu",
        "FrI", "Sat" };
    public void setStaff(Staffs staff){
        this.staffLogin = staff;
    }
    
    public Main() {
        initComponents();
        setDateTime();
        initForm();
        Staffs staff = Staffs.Staff_Logined;
        //test role
       // staff.Staff_Role = SysVar.role_Admin;
        setStaff(staff);
        loadTabByRole();
        
       
       // validate();
    }
        private void initForm(){
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
				Main.class.getResource("/image/main.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        setContentPane(contentpane);
        contentpane.setBackground(Color.BLACK);
        addIconTabbedPane();
        //set panel Home        
        pnlHome.setLayout(null);
        jTabbedPane1.setBounds(5, 5, 809, 620);
        
        lblWelcome.setFont(new Font("Droid Sans", Font.BOLD, 36));
        lblWelcome.setForeground(new Color(255, 255, 255));
        lblWelcome.setBounds(200, 261, 573, 63);
        
      //set side panel 
        setSidePanelByMember(sidePanel);
     //background wall
        
        UIHelper.bindBackground(contentpane,"/image/background.png");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void bindTab(boolean[] role){
        int max = role.length;
        int index = 0;
        for(int i = 0; i< max;i++){
            if(!(role[i])){
                jTabbedPane1.remove(index);                
            }else
                index++;
        }
    }
    private boolean loadTabByRole(){
        if(staffLogin != null){
            switch(staffLogin.Staff_Role){
                case SysVar.role_Admin:
                    bindTab(role_Admin);
                    break;
                case SysVar.role_Book:
                    bindTab(role_Book);
                    break;
                case SysVar.role_Fine:
                    bindTab(role_Fine);
                    break;
                case SysVar.role_IR:
                    bindTab(role_IR);
                    break;
                case SysVar.role_Member:
                    bindTab(role_Member);
                    break;    
            }
        }else{
            MessageHandle.showError("Please logout and Login Again");
            return false;
        }
        return true;
    }
    private void addIconTabbedPane(){
        for(int i = 0; i < imageFile.length ; i++){
            jTabbedPane1.setIconAt(i, new ImageIcon( mainForm.class
						.getResource("/image/"+ imageFile[i])));
        }
    }
    
    private void setSidePanelByMember(JPanel content){
/*       
 content.removeAll();
        JLabel lblAdminAcc = new JLabel("Admin Account: ");
        lblAdminAcc.setForeground(Color.WHITE);
        lblAdminAcc.setFont(new Font("Droid Sans", Font.BOLD, 18));
        lblAdminAcc.setBounds(20, 25, 200, 17);
        content.add(lblAdminAcc);
        
        JLabel lblAdminNumber = new JLabel("2");
        lblAdminNumber.setForeground(Color.ORANGE);
        lblAdminNumber.setFont(new Font("Droid Sans", Font.BOLD, 18));
        lblAdminNumber.setBounds(200, 25, 200, 17);
        content.add(lblAdminNumber);
        
        
        
        JTextField txtWOToday = new ClRoundTransTxt();
        txtWOToday.setHorizontalAlignment(SwingConstants.RIGHT);
        txtWOToday.setForeground(Color.YELLOW);
        txtWOToday.setFont(new Font("Tahoma", Font.PLAIN, 35));
        txtWOToday.setEditable(false);
        txtWOToday.setColumns(4);
        // txtWOToday.setBackground(Color.BLACK);
        txtWOToday.setBounds(190, 20, 120, 45);
        content.add(txtWOToday);
        */
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentpane = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlHome = new ClGlossyPanel();
        lblWelcome = new LabelGradient("");
        bgHome = new javax.swing.JLabel();
        pnlMember = new ClGlossyPanel();
        iconMem1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new ClPanelTransparent();
        jLabel12 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        pnlBook = new ClGlossyPanel();
        iconMem3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iconMem4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new ClPanelTransparent();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        pnlStaff = new ClGlossyPanel();
        iconMem5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new ClPanelTransparent();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        pnlIR = new ClGlossyPanel();
        iconMem7 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        iconMem8 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new ClPanelTransparent();
        jLabel19 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        pnlFine = new ClGlossyPanel();
        iconMem9 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new ClPanelTransparent();
        jLabel20 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        sidePanel = new ClPanelTransparent();
        iconMem11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        iconMem12 = new javax.swing.JLabel();
        iconMem13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(Main.class, "Main.title")); // NOI18N
        setBounds(new java.awt.Rectangle(100, 5, 1166, 736));
        setPreferredSize(new java.awt.Dimension(1080, 700));
        setResizable(false);

        contentpane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1240, 720));

        lblWelcome.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.lblWelcome.text")); // NOI18N
        lblWelcome.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        bgHome.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.bgHome.text")); // NOI18N

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bgHome)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(lblWelcome)))
                .addContainerGap(589, Short.MAX_VALUE))
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addComponent(bgHome)
                .addGap(283, 283, 283)
                .addComponent(lblWelcome)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlHome.TabConstraints.tabTitle"), pnlHome); // NOI18N

        iconMem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/member.png"))); // NOI18N
        iconMem1.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem1.text")); // NOI18N
        iconMem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel1.text")); // NOI18N

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel12.text")); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(java.awt.Color.orange);
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel24.text")); // NOI18N
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel25.text")); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(java.awt.Color.orange);
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel26.text")); // NOI18N
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel27.text")); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(java.awt.Color.orange);
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel28.text")); // NOI18N
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel29.text")); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(java.awt.Color.yellow);
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel30.text")); // NOI18N
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jSeparator1.setOpaque(true);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 204, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel31.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel24))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addContainerGap(325, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMemberLayout = new javax.swing.GroupLayout(pnlMember);
        pnlMember.setLayout(pnlMemberLayout);
        pnlMemberLayout.setHorizontalGroup(
            pnlMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMemberLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconMem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        pnlMemberLayout.setVerticalGroup(
            pnlMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMemberLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(iconMem1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(pnlMemberLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlMember.TabConstraints.tabTitle"), pnlMember); // NOI18N

        iconMem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem3.setIcon(new javax.swing.ImageIcon("image/copy.png"));
        iconMem3.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem3.text")); // NOI18N
        iconMem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel3.text")); // NOI18N

        iconMem4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book.png"))); // NOI18N
        iconMem4.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem4.text")); // NOI18N
        iconMem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem4MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel4.text")); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel32.text")); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel34.text")); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(java.awt.Color.yellow);
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel39.text")); // NOI18N
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 204, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel40.text")); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(java.awt.Color.yellow);
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel41.text")); // NOI18N
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel39))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel41))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBookLayout = new javax.swing.GroupLayout(pnlBook);
        pnlBook.setLayout(pnlBookLayout);
        pnlBookLayout.setHorizontalGroup(
            pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconMem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(iconMem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(276, 276, 276)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBookLayout.setVerticalGroup(
            pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlBookLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(pnlBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookLayout.createSequentialGroup()
                        .addComponent(iconMem4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(pnlBookLayout.createSequentialGroup()
                        .addComponent(iconMem3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(359, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlBook.TabConstraints.tabTitle"), pnlBook); // NOI18N

        iconMem5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/staff.png"))); // NOI18N
        iconMem5.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem5.text")); // NOI18N
        iconMem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem5MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel5.text")); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel33.text")); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(java.awt.Color.orange);
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel35.text")); // NOI18N
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel36.text")); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(java.awt.Color.orange);
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel37.text")); // NOI18N
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel38.text")); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(java.awt.Color.orange);
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel42.text")); // NOI18N
        jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel43.text")); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(java.awt.Color.yellow);
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel44.text")); // NOI18N
        jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jSeparator2.setOpaque(true);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 204, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel45.text")); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel46.text")); // NOI18N

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setForeground(java.awt.Color.orange);
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel47.text")); // NOI18N
        jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel48.text")); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(java.awt.Color.orange);
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel49.text")); // NOI18N
        jLabel49.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel35))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel42))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlStaffLayout = new javax.swing.GroupLayout(pnlStaff);
        pnlStaff.setLayout(pnlStaffLayout);
        pnlStaffLayout.setHorizontalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStaffLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconMem5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(480, 480, 480)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlStaffLayout.setVerticalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStaffLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(iconMem5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStaffLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlStaff.TabConstraints.tabTitle"), pnlStaff); // NOI18N

        pnlIR.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N

        iconMem7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/issue_MENU.png"))); // NOI18N
        iconMem7.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem7.text")); // NOI18N
        iconMem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem7MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel7.text")); // NOI18N

        iconMem8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return_Menu.png"))); // NOI18N
        iconMem8.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem8.text")); // NOI18N
        iconMem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem8MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel8.text")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel19.text")); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(java.awt.Color.yellow);
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel50.text")); // NOI18N
        jLabel50.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel51.text")); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(java.awt.Color.yellow);
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel52.text")); // NOI18N
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 204, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel57.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel50))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlIRLayout = new javax.swing.GroupLayout(pnlIR);
        pnlIR.setLayout(pnlIRLayout);
        pnlIRLayout.setHorizontalGroup(
            pnlIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIRLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconMem7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(pnlIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconMem8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(272, 272, 272)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlIRLayout.setVerticalGroup(
            pnlIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIRLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(pnlIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIRLayout.createSequentialGroup()
                        .addComponent(iconMem7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(pnlIRLayout.createSequentialGroup()
                        .addComponent(iconMem8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIRLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlIR.TabConstraints.tabTitle"), pnlIR); // NOI18N

        iconMem9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/fine.png"))); // NOI18N
        iconMem9.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem9.text")); // NOI18N
        iconMem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem9MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel9.text")); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel20.text")); // NOI18N

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(java.awt.Color.orange);
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel53.text")); // NOI18N
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 204, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel61.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel53))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFineLayout = new javax.swing.GroupLayout(pnlFine);
        pnlFine.setLayout(pnlFineLayout);
        pnlFineLayout.setHorizontalGroup(
            pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFineLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconMem9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(480, 480, 480)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFineLayout.setVerticalGroup(
            pnlFineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFineLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(iconMem9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(pnlFineLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(Main.class, "Main.pnlFine.TabConstraints.tabTitle"), pnlFine); // NOI18N

        iconMem11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/viewprofile.png"))); // NOI18N
        iconMem11.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem11.text")); // NOI18N
        iconMem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem11MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel14.text")); // NOI18N

        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel15.text")); // NOI18N

        iconMem12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/changepassword.png"))); // NOI18N
        iconMem12.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem12.text")); // NOI18N
        iconMem12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem12MouseClicked(evt);
            }
        });

        iconMem13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exit (2).png"))); // NOI18N
        iconMem13.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.iconMem13.text")); // NOI18N
        iconMem13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconMem13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconMem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMem13MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.jLabel16.text")); // NOI18N

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconMem11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconMem12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconMem13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(iconMem11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(55, 55, 55)
                .addComponent(iconMem12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(61, 61, 61)
                .addComponent(iconMem13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(18, 18, 18))
        );

        lblTime.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        lblTime.setForeground(java.awt.Color.yellow);
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.lblTime.text")); // NOI18N

        lblDate.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblDate.setForeground(java.awt.Color.yellow);
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText(org.openide.util.NbBundle.getMessage(Main.class, "Main.lblDate.text")); // NOI18N

        javax.swing.GroupLayout contentpaneLayout = new javax.swing.GroupLayout(contentpane);
        contentpane.setLayout(contentpaneLayout);
        contentpaneLayout.setHorizontalGroup(
            contentpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentpaneLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(contentpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentpaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(contentpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentpaneLayout.createSequentialGroup()
                                .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8))))
                    .addGroup(contentpaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentpaneLayout.setVerticalGroup(
            contentpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentpaneLayout.createSequentialGroup()
                .addGroup(contentpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentpaneLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentpane, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconMem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem1MouseClicked
        // TODO add your handling code here:
        new Members().setVisible(true);
    }//GEN-LAST:event_iconMem1MouseClicked

    private void iconMem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem3MouseClicked
        // TODO add your handling code here:
        new Category().setVisible(true);
    }//GEN-LAST:event_iconMem3MouseClicked

    private void iconMem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem4MouseClicked
        // TODO add your handling code here:
        new BookList().setVisible(true);
    }//GEN-LAST:event_iconMem4MouseClicked

    private void iconMem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem5MouseClicked
        // TODO add your handling code here:
         new Staff().setVisible(true);
    }//GEN-LAST:event_iconMem5MouseClicked

    private void iconMem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem7MouseClicked
        // TODO add your handling code here:
        new IssueManagement().setVisible(true);
    }//GEN-LAST:event_iconMem7MouseClicked

    private void iconMem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem8MouseClicked
        // TODO add your handling code here:
        new ReturnManagement1().setVisible(true);
    }//GEN-LAST:event_iconMem8MouseClicked

    private void iconMem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem9MouseClicked
        // TODO add your handling code here:
        new FineManagement1().setVisible(true);
    }//GEN-LAST:event_iconMem9MouseClicked

    private void iconMem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem11MouseClicked
        // TODO add your handling code here:
        new Profile().setVisible(true);
    }//GEN-LAST:event_iconMem11MouseClicked

    private void iconMem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem12MouseClicked
        // TODO add your handling code here:
        new ChangePassword().setVisible(true);
    }//GEN-LAST:event_iconMem12MouseClicked

    private void iconMem13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMem13MouseClicked
        // TODO add your handling code here:
        this.dispose();
        Login login = new Login();
                login.setVisible(true);
    }//GEN-LAST:event_iconMem13MouseClicked
    public void setDateTime() {
        
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Calendar now = Calendar.getInstance();
                Date cur = now.getTime();             
                lblDate.setText(simpleDate.format(cur));
                lblTime.setText(simpleTime.format(cur));
            }
        };
		new Timer(1000, taskPerformer).start();
	}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgHome;
    private javax.swing.JPanel contentpane;
    private javax.swing.JLabel iconMem1;
    private javax.swing.JLabel iconMem11;
    private javax.swing.JLabel iconMem12;
    private javax.swing.JLabel iconMem13;
    private javax.swing.JLabel iconMem3;
    private javax.swing.JLabel iconMem4;
    private javax.swing.JLabel iconMem5;
    private javax.swing.JLabel iconMem7;
    private javax.swing.JLabel iconMem8;
    private javax.swing.JLabel iconMem9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlBook;
    private javax.swing.JPanel pnlFine;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlIR;
    private javax.swing.JPanel pnlMember;
    private javax.swing.JPanel pnlStaff;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
