/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
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
public final class RMPenilaianTerapiAktivitasKelompok extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private String finger="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianTerapiAktivitasKelompok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Kemampuan","Skor TAK","Keterangan",
            "Total TAK",
            "Kesimpulan","NIP","Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 40; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(130);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setPreferredWidth(70);
            }else if(i==12){
                column.setPreferredWidth(130);
            }else if(i==13){
                column.setPreferredWidth(70);
            }else if(i==14){
                column.setPreferredWidth(80);
            }else if(i==15){
                column.setPreferredWidth(70);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(70);
            }else if(i==18){
                column.setPreferredWidth(160);
            }else if(i==19){
                column.setPreferredWidth(70);
            }else if(i==20){
                column.setPreferredWidth(80);
            }else if(i==21){
                column.setPreferredWidth(70);
            }else if(i==22){
                column.setPreferredWidth(80);
            }else if(i==23){
                column.setPreferredWidth(70);
            }else if(i==24){
                column.setPreferredWidth(130);
            }else if(i==25){
                column.setPreferredWidth(90);
            }else if(i==26){
                column.setPreferredWidth(80);
            }else if(i==27){
                column.setPreferredWidth(90);
            }else if(i==28){
                column.setPreferredWidth(80);
            }else if(i==29){
                column.setPreferredWidth(90);
            }else if(i==30){
                column.setPreferredWidth(130);
            }else if(i==31){
                column.setPreferredWidth(90);
            }else if(i==32){
                column.setPreferredWidth(80);
            }else if(i==33){
                column.setPreferredWidth(90);
            }else if(i==34){
                column.setPreferredWidth(80);
            }else if(i==35){
                column.setPreferredWidth(90);
            }else if(i==36){
                column.setPreferredWidth(130);
            }else if(i==37){
                column.setPreferredWidth(90);
            }else if(i==38){
                column.setPreferredWidth(80);
            }else if(i==39){
                column.setPreferredWidth(90);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
        a1.setDocument(new batasInput((byte)1).getKata(a1));
        b1.setDocument(new batasInput((byte)1).getKata(b1));
        c1.setDocument(new batasInput((byte)1).getKata(c1));
        d1.setDocument(new batasInput((byte)1).getKata(d1));
        e1.setDocument(new batasInput((byte)1).getKata(e1));
        f1.setDocument(new batasInput((byte)1).getKata(f1));
        g1.setDocument(new batasInput((byte)1).getKata(g1));
        h1.setDocument(new batasInput((byte)1).getKata(h1));
        i1.setDocument(new batasInput((byte)1).getKata(i1));
        j1.setDocument(new batasInput((byte)1).getKata(j1));
//        FaktorPencegahan.setDocument(new batasInput((int)500).getKata(FaktorPencegahan));
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
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
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
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianTambahanMelarikanDiri = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel57 = new widget.Label();
        jLabel217 = new widget.Label();
        a1 = new widget.TextBox();
        jLabel220 = new widget.Label();
        b1 = new widget.TextBox();
        jLabel223 = new widget.Label();
        c1 = new widget.TextBox();
        jLabel226 = new widget.Label();
        d1 = new widget.TextBox();
        jLabel229 = new widget.Label();
        e1 = new widget.TextBox();
        jLabel235 = new widget.Label();
        TingkatResiko1 = new widget.Label();
        jLabel230 = new widget.Label();
        jLabel232 = new widget.Label();
        jLabel233 = new widget.Label();
        jLabel234 = new widget.Label();
        jLabel236 = new widget.Label();
        jLabel58 = new widget.Label();
        f1 = new widget.TextBox();
        g1 = new widget.TextBox();
        h1 = new widget.TextBox();
        i1 = new widget.TextBox();
        j1 = new widget.TextBox();
        a2 = new widget.TextBox();
        b2 = new widget.TextBox();
        c2 = new widget.TextBox();
        d2 = new widget.TextBox();
        e2 = new widget.TextBox();
        f2 = new widget.TextBox();
        g2 = new widget.TextBox();
        h2 = new widget.TextBox();
        i2 = new widget.TextBox();
        j2 = new widget.TextBox();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        scrollPane2 = new widget.ScrollPane();
        Kesimpulan = new widget.TextArea();
        TingkatResiko2 = new widget.Label();
        TingkatResiko3 = new widget.Label();
        TingkatResiko4 = new widget.Label();
        TingkatResiko5 = new widget.Label();
        TingkatResiko6 = new widget.Label();
        Tak1 = new widget.TextBox();
        jLabel63 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianTambahanMelarikanDiri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianTambahanMelarikanDiri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianTambahanMelarikanDiri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setText("Formulir Penilaian Tambahan Berisiko Melarikan Diri");
        MnPenilaianTambahanMelarikanDiri.setName("MnPenilaianTambahanMelarikanDiri"); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setPreferredSize(new java.awt.Dimension(305, 26));
        MnPenilaianTambahanMelarikanDiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianTambahanMelarikanDiriActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianTambahanMelarikanDiri);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Terhadap Aktivitas Kelompok ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 456));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 760));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(178, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(243, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(308, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(373, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(474, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(570, 40, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(761, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Kemampuan Yang Dinilai");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(20, 70, 170, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1. Kemampuan berkomunikasi dengan topic bahasan.");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(20, 100, 260, 20);

        a1.setFocusTraversalPolicyProvider(true);
        a1.setName("a1"); // NOI18N
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });
        FormInput.add(a1);
        a1.setBounds(420, 100, 40, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Kemampuan menyampaikan ide.");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(20, 140, 180, 23);

        b1.setFocusTraversalPolicyProvider(true);
        b1.setName("b1"); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        FormInput.add(b1);
        b1.setBounds(420, 140, 40, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Kemampuan mengendalikan komunikasi.");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(20, 180, 220, 23);

        c1.setFocusTraversalPolicyProvider(true);
        c1.setName("c1"); // NOI18N
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });
        FormInput.add(c1);
        c1.setBounds(420, 180, 40, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Kemampuan berorientasi realitas.");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(20, 220, 180, 23);

        d1.setFocusTraversalPolicyProvider(true);
        d1.setName("d1"); // NOI18N
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });
        FormInput.add(d1);
        d1.setBounds(420, 220, 40, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("7. Kemampuan beraktivitas sesuai topic.");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(20, 340, 200, 23);

        e1.setFocusTraversalPolicyProvider(true);
        e1.setName("e1"); // NOI18N
        e1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e1ActionPerformed(evt);
            }
        });
        FormInput.add(e1);
        e1.setBounds(420, 260, 40, 23);

        jLabel235.setText("Jumlah :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(350, 500, 50, 23);

        TingkatResiko1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko1.setText("Kseimpulan dan rencana tindak lanjut :");
        TingkatResiko1.setToolTipText("");
        TingkatResiko1.setName("TingkatResiko1"); // NOI18N
        FormInput.add(TingkatResiko1);
        TingkatResiko1.setBounds(20, 530, 190, 23);

        jLabel230.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel230.setText("5. Kemampuan memberikan instruksi.");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(20, 260, 180, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("6. Kemampuan memberikan respon.");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(20, 300, 180, 23);

        jLabel233.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel233.setText("8. Kemampuan mengikuti aturan / instruksi.");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(20, 380, 210, 23);

        jLabel234.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel234.setText("9. Kemampuan bekerjasama secara team.");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(20, 420, 210, 23);

        jLabel236.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel236.setText("10. Mendemonstrasikan prilaku adaptif sesuai dengan masalah yang dihadapi. ");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(20, 460, 380, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Keterangan Tambahan");
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(550, 70, 140, 23);

        f1.setFocusTraversalPolicyProvider(true);
        f1.setName("f1"); // NOI18N
        f1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                f1CaretUpdate(evt);
            }
        });
        f1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f1ActionPerformed(evt);
            }
        });
        FormInput.add(f1);
        f1.setBounds(420, 300, 40, 23);

        g1.setFocusTraversalPolicyProvider(true);
        g1.setName("g1"); // NOI18N
        g1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                g1CaretUpdate(evt);
            }
        });
        g1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1ActionPerformed(evt);
            }
        });
        FormInput.add(g1);
        g1.setBounds(420, 340, 40, 23);

        h1.setFocusTraversalPolicyProvider(true);
        h1.setName("h1"); // NOI18N
        h1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                h1CaretUpdate(evt);
            }
        });
        h1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h1ActionPerformed(evt);
            }
        });
        FormInput.add(h1);
        h1.setBounds(420, 380, 40, 23);

        i1.setFocusTraversalPolicyProvider(true);
        i1.setName("i1"); // NOI18N
        i1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                i1CaretUpdate(evt);
            }
        });
        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i1ActionPerformed(evt);
            }
        });
        FormInput.add(i1);
        i1.setBounds(420, 420, 40, 23);

        j1.setFocusTraversalPolicyProvider(true);
        j1.setName("j1"); // NOI18N
        j1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                j1CaretUpdate(evt);
            }
        });
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });
        FormInput.add(j1);
        j1.setBounds(420, 460, 40, 23);

        a2.setFocusTraversalPolicyProvider(true);
        a2.setName("a2"); // NOI18N
        a2.setPreferredSize(new java.awt.Dimension(75, 24));
        a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a2ActionPerformed(evt);
            }
        });
        FormInput.add(a2);
        a2.setBounds(490, 100, 240, 23);

        b2.setFocusTraversalPolicyProvider(true);
        b2.setName("b2"); // NOI18N
        b2.setPreferredSize(new java.awt.Dimension(75, 24));
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        FormInput.add(b2);
        b2.setBounds(490, 140, 240, 23);

        c2.setFocusTraversalPolicyProvider(true);
        c2.setName("c2"); // NOI18N
        c2.setPreferredSize(new java.awt.Dimension(75, 24));
        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });
        FormInput.add(c2);
        c2.setBounds(490, 180, 240, 23);

        d2.setFocusTraversalPolicyProvider(true);
        d2.setName("d2"); // NOI18N
        d2.setPreferredSize(new java.awt.Dimension(75, 24));
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2ActionPerformed(evt);
            }
        });
        FormInput.add(d2);
        d2.setBounds(490, 220, 240, 23);

        e2.setFocusTraversalPolicyProvider(true);
        e2.setName("e2"); // NOI18N
        e2.setPreferredSize(new java.awt.Dimension(75, 24));
        e2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e2ActionPerformed(evt);
            }
        });
        FormInput.add(e2);
        e2.setBounds(490, 260, 240, 23);

        f2.setFocusTraversalPolicyProvider(true);
        f2.setName("f2"); // NOI18N
        f2.setPreferredSize(new java.awt.Dimension(75, 24));
        f2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                f2CaretUpdate(evt);
            }
        });
        f2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f2ActionPerformed(evt);
            }
        });
        FormInput.add(f2);
        f2.setBounds(490, 300, 240, 23);

        g2.setFocusTraversalPolicyProvider(true);
        g2.setName("g2"); // NOI18N
        g2.setPreferredSize(new java.awt.Dimension(75, 24));
        g2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                g2CaretUpdate(evt);
            }
        });
        g2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g2ActionPerformed(evt);
            }
        });
        FormInput.add(g2);
        g2.setBounds(490, 340, 240, 23);

        h2.setFocusTraversalPolicyProvider(true);
        h2.setName("h2"); // NOI18N
        h2.setPreferredSize(new java.awt.Dimension(75, 24));
        h2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                h2CaretUpdate(evt);
            }
        });
        h2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h2ActionPerformed(evt);
            }
        });
        FormInput.add(h2);
        h2.setBounds(490, 380, 240, 23);

        i2.setFocusTraversalPolicyProvider(true);
        i2.setName("i2"); // NOI18N
        i2.setPreferredSize(new java.awt.Dimension(75, 24));
        i2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                i2CaretUpdate(evt);
            }
        });
        i2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i2ActionPerformed(evt);
            }
        });
        FormInput.add(i2);
        i2.setBounds(490, 420, 240, 23);

        j2.setFocusTraversalPolicyProvider(true);
        j2.setName("j2"); // NOI18N
        j2.setPreferredSize(new java.awt.Dimension(75, 24));
        j2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                j2CaretUpdate(evt);
            }
        });
        j2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2ActionPerformed(evt);
            }
        });
        FormInput.add(j2);
        j2.setBounds(490, 460, 240, 23);

        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(10, 90, 750, 30);

        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(10, 130, 750, 30);

        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(10, 130, 750, 30);

        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(10, 170, 750, 30);

        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(10, 170, 750, 30);

        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(10, 210, 750, 30);

        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(10, 250, 750, 30);

        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(10, 290, 750, 30);

        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(10, 330, 750, 30);

        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(10, 370, 750, 30);

        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(10, 410, 750, 30);

        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(10, 450, 750, 30);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Kesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Kesimpulan.setColumns(20);
        Kesimpulan.setRows(5);
        Kesimpulan.setName("Kesimpulan"); // NOI18N
        Kesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Kesimpulan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(20, 550, 740, 90);

        TingkatResiko2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko2.setText("1 : Pasien menunjukan kemampuan minimal");
        TingkatResiko2.setToolTipText("");
        TingkatResiko2.setName("TingkatResiko2"); // NOI18N
        FormInput.add(TingkatResiko2);
        TingkatResiko2.setBounds(20, 690, 210, 23);

        TingkatResiko3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko3.setText("Petunjuk Penilaian :");
        TingkatResiko3.setToolTipText("");
        TingkatResiko3.setName("TingkatResiko3"); // NOI18N
        FormInput.add(TingkatResiko3);
        TingkatResiko3.setBounds(10, 650, 100, 23);

        TingkatResiko4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko4.setText("2 : Pasien menunjukan kemampuan sedang");
        TingkatResiko4.setToolTipText("");
        TingkatResiko4.setName("TingkatResiko4"); // NOI18N
        FormInput.add(TingkatResiko4);
        TingkatResiko4.setBounds(20, 710, 210, 23);

        TingkatResiko5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko5.setText("3 : Pasien menunjukan kemampuan maksimal");
        TingkatResiko5.setToolTipText("");
        TingkatResiko5.setName("TingkatResiko5"); // NOI18N
        FormInput.add(TingkatResiko5);
        TingkatResiko5.setBounds(20, 730, 220, 23);

        TingkatResiko6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko6.setText("0 : Pasien sama sekali tidak mampu menunjukan kemampuan");
        TingkatResiko6.setToolTipText("");
        TingkatResiko6.setName("TingkatResiko6"); // NOI18N
        FormInput.add(TingkatResiko6);
        TingkatResiko6.setBounds(20, 670, 300, 23);

        Tak1.setFocusTraversalPolicyProvider(true);
        Tak1.setName("Tak1"); // NOI18N
        Tak1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Tak1CaretUpdate(evt);
            }
        });
        Tak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tak1ActionPerformed(evt);
            }
        });
        FormInput.add(Tak1);
        Tak1.setBounds(420, 500, 40, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Skor TAK");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(410, 70, 60, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(Sequel.menyimpantf("penilaian_terapi_aktivitas_kelompok","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",25,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                a1.getText(),a2.getText(),
                b1.getText(),b2.getText(),
                c1.getText(),c2.getText(),
                d1.getText(),d2.getText(),
                e1.getText(),e2.getText(),
                f1.getText(),f2.getText(),
                g1.getText(),g2.getText(),
                h1.getText(),h2.getText(),
                i1.getText(),i2.getText(),
                j1.getText(),j2.getText(),Tak1.getText(),Kesimpulan.getText()
            })==true){
               tampil();
                emptTeks();
                LCount.setText(""+tabMode.getRowCount());
            }  
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,FaktorPencegahan,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
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
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
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
        petugas.dispose();
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
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
//                            +style=\"color:#00FFE3;\"
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>JK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle'  bgcolor='#FFFAF8' align='center'><b>Faktor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Statik</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Dinamis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor-faktor Pencegahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Level Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Petugas</b></td>"+
                    "</tr>"
                );
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,36).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,37).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,38).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,39).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,40).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,41).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,42).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,43).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,44).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,45).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,46).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='4400px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("DataPenilaianTambahanBerisikoMelarikanDiri.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='4400px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN TAMBAHAN BERISIKO MELARIKAN DIRI<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());
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
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void MnPenilaianTambahanMelarikanDiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianTambahanMelarikanDiriActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),46).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),45).toString():finger)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptPenilaianTerapiAktivitasKelompok1.jasper","report","::[ Formulir Penilaian Tambahan Berisiko Melarikan Diri ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,\n" +
"                    no_rawat,\n" +
"  tanggal,\n" +
"  nip,\n" +
"  kemampuan_berkomunikasi1,\n" +
"  kemampuan_berkomunikasi2,\n" +
"  kemampuan_berkomunikasi3,\n" +
"  kemampuan_berkomunikasi4,\n" +
"  kemampuan_berkomunikasi5,\n" +
"  kemampuan_menyampaikan_ide1,\n" +
"  kemampuan_menyampaikan_ide2,\n" +
"  kemampuan_menyampaikan_ide3,\n" +
"  kemampuan_menyampaikan_ide4,\n" +
"  kemampuan_menyampaikan_ide5,\n" +
"  kemampuan_mengendalikan_komunikasi1,\n" +
"  kemampuan_mengendalikan_komunikasi2,\n" +
"  kemampuan_mengendalikan_komunikasi3,\n" +
"  kemampuan_mengendalikan_komunikasi4,\n" +
"  kemampuan_mengendalikan_komunikasi5,\n" +
"  kemampuan_berorientasi_realita1,\n" +
"  kemampuan_berorientasi_realita2,\n" +
"  kemampuan_berorientasi_realita3,\n" +
"  kemampuan_berorientasi_realita4,\n" +
"  kemampuan_berorientasi_realita5,\n" +
"  kemampuan_memberikan_instruksi1,\n" +
"  kemampuan_memberikan_instruksi2,\n" +
"  kemampuan_memberikan_instruksi3,\n" +
"  kemampuan_memberikan_instruksi4,\n" +
"  kemampuan_memberikan_instruksi5,\n" +
"  kemampuan_memberikan_respon1,\n" +
"  kemampuan_memberikan_respon2,\n" +
"  kemampuan_memberikan_respon3,\n" +
"  kemampuan_memberikan_respon4,\n" +
"  kemampuan_memberikan_respon5,\n" +
"  beraktivitas_sesuai_topic1,\n" +
"  beraktivitas_sesuai_topic2,\n" +
"  beraktivitas_sesuai_topic3,\n" +
"  beraktivitas_sesuai_topic4,\n" +
"  beraktivitas_sesuai_topic5,\n" +
"  mengikuti_instruksi1,\n" +
"  mengikuti_instruksi2,\n" +
"  mengikuti_instruksi3,\n" +
"  mengikuti_instruksi4,\n" +
"  mengikuti_instruksi5,\n" +
"  kerjasama_team1,\n" +
"  kerjasama_team2,\n" +
"  kerjasama_team3,\n" +
"  kerjasama_team4,\n" +
"  kerjasama_team5,\n" +
"  demonstrasi_prilaku1,\n" +
"  demonstrasi_prilaku2,\n" +
"  demonstrasi_prilaku3,\n" +
"  demonstrasi_prilaku4,\n" +
"  demonstrasi_prilaku5,\n" +
"  tak1,\n" +
"  tak2,\n" +
"  tak3,\n" +
"  tak4,\n" +
"  tak5,\n" +
"  kesimpulan,\n" +
"                            petugas.nama                     \n" +
"									 from penilaian_terapi_aktivitas_kelompok inner join reg_periksa on no_rawat=reg_periksa.no_rawat \n" +
"                    inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis \n" +
"                    inner join petugas on nip=petugas.nip "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianTambahanMelarikanDiriActionPerformed

    private void Tak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tak1ActionPerformed

    }//GEN-LAST:event_Tak1ActionPerformed

    private void Tak1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Tak1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_Tak1CaretUpdate

    private void KesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesimpulanKeyPressed
        //        Valid.pindah2(evt,KeluhanUtama,RiwayatPenyakitDahulu);
    }//GEN-LAST:event_KesimpulanKeyPressed

    private void j2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_j2ActionPerformed

    private void j2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_j2CaretUpdate
        isTotalSkor();
    }//GEN-LAST:event_j2CaretUpdate

    private void i2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_i2ActionPerformed

    private void i2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_i2CaretUpdate

    }//GEN-LAST:event_i2CaretUpdate

    private void h2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_h2ActionPerformed

    private void h2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_h2CaretUpdate

    }//GEN-LAST:event_h2CaretUpdate

    private void g2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_g2ActionPerformed

    private void g2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_g2CaretUpdate

    }//GEN-LAST:event_g2CaretUpdate

    private void f2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_f2ActionPerformed

    private void f2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_f2CaretUpdate

    }//GEN-LAST:event_f2CaretUpdate

    private void e2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_e2ActionPerformed

    private void d2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_d2ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_c2ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_b2ActionPerformed

    private void a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a2ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_a2ActionPerformed

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_j1ActionPerformed

    private void j1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_j1CaretUpdate
        isTotalSkor();
    }//GEN-LAST:event_j1CaretUpdate

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_i1ActionPerformed

    private void i1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_i1CaretUpdate

    }//GEN-LAST:event_i1CaretUpdate

    private void h1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_h1ActionPerformed

    private void h1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_h1CaretUpdate

    }//GEN-LAST:event_h1CaretUpdate

    private void g1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_g1ActionPerformed

    private void g1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_g1CaretUpdate

    }//GEN-LAST:event_g1CaretUpdate

    private void f1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_f1ActionPerformed

    private void f1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_f1CaretUpdate

    }//GEN-LAST:event_f1CaretUpdate

    private void e1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_e1ActionPerformed

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_d1ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_c1ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_b1ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        isTotalSkor();
    }//GEN-LAST:event_a1ActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        //        Valid.pindah(evt,Detik,FaktorStatik1);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
    }//GEN-LAST:event_TNoRMKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
    }//GEN-LAST:event_TanggalKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
    }//GEN-LAST:event_TPasienKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{
            Valid.pindah(evt,TCari,Tanggal);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

