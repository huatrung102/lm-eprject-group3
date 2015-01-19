package form.book;
import ExSwing.ClPanelTransparent;
import Model.Books;
import Model.Copies;
import SysController.MessageHandle;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Exceptions;

public class BookList extends javax.swing.JFrame {
    DefaultTableModel tableModel;
//    String bookisbn, booktitle, bookpublisher, bookauthor, bookcontent, booklanguage,
//    bookimagefile, bookcreateDate, catid, catname;
//    Boolean bookisdeleted;
//    float bookprice;
    private String filename = null;
    byte[] cover_image = null;
    

    public BookList() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadListCategory();
        loadListBook();
        setNormalMode();
        cboCategory.setModel(Model.Categories.Categories_getCategoryCombobox());
        JLabel label_5 = new JLabel("");
        label_5.setIcon(new ImageIcon(BookList.class.getResource("/image/wall3.jpg")));        
        label_5.setBounds(0, 0, 2000, 800);
        pnlBackground.add(label_5);  
    }
    
    public void setNormalMode(){
        txtSearchTitle.setText(null);
        txtSearchAuthor.setText(null);
        txtSearchPublisher.setText(null);
        cboSearchStatus.setSelectedIndex(0);
        txtISBN.setText(null);
        txtTitle.setText(null);
        txtAuthor.setText(null);
        txtPublisher.setText(null);
        txtPrice.setText(null);
        txtDate.setText(null);
        cboCategory.setSelectedIndex(-1);
        cboLang.setSelectedIndex(-1);
        cboStatus.setSelectedIndex(-1);
        txaContent.setText(null);
        lblCover.setIcon(null);
        lblNumberOfCopies.setText("---");
        txtNumberOfCopies.setText(null);

        txtISBN.setEditable(false);
        txtTitle.setEditable(false);
        txtAuthor.setEditable(false);
        txtPublisher.setEditable(false);
        txtPrice.setEditable(false);
        txtDate.setEditable(false);
        cboCategory.setEditable(false);
        cboLang.setEditable(false);
        cboStatus.setEditable(false);
        cboCategory.setEnabled(false);
        cboLang.setEnabled(false);
        cboStatus.setEnabled(false);
        txaContent.setEditable(false);
        txtNumberOfCopies.setEditable(false);
        
        btnSearch.setEnabled(true);
        btnAddCopies.setVisible(false);
        btnChangeCover.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnNewBook.setEnabled(true);
        btnSave.setEnabled(false);
        btnSaveUpdate.setVisible(false);
        btnCancel.setEnabled(false);
    }
    
    public void setSelectedMode(){
        setNormalMode();        
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
    }
    
    public void setUpdateMode(){
        txtISBN.setEditable(false);
        txtISBN.setEnabled(false);
        txtTitle.setEditable(true);
        txtAuthor.setEditable(true);
        txtPublisher.setEditable(true);
        txtPrice.setEditable(true);
        txtDate.setEnabled(false);
        txtDate.setText("Auto Generate");
        cboCategory.setEditable(true);
        cboLang.setEditable(true);
        cboStatus.setEditable(true);
        cboCategory.setEnabled(true);
        cboLang.setEnabled(true);
        cboStatus.setEnabled(true);
        txaContent.setEditable(true);
        txtNumberOfCopies.setEditable(true);
        
        btnChangeCover.setEnabled(true);
        btnNewBook.setEnabled(false);
        btnSave.setVisible(false);
        btnSaveUpdate.setVisible(true);
        btnCancel.setEnabled(true);
    }
    
    public void setAddNewMode(){
        setNormalMode();
        
        txtISBN.setEditable(true);
        txtTitle.setEditable(true);
        txtAuthor.setEditable(true);
        txtPublisher.setEditable(true);
        txtPrice.setEditable(true);
        txtDate.setEnabled(false);
        txtDate.setText("Auto Generate");
        cboCategory.setEditable(true);
        cboLang.setEditable(true);
        cboStatus.setEditable(true);
        cboCategory.setEnabled(true);
        cboLang.setEnabled(true);
        cboStatus.setEnabled(true);
        txaContent.setEditable(true);
        txtNumberOfCopies.setEditable(true);
        
        btnChangeCover.setEnabled(true);
        btnNewBook.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }
    
    public void loadListCategory (){
        tblCategoryList.setModel(Model.Categories.Categories_getCategoryList());
    }
    
    public void setColumnWidth(){
        tblBookList.getColumnModel().getColumn(0).setMinWidth(0);
        tblBookList.getColumnModel().getColumn(0).setMaxWidth(0);
        tblBookList.getColumnModel().getColumn(0).setWidth(0);
        tblBookList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblBookList.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblBookList.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblBookList.getColumnModel().getColumn(4).setPreferredWidth(10);
    }
    
    public void loadListBook(){
        tableModel = new DefaultTableModel();
        
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Tile");
        tableModel.addColumn("Author");
        tableModel.addColumn("Publisher");
        tableModel.addColumn("Status");
        tblBookList.setModel(tableModel);
        
        tblBookList.getColumnModel().getColumn(0).setMinWidth(0);
        tblBookList.getColumnModel().getColumn(0).setMaxWidth(0);
        tblBookList.getColumnModel().getColumn(0).setWidth(0);
        tblBookList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblBookList.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblBookList.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblBookList.getColumnModel().getColumn(4).setPreferredWidth(10);
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

        pnlBackground = new javax.swing.JPanel();
        jPanel3 = new ClPanelTransparent();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSearchAuthor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSearchPublisher = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboSearchStatus = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new ClPanelTransparent();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoryList = new javax.swing.JTable();
        jPanel2 = new ClPanelTransparent();
        jPanel5 = new javax.swing.JPanel();
        txtISBN = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboCategory = new javax.swing.JComboBox();
        cboLang = new javax.swing.JComboBox();
        cboStatus = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaContent = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblCover = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblNumberOfCopies = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNumberOfCopies = new javax.swing.JTextField();
        btnAddCopies = new javax.swing.JButton();
        btnChangeCover = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnSaveUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel4 = new ClPanelTransparent();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBookList = new javax.swing.JTable();
        btnNewBook = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Managment");
        setMinimumSize(new java.awt.Dimension(1240, 620));
        setResizable(false);

        pnlBackground.setMaximumSize(new java.awt.Dimension(1240, 595));
        pnlBackground.setMinimumSize(new java.awt.Dimension(1240, 595));
        pnlBackground.setPreferredSize(new java.awt.Dimension(1240, 595));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.yellow);
        jLabel1.setText("Search Book");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Title");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Author");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Publisher");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Status");

        cboSearchStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Explore.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

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
                        .addComponent(txtSearchTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSearchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearchAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSearchPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboSearchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setMaximumSize(new java.awt.Dimension(195, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(195, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(195, 500));

        tblCategoryList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategoryList.setAutoscrolls(false);
        tblCategoryList.setPreferredSize(new java.awt.Dimension(191, 389));
        tblCategoryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategoryList);
        if (tblCategoryList.getColumnModel().getColumnCount() > 0) {
            tblCategoryList.getColumnModel().getColumn(0).setResizable(false);
            tblCategoryList.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtISBN.setText("ISBN111");
        txtISBN.setMaximumSize(new java.awt.Dimension(47, 20));
        txtISBN.setMinimumSize(new java.awt.Dimension(47, 20));

        jLabel7.setText("ISBN");

        txtTitle.setText("Lord of the Ring");
        txtTitle.setMaximumSize(new java.awt.Dimension(47, 20));
        txtTitle.setMinimumSize(new java.awt.Dimension(47, 20));

        txtAuthor.setText("Patrick John");
        txtAuthor.setMaximumSize(new java.awt.Dimension(47, 20));

        txtPublisher.setText("NXB Kim Đồng");

        txtPrice.setText("10000");

        txtDate.setText("12/12/2012");

        jLabel8.setText("Title");

        jLabel9.setText("Author");

        jLabel10.setText("Publisher");

        jLabel11.setText("Category");

        jLabel12.setText("Language");

        jLabel13.setText("Price");

        jLabel14.setText("Date");

        jLabel15.setText("Status");

        cboCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afrikaans", "Albanian", "Ancient Greek", "Arabic", "Armenian", "Basque", "Bengali", "Bulgarian", "Cantonese Chinese", "Catalan", "Chinese", "Cornish", "Creole", "Croatian", "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian", "Farsi", "Finnish", "French", "Galician", "Georgian", "German", "Greek", "Gujarati", "Hebrew", "Hindi", "Hungarian", "Icelandic", "Indonesian", "Irish", "Italian", "Japanese", "Kannada", "Korean", "Latin", "Latvian", "Lithuanian", "Macedonian", "Malay", "Malayalam", "Maori", "Marathi", "Middle English", "Nauru", "Norwegian", "Old English", "Persian", "Polish", "Portuguese", "Punjabi", "Romance", "Romanian", "Russian", "Sanskrit", "Scots", "Scots Gaelic", "Serbian", "Serbo-Croatian", "Slovak", "Slovene", "Somali", "Spanish", "Swahili", "Swedish", "Tagalog", "Taiwanese Chinese", "Tamil", "Telugu", "Thai", "Tibetan", "Turkish", "Ukrainian", "Urdu", "Vietnamese", "Welsh", "Xhosa", "Yiddish", "Zulu", "Other ..." }));

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        txaContent.setColumns(20);
        txaContent.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txaContent.setLineWrap(true);
        txaContent.setRows(5);
        txaContent.setText("Cà phê cùng Tony là sự tập hợp các bài viết trên trạng mạng xã hội của tác giả Tony Buổi Sáng (TnBS) về những bài học, câu chuyện anh đã trải nghiệm trong cuộc sống. Đó có thể là cách anh chia sẻ với các bạn trẻ về những chuyện to tát như khởi nghiệp,đạo đức kinh doanh, học tập đến những việc nhỏ nhặt như ăn mặc, giao tiếp, vệ sinh cơ thể…");
        txaContent.setWrapStyleWord(true);
        txaContent.setAutoscrolls(false);
        jScrollPane2.setViewportView(txaContent);

        jLabel16.setText("Content");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setMaximumSize(new java.awt.Dimension(93, 140));
        jPanel11.setMinimumSize(new java.awt.Dimension(93, 140));
        jPanel11.setPreferredSize(new java.awt.Dimension(93, 140));

        lblCover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgBook/book (4).JPG"))); // NOI18N
        lblCover.setMaximumSize(new java.awt.Dimension(93, 140));
        lblCover.setMinimumSize(new java.awt.Dimension(93, 140));
        lblCover.setPreferredSize(new java.awt.Dimension(93, 140));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(lblCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel17.setText("Number of copies");

        lblNumberOfCopies.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNumberOfCopies.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumberOfCopies.setText("12");

        jLabel20.setText("Add more copy(s)");

        btnAddCopies.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnAddCopies.setText("Add");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(lblNumberOfCopies, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumberOfCopies, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCopies)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblNumberOfCopies))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNumberOfCopies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCopies))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnChangeCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Picture.png"))); // NOI18N
        btnChangeCover.setText("Change");
        btnChangeCover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeCoverActionPerformed(evt);
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

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel6.add(btnSave);

        btnSaveUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSaveUpdate.setText("Save");
        btnSaveUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdateActionPerformed(evt);
            }
        });
        jPanel6.add(btnSaveUpdate);

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnChangeCover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addGap(12, 12, 12)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setMaximumSize(new java.awt.Dimension(564, 500));
        jPanel4.setMinimumSize(new java.awt.Dimension(564, 500));

        tblBookList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title", "Author", "Publisher", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBookList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBookList);
        if (tblBookList.getColumnModel().getColumnCount() > 0) {
            tblBookList.getColumnModel().getColumn(0).setResizable(false);
            tblBookList.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblBookList.getColumnModel().getColumn(1).setResizable(false);
            tblBookList.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblBookList.getColumnModel().getColumn(2).setResizable(false);
            tblBookList.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblBookList.getColumnModel().getColumn(3).setResizable(false);
            tblBookList.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        btnNewBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btnNewBook.setText("New Book");
        btnNewBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewBookActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(java.awt.Color.yellow);
        jLabel19.setText("Book List");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewBook))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(btnNewBook))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(8, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlBackground, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewBookActionPerformed
        setAddNewMode();
        
    }//GEN-LAST:event_btnNewBookActionPerformed

    private void tblCategoryListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryListMouseClicked
        setNormalMode();
        int line = tblCategoryList.getSelectedRow();
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblCategoryList.getModel();
        String catename = (String)tbm.getValueAt(line, 0);
        tblBookList.setModel(Model.Books.Books_getBookListByCatename(catename));
        setColumnWidth();
    }//GEN-LAST:event_tblCategoryListMouseClicked

    private void tblBookListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookListMouseClicked
        setSelectedMode();
        
        int line = tblBookList.getSelectedRow();
        
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblBookList.getModel();
        String isbn = (String)tbm.getValueAt(line, 0);
        
        Books obj = Books.getByISBN(isbn);
        
        txtISBN.setText(isbn);
        txtTitle.setText(obj.Book_Title);
        txtAuthor.setText(obj.Book_Author);
        txtPublisher.setText(obj.Book_Publisher);
        txtPrice.setText(String.valueOf(obj.Book_Price));
        txtDate.setText(obj.Book_CreateDate);
        txaContent.setText(obj.Book_Content);
        lblNumberOfCopies.setText(String.valueOf(obj.Book_Count));
        cboCategory.setSelectedItem(obj.Cat_Name);
        cboLang.setSelectedItem(obj.Book_Language);
        boolean status = obj.Book_isDeleted;
        if(status==true){
            cboStatus.setSelectedIndex(1);
        } else {
            cboStatus.setSelectedIndex(0);
        }
        
        ImageIcon icon = new ImageIcon(obj.Book_ImageFile);
        lblCover.setIcon(icon);
    }//GEN-LAST:event_tblBookListMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Books obj = new Books();
                    
        //Validate
        String pattern = "^(\\d){13}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtISBN.getText());
        if(txtISBN.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Book_ISBN do not NULL");
            txtISBN.requestFocus();
            return;
        } else if(!m.find()){
            JOptionPane.showMessageDialog(null, "Book_ISBN length must be 13 NUMBERS");
            txtISBN.requestFocus();
            return;
        } else {
            Books objCheck = Model.Books.getByISBN(txtISBN.getText());
            if (!objCheck.Book_ISBN.isEmpty()){
                JOptionPane.showMessageDialog(null, "Book ISBN exist, try again!");
                txtTitle.requestFocus();
                return;
            }
        }
        
        if (txtTitle.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Book Title do not NULL");
            txtTitle.requestFocus();
            return;
        } else if(txtTitle.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Book Title do not longer 50 chars");
            txtTitle.requestFocus();
            return;
        }
        
         if (txtAuthor.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Author do not NULL");
            txtAuthor.requestFocus();
            return;
        } else if(txtAuthor.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Author do not longer 50 chars");
            txtAuthor.requestFocus();
            return;
        }
         
        if (txtPublisher.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Publisher do not NULL");
            txtPublisher.requestFocus();
            return;
        } else if(txtPublisher.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Publisher do not longer 50 chars");
            txtPublisher.requestFocus();
            return;
        }
        
        if(cboCategory.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a category");
            cboCategory.requestFocus();
            return;
        }
        
        if(cboLang.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a language");
            cboLang.requestFocus();
            return;
        }
        
        pattern = "(\\d)";
        r = Pattern.compile(pattern);
        m = r.matcher(txtPrice.getText());
        if(txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please input book price!");
            txtPrice.requestFocus();
            return;
        } else if(!m.find()){
            JOptionPane.showMessageDialog(null, "Book price must be number!");
            txtPrice.requestFocus();
            return;
        } else if (Float.parseFloat(txtPrice.getText()) == 0){
            JOptionPane.showConfirmDialog(null, "Book price must be larger 0");
            txtPrice.requestFocus();
            return;
        }
        
        if(cboStatus.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a status of book");
            cboStatus.requestFocus();
            return;
        }
        
        if(txaContent.getText().isEmpty()){
            obj.Book_Content = "";
        }
        
        //Get data from form and add to Object
        obj.Book_ISBN = txtISBN.getText();
        obj.Book_Title = txtTitle.getText();
        obj.Book_Author = txtAuthor.getText();
        obj.Book_Publisher = txtPublisher.getText();       
        obj.Book_Price = Float.parseFloat(txtPrice.getText());
        obj.Book_Content = txaContent.getText();
        obj.Cat_Id = Model.Categories.Categories_getCategoryByCateName(
                (String)cboCategory.getSelectedItem()).Cat_Id;
        obj.Book_Language = (String) cboLang.getSelectedItem();
        
        //Copy file to imgBook folder
        if(lblCover.getIcon() == null){
            obj.Book_ImageFile = "imgBook/Nocover.jpg";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newfilename = sdf.format(Calendar.getInstance().getTime());
            File labelicon = new File(lblCover.getIcon().toString());
            File desfile = new File("imgBook\\"+newfilename+"_"+labelicon.getName());
            try {
                copyFile(labelicon, desfile);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        obj.Book_ImageFile = "imgBook/"+desfile.getName();
        }
        //Ket thuc phan upload Image
        
        if ((String)cboStatus.getSelectedItem() == "Active"){
            obj.Book_isDeleted = false;
        } else {
            obj.Book_isDeleted = true;
        }
        
        if(txtNumberOfCopies.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Must enter at least one copy");
            txtNumberOfCopies.requestFocus();
            return;
        } else {
            if(Integer.parseInt(txtNumberOfCopies.getText()) <= 0){
                JOptionPane.showMessageDialog(null, "Number of copies must be greater 0");
                txtNumberOfCopies.requestFocus();
                return;
            } else if(Integer.parseInt(txtNumberOfCopies.getText()) > 999) {
                JOptionPane.showMessageDialog(null, "Max of Number of copies is 999");
                txtNumberOfCopies.requestFocus();
                return;
            } else {
                //Insert Books
                int isSuccess = Model.Books.Books_Insert(obj);
                MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_insert, isSuccess);
                
                Copies objCopies = new Copies();
                String numi;
                int numberofcopies = Integer.parseInt(txtNumberOfCopies.getText());
                for (int i = 1; i <= numberofcopies; i++) {
                    objCopies.Book_ISBN = txtISBN.getText();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
                    String copno = sdf.format(Calendar.getInstance().getTime());

                    if(i>=100){
                        numi = Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                        
                    } else if (i >= 10){
                        numi = "0"+Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                        
                    } else {
                        numi = "00"+Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                    }
                }
            }
        }
        setAddNewMode();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setNormalMode();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnChangeCoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeCoverActionPerformed
        //Chọn file hình và lấy đường dẫn
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File sourefile = fc.getSelectedFile();
        
        //Set image vào label cover
        ImageIcon newIcon = new ImageIcon(sourefile.getPath());
        lblCover.setIcon(newIcon);
    }//GEN-LAST:event_btnChangeCoverActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String bookisbn = txtISBN.getText();
        int isSuccess = Model.Books.Books_DeleteBookByISBN(bookisbn);
        MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_delete , isSuccess);
        setNormalMode();
        int line = tblCategoryList.getSelectedRow();
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblCategoryList.getModel();
        String catename = (String)tbm.getValueAt(line, 0);
        tblBookList.setModel(Model.Books.Books_getBookListByCatename(catename));
        setColumnWidth();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        setUpdateMode();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateActionPerformed
        Books obj = new Books();
        String pattern; 
        Pattern r;
        Matcher m;
        //Validate
        
        if (txtTitle.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Book Title do not NULL");
            txtTitle.requestFocus();
            return;
        } else if(txtTitle.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Book Title do not longer 50 chars");
            txtTitle.requestFocus();
            return;
        }
        
         if (txtAuthor.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Author do not NULL");
            txtAuthor.requestFocus();
            return;
        } else if(txtAuthor.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Publisher do not longer 50 chars");
            txtAuthor.requestFocus();
            return;
        }
         
        if (txtPublisher.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Publisher do not NULL");
            txtPublisher.requestFocus();
            return;
        } else if(txtPublisher.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Publisher do not longer 50 chars");
            txtPublisher.requestFocus();
            return;
        }
        
        if(cboCategory.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a category");
            cboCategory.requestFocus();
            return;
        }
        
        if(cboLang.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a language");
            cboLang.requestFocus();
            return;
        }
        
        pattern = "(\\d)";
        r = Pattern.compile(pattern);
        m = r.matcher(txtPrice.getText());
        if(txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please input book price!");
            txtPrice.requestFocus();
            return;
        } else if(!m.find()){
            JOptionPane.showMessageDialog(null, "Book price must be number!");
            txtPrice.requestFocus();
            return;
        } else if (Float.parseFloat(txtPrice.getText()) == 0){
            JOptionPane.showConfirmDialog(null, "Book price must be larger 0");
            txtPrice.requestFocus();
            return;
        }
        
        if(cboStatus.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a status of book");
            cboStatus.requestFocus();
            return;
        }
        
        if(txaContent.getText().isEmpty()){
            obj.Book_Content = "";
        }
        
        //Get data from form and add to Object
        obj.Book_Title = txtTitle.getText();
        obj.Book_Author = txtAuthor.getText();
        obj.Book_Publisher = txtPublisher.getText();       
        obj.Book_Price = Float.parseFloat(txtPrice.getText());
        obj.Book_Content = txaContent.getText();
        obj.Cat_Id = Model.Categories.Categories_getCategoryByCateName(
                (String)cboCategory.getSelectedItem()).Cat_Id;
        obj.Book_Language = (String) cboLang.getSelectedItem();
        
        //Copy file to imgBook folder
        if(lblCover.getIcon() == null){
            obj.Book_ImageFile = "imgBook/Nocover.jpg";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String newfilename = sdf.format(Calendar.getInstance().getTime());
            File labelicon = new File(lblCover.getIcon().toString());
            File desfile = new File("imgBook\\"+newfilename+"_"+labelicon.getName());
            try {
                copyFile(labelicon, desfile);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        obj.Book_ImageFile = "imgBook/"+desfile.getName();
        }
        //Ket thuc phan upload Image
        
        if ((String)cboStatus.getSelectedItem() == "Active"){
            obj.Book_isDeleted = false;
        } else {
            obj.Book_isDeleted = true;
        }
        
        if(!txtNumberOfCopies.getText().isEmpty()) {
            if(Integer.parseInt(txtNumberOfCopies.getText()) <= 0){
                JOptionPane.showMessageDialog(null, "Number of copies must be greater 0");
                txtNumberOfCopies.requestFocus();
                return;
            } else if(Integer.parseInt(txtNumberOfCopies.getText()) > 999) {
                JOptionPane.showMessageDialog(null, "Max of Number of copies is 999");
                txtNumberOfCopies.requestFocus();
                return;
            } else {
                //Insert Books
                int isSuccess = Model.Books.Books_Update(obj, txtISBN.getText());
                MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_update, isSuccess);
                
                Copies objCopies = new Copies();
                String numi;
                int numberofcopies = Integer.parseInt(txtNumberOfCopies.getText());
                for (int i = 1; i <= numberofcopies; i++) {
                    objCopies.Book_ISBN = txtISBN.getText();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
                    String copno = sdf.format(Calendar.getInstance().getTime());

                    if(i>=100){
                        numi = Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                        
                    } else if (i >= 10){
                        numi = "0"+Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                        
                    } else {
                        numi = "00"+Integer.toString(i);
                        objCopies.Cop_No = copno+numi;
                        int a = Model.Copies.Copies_Insert(objCopies);
                        
                    }
                }
            }
        } else {
            int isSuccess = Model.Books.Books_Update(obj, txtISBN.getText());
            MessageHandle.showMessage(MessageHandle.Obj_Book, MessageHandle.Action_update, isSuccess);
        }
        setNormalMode();
        int line = tblCategoryList.getSelectedRow();
        DefaultTableModel tbm = new DefaultTableModel();
        tbm = (DefaultTableModel) tblCategoryList.getModel();
        String catename = (String)tbm.getValueAt(line, 0);
        tblBookList.setModel(Model.Books.Books_getBookListByCatename(catename));
        setColumnWidth();
    }//GEN-LAST:event_btnSaveUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String title, publisher, author;
        boolean isdelete;
        title = txtSearchTitle.getText();
        publisher = txtSearchPublisher.getText();
        author = txtSearchAuthor.getText();
        if(cboSearchStatus.getSelectedItem() == "Active"){
            isdelete = false;
        } else {
            isdelete = true;
        }
        tblBookList.setModel(Model.Books.Books_searchBook(title, publisher, author, isdelete));
        setColumnWidth();
    }//GEN-LAST:event_btnSearchActionPerformed

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
//            java.util.logging.Logger.getLogger(BookList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BookList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BookList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BookList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BookList().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCopies;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangeCover;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNewBook;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveUpdate;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboCategory;
    private javax.swing.JComboBox cboLang;
    private javax.swing.JComboBox cboSearchStatus;
    private javax.swing.JComboBox cboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCover;
    private javax.swing.JLabel lblNumberOfCopies;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTable tblBookList;
    private javax.swing.JTable tblCategoryList;
    private javax.swing.JTextArea txaContent;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtNumberOfCopies;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtSearchAuthor;
    private javax.swing.JTextField txtSearchPublisher;
    private javax.swing.JTextField txtSearchTitle;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
