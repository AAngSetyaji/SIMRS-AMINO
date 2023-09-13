/*
 * Kontribusi dari tim IT RSUD Prembun
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianPanssEC extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianPanssEC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Petugas","Nama Petugas","Tanggal","Gaduh Gelisah","Skor P4","Permusuhan","Skor P7","Ketegangan","Skor G4", 
            "Tidak Kooperatif","Skor G8","Pengendalian Impuls Buruk","Skor G14","Total Skor","Ruangan"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(115);
            }else if(i==8){
                column.setPreferredWidth(170);
            }else if(i==9){
                column.setPreferredWidth(50);
            }else if(i==10){
                column.setPreferredWidth(170);
            }else if(i==11){
                column.setPreferredWidth(50);
            }else if(i==12){
                column.setPreferredWidth(170);
            }else if(i==13){
                column.setPreferredWidth(50);
            }else if(i==14){
                column.setPreferredWidth(170);
            }else if(i==15){
                column.setPreferredWidth(50);
            }else if(i==16){
                column.setPreferredWidth(170);
            }else if(i==17){
                column.setPreferredWidth(60);
            }else if(i==18){
                column.setPreferredWidth(60);
            }else if(i==19){
                column.setPreferredWidth(50);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
         petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){ 
                    KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());   
                }              
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianMedis = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jSeparator2 = new javax.swing.JSeparator();
        p4_ringan = new javax.swing.JRadioButton();
        p4_tidak_ditemukan = new javax.swing.JRadioButton();
        p4_sedang = new javax.swing.JRadioButton();
        p4_minimal = new javax.swing.JRadioButton();
        p4_agak_berat = new javax.swing.JRadioButton();
        p4_sangat_berat = new javax.swing.JRadioButton();
        p4_berat = new javax.swing.JRadioButton();
        skor_p4 = new widget.TextBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        p7_ringan = new javax.swing.JRadioButton();
        p7_tidak_ditemukan = new javax.swing.JRadioButton();
        p7_sedang = new javax.swing.JRadioButton();
        p7_minimal = new javax.swing.JRadioButton();
        p7_agak_berat = new javax.swing.JRadioButton();
        p7_sangat_berat = new javax.swing.JRadioButton();
        p7_berat = new javax.swing.JRadioButton();
        skor_p7 = new widget.TextBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel14 = new widget.Label();
        g4_ringan = new javax.swing.JRadioButton();
        g4_tidak_ditemukan = new javax.swing.JRadioButton();
        g4_sedang = new javax.swing.JRadioButton();
        g4_minimal = new javax.swing.JRadioButton();
        g4_agak_berat = new javax.swing.JRadioButton();
        g4_sangat_berat = new javax.swing.JRadioButton();
        g4_berat = new javax.swing.JRadioButton();
        skor_g4 = new widget.TextBox();
        jLabel4 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel15 = new widget.Label();
        g8_ringan = new javax.swing.JRadioButton();
        g8_tidak_ditemukan = new javax.swing.JRadioButton();
        g8_sedang = new javax.swing.JRadioButton();
        g8_minimal = new javax.swing.JRadioButton();
        g8_agak_berat = new javax.swing.JRadioButton();
        g8_sangat_berat = new javax.swing.JRadioButton();
        g8_berat = new javax.swing.JRadioButton();
        skor_g8 = new widget.TextBox();
        jLabel5 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel16 = new widget.Label();
        g14_ringan = new javax.swing.JRadioButton();
        g14_tidak_ditemukan = new javax.swing.JRadioButton();
        g14_sedang = new javax.swing.JRadioButton();
        g14_minimal = new javax.swing.JRadioButton();
        g14_agak_berat = new javax.swing.JRadioButton();
        g14_sangat_berat = new javax.swing.JRadioButton();
        g14_berat = new javax.swing.JRadioButton();
        skor_g14 = new widget.TextBox();
        jLabel9 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel17 = new widget.Label();
        ruangan = new widget.TextBox();
        tskor = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianMedis.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianMedis.setText("Laporan Penilaian PANSS-EC");
        MnPenilaianMedis.setName("MnPenilaianMedis"); // NOI18N
        MnPenilaianMedis.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPenilaianMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianMedis);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Pre Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1410));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Petugas :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(166, 40, 50, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(220, 40, 90, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(312, 40, 180, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(494, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(80, 40, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("Gaduh Gelisah (P4)  :");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 110, 140, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 750, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(538, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023 08:36:07" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(594, 40, 130, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 750, 1);

        p4_ringan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_ringan);
        p4_ringan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_ringan.setForeground(new java.awt.Color(51, 51, 51));
        p4_ringan.setText("Ringan");
        p4_ringan.setToolTipText("Ringan : sedikit agiatif, waspada berlebihan, atau sedikit mudah terangsang selama anamnesis, tetapi tanpa episode gaduh gelisah yang jelas atau labilitas emosi yang mencolok.");
        p4_ringan.setName("p4_ringan"); // NOI18N
        p4_ringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_ringanActionPerformed(evt);
            }
        });
        FormInput.add(p4_ringan);
        p4_ringan.setBounds(160, 170, 90, 20);

        p4_tidak_ditemukan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_tidak_ditemukan);
        p4_tidak_ditemukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_tidak_ditemukan.setForeground(new java.awt.Color(51, 51, 51));
        p4_tidak_ditemukan.setText("Tidak Ditemukan");
        p4_tidak_ditemukan.setToolTipText("Tidak Ditemukan : tidak ditemukan adanya gaduh gelisah\n");
        p4_tidak_ditemukan.setName("p4_tidak_ditemukan"); // NOI18N
        p4_tidak_ditemukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p4_tidak_ditemukanMousePressed(evt);
            }
        });
        p4_tidak_ditemukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_tidak_ditemukanActionPerformed(evt);
            }
        });
        FormInput.add(p4_tidak_ditemukan);
        p4_tidak_ditemukan.setBounds(160, 110, 120, 19);

        p4_sedang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_sedang);
        p4_sedang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_sedang.setForeground(new java.awt.Color(51, 51, 51));
        p4_sedang.setText("Sedang");
        p4_sedang.setToolTipText("Sedang : agitasi atau mudah terangsang yang jelas terbukti selama anamnesis, mempengaruhi pembicaraan dan mobilitas umum atau ledakan-ledakan episodik yang terjadi secara sporadik\n");
        p4_sedang.setName("p4_sedang"); // NOI18N
        p4_sedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_sedangActionPerformed(evt);
            }
        });
        FormInput.add(p4_sedang);
        p4_sedang.setBounds(160, 200, 70, 20);

        p4_minimal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_minimal);
        p4_minimal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_minimal.setForeground(new java.awt.Color(51, 51, 51));
        p4_minimal.setText("Minimal");
        p4_minimal.setToolTipText("Minimal : gaduh gelisah minimal, keadaan patologis diragukan");
        p4_minimal.setName("p4_minimal"); // NOI18N
        p4_minimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_minimalActionPerformed(evt);
            }
        });
        FormInput.add(p4_minimal);
        p4_minimal.setBounds(160, 140, 80, 20);

        p4_agak_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_agak_berat);
        p4_agak_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_agak_berat.setForeground(new java.awt.Color(51, 51, 51));
        p4_agak_berat.setText("Agak Berat");
        p4_agak_berat.setToolTipText("<html>Agak Berat : tampak hiperaktivitas yang bermakna, atau sering terjadi ledakan atau aktivitas motorik yang menyebabkan<br>\n kesulitan bagi pasien tetap duduk untuk waktu yang lebih lama dari beberapa menit dalam setiap kesempatan</html>");
        p4_agak_berat.setName("p4_agak_berat"); // NOI18N
        p4_agak_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_agak_beratActionPerformed(evt);
            }
        });
        FormInput.add(p4_agak_berat);
        p4_agak_berat.setBounds(160, 230, 100, 20);

        p4_sangat_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_sangat_berat);
        p4_sangat_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_sangat_berat.setForeground(new java.awt.Color(51, 51, 51));
        p4_sangat_berat.setText("Sangat Berat");
        p4_sangat_berat.setToolTipText("<html>Sangat Berat : gaduh gelisah yang mencolok secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal,  <br>\npercepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelahan</html>\n");
        p4_sangat_berat.setName("p4_sangat_berat"); // NOI18N
        p4_sangat_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_sangat_beratActionPerformed(evt);
            }
        });
        FormInput.add(p4_sangat_berat);
        p4_sangat_berat.setBounds(160, 290, 100, 20);

        p4_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(p4_berat);
        p4_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p4_berat.setForeground(new java.awt.Color(51, 51, 51));
        p4_berat.setText("Berat");
        p4_berat.setToolTipText("Berat : gaduh gelisah yang mencolok mendominasi anamnesis, membatasi perhatian, sedemikian rupa sehingga mempengaruhi fungsi sehari-hari, seperti makan dan tidur");
        p4_berat.setName("p4_berat"); // NOI18N
        p4_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4_beratActionPerformed(evt);
            }
        });
        FormInput.add(p4_berat);
        p4_berat.setBounds(160, 260, 80, 20);

        skor_p4.setEditable(false);
        skor_p4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor_p4.setHighlighter(null);
        skor_p4.setName("skor_p4"); // NOI18N
        skor_p4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor_p4ActionPerformed(evt);
            }
        });
        FormInput.add(skor_p4);
        skor_p4.setBounds(420, 290, 80, 23);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Skor :");
        jLabel2.setName("jLabel2"); // NOI18N
        FormInput.add(jLabel2);
        jLabel2.setBounds(380, 290, 37, 20);

        jLabel12.setText("Masuk Ruangan :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(240, 1370, 160, 23);

        jLabel13.setText("Permusuhan (P7)  :");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 340, 140, 23);

        p7_ringan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_ringan);
        p7_ringan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_ringan.setForeground(new java.awt.Color(51, 51, 51));
        p7_ringan.setText("Ringan");
        p7_ringan.setToolTipText("Ringan : melampiaskan kemarahan secara tak langsung atau ditahan, seperti sarkasme, sikap tidak sopan, ekspresi bermusuhan, dan kadang - kadang iritabilitas");
        p7_ringan.setName("p7_ringan"); // NOI18N
        p7_ringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_ringanActionPerformed(evt);
            }
        });
        FormInput.add(p7_ringan);
        p7_ringan.setBounds(160, 400, 90, 20);

        p7_tidak_ditemukan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_tidak_ditemukan);
        p7_tidak_ditemukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_tidak_ditemukan.setForeground(new java.awt.Color(51, 51, 51));
        p7_tidak_ditemukan.setText("Tidak Ditemukan");
        p7_tidak_ditemukan.setToolTipText("Tidak Ditemukan : tidak ditemukan adanya permusuhan\n");
        p7_tidak_ditemukan.setName("p7_tidak_ditemukan"); // NOI18N
        p7_tidak_ditemukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_tidak_ditemukanActionPerformed(evt);
            }
        });
        FormInput.add(p7_tidak_ditemukan);
        p7_tidak_ditemukan.setBounds(160, 340, 120, 19);

        p7_sedang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_sedang);
        p7_sedang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_sedang.setForeground(new java.awt.Color(51, 51, 51));
        p7_sedang.setText("Sedang");
        p7_sedang.setToolTipText("Sedang : adanya sikap bermusuhan yang nyata, sering memperlihatkan iritabilitas, dan ekspresi kemarahan atau kebencian yang langsung");
        p7_sedang.setName("p7_sedang"); // NOI18N
        p7_sedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_sedangActionPerformed(evt);
            }
        });
        FormInput.add(p7_sedang);
        p7_sedang.setBounds(160, 430, 70, 20);

        p7_minimal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_minimal);
        p7_minimal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_minimal.setForeground(new java.awt.Color(51, 51, 51));
        p7_minimal.setText("Minimal");
        p7_minimal.setToolTipText("Minimal : permusuhan minimal, keadaan patologis diragukan");
        p7_minimal.setName("p7_minimal"); // NOI18N
        p7_minimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_minimalActionPerformed(evt);
            }
        });
        FormInput.add(p7_minimal);
        p7_minimal.setBounds(160, 370, 80, 20);

        p7_agak_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_agak_berat);
        p7_agak_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_agak_berat.setForeground(new java.awt.Color(51, 51, 51));
        p7_agak_berat.setText("Agak Berat");
        p7_agak_berat.setToolTipText("Agak berat : pasien sangat mudah marah dan kadang-kadang memaki dengan kata - kata kasar atau mengancam");
        p7_agak_berat.setName("p7_agak_berat"); // NOI18N
        p7_agak_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_agak_beratActionPerformed(evt);
            }
        });
        FormInput.add(p7_agak_berat);
        p7_agak_berat.setBounds(160, 460, 100, 20);

        p7_sangat_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_sangat_berat);
        p7_sangat_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_sangat_berat.setForeground(new java.awt.Color(51, 51, 51));
        p7_sangat_berat.setText("Sangat Berat");
        p7_sangat_berat.setToolTipText("Sangat Berat : kemarahan hebat yang mengakibatkan subyek sangat tidak kooperatif, menghalangi interaksi, atau secara episodik melakukan penyerangan fisik terhadap orang lain");
        p7_sangat_berat.setName("p7_sangat_berat"); // NOI18N
        p7_sangat_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_sangat_beratActionPerformed(evt);
            }
        });
        FormInput.add(p7_sangat_berat);
        p7_sangat_berat.setBounds(160, 520, 100, 20);

        p7_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(p7_berat);
        p7_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        p7_berat.setForeground(new java.awt.Color(51, 51, 51));
        p7_berat.setText("Berat");
        p7_berat.setToolTipText("<html>Berat :  tidak kooperatif dan mencaci maki dengan kasar atau mengancam, khususnya dalam upaya mempengaruhi pemeriksa, dan berdampak serius terhadap hubungan sosial. <br>Pasien dapat beringas dan merusak tapi tidak menyerang orang lain secara fisik.</html>");
        p7_berat.setName("p7_berat"); // NOI18N
        p7_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7_beratActionPerformed(evt);
            }
        });
        FormInput.add(p7_berat);
        p7_berat.setBounds(160, 490, 80, 20);

        skor_p7.setEditable(false);
        skor_p7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor_p7.setHighlighter(null);
        skor_p7.setName("skor_p7"); // NOI18N
        FormInput.add(skor_p7);
        skor_p7.setBounds(420, 520, 80, 23);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Skor :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(380, 520, 37, 20);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 330, 750, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 330, 750, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 560, 750, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 560, 750, 1);

        jLabel14.setText("Ketegangan (G4)  :");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 580, 140, 23);

        g4_ringan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_ringan);
        g4_ringan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_ringan.setForeground(new java.awt.Color(51, 51, 51));
        g4_ringan.setText("Ringan");
        g4_ringan.setToolTipText("<html>Ringan : postur dan gerakan gerakan yang menunjukan kekhawatiran ringan, ketidaktenangan yang sesekali timbul, <br>perubahan posisi, dan tremor tangan yang halus dan cepat.</html>");
        g4_ringan.setName("g4_ringan"); // NOI18N
        g4_ringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_ringanActionPerformed(evt);
            }
        });
        FormInput.add(g4_ringan);
        g4_ringan.setBounds(160, 640, 90, 20);

        g4_tidak_ditemukan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_tidak_ditemukan);
        g4_tidak_ditemukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_tidak_ditemukan.setForeground(new java.awt.Color(51, 51, 51));
        g4_tidak_ditemukan.setText("Tidak Ditemukan");
        g4_tidak_ditemukan.setToolTipText("Tidak Ditemukan : tidak ditemukan adanya ketegangan");
        g4_tidak_ditemukan.setName("g4_tidak_ditemukan"); // NOI18N
        g4_tidak_ditemukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_tidak_ditemukanActionPerformed(evt);
            }
        });
        FormInput.add(g4_tidak_ditemukan);
        g4_tidak_ditemukan.setBounds(160, 580, 120, 19);

        g4_sedang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_sedang);
        g4_sedang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_sedang.setForeground(new java.awt.Color(51, 51, 51));
        g4_sedang.setText("Sedang");
        g4_sedang.setToolTipText("Sedang : suatu penampilan yang nyata - nyata gelisah yang terbukti dari adanya berbagai manifestasi, seperti perilaku tidak tenang, tremor tangan yang nyata, keringat berlebihan, manerisme karena gugup");
        g4_sedang.setName("g4_sedang"); // NOI18N
        g4_sedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_sedangActionPerformed(evt);
            }
        });
        FormInput.add(g4_sedang);
        g4_sedang.setBounds(160, 670, 70, 20);

        g4_minimal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_minimal);
        g4_minimal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_minimal.setForeground(new java.awt.Color(51, 51, 51));
        g4_minimal.setText("Minimal");
        g4_minimal.setToolTipText("Minimal : ketegangan minimal, keadaan patologis diragukan");
        g4_minimal.setName("g4_minimal"); // NOI18N
        g4_minimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_minimalActionPerformed(evt);
            }
        });
        FormInput.add(g4_minimal);
        g4_minimal.setBounds(160, 610, 80, 20);

        g4_agak_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_agak_berat);
        g4_agak_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_agak_berat.setForeground(new java.awt.Color(51, 51, 51));
        g4_agak_berat.setText("Agak Berat");
        g4_agak_berat.setToolTipText("<html>Agak Berat : ketegangan berat yang dibuktikan oleh manifestasi, seperti gemetar karena gugup, keringat yang berlebihan, dan ketidaktenangan.<br> Tetapi perilaku selama anamnesis tidak terpengaruh secara bermakna</html>");
        g4_agak_berat.setName("g4_agak_berat"); // NOI18N
        g4_agak_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_agak_beratActionPerformed(evt);
            }
        });
        FormInput.add(g4_agak_berat);
        g4_agak_berat.setBounds(160, 700, 100, 20);

        g4_sangat_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_sangat_berat);
        g4_sangat_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_sangat_berat.setForeground(new java.awt.Color(51, 51, 51));
        g4_sangat_berat.setText("Sangat Berat");
        g4_sangat_berat.setToolTipText("<html>Sangat Berat : ketegangan sangat mencolok yang dimanifestasikan sebagai tanda-tanda panik atau percepatan gerakan motorik kasar, <br>seperti langkah cepat yang gelisah, ketidakmampuan tetap duduk tenang dalam waktu lebih dari satu menit, sehingga anamnesis tidak bisa dilanjutkan.</html>");
        g4_sangat_berat.setName("g4_sangat_berat"); // NOI18N
        g4_sangat_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_sangat_beratActionPerformed(evt);
            }
        });
        FormInput.add(g4_sangat_berat);
        g4_sangat_berat.setBounds(160, 760, 100, 20);

        g4_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(g4_berat);
        g4_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g4_berat.setForeground(new java.awt.Color(51, 51, 51));
        g4_berat.setText("Berat");
        g4_berat.setToolTipText("<html>Berat : gaduh gelisah yang mencolok, secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal,<br> percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelihan. </html>");
        g4_berat.setName("g4_berat"); // NOI18N
        g4_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4_beratActionPerformed(evt);
            }
        });
        FormInput.add(g4_berat);
        g4_berat.setBounds(160, 730, 80, 20);

        skor_g4.setEditable(false);
        skor_g4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor_g4.setHighlighter(null);
        skor_g4.setName("skor_g4"); // NOI18N
        FormInput.add(skor_g4);
        skor_g4.setBounds(420, 760, 80, 23);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Skor :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(380, 760, 37, 20);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(0, 800, 750, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 800, 750, 1);

        jLabel15.setText("Tidak Kooperatif (G8) :");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 820, 140, 23);

        g8_ringan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_ringan);
        g8_ringan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_ringan.setForeground(new java.awt.Color(51, 51, 51));
        g8_ringan.setText("Ringan");
        g8_ringan.setToolTipText("Ringan : patuh tapi disertai sikap marah,tidak sabar, atau sarkasme, mungkin ada penolakan yang tidak menganggu penyelidikan terhadap masalah – masalah sensitive selama anamnesis.");
        g8_ringan.setName("g8_ringan"); // NOI18N
        g8_ringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_ringanActionPerformed(evt);
            }
        });
        FormInput.add(g8_ringan);
        g8_ringan.setBounds(160, 880, 90, 20);

        g8_tidak_ditemukan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_tidak_ditemukan);
        g8_tidak_ditemukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_tidak_ditemukan.setForeground(new java.awt.Color(51, 51, 51));
        g8_tidak_ditemukan.setText("Tidak Ditemukan");
        g8_tidak_ditemukan.setToolTipText("Tidak Ditemukan : tidak ditemukan adanya ketidak kooperatifan\n");
        g8_tidak_ditemukan.setName("g8_tidak_ditemukan"); // NOI18N
        g8_tidak_ditemukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_tidak_ditemukanActionPerformed(evt);
            }
        });
        FormInput.add(g8_tidak_ditemukan);
        g8_tidak_ditemukan.setBounds(160, 820, 120, 19);

        g8_sedang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_sedang);
        g8_sedang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_sedang.setForeground(new java.awt.Color(51, 51, 51));
        g8_sedang.setText("Sedang");
        g8_sedang.setToolTipText("<html>Sedang : kadang menolak patuh pada tuntutan social normal, mis. Merapikan tempat tidur atau mengikuti kegiatan sesuai jadwal. <br>Pasien dapat bersikap bermusuhan, defensive, atau bersifat negatif, tetapi biasanya masih dapat diatasi.</html>");
        g8_sedang.setName("g8_sedang"); // NOI18N
        g8_sedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_sedangActionPerformed(evt);
            }
        });
        FormInput.add(g8_sedang);
        g8_sedang.setBounds(160, 910, 70, 20);

        g8_minimal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_minimal);
        g8_minimal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_minimal.setForeground(new java.awt.Color(51, 51, 51));
        g8_minimal.setText("Minimal");
        g8_minimal.setToolTipText("Minimal : tidak kooperatif minimal, keadaan patologis diragukan");
        g8_minimal.setName("g8_minimal"); // NOI18N
        g8_minimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_minimalActionPerformed(evt);
            }
        });
        FormInput.add(g8_minimal);
        g8_minimal.setBounds(160, 850, 80, 20);

        g8_agak_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_agak_berat);
        g8_agak_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_agak_berat.setForeground(new java.awt.Color(51, 51, 51));
        g8_agak_berat.setText("Agak Berat");
        g8_agak_berat.setToolTipText("<html>Agak Berat : sering tidak patuh terhadap tuntutan lingkungan dan mungkin serign disebut sebagai orang dengan masalah sikap yang serius.<br> Ketidak kooperatifan tercermin jelas dalam sikap defensive atau iritabilitas terhadap pemeriksa dan mungkin menolak banyak ditanya.</html>");
        g8_agak_berat.setName("g8_agak_berat"); // NOI18N
        g8_agak_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_agak_beratActionPerformed(evt);
            }
        });
        FormInput.add(g8_agak_berat);
        g8_agak_berat.setBounds(160, 940, 100, 20);

        g8_sangat_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_sangat_berat);
        g8_sangat_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_sangat_berat.setForeground(new java.awt.Color(51, 51, 51));
        g8_sangat_berat.setText("Sangat Berat");
        g8_sangat_berat.setToolTipText("<html>Sangat Berat : resistensi aktif yang berdampak serius pada hamper seluruh fungsi. Pasien mungkin menolak berpartisipasi dalam aktivitas sosial apapun,<br> mengurus kebersihan diri, bercakapcakap dengan keluarga, dan bahkan menolak anamnesis singkat.</html>");
        g8_sangat_berat.setName("g8_sangat_berat"); // NOI18N
        g8_sangat_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_sangat_beratActionPerformed(evt);
            }
        });
        FormInput.add(g8_sangat_berat);
        g8_sangat_berat.setBounds(160, 1000, 100, 20);

        g8_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(g8_berat);
        g8_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g8_berat.setForeground(new java.awt.Color(51, 51, 51));
        g8_berat.setText("Berat");
        g8_berat.setToolTipText("<html>Berat : pasien sangat tidak kooperatif, negativistik, dan mungkin membangkang. Menolak untuk patuh terhadap sebagian besar tuntutan social dan<br> mungkin tidak mau memulai atau mengikuti anamnesis sepenuhnya</html>");
        g8_berat.setName("g8_berat"); // NOI18N
        g8_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8_beratActionPerformed(evt);
            }
        });
        FormInput.add(g8_berat);
        g8_berat.setBounds(160, 970, 80, 20);

        skor_g8.setEditable(false);
        skor_g8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor_g8.setHighlighter(null);
        skor_g8.setName("skor_g8"); // NOI18N
        skor_g8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor_g8ActionPerformed(evt);
            }
        });
        FormInput.add(skor_g8);
        skor_g8.setBounds(420, 1000, 80, 23);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Skor :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(380, 1000, 37, 20);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 1040, 750, 1);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 1040, 750, 1);

        jLabel16.setText("yang Buruk (G14) :");
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 1080, 140, 23);

        g14_ringan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_ringan);
        g14_ringan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_ringan.setForeground(new java.awt.Color(51, 51, 51));
        g14_ringan.setText("Ringan");
        g14_ringan.setToolTipText("Ringan : pasien cenderung mudah marah dan frustasi bila menghadapi stress atau pemuasannya ditolak, tapi jarang bertindak impulsive");
        g14_ringan.setName("g14_ringan"); // NOI18N
        g14_ringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_ringanActionPerformed(evt);
            }
        });
        FormInput.add(g14_ringan);
        g14_ringan.setBounds(160, 1140, 90, 20);

        g14_tidak_ditemukan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_tidak_ditemukan);
        g14_tidak_ditemukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_tidak_ditemukan.setForeground(new java.awt.Color(51, 51, 51));
        g14_tidak_ditemukan.setText("Tidak Ditemukan");
        g14_tidak_ditemukan.setToolTipText("Tidak ditemukan adanya pengendalian impuls yang buruk");
        g14_tidak_ditemukan.setName("g14_tidak_ditemukan"); // NOI18N
        g14_tidak_ditemukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_tidak_ditemukanActionPerformed(evt);
            }
        });
        FormInput.add(g14_tidak_ditemukan);
        g14_tidak_ditemukan.setBounds(160, 1080, 120, 19);

        g14_sedang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_sedang);
        g14_sedang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_sedang.setForeground(new java.awt.Color(51, 51, 51));
        g14_sedang.setText("Sedang");
        g14_sedang.setToolTipText("<html>Sedang : dengan provokasi minimal, pasien menjadi marah dan mencaci maki. Mungkin sesekali mengancam, merusak, atau terdapat satu-dua episode <br>yang melibatkan konfrontasi fisik atau persilisihan ringan</html>");
        g14_sedang.setName("g14_sedang"); // NOI18N
        g14_sedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_sedangActionPerformed(evt);
            }
        });
        FormInput.add(g14_sedang);
        g14_sedang.setBounds(160, 1170, 70, 20);

        g14_minimal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_minimal);
        g14_minimal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_minimal.setForeground(new java.awt.Color(51, 51, 51));
        g14_minimal.setText("Minimal");
        g14_minimal.setToolTipText("Minimal, patologis diragukan");
        g14_minimal.setName("g14_minimal"); // NOI18N
        g14_minimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_minimalActionPerformed(evt);
            }
        });
        FormInput.add(g14_minimal);
        g14_minimal.setBounds(160, 1110, 80, 20);

        g14_agak_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_agak_berat);
        g14_agak_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_agak_berat.setForeground(new java.awt.Color(51, 51, 51));
        g14_agak_berat.setText("Agak Berat");
        g14_agak_berat.setToolTipText("<html>Agak Berat : pasien memperlihatkan episode impulsive yang berulang ulang termasuk mencaci maki, merusak harta benda, atau ancaman fisik. <br>Mungkin ada satu atau dua episode yang melibatkan serangan serius hingga pasien perlu diisolasi, difiksasi, atau bila perlu diberikan sedasi.</html>");
        g14_agak_berat.setName("g14_agak_berat"); // NOI18N
        g14_agak_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_agak_beratActionPerformed(evt);
            }
        });
        FormInput.add(g14_agak_berat);
        g14_agak_berat.setBounds(160, 1200, 100, 20);

        g14_sangat_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_sangat_berat);
        g14_sangat_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_sangat_berat.setForeground(new java.awt.Color(51, 51, 51));
        g14_sangat_berat.setText("Sangat Berat");
        g14_sangat_berat.setToolTipText("Sangat Berat : pasien memperlihatkan serangan yang nyata mengancam keselamatan orang, penyerangan seksual, perilaku brutal yang berulang, atau perilaku menyakiti diri sendiri. ");
        g14_sangat_berat.setName("g14_sangat_berat"); // NOI18N
        g14_sangat_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_sangat_beratActionPerformed(evt);
            }
        });
        FormInput.add(g14_sangat_berat);
        g14_sangat_berat.setBounds(160, 1260, 100, 20);

        g14_berat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(g14_berat);
        g14_berat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        g14_berat.setForeground(new java.awt.Color(51, 51, 51));
        g14_berat.setText("Berat");
        g14_berat.setToolTipText("<html>Berat : pasien sering menunjukkan agresivitas secara impulsive, mengancam, menuntut, dan merusak, tanpa mempertimbangkan konsekuensinya.<br> Menunjukkan perilaku menyerang dan mungkin juga serangan seksual, atau berperilaku yang merupakan respon terhadap perintah yang bersifat halusinasi.</html>");
        g14_berat.setName("g14_berat"); // NOI18N
        g14_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g14_beratActionPerformed(evt);
            }
        });
        FormInput.add(g14_berat);
        g14_berat.setBounds(160, 1230, 80, 20);

        skor_g14.setEditable(false);
        skor_g14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor_g14.setHighlighter(null);
        skor_g14.setName("skor_g14"); // NOI18N
        FormInput.add(skor_g14);
        skor_g14.setBounds(420, 1260, 80, 23);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Skor :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(380, 1260, 37, 20);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 1320, 750, 1);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 1320, 750, 1);

        jLabel17.setText("Pengendalian Impuls");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 1060, 140, 23);

        ruangan.setEditable(false);
        ruangan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ruangan.setHighlighter(null);
        ruangan.setName("ruangan"); // NOI18N
        FormInput.add(ruangan);
        ruangan.setBounds(420, 1370, 190, 23);

        tskor.setEditable(false);
        tskor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tskor.setHighlighter(null);
        tskor.setName("tskor"); // NOI18N
        tskor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tskorActionPerformed(evt);
            }
        });
        FormInput.add(tskor);
        tskor.setBounds(420, 1340, 80, 23);

        jLabel22.setText("J.K. :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 40, 70, 23);

        jLabel23.setText("Total Skor :");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(240, 1340, 160, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnPetugas);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String gaduh_gelisah = "";
        String permusuhan = "";
        String ketegangan = "";
        String tidak_kooperatif = "";
        String impuls = "";
        
        if (p4_tidak_ditemukan.isSelected()){
            gaduh_gelisah = "Tidak Ditemukan : tidak ditemukan adanya gaduh gelisah ";
        } if (p4_minimal.isSelected()){
            gaduh_gelisah = "Minimal : gaduh gelisah minimal, keadaan patologis diragukan";
        } if (p4_ringan.isSelected()){
            gaduh_gelisah = "Ringan : sedikit agiatif, waspada berlebihan, atau sedikit mudah terangsang selama anamnesis, tetapi tanpa episode gaduh gelisah yang jelas atau labilitas emosi yang mencolok.";
        } if (p4_sedang.isSelected()){
            gaduh_gelisah = "Sedang : agitasi atau mudah terangsang yang jelas terbukti selama anamnesis, mempengaruhi pembicaraan dan mobilitas umum atau ledakan-ledakan episodik yang terjadi secara sporadik ";
        } if (p4_agak_berat.isSelected()){
            gaduh_gelisah = "Agak Berat : tampak hiperaktivitas yang bermakna, atau sering terjadi ledakan atau aktivitas motorik yang menyebabkan kesulitan bagi pasien tetap duduk untuk waktu yang lebih lama dari beberapa menit dalam setiap kesempatan";
        } if (p4_berat.isSelected()){
            gaduh_gelisah = "Berat : gaduh gelisah yang mencolok mendominasi anamnesis, membatasi perhatian, sedemikian rupa sehingga mempengaruhi fungsi sehari-hari, seperti makan dan tidur";
        } if (p4_sangat_berat.isSelected()){
            gaduh_gelisah = "Sangat Berat : gaduh gelisah yang mencolok secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelahan ";
        }
        
        if (p7_tidak_ditemukan.isSelected()){
            permusuhan = "Tidak Ditemukan : tidak ditemukan adanya permusuhan ";
        } if (p7_minimal.isSelected()){
            permusuhan = "Minimal : permusuhan minimal, keadaan patologis diragukan";
        } if (p7_ringan.isSelected()){
            permusuhan = "Ringan : melampiaskan kemarahan secara tak langsung atau ditahan, seperti sarkasme, sikap tidak sopan, ekspresi bermusuhan, dan kadang - kadang iritabilitas";
        } if (p7_sedang.isSelected()){
            permusuhan = "Sedang : adanya sikap bermusuhan yang nyata, sering memperlihatkan iritabilitas, dan ekspresi kemarahan atau kebencian yang langsung";
        } if (p7_agak_berat.isSelected()){
            permusuhan = "Agak berat : pasien sangat mudah marah dan kadang-kadang memaki dengan kata - kata kasar atau mengancam";
        } if (p7_berat.isSelected()){
            permusuhan = "Berat :  tidak kooperatif dan mencaci maki dengan kasar atau mengancam, khususnya dalam upaya mempengaruhi pemeriksa, dan berdampak serius terhadap hubungan sosial. Pasien dapat beringas dan merusak tapi tidak menyerang orang lain secara fisik";
        } if (p7_sangat_berat.isSelected()){
            permusuhan = "Sangat Berat : kemarahan hebat yang mengakibatkan subyek sangat tidak kooperatif, menghalangi interaksi, atau secara episodik melakukan penyerangan fisik terhadap orang lain";
        }
        
        if (g4_tidak_ditemukan.isSelected()){
            ketegangan = "Tidak Ditemukan : tidak ditemukan adanya ketegangan";
        } if (g4_minimal.isSelected()){
            ketegangan = "Minimal : ketegangan minimal, keadaan patologis diragukan";
        } if (g4_ringan.isSelected()){
            ketegangan = "Ringan : postur dan gerakan gerakan yang menunjukan kekhawatiran ringan, ketidaktenangan yang sesekali timbul, perubahan posisi, dan tremor tangan yang halus dan cepat";
        } if (g4_sedang.isSelected()){
            ketegangan = "Sedang : suatu penampilan yang nyata - nyata gelisah yang terbukti dari adanya berbagai manifestasi, seperti perilaku tidak tenang, tremor tangan yang nyata, keringat berlebihan, manerisme karena gugup";
        } if (g4_agak_berat.isSelected()){
            ketegangan = "Agak Berat : ketegangan berat yang dibuktikan oleh manifestasi, seperti gemetar karena gugup, keringat yang berlebihan, dan ketidaktenangan. Tetapi perilaku selama anamnesis tidak terpengaruh secara bermakna";
        } if (g4_berat.isSelected()){
            ketegangan = "Berat : gaduh gelisah yang mencolok, secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelihan";
        } if (g4_sangat_berat.isSelected()){
            ketegangan = "Sangat Berat : ketegangan sangat mencolok yang dimanifestasikan sebagai tanda-tanda panik atau percepatan gerakan motorik kasar, seperti langkah cepat yang gelisah, ketidakmampuan tetap duduk tenang dalam waktu lebih dari satu menit, sehingga anamnesis tidak bisa dilanjutkan";
        }
        
        if (g8_tidak_ditemukan.isSelected()){
            tidak_kooperatif = "Tidak Ditemukan : tidak ditemukan adanya ketidak kooperatifan";
        } if (g8_minimal.isSelected()){
            tidak_kooperatif = "Minimal : tidak kooperatif minimal, keadaan patologis diragukan";
        } if (g8_ringan.isSelected()){
            tidak_kooperatif = "Ringan : patuh tapi disertai sikap marah,tidak sabar, atau sarkasme, mungkin ada penolakan yang tidak menganggu penyelidikan terhadap masalah – masalah sensitive selama anamnesis.";
        } if (g8_sedang.isSelected()){
            tidak_kooperatif = "Sedang : kadang menolak patuh pada tuntutan social normal, mis. Merapikan tempat tidur atau mengikuti kegiatan sesuai jadwal. Pasien dapat bersikap bermusuhan, defensive, atau bersifat negatif, tetapi biasanya masih dapat diatasi";
        } if (g8_agak_berat.isSelected()){
            tidak_kooperatif = "Agak Berat : sering tidak patuh terhadap tuntutan lingkungan dan mungkin serign disebut sebagai orang dengan masalah sikap yang serius. Ketidak kooperatifan tercermin jelas dalam sikap defensive atau iritabilitas terhadap pemeriksa dan mungkin menolak banyak ditanya";
        } if (g8_berat.isSelected()){
            tidak_kooperatif = "Berat : pasien sangat tidak kooperatif, negativistik, dan mungkin membangkang. Menolak untuk patuh terhadap sebagian besar tuntutan social dan mungkin tidak mau memulai atau mengikuti anamnesis sepenuhnya";
        } if (g8_sangat_berat.isSelected()){
            tidak_kooperatif = "Sangat Berat : resistensi aktif yang berdampak serius pada hamper seluruh fungsi. Pasien mungkin menolak berpartisipasi dalam aktivitas sosial apapun, mengurus kebersihan diri, bercakapcakap dengan keluarga, dan bahkan menolak anamnesis singkat";
        }
        
        if (g14_tidak_ditemukan.isSelected()){
            impuls = "Tidak ditemukan adanya pengendalian impuls yang buruk";
        } if (g14_minimal.isSelected()){
            impuls = "Minimal, patologis diragukan";
        } if (g14_ringan.isSelected()){
            impuls = "Ringan : pasien cenderung mudah marah dan frustasi bila menghadapi stress atau pemuasannya ditolak, tapi jarang bertindak impulsive";
        } if (g14_sedang.isSelected()){
            impuls = "Sedang : dengan provokasi minimal, pasien menjadi marah dan mencaci maki. Mungkin sesekali mengancam, merusak, atau terdapat satu-dua episode yang melibatkan konfrontasi fisik atau persilisihan ringan";
        } if (g14_agak_berat.isSelected()){
            impuls = "Agak Berat : pasien memperlihatkan episode impulsive yang berulang ulang termasuk mencaci maki, merusak harta benda, atau ancaman fisik. Mungkin ada satu atau dua episode yang melibatkan serangan serius hingga pasien perlu diisolasi, difiksasi, atau bila perlu diberikan sedasi";
        } if (g14_berat.isSelected()){
            impuls = "Berat : pasien sering menunjukkan agresivitas secara impulsive, mengancam, menuntut, dan merusak, tanpa mempertimbangkan konsekuensinya. Menunjukkan perilaku menyerang dan mungkin juga serangan seksual, atau berperilaku yang merupakan respon terhadap perintah yang bersifat halusinasi";
        } if (g14_sangat_berat.isSelected()){
            impuls = "Sangat Berat : pasien memperlihatkan serangan yang nyata mengancam keselamatan orang, penyerangan seksual, perilaku brutal yang berulang, atau perilaku menyakiti diri sendiri.";
        }
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else{
            if(Sequel.menyimpantf("penilaian_ranap_panss_ec","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",15,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdPetugas.getText(),gaduh_gelisah, skor_p4.getText(), permusuhan,
                skor_p7.getText(), ketegangan, skor_g4.getText(), tidak_kooperatif, skor_g8.getText(), impuls, skor_g14.getText(), tskor.getText(), ruangan.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnSimpanActionPerformed(null);
//        }else{
//           Valid.pindah(evt,CatatanKhusus,BtnBatal);
//        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }              
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_petugas,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join petugas on penilaian_ranap_panss_ec.kd_petugas=petugas.nip where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? order by penilaian_ranap_panss_ec.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_petugas,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join petugas on penilaian_ranap_panss_ec.kd_petugas=petugas.nip where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_ranap_panss_ec.kd_petugas like ? or petugas.nama like ?) order by penilaian_ranap_panss_ec.tanggal");
                }

                try {
                    if(TCari.getText().trim().equals("")){
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    }else{
                        ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                        ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                        ps.setString(3,"%"+TCari.getText()+"%");
                        ps.setString(4,"%"+TCari.getText()+"%");
                        ps.setString(5,"%"+TCari.getText()+"%");
                        ps.setString(6,"%"+TCari.getText()+"%");
                        ps.setString(7,"%"+TCari.getText()+"%");
                    }  
                    rs=ps.executeQuery();
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Petugas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Petugas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Gaduh Gelisah (P4)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Permusuhan (P7)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Ketegangan (G4)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tidak Kooperatif (G8)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pengendalian Impuls yang Buruk (G14)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Skor</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesimpulan Masuk Ruangan</b></td>"+
                                
                        "</tr>"
                    );
                    while(rs.next()){
                        htmlContent.append(
                            "<tr class='isi'>"+
                               "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                               "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                               "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                               "<td valign='top'>"+rs.getString("jk")+"</td>"+
                               "<td valign='top'>"+rs.getString("kd_petugas")+"</td>"+
                               "<td valign='top'>"+rs.getString("nama")+"</td>"+
                               "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                               "<td valign='top'>"+rs.getString("gaduh_gelisah")+"</td>"+
                               "<td valign='top'>"+rs.getString("permusuhan")+"</td>"+
                               "<td valign='top'>"+rs.getString("ketegangan")+"</td>"+
                               "<td valign='top'>"+rs.getString("tidak_kooperatif")+"</td>"+
                               "<td valign='top'>"+rs.getString("pengendalian_impuls_buruk")+"</td>"+
                               "<td valign='top'>"+rs.getString("total_skor")+"</td>"+
                               "<td valign='top'>"+rs.getString("kesimpulan")+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='4500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataPenilaianAwalMedisRalan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='4500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN PRE ANESTESI<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }

            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
//        Valid.pindah(evt,BtnDokter,Diagnosa);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void MnPenilaianMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianMedisActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianPanssEc.jasper","report","::[ Laporan Penilaian PANSS-EC ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                "penilaian_ranap_panss_ec.kd_petugas,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,petugas.nama "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+ 
                "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                "inner join petugas on penilaian_ranap_panss_ec.kd_petugas=petugas.nip "+
                "where penilaian_ranap_panss_ec.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and penilaian_ranap_panss_ec.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void TotalSkor() {
        try {
            tskor.setText((Integer.parseInt(skor_p4.getText())+Integer.parseInt(skor_p7.getText())+Integer.parseInt(skor_g4.getText())+Integer.parseInt(skor_g8.getText())+Integer.parseInt(skor_g14.getText()))+"");
            if (Integer.parseInt(tskor.getText()) >= 20) {
                ruangan.setText("UPIP");
            } else {
                ruangan.setText("Bangsal Akut");
            }
        }catch (Exception e){
            tskor.setText(Integer.toString(0));
            ruangan.setText("-");
        }
    }
    
    private void p4_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_beratActionPerformed
        // TODO add your handling code here:
        if (p4_berat.isSelected()){
            int skor1 = 6;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_beratActionPerformed

    private void p7_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_beratActionPerformed
        // TODO add your handling code here:
        if (p7_berat.isSelected()){
            int skor2 = 6;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_beratActionPerformed

    private void g4_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_beratActionPerformed
        // TODO add your handling code here:
        if (g4_berat.isSelected()){
            int skor3 = 6;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_beratActionPerformed

    private void g8_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_beratActionPerformed
        // TODO add your handling code here:
        if (g8_berat.isSelected()){
            int skor4 = 6;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_beratActionPerformed

    private void g14_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_beratActionPerformed
        // TODO add your handling code here:
        if (g14_berat.isSelected()){
            int skor5 = 6;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_beratActionPerformed

    private void skor_p4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor_p4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor_p4ActionPerformed

    private void p4_tidak_ditemukanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4_tidak_ditemukanMousePressed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_p4_tidak_ditemukanMousePressed

    private void p4_tidak_ditemukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_tidak_ditemukanActionPerformed
        // TODO add your handling code here:
        if (p4_tidak_ditemukan.isSelected()){
            int skor1 = 1;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_tidak_ditemukanActionPerformed

    private void p4_minimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_minimalActionPerformed
        // TODO add your handling code here:
        if (p4_minimal.isSelected()){
            int skor1 = 2;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_minimalActionPerformed

    private void p4_ringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_ringanActionPerformed
        // TODO add your handling code here:
        if (p4_ringan.isSelected()){
            int skor1 = 3;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_ringanActionPerformed

    private void p4_sedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_sedangActionPerformed
        // TODO add your handling code here:
        if (p4_sedang.isSelected()){
            int skor1 = 4;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_sedangActionPerformed

    private void p4_agak_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_agak_beratActionPerformed
        // TODO add your handling code here:
        if (p4_agak_berat.isSelected()){
            int skor1 = 5;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_agak_beratActionPerformed

    private void p4_sangat_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p4_sangat_beratActionPerformed
        // TODO add your handling code here:
        if (p4_sangat_berat.isSelected()){
            int skor1 = 7;
            skor_p4.setText(Integer.toString(skor1));
        }
        TotalSkor();
    }//GEN-LAST:event_p4_sangat_beratActionPerformed

    private void p7_tidak_ditemukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_tidak_ditemukanActionPerformed
        // TODO add your handling code here:
        if (p7_tidak_ditemukan.isSelected()){
            int skor2 = 1;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_tidak_ditemukanActionPerformed

    private void p7_minimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_minimalActionPerformed
        // TODO add your handling code here:
        if (p7_minimal.isSelected()){
            int skor2 = 2;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_minimalActionPerformed

    private void p7_ringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_ringanActionPerformed
        // TODO add your handling code here:
        if (p7_ringan.isSelected()){            
            int skor2 = 3;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_ringanActionPerformed

    private void p7_sedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_sedangActionPerformed
        // TODO add your handling code here:
        if (p7_sedang.isSelected()){
            int skor2 = 4;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_sedangActionPerformed

    private void p7_agak_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_agak_beratActionPerformed
        // TODO add your handling code here:
        if (p7_agak_berat.isSelected()){
            int skor2 = 5;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_agak_beratActionPerformed

    private void p7_sangat_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p7_sangat_beratActionPerformed
        // TODO add your handling code here:
        if (p7_sangat_berat.isSelected()){
            int skor2 = 7;
            skor_p7.setText(Integer.toString(skor2));
        }
        TotalSkor();
    }//GEN-LAST:event_p7_sangat_beratActionPerformed

    private void g8_tidak_ditemukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_tidak_ditemukanActionPerformed
        // TODO add your handling code here:
        if (g8_tidak_ditemukan.isSelected()){
            int skor4 = 1;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_tidak_ditemukanActionPerformed

    private void g8_minimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_minimalActionPerformed
        // TODO add your handling code here:
        if (g8_minimal.isSelected()){
            int skor4 = 2;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_minimalActionPerformed

    private void g8_ringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_ringanActionPerformed
        // TODO add your handling code here:
        if (g8_ringan.isSelected()){
            int skor4 = 3;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_ringanActionPerformed

    private void g8_sedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_sedangActionPerformed
        // TODO add your handling code here:
        if (g8_sedang.isSelected()){
            int skor4 = 4;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_sedangActionPerformed

    private void g8_agak_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_agak_beratActionPerformed
        // TODO add your handling code here:
        if (g8_agak_berat.isSelected()){
            int skor4 = 5;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_agak_beratActionPerformed

    private void g8_sangat_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8_sangat_beratActionPerformed
        // TODO add your handling code here:
        if (g8_sangat_berat.isSelected()){
            int skor4 = 7;
            skor_g8.setText(Integer.toString(skor4));
        }
        TotalSkor();
    }//GEN-LAST:event_g8_sangat_beratActionPerformed

    private void g14_tidak_ditemukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_tidak_ditemukanActionPerformed
        // TODO add your handling code here:
        if (g14_tidak_ditemukan.isSelected()){
            int skor5 = 1;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_tidak_ditemukanActionPerformed

    private void g14_minimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_minimalActionPerformed
        // TODO add your handling code here:
        if (g14_minimal.isSelected()){
            int skor5 = 2;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_minimalActionPerformed

    private void g14_ringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_ringanActionPerformed
        // TODO add your handling code here:
        if (g14_ringan.isSelected()){
            int skor5 = 3;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_ringanActionPerformed

    private void g14_sedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_sedangActionPerformed
        // TODO add your handling code here:
        if (g14_sedang.isSelected()){
            int skor5 = 4;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_sedangActionPerformed

    private void g14_agak_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_agak_beratActionPerformed
        // TODO add your handling code here:
        if (g14_agak_berat.isSelected()){
            int skor5 = 5;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_agak_beratActionPerformed

    private void g14_sangat_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g14_sangat_beratActionPerformed
        // TODO add your handling code here:
        if (g14_sangat_berat.isSelected()){
            int skor5 = 7;
            skor_g14.setText(Integer.toString(skor5));
        }
        TotalSkor();
    }//GEN-LAST:event_g14_sangat_beratActionPerformed

    private void g4_tidak_ditemukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_tidak_ditemukanActionPerformed
        // TODO add your handling code here:
        if (g4_tidak_ditemukan.isSelected()){
            int skor3 = 1;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_tidak_ditemukanActionPerformed

    private void g4_minimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_minimalActionPerformed
        // TODO add your handling code here:
        if (g4_minimal.isSelected()){
            int skor3 = 2;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_minimalActionPerformed

    private void g4_ringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_ringanActionPerformed
        // TODO add your handling code here:
        if (g4_ringan.isSelected()){
            int skor3 = 3;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_ringanActionPerformed

    private void g4_sedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_sedangActionPerformed
        // TODO add your handling code here:
        if (g4_sedang.isSelected()){
            int skor3 = 4;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_sedangActionPerformed

    private void g4_agak_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_agak_beratActionPerformed
        // TODO add your handling code here:
        if (g4_agak_berat.isSelected()){
            int skor3 = 5;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_agak_beratActionPerformed

    private void g4_sangat_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4_sangat_beratActionPerformed
        // TODO add your handling code here:
        if (g4_sangat_berat.isSelected()){
            int skor3 = 7;
            skor_g4.setText(Integer.toString(skor3));
        }
        TotalSkor();
    }//GEN-LAST:event_g4_sangat_beratActionPerformed

    private void tskorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tskorActionPerformed
        // TODO add your handling code here:
        int p4 = Integer.parseInt(skor_p4.getText());
        int p7 = Integer.parseInt(skor_p7.getText());
        int g4 = Integer.parseInt(skor_g4.getText());
        int g8 = Integer.parseInt(skor_g8.getText());
        int g14 = Integer.parseInt(skor_g14.getText());
        
        int totalskor = p4 + p7 + g4 + g8 + g14;
        
        tskor.setText(Integer.toString(totalskor));
    }//GEN-LAST:event_tskorActionPerformed

    private void skor_g8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor_g8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor_g8ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianPanssEC dialog = new RMPenilaianPanssEC(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdPetugas;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmPetugas;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JRadioButton g14_agak_berat;
    private javax.swing.JRadioButton g14_berat;
    private javax.swing.JRadioButton g14_minimal;
    private javax.swing.JRadioButton g14_ringan;
    private javax.swing.JRadioButton g14_sangat_berat;
    private javax.swing.JRadioButton g14_sedang;
    private javax.swing.JRadioButton g14_tidak_ditemukan;
    private javax.swing.JRadioButton g4_agak_berat;
    private javax.swing.JRadioButton g4_berat;
    private javax.swing.JRadioButton g4_minimal;
    private javax.swing.JRadioButton g4_ringan;
    private javax.swing.JRadioButton g4_sangat_berat;
    private javax.swing.JRadioButton g4_sedang;
    private javax.swing.JRadioButton g4_tidak_ditemukan;
    private javax.swing.JRadioButton g8_agak_berat;
    private javax.swing.JRadioButton g8_berat;
    private javax.swing.JRadioButton g8_minimal;
    private javax.swing.JRadioButton g8_ringan;
    private javax.swing.JRadioButton g8_sangat_berat;
    private javax.swing.JRadioButton g8_sedang;
    private javax.swing.JRadioButton g8_tidak_ditemukan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private javax.swing.JLabel jLabel2;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label14;
    private javax.swing.JRadioButton p4_agak_berat;
    private javax.swing.JRadioButton p4_berat;
    private javax.swing.JRadioButton p4_minimal;
    private javax.swing.JRadioButton p4_ringan;
    private javax.swing.JRadioButton p4_sangat_berat;
    private javax.swing.JRadioButton p4_sedang;
    private javax.swing.JRadioButton p4_tidak_ditemukan;
    private javax.swing.JRadioButton p7_agak_berat;
    private javax.swing.JRadioButton p7_berat;
    private javax.swing.JRadioButton p7_minimal;
    private javax.swing.JRadioButton p7_ringan;
    private javax.swing.JRadioButton p7_sangat_berat;
    private javax.swing.JRadioButton p7_sedang;
    private javax.swing.JRadioButton p7_tidak_ditemukan;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox ruangan;
    private widget.ScrollPane scrollInput;
    private widget.TextBox skor_g14;
    private widget.TextBox skor_g4;
    private widget.TextBox skor_g8;
    private widget.TextBox skor_p4;
    private widget.TextBox skor_p7;
    private widget.Table tbObat;
    private widget.TextBox tskor;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_petugas,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join petugas on penilaian_ranap_panss_ec.kd_petugas=petugas.nip where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? order by penilaian_ranap_panss_ec.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_petugas,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join petugas on penilaian_ranap_panss_ec.kd_petugas=petugas.nip where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_ranap_panss_ec.kd_petugas like ? or petugas.nip like ?) order by penilaian_ranap_panss_ec.tanggal");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_petugas"),rs.getString("nama"),rs.getString("tanggal"),
                        rs.getString("gaduh_gelisah"),rs.getString("skor_p4"),rs.getString("permusuhan"),rs.getString("skor_p7"),rs.getString("ketegangan"),rs.getString("skor_g4"),rs.getString("tidak_kooperatif"),rs.getString("skor_g8"),rs.getString("pengendalian_impuls_buruk"),rs.getString("skor_g14"),rs.getString("total_skor"),rs.getString("kesimpulan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        TglAsuhan.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Jk.setText("");
        p4_tidak_ditemukan.setSelected(false);
        p7_tidak_ditemukan.setSelected(false);        
        g4_tidak_ditemukan.setSelected(false);        
        g8_tidak_ditemukan.setSelected(false);        
        g14_tidak_ditemukan.setSelected(false);
        p4_minimal.setSelected(false);        
        p7_minimal.setSelected(false);        
        g4_minimal.setSelected(false);
        g8_minimal.setSelected(false);        
        g14_minimal.setSelected(false);
        p4_ringan.setSelected(false);
        p7_ringan.setSelected(false);
        g4_ringan.setSelected(false);
        g8_ringan.setSelected(false);
        g14_ringan.setSelected(false);
        p4_sedang.setSelected(false);
        p7_sedang.setSelected(false);
        g4_sedang.setSelected(false);
        g8_sedang.setSelected(false);
        g14_sedang.setSelected(false);
        p4_agak_berat.setSelected(false);
        p7_agak_berat.setSelected(false);
        g4_agak_berat.setSelected(false);
        g8_agak_berat.setSelected(false);
        g14_agak_berat.setSelected(false);
        p4_berat.setSelected(false);
        p7_berat.setSelected(false);
        g4_berat.setSelected(false);
        g8_berat.setSelected(false);
        g14_berat.setSelected(false);
        p4_sangat_berat.setSelected(false);
        p7_sangat_berat.setSelected(false);
        g4_sangat_berat.setSelected(false);
        g8_sangat_berat.setSelected(false);
        g14_sangat_berat.setSelected(false);
        skor_p4.setText("");
        skor_p7.setText("");
        skor_g4.setText("");
        skor_g8.setText("");
        skor_g14.setText("");
        tskor.setText("");
        ruangan.setText("");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); 
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); 
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            
            String p4 = tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString();
            if (p4.contains("Tidak Ditemukan : tidak ditemukan adanya gaduh gelisah")) {
                p4_tidak_ditemukan.setSelected(true);
            } if (p4.contains("Minimal : gaduh gelisah minimal, keadaan patologis diragukan")) {
                p4_minimal.setSelected(true);
            } if (p4.contains("Ringan : sedikit agiatif, waspada berlebihan, atau sedikit mudah terangsang selama anamnesis, tetapi tanpa episode gaduh gelisah yang jelas atau labilitas emosi yang mencolok.")) {
                p4_ringan.setSelected(true);
            } if (p4.contains("Sedang : agitasi atau mudah terangsang yang jelas terbukti selama anamnesis, mempengaruhi pembicaraan dan mobilitas umum atau ledakan-ledakan episodik yang terjadi secara sporadik")) {
                p4_sedang.setSelected(true);
            } if (p4.contains("Agak Berat : tampak hiperaktivitas yang bermakna, atau sering terjadi ledakan atau aktivitas motorik yang menyebabkan kesulitan bagi pasien tetap duduk untuk waktu yang lebih lama dari beberapa menit dalam setiap kesempatan")) {
                p4_agak_berat.setSelected(true);
            } if (p4.contains("Berat : gaduh gelisah yang mencolok mendominasi anamnesis, membatasi perhatian, sedemikian rupa sehingga mempengaruhi fungsi sehari-hari, seperti makan dan tidur")) {
                p4_berat.setSelected(true);
            } if (p4.contains("Sangat Berat : gaduh gelisah yang mencolok secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelahan")) {
                p4_sangat_berat.setSelected(true);
            }
            
            skor_p4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            
            String p7 = tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString();
            if (p7.contains("Tidak Ditemukan : tidak ditemukan adanya permusuhan")) {
                p7_tidak_ditemukan.setSelected(true);
            } if (p7.contains("Minimal : permusuhan minimal, keadaan patologis diragukan")) {
                p7_minimal.setSelected(true);
            } if (p7.contains("Ringan : melampiaskan kemarahan secara tak langsung atau ditahan, seperti sarkasme, sikap tidak sopan, ekspresi bermusuhan, dan kadang - kadang iritabilitas")) {
                p7_ringan.setSelected(true);
            } if (p7.contains("Sedang : adanya sikap bermusuhan yang nyata, sering memperlihatkan iritabilitas, dan ekspresi kemarahan atau kebencian yang langsung")) {
                p7_sedang.setSelected(true);
            } if (p7.contains("Agak berat : pasien sangat mudah marah dan kadang-kadang memaki dengan kata - kata kasar atau mengancam")) {
                p7_agak_berat.setSelected(true);
            } if (p7.contains("Berat :  tidak kooperatif dan mencaci maki dengan kasar atau mengancam, khususnya dalam upaya mempengaruhi pemeriksa, dan berdampak serius terhadap hubungan sosial. Pasien dapat beringas dan merusak tapi tidak menyerang orang lain secara fisik")) {
                p7_berat.setSelected(true);
            } if (p7.contains("Sangat Berat : kemarahan hebat yang mengakibatkan subyek sangat tidak kooperatif, menghalangi interaksi, atau secara episodik melakukan penyerangan fisik terhadap orang lain")) {
                p7_sangat_berat.setSelected(true);
            }
            
            skor_p7.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString()); 
            
            String g4 = tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString();
            if (g4.contains("Tidak Ditemukan : tidak ditemukan adanya ketegangan")) {
                g4_tidak_ditemukan.setSelected(true);
            } if (g4.contains("Minimal : ketegangan minimal, keadaan patologis diragukan")) {
                g4_minimal.setSelected(true);
            } if (g4.contains("Ringan : postur dan gerakan gerakan yang menunjukan kekhawatiran ringan, ketidaktenangan yang sesekali timbul, perubahan posisi, dan tremor tangan yang halus dan cepat")) {
                g4_ringan.setSelected(true);
            } if (g4.contains("Sedang : suatu penampilan yang nyata - nyata gelisah yang terbukti dari adanya berbagai manifestasi, seperti perilaku tidak tenang, tremor tangan yang nyata, keringat berlebihan, manerisme karena gugup")) {
                g4_sedang.setSelected(true);
            } if (g4.contains("Agak Berat : ketegangan berat yang dibuktikan oleh manifestasi, seperti gemetar karena gugup, keringat yang berlebihan, dan ketidaktenangan. Tetapi perilaku selama anamnesis tidak terpengaruh secara bermakna")) {
                g4_agak_berat.setSelected(true);
            } if (g4.contains("Berat : gaduh gelisah yang mencolok, secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelihan")) {
                g4_berat.setSelected(true);
            } if (g4.contains("Sangat Berat : ketegangan sangat mencolok yang dimanifestasikan sebagai tanda-tanda panik atau percepatan gerakan motorik kasar, seperti langkah cepat yang gelisah, ketidakmampuan tetap duduk tenang dalam waktu lebih dari satu menit, sehingga anamnesis tidak bisa dilanjutkan")) {
                g4_sangat_berat.setSelected(true);
            }
            
            skor_g4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); 
            
            String g8 = tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString();
            if (g8.contains("Tidak Ditemukan : tidak ditemukan adanya ketidak kooperatifan")) {
                g8_tidak_ditemukan.setSelected(true);
            } if (g8.contains("Minimal : tidak kooperatif minimal, keadaan patologis diragukan")) {
                g8_minimal.setSelected(true);
            } if (g8.contains("Ringan : patuh tapi disertai sikap marah,tidak sabar, atau sarkasme, mungkin ada penolakan yang tidak menganggu penyelidikan terhadap masalah – masalah sensitive selama anamnesis.")) {
                g8_ringan.setSelected(true);
            } if (g8.contains("Sedang : kadang menolak patuh pada tuntutan social normal, mis. Merapikan tempat tidur atau mengikuti kegiatan sesuai jadwal. Pasien dapat bersikap bermusuhan, defensive, atau bersifat negatif, tetapi biasanya masih dapat diatasi")) {
                g8_sedang.setSelected(true);
            } if (g8.contains("Agak Berat : sering tidak patuh terhadap tuntutan lingkungan dan mungkin serign disebut sebagai orang dengan masalah sikap yang serius. Ketidak kooperatifan tercermin jelas dalam sikap defensive atau iritabilitas terhadap pemeriksa dan mungkin menolak banyak ditanya")) {
                g8_agak_berat.setSelected(true);
            } if (g8.contains("Berat : pasien sangat tidak kooperatif, negativistik, dan mungkin membangkang. Menolak untuk patuh terhadap sebagian besar tuntutan social dan mungkin tidak mau memulai atau mengikuti anamnesis sepenuhnya")) {
                g8_berat.setSelected(true);
            } if (g8.contains("Sangat Berat : resistensi aktif yang berdampak serius pada hamper seluruh fungsi. Pasien mungkin menolak berpartisipasi dalam aktivitas sosial apapun, mengurus kebersihan diri, bercakapcakap dengan keluarga, dan bahkan menolak anamnesis singkat")) {
                g8_sangat_berat.setSelected(true);
            }
            
            skor_g8.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString()); 
            
            String g14 = tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString();
            if (g14.contains("Tidak ditemukan adanya pengendalian impuls yang buruk")) {
                g14_tidak_ditemukan.setSelected(true);
            } if (g14.contains("Minimal, patologis diragukan")) {
                g14_minimal.setSelected(true);
            } if (g14.contains("Ringan : pasien cenderung mudah marah dan frustasi bila menghadapi stress atau pemuasannya ditolak, tapi jarang bertindak impulsive")) {
                g14_ringan.setSelected(true);
            } if (g14.contains("Sedang : dengan provokasi minimal, pasien menjadi marah dan mencaci maki. Mungkin sesekali mengancam, merusak, atau terdapat satu-dua episode yang melibatkan konfrontasi fisik atau persilisihan ringan")) {
                g14_sedang.setSelected(true);
            } if (g14.contains("Agak Berat : pasien memperlihatkan episode impulsive yang berulang ulang termasuk mencaci maki, merusak harta benda, atau ancaman fisik. Mungkin ada satu atau dua episode yang melibatkan serangan serius hingga pasien perlu diisolasi, difiksasi, atau bila perlu diberikan sedasi")) {
                g14_agak_berat.setSelected(true);
            } if (g14.contains("Berat : pasien sering menunjukkan agresivitas secara impulsive, mengancam, menuntut, dan merusak, tanpa mempertimbangkan konsekuensinya. Menunjukkan perilaku menyerang dan mungkin juga serangan seksual, atau berperilaku yang merupakan respon terhadap perintah yang bersifat halusinasi")) {
                g14_berat.setSelected(true);
            } if (g14.contains("Sangat Berat : pasien memperlihatkan serangan yang nyata mengancam keselamatan orang, penyerangan seksual, perilaku brutal yang berulang, atau perilaku menyakiti diri sendiri. ")) {
                g14_sangat_berat.setSelected(true);
            }
            
            skor_g14.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString()); 
            
            tskor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString()); 
            ruangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString()); 
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpanss_ec());
        BtnHapus.setEnabled(akses.getpanss_ec());
        BtnEdit.setEnabled(akses.getpanss_ec());
        BtnEdit.setEnabled(akses.getpanss_ec());
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if(NmPetugas.getText().equals("")){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_ranap_panss_ec where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        String gaduh_gelisah = "";
        String permusuhan = "";
        String ketegangan = "";
        String tidak_kooperatif = "";
        String impuls = "";
        
        if (p4_tidak_ditemukan.isSelected()){
            gaduh_gelisah = "Tidak Ditemukan : tidak ditemukan adanya gaduh gelisah ";
        } if (p4_minimal.isSelected()){
            gaduh_gelisah = "Minimal : gaduh gelisah minimal, keadaan patologis diragukan";
        } if (p4_ringan.isSelected()){
            gaduh_gelisah = "Ringan : sedikit agiatif, waspada berlebihan, atau sedikit mudah terangsang selama anamnesis, tetapi tanpa episode gaduh gelisah yang jelas atau labilitas emosi yang mencolok.";
        } if (p4_sedang.isSelected()){
            gaduh_gelisah = "Sedang : agitasi atau mudah terangsang yang jelas terbukti selama anamnesis, mempengaruhi pembicaraan dan mobilitas umum atau ledakan-ledakan episodik yang terjadi secara sporadik ";
        } if (p4_agak_berat.isSelected()){
            gaduh_gelisah = "Agak Berat : tampak hiperaktivitas yang bermakna, atau sering terjadi ledakan atau aktivitas motorik yang menyebabkan kesulitan bagi pasien tetap duduk untuk waktu yang lebih lama dari beberapa menit dalam setiap kesempatan";
        } if (p4_berat.isSelected()){
            gaduh_gelisah = "Berat : gaduh gelisah yang mencolok mendominasi anamnesis, membatasi perhatian, sedemikian rupa sehingga mempengaruhi fungsi sehari-hari, seperti makan dan tidur";
        } if (p4_sangat_berat.isSelected()){
            gaduh_gelisah = "Sangat Berat : gaduh gelisah yang mencolok secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelahan ";
        }
        
        if (p7_tidak_ditemukan.isSelected()){
            permusuhan = "Tidak Ditemukan : tidak ditemukan adanya permusuhan ";
        } if (p7_minimal.isSelected()){
            permusuhan = "Minimal : permusuhan minimal, keadaan patologis diragukan";
        } if (p7_ringan.isSelected()){
            permusuhan = "Ringan : melampiaskan kemarahan secara tak langsung atau ditahan, seperti sarkasme, sikap tidak sopan, ekspresi bermusuhan, dan kadang - kadang iritabilitas";
        } if (p7_sedang.isSelected()){
            permusuhan = "Sedang : adanya sikap bermusuhan yang nyata, sering memperlihatkan iritabilitas, dan ekspresi kemarahan atau kebencian yang langsung";
        } if (p7_agak_berat.isSelected()){
            permusuhan = "Agak berat : pasien sangat mudah marah dan kadang-kadang memaki dengan kata - kata kasar atau mengancam";
        } if (p7_berat.isSelected()){
            permusuhan = "Berat :  tidak kooperatif dan mencaci maki dengan kasar atau mengancam, khususnya dalam upaya mempengaruhi pemeriksa, dan berdampak serius terhadap hubungan sosial. Pasien dapat beringas dan merusak tapi tidak menyerang orang lain secara fisik";
        } if (p7_sangat_berat.isSelected()){
            permusuhan = "Sangat Berat : kemarahan hebat yang mengakibatkan subyek sangat tidak kooperatif, menghalangi interaksi, atau secara episodik melakukan penyerangan fisik terhadap orang lain";
        }
        
        if (g4_tidak_ditemukan.isSelected()){
            ketegangan = "Tidak Ditemukan : tidak ditemukan adanya ketegangan";
        } if (g4_minimal.isSelected()){
            ketegangan = "Minimal : ketegangan minimal, keadaan patologis diragukan";
        } if (g4_ringan.isSelected()){
            ketegangan = "Ringan : postur dan gerakan gerakan yang menunjukan kekhawatiran ringan, ketidaktenangan yang sesekali timbul, perubahan posisi, dan tremor tangan yang halus dan cepat";
        } if (g4_sedang.isSelected()){
            ketegangan = "Sedang : suatu penampilan yang nyata - nyata gelisah yang terbukti dari adanya berbagai manifestasi, seperti perilaku tidak tenang, tremor tangan yang nyata, keringat berlebihan, manerisme karena gugup";
        } if (g4_agak_berat.isSelected()){
            ketegangan = "Agak Berat : ketegangan berat yang dibuktikan oleh manifestasi, seperti gemetar karena gugup, keringat yang berlebihan, dan ketidaktenangan. Tetapi perilaku selama anamnesis tidak terpengaruh secara bermakna";
        } if (g4_berat.isSelected()){
            ketegangan = "Berat : gaduh gelisah yang mencolok, secara serius mempengaruhi kegiatan makan dan tidur, jelas tidak memungkinkan interaksi interpersonal, percepatan bicara, dan aktivitas motorik dapat menimbulkan inkoherensi dan kelelihan";
        } if (g4_sangat_berat.isSelected()){
            ketegangan = "Sangat Berat : ketegangan sangat mencolok yang dimanifestasikan sebagai tanda-tanda panik atau percepatan gerakan motorik kasar, seperti langkah cepat yang gelisah, ketidakmampuan tetap duduk tenang dalam waktu lebih dari satu menit, sehingga anamnesis tidak bisa dilanjutkan";
        }
        
        if (g8_tidak_ditemukan.isSelected()){
            tidak_kooperatif = "Tidak Ditemukan : tidak ditemukan adanya ketidak kooperatifan";
        } if (g8_minimal.isSelected()){
            tidak_kooperatif = "Minimal : tidak kooperatif minimal, keadaan patologis diragukan";
        } if (g8_ringan.isSelected()){
            tidak_kooperatif = "Ringan : patuh tapi disertai sikap marah,tidak sabar, atau sarkasme, mungkin ada penolakan yang tidak menganggu penyelidikan terhadap masalah – masalah sensitive selama anamnesis.";
        } if (g8_sedang.isSelected()){
            tidak_kooperatif = "Sedang : kadang menolak patuh pada tuntutan social normal, mis. Merapikan tempat tidur atau mengikuti kegiatan sesuai jadwal. Pasien dapat bersikap bermusuhan, defensive, atau bersifat negatif, tetapi biasanya masih dapat diatasi";
        } if (g8_agak_berat.isSelected()){
            tidak_kooperatif = "Agak Berat : sering tidak patuh terhadap tuntutan lingkungan dan mungkin serign disebut sebagai orang dengan masalah sikap yang serius. Ketidak kooperatifan tercermin jelas dalam sikap defensive atau iritabilitas terhadap pemeriksa dan mungkin menolak banyak ditanya";
        } if (g8_berat.isSelected()){
            tidak_kooperatif = "Berat : pasien sangat tidak kooperatif, negativistik, dan mungkin membangkang. Menolak untuk patuh terhadap sebagian besar tuntutan social dan mungkin tidak mau memulai atau mengikuti anamnesis sepenuhnya";
        } if (g8_sangat_berat.isSelected()){
            tidak_kooperatif = "Sangat Berat : resistensi aktif yang berdampak serius pada hamper seluruh fungsi. Pasien mungkin menolak berpartisipasi dalam aktivitas sosial apapun, mengurus kebersihan diri, bercakapcakap dengan keluarga, dan bahkan menolak anamnesis singkat";
        }
        
        if (g14_tidak_ditemukan.isSelected()){
            impuls = "Tidak ditemukan adanya pengendalian impuls yang buruk";
        } if (g14_minimal.isSelected()){
            impuls = "Minimal, patologis diragukan";
        } if (g14_ringan.isSelected()){
            impuls = "Ringan : pasien cenderung mudah marah dan frustasi bila menghadapi stress atau pemuasannya ditolak, tapi jarang bertindak impulsive";
        } if (g14_sedang.isSelected()){
            impuls = "Sedang : dengan provokasi minimal, pasien menjadi marah dan mencaci maki. Mungkin sesekali mengancam, merusak, atau terdapat satu-dua episode yang melibatkan konfrontasi fisik atau persilisihan ringan";
        } if (g14_agak_berat.isSelected()){
            impuls = "Agak Berat : pasien memperlihatkan episode impulsive yang berulang ulang termasuk mencaci maki, merusak harta benda, atau ancaman fisik. Mungkin ada satu atau dua episode yang melibatkan serangan serius hingga pasien perlu diisolasi, difiksasi, atau bila perlu diberikan sedasi";
        } if (g14_berat.isSelected()){
            impuls = "Berat : pasien sering menunjukkan agresivitas secara impulsive, mengancam, menuntut, dan merusak, tanpa mempertimbangkan konsekuensinya. Menunjukkan perilaku menyerang dan mungkin juga serangan seksual, atau berperilaku yang merupakan respon terhadap perintah yang bersifat halusinasi";
        } if (g14_sangat_berat.isSelected()){
            impuls = "Sangat Berat : pasien memperlihatkan serangan yang nyata mengancam keselamatan orang, penyerangan seksual, perilaku brutal yang berulang, atau perilaku menyakiti diri sendiri.";
        }
        
        if(Sequel.mengedittf("penilaian_ranap_panss_ec","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_petugas=?,gaduh_gelisah=?,skor_p4=?,permusuhan=?,skor_p7=?,ketegangan=?,skor_g4=?,tidak_kooperatif=?,skor_g8=?,pengendalian_impuls_buruk=?,skor_g14=?,total_skor=?,kesimpulan=?",17,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdPetugas.getText(),
                gaduh_gelisah, skor_p4.getText(), permusuhan, skor_p7.getText(), ketegangan, skor_g4.getText(), tidak_kooperatif, skor_g8.getText(), impuls, skor_g14.getText(), tskor.getText(), ruangan.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