//    public void tak1(){
//    int q=Integer.parseInt(a1.getText());
////    (Integer.parseInt(textBox1.getText())
//int y=Integer.parseInt(b1.getText());
//int a=Integer.parseInt(c1.getText());
//int h=Integer.parseInt(d1.getText());
//int x=Integer.parseInt(e1.getText());
//int m=Integer.parseInt(f1.getText());
//int tt=Integer.parseInt(g1.getText());
//int pp=Integer.parseInt(h1.getText());
//int gg=Integer.parseInt(i1.getText());
//int zz=Integer.parseInt(j1.getText());
//
//int total1 = q + y + a + h + x + m + tt + pp + gg + zz;
//String hasil1=Integer.toString(total1);
//    try {
//        Tak1.setText(hasil1);
//        } catch (Exception e) {
//            Tak1.setText(Integer.toString(0));
//        }
//    }
//    public void tak2(){
//       int w=Integer.valueOf(a2.getText());
//int u=Integer.valueOf(b2.getText());
//int s=Integer.valueOf(c2.getText());
//int j=Integer.valueOf(d2.getText());
//int c=Integer.valueOf(e2.getText());
//int qq=Integer.valueOf(f2.getText());
//int yy=Integer.valueOf(g2.getText());
//int aa=Integer.valueOf(h2.getText());
//int hh=Integer.valueOf(i2.getText());
//int xx=Integer.valueOf(j2.getText());
//
//int total2 = w + u + s + j + c + qq + yy + aa + hh + xx;
//String hasil2=Integer.toString(total2);
//    try {
//        Tak2.setText(hasil2);
//        } catch (Exception e) {
//            Tak2.setText(Integer.toString(0));
//        }
//    }
//    public void tak3(){
//        int eq=Integer.valueOf(a3.getText());
//int i=Integer.valueOf(b3.getText());
//int d=Integer.valueOf(c3.getText());
//int k=Integer.valueOf(d3.getText());
//int v=Integer.valueOf(e3.getText());
//int ww=Integer.valueOf(f3.getText());
//int uu=Integer.valueOf(g3.getText());
//int ss=Integer.valueOf(h3.getText());
//int jj=Integer.valueOf(i3.getText());
//int cc=Integer.valueOf(j3.getText());
//
//int total3=eq+i+d+k+v+ww+uu+ss+jj+cc;
//String hasil3=Integer.toString(total3);
//    try {
//        Tak3.setText(hasil3);
//        } catch (Exception e) {
//            Tak3.setText(Integer.toString(0));
//        }
//    }
//    public void tak4(){
//     int r=Integer.valueOf(a4.getText());
//int o=Integer.valueOf(b4.getText());
//int f=Integer.valueOf(c4.getText());
//int l=Integer.valueOf(d4.getText());
//int b=Integer.valueOf(e4.getText());
//int ee=Integer.valueOf(f4.getText());
//int ii=Integer.valueOf(g4.getText());
//int dd=Integer.valueOf(h4.getText());
//int kk=Integer.valueOf(i4.getText());
//int vv=Integer.valueOf(j4.getText());
//
//int total4=r+o+f+l+b+ee+ii+dd+kk+vv;
//String hasil4=Integer.toString(total4);
//        
//    try {
//        Tak4.setText(hasil4);
//        } catch (Exception e) {
//            Tak4.setText(Integer.toString(0));
//        }
//    }
//    public void tak5(){
//       int t=Integer.valueOf(a5.getText());
//int p=Integer.valueOf(b5.getText());
//int g=Integer.valueOf(c5.getText());
//int z=Integer.valueOf(d5.getText());
//int n=Integer.valueOf(e5.getText());
//int rr=Integer.valueOf(f5.getText());
//int oo=Integer.valueOf(g5.getText());
//int ff=Integer.valueOf(h5.getText());
//int ll=Integer.valueOf(i5.getText());
//int bb=Integer.valueOf(j5.getText());
//
//int total5 = t + p + g + z + n + rr + oo + ff + ll + bb;
//String hasil5=Integer.toString(total5);
//       
//    try {
//        Tak5.setText(hasil5);
//        } catch (Exception e) {
//            Tak5.setText(Integer.toString(0));
//        }
//    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianTerapiAktivitasKelompok dialog = new RMPenilaianTerapiAktivitasKelompok(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.TextArea Kesimpulan;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnPenilaianTambahanMelarikanDiri;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tak1;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.Label TingkatResiko1;
    private widget.Label TingkatResiko2;
    private widget.Label TingkatResiko3;
    private widget.Label TingkatResiko4;
    private widget.Label TingkatResiko5;
    private widget.Label TingkatResiko6;
    private widget.TextBox a1;
    private widget.TextBox a2;
    private widget.TextBox b1;
    private widget.TextBox b2;
    private widget.Button btnPetugas;
    private widget.TextBox c1;
    private widget.TextBox c2;
    private widget.TextBox d1;
    private widget.TextBox d2;
    private widget.TextBox e1;
    private widget.TextBox e2;
    private widget.TextBox f1;
    private widget.TextBox f2;
    private widget.TextBox g1;
    private widget.TextBox g2;
    private widget.TextBox h1;
    private widget.TextBox h2;
    private widget.TextBox i1;
    private widget.TextBox i2;
    private widget.InternalFrame internalFrame1;
    private widget.TextBox j1;
    private widget.TextBox j2;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel217;
    private widget.Label jLabel220;
    private widget.Label jLabel223;
    private widget.Label jLabel226;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel4;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_terapi_aktivitas_kelompok.no_rawat,\n" +
                    "penilaian_terapi_aktivitas_kelompok.tanggal,\n" +
                    "penilaian_terapi_aktivitas_kelompok.nip,\n" +
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_berkomunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_menyampaikan_ide,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_mengendalikan_komunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_berorientasi_realita,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_memberikan_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_memberikan_respon,"+
                    "penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_beraktivitas_sesuai_topic,"+
                    "penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_mengikuti_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.kerjasama_team,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kerjasama_team,"+
                    "penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_demonstrasi_prilaku,"+
                    "penilaian_terapi_aktivitas_kelompok.jumlah_total,"+
                    "penilaian_terapi_aktivitas_kelompok.kesimpulan,"+
                    "petugas.nama "+
                    "from penilaian_terapi_aktivitas_kelompok inner join reg_periksa on penilaian_terapi_aktivitas_kelompok.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_terapi_aktivitas_kelompok.nip=petugas.nip where "+
                    "tanggal between ? and ? order by tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_terapi_aktivitas_kelompok.no_rawat,\n" +
                    "penilaian_terapi_aktivitas_kelompok.tanggal,\n" +
                    "penilaian_terapi_aktivitas_kelompok.nip,\n" +
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_berkomunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_menyampaikan_ide,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_mengendalikan_komunikasi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_berorientasi_realita,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_memberikan_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kemampuan_memberikan_respon,"+
                    "penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_beraktivitas_sesuai_topic,"+
                    "penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_mengikuti_instruksi,"+
                    "penilaian_terapi_aktivitas_kelompok.kerjasama_team,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_kerjasama_team,"+
                    "penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku,"+
                    "penilaian_terapi_aktivitas_kelompok.ket_demonstrasi_prilaku,"+
                    "penilaian_terapi_aktivitas_kelompok.jumlah_total,"+
                    "penilaian_terapi_aktivitas_kelompok.kesimpulan,"+
                    "petugas.nama "+
                    "from penilaian_terapi_aktivitas_kelompok inner join reg_periksa on penilaian_terapi_aktivitas_kelompok.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_terapi_aktivitas_kelompok.nip=petugas.nip where "+
                    "tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_terapi_aktivitas_kelompok.nip like ? or petugas.nama like ?) "+
                    "order by tanggal ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        
                        "Komunikasi sesuai Topik :",rs.getString("kemampuan_berkomunikasi"),rs.getString("ket_kemampuan_berkomunikasi"),
                        "Menyampaikan ide :",rs.getString("kemampuan_menyampaikan_ide"),rs.getString("ket_kemampuan_menyampaikan_ide"),
                        "Mengendalikan Komunikasi :",rs.getString("kemampuan_mengendalikan_komunikasi"),rs.getString("ket_kemampuan_mengendalikan_komunikasi"),
                        "Berorientasi realitas :",rs.getString("kemampuan_berorientasi_realita"),rs.getString("ket_kemampuan_berorientasi_realita"),
                        "Memberikan instruksi :",rs.getString("kemampuan_memberikan_instruksi"),rs.getString("ket_kemampuan_memberikan_instruksi"),
                        "Memberikan respon :",rs.getString("kemampuan_memberikan_respon"),rs.getString("ket_kemampuan_memberikan_respon"),
                        "Beraktivitas sesuai topic :",rs.getString("beraktivitas_sesuai_topic"),rs.getString("ket_beraktivitas_sesuai_topic"),
                        "Mengikuti aturan/instruksi :",rs.getString("mengikuti_instruksi"),rs.getString("ket_mengikuti_instruksi"),
                        "Bekerjasama secara team :",rs.getString("kerjasama_team"),rs.getString("ket_kerjasama_team"),
                        " Mendemonstrasikan prilaku adaptif sesuai dengan masalah yang dihadapi:",rs.getString("demonstrasi_prilaku"),rs.getString("ket_demonstrasi_prilaku"),
                        rs.getString("jumlah_total"),rs.getString("kesimpulan"),rs.getString("nip"),rs.getString("nama"),
                        
