/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.ir;

import Config.SysVar;
import ExSwing.*;
import Helpers.StringHelper;
import Helpers.UIHelper;
import Model.Books;
import Model.Copies;
import Model.IRBooks;
import Model.Members;
import SysController.MessageHandle;

import form.main.Main;
import form.member.MemberSearch;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator PC
 */
public class IssueManagement extends javax.swing.JFrame {

    /**
     * Creates new form IRManagement
     */
    //Books glBook;
    private Members selectedMember;    
    private ArrayList<Books> listBooks;
    private IRBooks ir;
    int countSTT = 1;
    int countAllow = 0;
    private HashMap<String, String> Cop_IdList;
    
    public String Member_Id;
    
    private static String issue_col[] = {"No","ISBN","Title","Category","Copy No"};
    private static String copies_col[] = {"ISBN","Title","Author"};
    public IssueManagement() {
        initComponents();       
        UIHelper.bindBackground(pnlIssue);
        initForm();
        initMember();
        initTblIssuing();
        initTblCopies();
        listBooks = new ArrayList<>();
        ir = new IRBooks();
        Cop_IdList = new HashMap<>(5);
       // loadBook();
      //  loadCopies();        
       // loadIRBook();
    }
    private void initForm(){
        btSearchMem.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/Explore.png")));
         btSearchBook.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/Explore.png")));
        btReset.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/reset.png")));
        btIssue.setIcon(new ImageIcon(IssueManagement.class
                        .getResource("/image/issue.png")));
        tblCopies.getTableHeader().setReorderingAllowed(false);
        tblIssuing.getTableHeader().setReorderingAllowed(false);
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    private void loadCopies(){
      //  tblCopies.setModel(Copies.getTestCopyByISBN());        
    }
    private void loadBook(){
        Books book = null;//= Books.getTestBook();
       // glBook = book;
        lblAuthor.setText(book.Book_Author);
        lblCategory.setText(book.Cat_Name);
        lblLanguage.setText(book.Book_Language);
        lblPublisher.setText(book.Book_Publisher);
        lblTitle.setText(book.Book_Title);
      //  lblPrice.setText(book.Book_Price + " $");
        
        //load image book
        lblImgBook.setIcon(new ImageIcon(IssueManagement.class
                        .getResource(book.Book_ImageFile)));        
        lblImgBook.setBounds(0, 0, 140, 140);
    }
    private void loadIRBook(){
       // tblIssuing.setModel(IRBooks.getTestIRBookIssue(glBook));
    }
    public void setDataPopUp(String memberId) {
		this.Member_Id = memberId;
    }

    public String getDataPopUp() {
		return Member_Id;
    }
    
    private void initMember(){
        lblFullname.setText("");
        lblPhone.setText("");
        lblStatusMem.setText("");
        lblRegisterDate.setText("");
        //load image member
        lblImgMember.setIcon(new ImageIcon(IssueManagement.class
                        .getResource(SysVar.image_member_defaut)));        
        lblImgMember.setBounds(0, 0, 140, 140);
    }
    private void initTblIssuing(){
        DefaultTableModel tblM = new DefaultTableModel(issue_col, 0);
        tblIssuing.setModel(tblM);
    }
    private void initTblCopies(){
        DefaultTableModel tblM = new DefaultTableModel(copies_col, 0);
        tblCopies.setModel(tblM);
    }
    private void loadMember(Members mem){  
        JLabel1111.setText("");
        lblFullname.setText(mem.Mem_FirstName + " " + mem.Mem_LastName);
        lblPhone.setText(mem.Mem_Phone);
        lblStatusMem.setText(mem.Mem_Status?"Active" : "Inactive");
        lblRegisterDate.setText(mem.Mem_CreateDate);
        //load image member
        lblImgMember.setIcon(new ImageIcon(Main.class
                        .getResource(mem.Mem_ImageFile)));        
        lblImgMember.setBounds(0, 0, 140, 140);
        
    }
    
    public static void loadMemberBySearch(String Member_Id){
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIssue = new ClPanelTransparent();
        jPanel1 = new ClPanelTransparent();
        jPanel3 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMemNo = new javax.swing.JTextField();
        btSearchMem = new ClButtonTransparan("Search");
        jPanel6 = new ClPanelTransparent();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        btSearchBook = new ClButtonTransparan("Search");
        jLabel14 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox();
        txtTitle = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new ClPanelTransparent();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIssuing = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                return false;
            };
        };
        jPanel11 = new ClPanelTransparent();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCopies = new javax.swing.JTable(){

            public boolean isCellEditable(int row,int column){
                return false;
            };
        };
        jPanel2 = new ClPanelTransparent();
        jPanel4 = new ClPanelTransparent();
        jPanel9 = new javax.swing.JPanel();
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
        JLabel1111 = new javax.swing.JLabel();
        lblMemberNo = new javax.swing.JLabel();
        jPanel7 = new ClPanelTransparent();
        jPanel13 = new javax.swing.JPanel();
        pnlImgBook = new javax.swing.JPanel();
        lblImgBook = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        lblPublisher = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblPrice1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblLanguage = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new ClPanelTransparent();
        btIssue = new ClButtonTransparan("Issue");
        btReset = new ClButtonTransparan("Reset");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1162, 570));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel1.text")); // NOI18N

        txtMemNo.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.txtMemNo.text")); // NOI18N
        txtMemNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemNoActionPerformed(evt);
            }
        });

        btSearchMem.setForeground(java.awt.Color.darkGray);
        btSearchMem.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.btSearchMem.text")); // NOI18N
        btSearchMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchMemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtMemNo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearchMem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMemNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchMem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel6.text")); // NOI18N

        txtISBN.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.txtISBN.text")); // NOI18N

        btSearchBook.setForeground(java.awt.Color.darkGray);
        btSearchBook.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.btSearchBook.text")); // NOI18N
        btSearchBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchBookActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel14.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel14.text")); // NOI18N

        cbCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTitle.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.txtTitle.text")); // NOI18N

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel15.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel15.text")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btSearchBook))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTitle)
                                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btSearchBook)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblIssuing.setModel(new javax.swing.table.DefaultTableModel(
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
        tblIssuing.setPreferredSize(new java.awt.Dimension(300, 150));
        tblIssuing.setRowSelectionAllowed(true);
        tblIssuing.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblIssuing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIssuingMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblIssuing);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel11.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N

        tblCopies.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCopies.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCopies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCopiesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCopies);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 176));
        jPanel4.setLayout(new java.awt.BorderLayout());

        pnlImgMember.setPreferredSize(new java.awt.Dimension(140, 140));

        lblImgMember.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblImgMember.text")); // NOI18N
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
        jLabel2.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel5.text")); // NOI18N

        lblFullname.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblFullname.text")); // NOI18N

        lblPhone.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblPhone.text")); // NOI18N
        lblPhone.setPreferredSize(new java.awt.Dimension(40, 15));

        lblRegisterDate.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblRegisterDate.text")); // NOI18N

        lblStatusMem.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblStatusMem.text")); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel16.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel16.text")); // NOI18N

        lblStatusMem1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblStatusMem1.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblStatusMem1.text")); // NOI18N

        JLabel1111.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        JLabel1111.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.JLabel1111.text")); // NOI18N

        lblMemberNo.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblMemberNo.text")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(JLabel1111, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMemberNo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblStatusMem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegisterDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStatusMem1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(260, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel1111)
                    .addComponent(lblMemberNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFullname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblRegisterDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblStatusMem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblStatusMem1))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgMember, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jPanel7.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.yellow)); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(430, 215));
        jPanel7.setLayout(new java.awt.BorderLayout());

        pnlImgBook.setPreferredSize(new java.awt.Dimension(140, 140));

        lblImgBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImgBook.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblImgBook.text")); // NOI18N

        javax.swing.GroupLayout pnlImgBookLayout = new javax.swing.GroupLayout(pnlImgBook);
        pnlImgBook.setLayout(pnlImgBookLayout);
        pnlImgBookLayout.setHorizontalGroup(
            pnlImgBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgBookLayout.createSequentialGroup()
                .addComponent(lblImgBook, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlImgBookLayout.setVerticalGroup(
            pnlImgBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImgBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgBook, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel9.text")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel8.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel8.text")); // NOI18N

        lblAuthor.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblAuthor.text")); // NOI18N

        lblPublisher.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblPublisher.text")); // NOI18N

        lblCategory.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblCategory.text")); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel7.text")); // NOI18N

        lblTitle.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblTitle.text")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel11.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel11.text")); // NOI18N

        lblPrice1.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblPrice1.text")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel13.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel13.text")); // NOI18N

        lblLanguage.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.lblLanguage.text")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.jLabel10.text")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlImgBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblPrice1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPublisher, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLanguage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(pnlImgBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPrice1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(lblTitle))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblCategory))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPublisher)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(lblAuthor))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(lblLanguage))))
                    .addContainerGap()))
        );

        jPanel7.add(jPanel13, java.awt.BorderLayout.CENTER);

        btIssue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btIssue.setForeground(java.awt.Color.yellow);
        btIssue.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.btIssue.text")); // NOI18N

        btReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReset.setForeground(java.awt.Color.yellow);
        btReset.setText(org.openide.util.NbBundle.getMessage(IssueManagement.class, "IssueManagement.btReset.text")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btIssue)
                .addGap(80, 80, 80)
                .addComponent(btReset)
                .addGap(100, 100, 100))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btReset)
                .addComponent(btIssue))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout pnlIssueLayout = new javax.swing.GroupLayout(pnlIssue);
        pnlIssue.setLayout(pnlIssueLayout);
        pnlIssueLayout.setHorizontalGroup(
            pnlIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlIssueLayout.setVerticalGroup(
            pnlIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIssueLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlIssueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlIssue, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMemNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemNoActionPerformed
        // TODO add your handling code here:
        String mem_No = txtMemNo.getText();
        Members obj = Members.getIRCountInformation(mem_No);
        if(obj != null){
            loadMember(obj);
        }else{
            MessageHandle.showError("Can not find Member with No: " + mem_No);
        }
    }//GEN-LAST:event_txtMemNoActionPerformed
    private void bindTblIssue(IRBooks irb){
        DefaultTableModel tblM = (DefaultTableModel)tblIssuing.getModel();
        tblM.addRow(new Object[]{
                                countSTT++
                                ,irb.book.Book_ISBN
                                ,irb.book.Book_Title
                                ,irb.book.Cat_Name
                                ,irb.copy.Cop_No });
        tblIssuing.setModel(tblM);
        Cop_IdList.put(irb.copy.Cop_Id, irb.copy.Cop_Id);
    }
    private void tblCopiesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCopiesMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
           
            int index = tblCopies.getSelectedRow();
            boolean status = index != -1;
                if(status){
                    if(countAllow < 5){
                        //get ISBN on table
                        String ISBN = String.valueOf(tblCopies.getModel().getValueAt(index, 0)) ;
                        ir.book = Books.getByISBN(ISBN);                        
                        ir.copy = Copies.getLastestIsFree(ir.book.Book_ISBN,Cop_IdList); //StringHelper.getStringByMap(Cop_IdList));
                        if(ir.copy != null){
                            bindTblIssue(ir);
                            setCountSttOnTblIssuing();
                            ++countAllow;                            
                            validate();
                        }else
                            MessageHandle.showError("Not exists Copy");
                    }else{
                        MessageHandle.showError("Not Allow total issue more than 5 book");
                    }
                }                
        }
    }//GEN-LAST:event_tblCopiesMouseClicked
    
    private void btSearchBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchBookActionPerformed
        // TODO add your handling code here:
        Books obj = new Books();
        obj.Book_ISBN = txtISBN.getText().trim();
        obj.Book_Title = txtTitle.getText();
        obj.Cat_Id = String.valueOf(cbCategory.getSelectedObjects()[0]);//get value of comboBox
        //test
        obj.Cat_Id = "";
        
        DefaultTableModel tblM = IRBooks.getListBookByFilter(obj);
        
        tblCopies.setModel(tblM);
        if(tblM.getRowCount() == 0)
            MessageHandle.showError("No Book satisfy depend condition");
    }//GEN-LAST:event_btSearchBookActionPerformed

    private void tblIssuingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIssuingMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            JTable target = (JTable)evt.getSource();            
            int index = target.getSelectedRow();
            boolean status = index != -1;
                if(status){
                    DefaultTableModel tblM = (DefaultTableModel) tblIssuing.getModel();
                    tblM.removeRow(tblCopies.convertRowIndexToModel(index)); 
                    tblIssuing.setModel(tblM);    
                    setCountSttOnTblIssuing();
                    --countAllow;
                    
                }                
        }
    }//GEN-LAST:event_tblIssuingMouseClicked

    private void btSearchMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchMemActionPerformed
        // TODO add your handling code here:
        MemberSearch memberSearchBox = new MemberSearch(new javax.swing.JFrame(), true);
        memberSearchBox.setVisible(true);
        txtMemNo.setText(memberSearchBox.getPopUpData());        
    }//GEN-LAST:event_btSearchMemActionPerformed
    private void setCountSttOnTblIssuing(){        
        for(int i = 0; i< tblIssuing.getRowCount(); i ++){
            tblIssuing.setValueAt(String.valueOf(i+1), i, 0);
        }
    }
    /**
     * @param args the command line arguments
     */
     /*
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueManagement().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel1111;
    private javax.swing.JButton btIssue;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSearchBook;
    private javax.swing.JButton btSearchMem;
    private javax.swing.JComboBox cbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblImgBook;
    private javax.swing.JLabel lblImgMember;
    private javax.swing.JLabel lblLanguage;
    private javax.swing.JLabel lblMemberNo;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPrice1;
    private javax.swing.JLabel lblPublisher;
    private javax.swing.JLabel lblRegisterDate;
    private javax.swing.JLabel lblStatusMem;
    private javax.swing.JLabel lblStatusMem1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlImgBook;
    private javax.swing.JPanel pnlImgMember;
    private javax.swing.JPanel pnlIssue;
    private javax.swing.JTable tblCopies;
    private javax.swing.JTable tblIssuing;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtMemNo;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