//                        
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
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        Tanggal.setDate(new Date());
//        TNoRw.setText(""); 
//            TNoRM.setText("");
//            TPasien.setText("");
//            TglLahir.setText("");
//            JK.setText("");
            
            a1.setText("");
            a2.setText("");
            
            
            b1.setText("");
            b2.setText("");
            
            
            c1.setText("");
            c2.setText("");
            
            
            d1.setText("");
            d2.setText("");
            
            
            e1.setText("");
            e2.setText("");
            
           
            f1.setText("");
            f2.setText("");
            
            
            g1.setText("");
            g2.setText("");
            
            
            h1.setText("");
            h2.setText("");
            
            
            i1.setText("");
            i2.setText("");
            
            
            j1.setText("");
            j2.setText("");
            
            
            Kesimpulan.setText("");
//            NIP.setText("");
//            NamaPetugas.setText("");
            Tak1.setText("");
            
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            
            
            a1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            a2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            
            
            b1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            b2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            
            
            c1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            c2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            
            
            d1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            d2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            
            
            e1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            e2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
           
           
            f1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            f2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            
            
            g1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            g2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            
            
            h1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            h2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            
            
            i1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            i2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
          
            
            j1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            j2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
           
            Tak1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            
            
            Kesimpulan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            
            
            
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,16));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        try {
            ps=koneksi.prepareStatement("select pasien.nm_pasien,pasien.jk,pasien.tgl_lahir as lahir from pasien where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }finally {
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
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>628){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,456));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-172));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_aktivitas_kelompok());
        BtnHapus.setEnabled(akses.getpenilaian_aktivitas_kelompok());
        BtnEdit.setEnabled(akses.getpenilaian_aktivitas_kelompok());
        BtnPrint.setEnabled(akses.getpenilaian_aktivitas_kelompok()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if(Sequel.mengedittf("penilaian_terapi_aktivitas_kelompok","no_rawat=?",
                "no_rawat=?,tanggal=?,nip=?,"+
                        "kemampuan_berkomunikasi=?,"+
  "ket_kemampuan_berkomunikasi=?,"+
  "kemampuan_menyampaikan_ide=?,"+
  "ket_kemampuan_menyampaikan_ide=?,"+
  "kemampuan_mengendalikan_komunikasi=?,"+
  "ket_kemampuan_mengendalikan_komunikasi=?,"+
  "kemampuan_berorientasi_realita=?,"+
  "ket_kemampuan_berorientasi_realita=?,"+
  "kemampuan_memberikan_instruksi=?,"+
  "ket_kemampuan_memberikan_instruksi=?,"+
  "kemampuan_memberikan_respon=?,"+
  "ket_kemampuan_memberikan_respon=?,"+
  "beraktivitas_sesuai_topic=?,"+
  "ket_beraktivitas_sesuai_topic=?,"+
  "mengikuti_instruksi=?,"+
  "ket_mengikuti_instruksi=?,"+
  "kerjasama_team=?,"+
  "ket_kerjasama_team=?,"+
  "demonstrasi_prilaku=?,"+
  "ket_demonstrasi_prilaku=?,"+
  "jumlah_total=?,"+
  "kesimpulan=?"
                ,26,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                a1.getText(),a2.getText(),
                b1.getText(),b2.getText(),
                c1.getText(),c2.getText(),
                d1.getText(),d2.getText(),
                e1.getText(),e2.getText(),
                f1.getText(),f2.getText(),
                g1.getText(),g2.getText(),
                h1.getText(),h2.getText(),
                i1.getText(),i2.getText(),
                j1.getText(),j2.getText(),Tak1.getText(),Kesimpulan.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
            })==true){
            tampil();
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_terapi_aktivitas_kelompok where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private void isTotalSkor(){
        try {
            Tak1.setText((Integer.parseInt(a1.getText())+Integer.parseInt(b1.getText())+Integer.parseInt(c1.getText())+Integer.parseInt(d1.getText())+Integer.parseInt(e1.getText())+Integer.parseInt(f1.getText())+Integer.parseInt(g1.getText())+Integer.parseInt(h1.getText())+Integer.parseInt(i1.getText())+Integer.parseInt(j1.getText()))+"");
            
//            }
        } catch (Exception e) {
            Tak1.setText("0");
            
//            Level.setText("Rendah(<7)");
        }
    }
}
