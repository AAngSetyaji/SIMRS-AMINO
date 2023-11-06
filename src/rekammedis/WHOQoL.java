/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rekammedis;

import fungsi.koneksiDB;
import fungsi.validasi;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.ResultSet;
import fungsi.sekuel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import jxl.Range;
/**
 *
 * @author firka
 */
public class WHOQoL extends javax.swing.JDialog {
public int Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q22,Q23,Q24,Q25,Q26,
        totdom1,totdom2,totdom3,totdom4,totsemua,i,ii,grandtotdom1,grandtotdom2,grandtotdom3,grandtotdom4;
private DefaultTableModel tabMode;
//private int a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,
//        a24,a25,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35;
private ResultSet rs;
private validasi Valid = new validasi();
private sekuel sequel = new sekuel();
private Connection koneksi = koneksiDB.condb();
private PreparedStatement ps;
private String NULL;
private String update_data = "update penilaianwho set tanggal=?, Q1=?, Q2=?, Q3=?, Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,\n" +
            "Q20,Q21,Q22,Q23,Q24,Q25,Q26,totdom1,totdom2,totdom3,totdom4,hsltot ";
private String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    /**
     * Creates new form WHOQoL
     */
    public WHOQoL(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ii=0;
        tabMode=new DefaultTableModel(null,new String[]{
            "No Rawat","Tanggal","Q1","Q2","Q3","Q4","Q5","Q6","Q7","Q8","Q9","Q10",
            "Q11","Q12","Q13","Q14","Q15","Q16","Q17","Q18","Q19","Q20","Q21","Q22",
            "Q23","Q24","Q25","Q26","Dom I","Dom II","Dom III","Dom IV","Grand Total"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbList.setModel(tabMode);
        tbList.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 33; i++) {
            TableColumn column = tbList.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(160);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(65);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(65);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(65);
            }else if(i==9){
                column.setPreferredWidth(65);
            }else if(i==10){
                column.setPreferredWidth(65);
            }else if(i==11){
                column.setPreferredWidth(65);
            }else if(i==12){
                column.setPreferredWidth(65);
            }else if(i==13){
                column.setPreferredWidth(65);
            }else if(i==14){
                column.setPreferredWidth(65);
            }else if(i==15){
                column.setPreferredWidth(65);
            }else if(i==16){
                column.setPreferredWidth(65);
            }else if(i==17){
                column.setPreferredWidth(65);
            }else if(i==18){
                column.setPreferredWidth(65);
            }else if(i==19){
                column.setPreferredWidth(65);
            }else if(i==20){
                column.setPreferredWidth(65);
            }else if(i==21){
                column.setPreferredWidth(65);
            }else if(i==22){
                column.setPreferredWidth(65);
            }else if(i==23){
                column.setPreferredWidth(65);
            }else if(i==24){
                column.setPreferredWidth(65);
            }else if(i==25){
                column.setPreferredWidth(65);
            }else if(i==26){
                column.setPreferredWidth(65);
            }else if(i==27){
                column.setPreferredWidth(65);
            }else if(i==28){
                column.setPreferredWidth(100);
            }else if(i==29){
                column.setPreferredWidth(100);
            }else if(i==30){
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(100);
            }else if(i==32){
                column.setPreferredWidth(100);
            }
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

        bg1 = new javax.swing.ButtonGroup();
        bg2 = new javax.swing.ButtonGroup();
        bg3 = new javax.swing.ButtonGroup();
        bg4 = new javax.swing.ButtonGroup();
        bg5 = new javax.swing.ButtonGroup();
        bg6 = new javax.swing.ButtonGroup();
        bg7 = new javax.swing.ButtonGroup();
        bg8 = new javax.swing.ButtonGroup();
        bg9 = new javax.swing.ButtonGroup();
        bg10 = new javax.swing.ButtonGroup();
        bg11 = new javax.swing.ButtonGroup();
        bg12 = new javax.swing.ButtonGroup();
        bg13 = new javax.swing.ButtonGroup();
        bg14 = new javax.swing.ButtonGroup();
        bg15 = new javax.swing.ButtonGroup();
        bg16 = new javax.swing.ButtonGroup();
        bg17 = new javax.swing.ButtonGroup();
        bg18 = new javax.swing.ButtonGroup();
        bg19 = new javax.swing.ButtonGroup();
        bg20 = new javax.swing.ButtonGroup();
        bg21 = new javax.swing.ButtonGroup();
        bg22 = new javax.swing.ButtonGroup();
        bg23 = new javax.swing.ButtonGroup();
        bg24 = new javax.swing.ButtonGroup();
        bg25 = new javax.swing.ButtonGroup();
        bg26 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        jPanel6 = new javax.swing.JPanel();
        label133 = new widget.Label();
        C15 = new widget.RadioButton();
        C11 = new widget.RadioButton();
        C12 = new widget.RadioButton();
        C13 = new widget.RadioButton();
        C14 = new widget.RadioButton();
        label134 = new widget.Label();
        label135 = new widget.Label();
        label136 = new widget.Label();
        label137 = new widget.Label();
        label138 = new widget.Label();
        label139 = new widget.Label();
        label140 = new widget.Label();
        label141 = new widget.Label();
        label142 = new widget.Label();
        label143 = new widget.Label();
        label144 = new widget.Label();
        label145 = new widget.Label();
        label146 = new widget.Label();
        label147 = new widget.Label();
        label148 = new widget.Label();
        label149 = new widget.Label();
        label150 = new widget.Label();
        label151 = new widget.Label();
        label152 = new widget.Label();
        label153 = new widget.Label();
        label154 = new widget.Label();
        label155 = new widget.Label();
        label156 = new widget.Label();
        C31 = new widget.RadioButton();
        C32 = new widget.RadioButton();
        C33 = new widget.RadioButton();
        C34 = new widget.RadioButton();
        C35 = new widget.RadioButton();
        label157 = new widget.Label();
        C151 = new widget.RadioButton();
        C152 = new widget.RadioButton();
        C153 = new widget.RadioButton();
        C154 = new widget.RadioButton();
        C155 = new widget.RadioButton();
        C161 = new widget.RadioButton();
        C162 = new widget.RadioButton();
        C163 = new widget.RadioButton();
        C164 = new widget.RadioButton();
        C165 = new widget.RadioButton();
        label158 = new widget.Label();
        C171 = new widget.RadioButton();
        C172 = new widget.RadioButton();
        C173 = new widget.RadioButton();
        C174 = new widget.RadioButton();
        C175 = new widget.RadioButton();
        C183 = new widget.RadioButton();
        C181 = new widget.RadioButton();
        C184 = new widget.RadioButton();
        C185 = new widget.RadioButton();
        C182 = new widget.RadioButton();
        C192 = new widget.RadioButton();
        C193 = new widget.RadioButton();
        C194 = new widget.RadioButton();
        C195 = new widget.RadioButton();
        C191 = new widget.RadioButton();
        C204 = new widget.RadioButton();
        C203 = new widget.RadioButton();
        C205 = new widget.RadioButton();
        C202 = new widget.RadioButton();
        C201 = new widget.RadioButton();
        C211 = new widget.RadioButton();
        C212 = new widget.RadioButton();
        C213 = new widget.RadioButton();
        C214 = new widget.RadioButton();
        C215 = new widget.RadioButton();
        C221 = new widget.RadioButton();
        C225 = new widget.RadioButton();
        C222 = new widget.RadioButton();
        C224 = new widget.RadioButton();
        C223 = new widget.RadioButton();
        C231 = new widget.RadioButton();
        C232 = new widget.RadioButton();
        C233 = new widget.RadioButton();
        C234 = new widget.RadioButton();
        C235 = new widget.RadioButton();
        C243 = new widget.RadioButton();
        C244 = new widget.RadioButton();
        C242 = new widget.RadioButton();
        C241 = new widget.RadioButton();
        C245 = new widget.RadioButton();
        C251 = new widget.RadioButton();
        C252 = new widget.RadioButton();
        C253 = new widget.RadioButton();
        C254 = new widget.RadioButton();
        C255 = new widget.RadioButton();
        C261 = new widget.RadioButton();
        C262 = new widget.RadioButton();
        C263 = new widget.RadioButton();
        C264 = new widget.RadioButton();
        C265 = new widget.RadioButton();
        C21 = new widget.RadioButton();
        C22 = new widget.RadioButton();
        C23 = new widget.RadioButton();
        C24 = new widget.RadioButton();
        C25 = new widget.RadioButton();
        C41 = new widget.RadioButton();
        C42 = new widget.RadioButton();
        C43 = new widget.RadioButton();
        C44 = new widget.RadioButton();
        C45 = new widget.RadioButton();
        C51 = new widget.RadioButton();
        C52 = new widget.RadioButton();
        C53 = new widget.RadioButton();
        C54 = new widget.RadioButton();
        C55 = new widget.RadioButton();
        C64 = new widget.RadioButton();
        C61 = new widget.RadioButton();
        C65 = new widget.RadioButton();
        C62 = new widget.RadioButton();
        C63 = new widget.RadioButton();
        C71 = new widget.RadioButton();
        C72 = new widget.RadioButton();
        C73 = new widget.RadioButton();
        C74 = new widget.RadioButton();
        C75 = new widget.RadioButton();
        C83 = new widget.RadioButton();
        C84 = new widget.RadioButton();
        C81 = new widget.RadioButton();
        C85 = new widget.RadioButton();
        C82 = new widget.RadioButton();
        C91 = new widget.RadioButton();
        C92 = new widget.RadioButton();
        C93 = new widget.RadioButton();
        C94 = new widget.RadioButton();
        C95 = new widget.RadioButton();
        C101 = new widget.RadioButton();
        C103 = new widget.RadioButton();
        C105 = new widget.RadioButton();
        C102 = new widget.RadioButton();
        C104 = new widget.RadioButton();
        C115 = new widget.RadioButton();
        C111 = new widget.RadioButton();
        C112 = new widget.RadioButton();
        C113 = new widget.RadioButton();
        C114 = new widget.RadioButton();
        C125 = new widget.RadioButton();
        C121 = new widget.RadioButton();
        C122 = new widget.RadioButton();
        C123 = new widget.RadioButton();
        C124 = new widget.RadioButton();
        C131 = new widget.RadioButton();
        C132 = new widget.RadioButton();
        C133 = new widget.RadioButton();
        C134 = new widget.RadioButton();
        C135 = new widget.RadioButton();
        C144 = new widget.RadioButton();
        C145 = new widget.RadioButton();
        C142 = new widget.RadioButton();
        C141 = new widget.RadioButton();
        C143 = new widget.RadioButton();
        panelGray3 = new widget.PanelGray();
        panelGray1 = new widget.PanelGray();
        label27 = new widget.Label();
        label161 = new widget.Label();
        D11 = new widget.Label();
        label163 = new widget.Label();
        D12 = new widget.Label();
        label165 = new widget.Label();
        label166 = new widget.Label();
        D21 = new widget.Label();
        label168 = new widget.Label();
        D22 = new widget.Label();
        label170 = new widget.Label();
        label171 = new widget.Label();
        D31 = new widget.Label();
        label173 = new widget.Label();
        D32 = new widget.Label();
        label175 = new widget.Label();
        label176 = new widget.Label();
        D41 = new widget.Label();
        label178 = new widget.Label();
        D42 = new widget.Label();
        hsldom1 = new widget.Label();
        hsldom2 = new widget.Label();
        hsldom3 = new widget.Label();
        hsldom4 = new widget.Label();
        hsldom5 = new widget.Label();
        hsldom6 = new widget.Label();
        hsldom7 = new widget.Label();
        hsldom8 = new widget.Label();
        btHitung = new widget.Button();
        btSimpan = new widget.Button();
        button11 = new widget.Button();
        button10 = new widget.Button();
        button12 = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        panelGray2 = new widget.PanelGray();
        NoRwt = new widget.TextBox();
        label1 = new widget.Label();
        label159 = new widget.Label();
        tanggal1 = new widget.Tanggal();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbList = new widget.Table();
        label162 = new widget.Label();
        nmPas = new widget.TextBox();
        noRM = new widget.TextBox();
        label164 = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Formulir WHOQoL ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setForeground(new java.awt.Color(255, 255, 255));
        internalFrame1.setLayout(new java.awt.BorderLayout());

        scrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        label133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label133.setText("1. Bagaimana menurut anda, kualitas hidup anda ?");
        label133.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label133.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bg1.add(C15);
        C15.setText("Sangat Baik");

        bg1.add(C11);
        C11.setText("Sangat Buruk");

        bg1.add(C12);
        C12.setText("Buruk");

        bg1.add(C13);
        C13.setText("Biasa-biasa saja");

        bg1.add(C14);
        C14.setText("Baik");

        label134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label134.setText("2. Seberapa puas Anda terhadap kesehatan anda ?");
        label134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label134.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label135.setText("5. Seberapa jauh Anda menikmati hidup Anda ?");
        label135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label135.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label136.setText("6. Seberapa jauh Anda merasa hidup Anda berarti ?");
        label136.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label136.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label137.setText("8. Secara umum seberapa aman Anda rasakan dalam kehidupan anda sehari-hari ?");
        label137.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label137.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label137.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label138.setText("12. Apakah anda memiliki cukup uang untuk memenuhi kebutuhan anda ?");
        label138.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label138.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label139.setText("4. Sebagaimana sering Anda membutuhkan terapi medis untuk dapat berfungsi dalam kehidupan sehari-hari Anda ?");
        label139.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label139.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label140.setText("3. Seberapa jauh rasa sakit fisik Anda, mencegah Anda dalam beraktivitas  sesuai kebutuhan anda ?");
        label140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label140.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label141.setText("9. Seberapa  sehat lingkuingan dimana anda tinggal (berkaitan dengan sarana dan prasarana) ?");
        label141.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label141.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label141.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label142.setText("10. Apakah anda memiliki vitalitas yang cukup untuk beraktivitas sehari-hari ?");
        label142.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label142.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label143.setText("11. Apakah anda dapat menerima penampilan tubuh anda ?");
        label143.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label143.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label144.setText("7. Seberapa jauh Anda mampu berkonsentrasi ?");
        label144.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label144.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label145.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label145.setText("13. Seberapa jauh ketersediaan informasi bagi kehidupan Anda dari hari ke hari ?");
        label145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label145.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label146.setText("14. Seberapa sering anda memiliki kesempatan untuk bersenang-senang/rekreasi ?");
        label146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label146.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label147.setText("15. Seberapa baik kemampuan Anda dalam bergaul ?");
        label147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label147.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label148.setText("16. Seberapa puaskah anda dengan tidur anda ?");
        label148.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label148.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label149.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label149.setText("17. Seberapa puaskah anda dengan kemampuan anda untuk menampilkan aktivitas kehidupan anda sehari-hari ?");
        label149.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label149.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label150.setText("18. Seberapa puaskah anda dengan kemampuan anda untuk bekerja ?");
        label150.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label150.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label151.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label151.setText("19. Seberapa puaskah anda terhadap diri anda ?");
        label151.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label151.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label152.setText("20. Seberapa puaskah anda dengan hubungan personal/sosial anda ?");
        label152.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label152.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label153.setText("21. Seberapa puaskah anda dengan kehidupan seksual anda ?");
        label153.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label153.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label154.setText("22. Seberapa puaskah anda dengan dukungan yang anda peroleh dari teman anda ?");
        label154.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label154.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label155.setText("23. Seberapa puaskah anda dengan kondisi tempat tinggal anda ?");
        label155.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label155.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label156.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label156.setText("25. Seberapa puaskah anda dengan transportasi yang harus anda jalani ?");
        label156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label156.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bg3.add(C31);
        C31.setText("Tidak Sama Sekali");

        bg3.add(C32);
        C32.setText("Sedikit");

        bg3.add(C33);
        C33.setText("Dalam jumlah sedang");

        bg3.add(C34);
        C34.setText("Sangat Sering");

        bg3.add(C35);
        C35.setText("Dalam jumlah berlebihan");

        label157.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label157.setText("24. Seberapa puaskah anda dengan akses anda pada layanan kesehatan ?");
        label157.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label157.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bg15.add(C151);
        C151.setText("Sangat Buruk");

        bg15.add(C152);
        C152.setText("Buruk");

        bg15.add(C153);
        C153.setText("Biasa-biasa saja");

        bg15.add(C154);
        C154.setText("Baik");

        bg15.add(C155);
        C155.setText("Sangat Baik");

        bg16.add(C161);
        C161.setText("Sangat Tidak Memuaskan");

        bg16.add(C162);
        C162.setText("Tidak Memuaskan");

        bg16.add(C163);
        C163.setText("Biasa-biasa saja");

        bg16.add(C164);
        C164.setText("Memuaskan");

        bg16.add(C165);
        C165.setText("Sangat Memuaskan");

        label158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label158.setText("26. Seberapa sering anda memiliki perasaan negatif seperti 'feeling blue (kesepian),  putus asa, cemas dan depresi  ?");
        label158.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label158.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bg17.add(C171);
        C171.setText("Sangat Tidak Memuaskan");

        bg17.add(C172);
        C172.setText("Tidak Memuaskan");

        bg17.add(C173);
        C173.setText("Biasa-biasa saja");

        bg17.add(C174);
        C174.setText("Memuaskan");

        bg17.add(C175);
        C175.setText("Sangat Memuaskan");

        bg18.add(C183);
        C183.setText("Biasa-biasa saja");

        bg18.add(C181);
        C181.setText("Sangat Tidak Memuaskan");

        bg18.add(C184);
        C184.setText("Memuaskan");

        bg18.add(C185);
        C185.setText("Sangat Memuaskan");

        bg18.add(C182);
        C182.setText("Tidak Memuaskan");

        bg19.add(C192);
        C192.setText("Tidak Memuaskan");

        bg19.add(C193);
        C193.setText("Biasa-biasa saja");

        bg19.add(C194);
        C194.setText("Memuaskan");

        bg19.add(C195);
        C195.setText("Sangat Memuaskan");

        bg19.add(C191);
        C191.setText("Sangat Tidak Memuaskan");

        bg20.add(C204);
        C204.setText("Memuaskan");

        bg20.add(C203);
        C203.setText("Biasa-biasa saja");

        bg20.add(C205);
        C205.setText("Sangat Memuaskan");

        bg20.add(C202);
        C202.setText("Tidak Memuaskan");

        bg20.add(C201);
        C201.setText("Sangat Tidak Memuaskan");

        bg21.add(C211);
        C211.setText("Sangat Tidak Memuaskan");

        bg21.add(C212);
        C212.setText("Tidak Memuaskan");

        bg21.add(C213);
        C213.setText("Biasa-biasa saja");

        bg21.add(C214);
        C214.setText("Memuaskan");

        bg21.add(C215);
        C215.setText("Sangat Memuaskan");

        bg22.add(C221);
        C221.setText("Sangat Tidak Memuaskan");

        bg22.add(C225);
        C225.setText("Sangat Memuaskan");

        bg22.add(C222);
        C222.setText("Tidak Memuaskan");

        bg22.add(C224);
        C224.setText("Memuaskan");

        bg22.add(C223);
        C223.setText("Biasa-biasa saja");

        bg23.add(C231);
        C231.setText("Sangat Tidak Memuaskan");

        bg23.add(C232);
        C232.setText("Tidak Memuaskan");

        bg23.add(C233);
        C233.setText("Biasa-biasa saja");

        bg23.add(C234);
        C234.setText("Memuaskan");

        bg23.add(C235);
        C235.setText("Sangat Memuaskan");

        bg24.add(C243);
        C243.setText("Biasa-biasa saja");

        bg24.add(C244);
        C244.setText("Memuaskan");

        bg24.add(C242);
        C242.setText("Tidak Memuaskan");

        bg24.add(C241);
        C241.setText("Sangat Tidak Memuaskan");

        bg24.add(C245);
        C245.setText("Sangat Memuaskan");

        bg25.add(C251);
        C251.setText("Sangat Tidak Memuaskan");

        bg25.add(C252);
        C252.setText("Tidak Memuaskan");

        bg25.add(C253);
        C253.setText("Biasa-biasa saja");

        bg25.add(C254);
        C254.setText("Memuaskan");

        bg25.add(C255);
        C255.setText("Sangat Memuaskan");

        bg26.add(C261);
        C261.setText("Tidak Pernah");

        bg26.add(C262);
        C262.setText("Jarang");
        C262.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R262ActionPerformed(evt);
            }
        });

        bg26.add(C263);
        C263.setText("Cukup Sering");

        bg26.add(C264);
        C264.setText("Sangat Sering");

        bg26.add(C265);
        C265.setText("Selalu");

        bg2.add(C21);
        C21.setText("Sangat Tidak Memuaskan");

        bg2.add(C22);
        C22.setText("Tidak Memuaskan");

        bg2.add(C23);
        C23.setText("Biasa-biasa saja");

        bg2.add(C24);
        C24.setText("Memuaskan");

        bg2.add(C25);
        C25.setText("Sangat Memuaskan");

        bg4.add(C41);
        C41.setText("Tidak Sama Sekali");

        bg4.add(C42);
        C42.setText("Sedikit");

        bg4.add(C43);
        C43.setText("Dalam jumlah sedang");

        bg4.add(C44);
        C44.setText("Sangat Sering");

        bg4.add(C45);
        C45.setText("Dalam jumlah berlebihan");

        bg5.add(C51);
        C51.setText("Tidak Sama Sekali");

        bg5.add(C52);
        C52.setText("Sedikit");

        bg5.add(C53);
        C53.setText("Dalam jumlah sedang");

        bg5.add(C54);
        C54.setText("Sangat Sering");

        bg5.add(C55);
        C55.setText("Dalam jumlah berlebihan");

        bg6.add(C64);
        C64.setText("Sangat Sering");

        bg6.add(C61);
        C61.setText("Tidak Sama Sekali");

        bg6.add(C65);
        C65.setText("Dalam jumlah berlebihan");

        bg6.add(C62);
        C62.setText("Sedikit");

        bg6.add(C63);
        C63.setText("Dalam jumlah sedang");

        bg7.add(C71);
        C71.setText("Tidak Sama Sekali");

        bg7.add(C72);
        C72.setText("Sedikit");

        bg7.add(C73);
        C73.setText("Dalam jumlah sedang");

        bg7.add(C74);
        C74.setText("Sangat Sering");

        bg7.add(C75);
        C75.setText("Dalam jumlah berlebihan");

        bg8.add(C83);
        C83.setText("Dalam jumlah sedang");

        bg8.add(C84);
        C84.setText("Sangat Sering");

        bg8.add(C81);
        C81.setText("Tidak Sama Sekali");

        bg8.add(C85);
        C85.setText("Dalam jumlah berlebihan");

        bg8.add(C82);
        C82.setText("Sedikit");

        bg9.add(C91);
        C91.setText("Tidak Sama Sekali");

        bg9.add(C92);
        C92.setText("Sedikit");

        bg9.add(C93);
        C93.setText("Dalam jumlah sedang");

        bg9.add(C94);
        C94.setText("Sangat Sering");

        bg9.add(C95);
        C95.setText("Dalam jumlah berlebihan");

        bg10.add(C101);
        C101.setText("Tidak Sama Sekali");

        bg10.add(C103);
        C103.setText("Sedang");

        bg10.add(C105);
        C105.setText("Sepenuhnya dialami");

        bg10.add(C102);
        C102.setText("Sedikit");

        bg10.add(C104);
        C104.setText("Seringkali");

        bg11.add(C115);
        C115.setText("Sepenuhnya dialami");

        bg11.add(C111);
        C111.setText("Tidak Sama Sekali");

        bg11.add(C112);
        C112.setText("Sedikit");

        bg11.add(C113);
        C113.setText("Sedang");

        bg11.add(C114);
        C114.setText("Seringkali");

        bg12.add(C125);
        C125.setText("Sepenuhnya dialami");

        bg12.add(C121);
        C121.setText("Tidak Sama Sekali");

        bg12.add(C122);
        C122.setText("Sedikit");

        bg12.add(C123);
        C123.setText("Sedang");

        bg12.add(C124);
        C124.setText("Seringkali");

        bg13.add(C131);
        C131.setText("Tidak Sama Sekali");

        bg13.add(C132);
        C132.setText("Sedikit");

        bg13.add(C133);
        C133.setText("Sedang");

        bg13.add(C134);
        C134.setText("Seringkali");

        bg13.add(C135);
        C135.setText("Sepenuhnya dialami");

        bg14.add(C144);
        C144.setText("Seringkali");

        bg14.add(C145);
        C145.setText("Sepenuhnya dialami");

        bg14.add(C142);
        C142.setText("Sedikit");

        bg14.add(C141);
        C141.setText("Tidak Sama Sekali");

        bg14.add(C143);
        C143.setText("Sedang");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C261, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(C262, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(C263, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(C264, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(C265, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C251, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C252, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C253, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C254, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C255, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C241, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C242, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C243, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C244, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C245, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C231, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C232, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C233, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C234, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C235, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C221, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C222, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C223, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C224, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C225, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C211, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C212, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C213, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C214, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C215, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C201, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C202, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C203, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C204, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C205, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C191, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C192, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C193, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C194, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C195, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C181, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C182, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C183, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C184, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C185, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C171, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C172, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C173, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C174, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C175, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C163, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(label133, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label158, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label156, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label155, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label153, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label149, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label147, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label154, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(C151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(C152, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(C153, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C154, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C155, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label152, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label140, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(376, 587, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(label133, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(label134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(label139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(label135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(label138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label147, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C154, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C152, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C153, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C155, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C163, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label149, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C174, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C171, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C172, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C173, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C175, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(label150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C184, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C181, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C182, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C183, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C185, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label151, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C194, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C191, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C192, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C193, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C195, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label152, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C204, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C201, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C202, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C203, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C205, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label153, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C214, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C211, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C212, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C213, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C215, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label154, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C224, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C221, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C222, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C223, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C225, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label155, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C234, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C231, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C232, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C233, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C235, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label157, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C244, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C241, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C242, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C243, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C245, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label156, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C254, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C251, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C252, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C253, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C255, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label158, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C264, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C261, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C262, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C263, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C265, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        scrollPane1.setViewportView(jPanel6);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        label27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label27.setText("Domain I :");
        label27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label161.setForeground(new java.awt.Color(0, 0, 255));
        label161.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label161.setText("Score 4-20 =");
        label161.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D11.setText("0");
        D11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label163.setForeground(new java.awt.Color(0, 0, 255));
        label163.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label163.setText("Score 0-100 =");
        label163.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D12.setText("0");
        D12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label165.setText("Domain II :");
        label165.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label166.setForeground(new java.awt.Color(0, 0, 255));
        label166.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label166.setText("Score 4-20 =");
        label166.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D21.setText("0");
        D21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label168.setForeground(new java.awt.Color(0, 0, 255));
        label168.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label168.setText("Score 0-100 =");
        label168.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D22.setText("0");
        D22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label170.setText("Domain III :");
        label170.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label171.setForeground(new java.awt.Color(0, 0, 255));
        label171.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label171.setText("Score 4-20 =");
        label171.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D31.setText("0");
        D31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label173.setForeground(new java.awt.Color(0, 0, 255));
        label173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label173.setText("Score 0-100 =");
        label173.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D32.setText("0");
        D32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label175.setText("Domain IV :");
        label175.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label176.setForeground(new java.awt.Color(0, 0, 255));
        label176.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label176.setText("Score 4-20 =");
        label176.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D41.setText("0");
        D41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label178.setForeground(new java.awt.Color(0, 0, 255));
        label178.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label178.setText("Score 0-100 =");
        label178.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        D42.setText("0");
        D42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom1.setForeground(new java.awt.Color(0, 0, 0));
        hsldom1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hsldom1.setText("HASIL");
        hsldom1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom2.setForeground(new java.awt.Color(255, 51, 0));
        hsldom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hsldom2.setText("-");
        hsldom2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom3.setForeground(new java.awt.Color(0, 0, 0));
        hsldom3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hsldom3.setText("HASIL");
        hsldom3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom4.setForeground(new java.awt.Color(255, 51, 0));
        hsldom4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hsldom4.setText("-");
        hsldom4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom5.setForeground(new java.awt.Color(0, 0, 0));
        hsldom5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hsldom5.setText("HASIL");
        hsldom5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom6.setForeground(new java.awt.Color(255, 51, 0));
        hsldom6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hsldom6.setText("-");
        hsldom6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom7.setForeground(new java.awt.Color(0, 0, 0));
        hsldom7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hsldom7.setText("HASIL");
        hsldom7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hsldom8.setForeground(new java.awt.Color(255, 51, 0));
        hsldom8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hsldom8.setText("-");
        hsldom8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout panelGray1Layout = new javax.swing.GroupLayout(panelGray1);
        panelGray1.setLayout(panelGray1Layout);
        panelGray1Layout.setHorizontalGroup(
            panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGray1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label27, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(label163, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(label161, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(D11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(hsldom1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hsldom2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label166, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label168, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(hsldom3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hsldom4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label170, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelGray1Layout.createSequentialGroup()
                                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label171, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label173, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(D32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(49, 49, 49))
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(hsldom5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hsldom6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(hsldom7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hsldom8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label175, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label176, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label178, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelGray1Layout.setVerticalGroup(
            panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGray1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hsldom5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hsldom6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGray1Layout.createSequentialGroup()
                        .addComponent(label170, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label171, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label173, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelGray1Layout.createSequentialGroup()
                            .addComponent(label175, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label176, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(D41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(D42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label178, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hsldom7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hsldom8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelGray1Layout.createSequentialGroup()
                            .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelGray1Layout.createSequentialGroup()
                                    .addComponent(label165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label166, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(D21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(5, 5, 5)
                                    .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label168, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(D22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelGray1Layout.createSequentialGroup()
                                    .addComponent(label27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(D11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label163, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(D12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hsldom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hsldom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelGray1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hsldom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hsldom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelGray3.add(panelGray1);

        btHitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/TestTubes.png"))); // NOI18N
        btHitung.setText("Hitung");
        btHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHitungActionPerformed(evt);
            }
        });
        panelGray3.add(btHitung);

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/095.png"))); // NOI18N
        btSimpan.setText("Simpan");
        btSimpan.setPreferredSize(new java.awt.Dimension(105, 30));
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        panelGray3.add(btSimpan);

        button11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        button11.setText("Cetak");
        button11.setPreferredSize(new java.awt.Dimension(100, 30));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        panelGray3.add(button11);

        button10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Remove tooth SH.png"))); // NOI18N
        button10.setText("Hapus");
        button10.setPreferredSize(new java.awt.Dimension(105, 30));
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        panelGray3.add(button10);

        button12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button12.setText("Keluar");
        button12.setPreferredSize(new java.awt.Dimension(100, 30));
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        panelGray3.add(button12);

        internalFrame1.add(panelGray3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Data Pasien ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        label1.setText("No Rawat");
        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        label159.setText("Tanggal");
        label159.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbList);

        label162.setText("Nama pasien");
        label162.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        nmPas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmPasActionPerformed(evt);
            }
        });

        label164.setText("No RM");
        label164.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout panelGray2Layout = new javax.swing.GroupLayout(panelGray2);
        panelGray2.setLayout(panelGray2Layout);
        panelGray2Layout.setHorizontalGroup(
            panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGray2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGray2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NoRwt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noRM, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nmPas, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(label159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        panelGray2Layout.setVerticalGroup(
            panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGray2Layout.createSequentialGroup()
                .addGroup(panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nmPas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGray2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NoRwt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noRM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        internalFrame2.add(panelGray2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    refresh_data();

    }//GEN-LAST:event_formWindowOpened

    private void R262ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R262ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R262ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        int i = tbList.getSelectedRow();
         if(tbList.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();     
            param.put("Q1", bcTombol(bg1));
            param.put("Q2", bcTombol(bg2));
            param.put("Q3", bcTombol(bg3));
            param.put("Q4", bcTombol(bg4));
            param.put("Q5", bcTombol(bg5));
            param.put("Q6", bcTombol(bg6));
            param.put("Q7", bcTombol(bg7));
            param.put("Q8", bcTombol(bg8));
            param.put("Q9", bcTombol(bg9));
            param.put("Q10", bcTombol(bg10));
            param.put("Q11", bcTombol(bg11));
            param.put("Q12", bcTombol(bg12));
            param.put("Q13", bcTombol(bg13));
            param.put("Q14", bcTombol(bg14));
            param.put("Q15", bcTombol(bg15));
            param.put("Q16", bcTombol(bg16));
            param.put("Q17", bcTombol(bg17));
            param.put("Q18", bcTombol(bg18));
            param.put("Q19", bcTombol(bg19));
            param.put("Q20", bcTombol(bg20));
            param.put("Q21", bcTombol(bg21));
            param.put("Q22", bcTombol(bg22));
            param.put("Q23", bcTombol(bg23));
            param.put("Q24", bcTombol(bg24));
            param.put("Q25", bcTombol(bg25));
            param.put("Q26", bcTombol(bg26));
            param.put("noRM", noRM.getText());
            param.put("nmPas", nmPas.getText());
            param.put("dom1", grandtotdom1);
            param.put("dom2", grandtotdom2);
            param.put("dom3", grandtotdom3);
            param.put("dom4", grandtotdom4);
            param.put("hsldom1", hsldom2.getText());
            param.put("hsldom2", hsldom4.getText());
            param.put("hsldom3", hsldom6.getText());
            param.put("hsldom4", hsldom8.getText());
            param.put("logo",sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("penilaianwho.jasper", "report", "::[ Laporan Penilaian WHOQoL ]::", "SELECT no_rawat, tanggal, Q1, Q2, Q3, Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,\n" +
            "Q20,Q21,Q22,Q23,Q24,Q25,Q26,totdom1,totdom2,totdom3,totdom4,hsltot FROM penilaianwho where no_rawat='"+tbList.getValueAt(i, 0).toString()+"'",param);
        }else{
             JOptionPane.showMessageDialog(null, "Silahkan pilih data pasien dahulu");
         }
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        dispose();
    }//GEN-LAST:event_button12ActionPerformed

    public void setNo(String noRwt, String nmPasien, String noRMD){
    NoRwt.setText(noRwt);
    nmPas.setText(nmPasien);
    noRM.setText(noRMD);
    }
    
    private void simpan(){
        try{
            ps = koneksi.prepareStatement("insert into penilaianwho values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, NoRwt.getText());
            ps.setString(2, dateStamp);
            ps.setString(3, Integer.toString(Q1) );
            ps.setString(4, Integer.toString(Q2) );
            ps.setString(5, Integer.toString(Q3) );
            ps.setString(6, Integer.toString(Q4) );
            ps.setString(7, Integer.toString(Q5) );
            ps.setString(8, Integer.toString(Q6) );
            ps.setString(9, Integer.toString(Q7) );
            ps.setString(10, Integer.toString(Q8));
            ps.setString(11, Integer.toString(Q9));
            ps.setString(12, Integer.toString(Q10));
            ps.setString(13, Integer.toString(Q11));
            ps.setString(14, Integer.toString(Q12));
            ps.setString(15, Integer.toString(Q13));
            ps.setString(16, Integer.toString(Q14));
            ps.setString(17, Integer.toString(Q15));
            ps.setString(18, Integer.toString(Q16));
            ps.setString(19, Integer.toString(Q17));
            ps.setString(20, Integer.toString(Q18));
            ps.setString(21, Integer.toString(Q19));
            ps.setString(22, Integer.toString(Q20));
            ps.setString(23, Integer.toString(Q21));
            ps.setString(24, Integer.toString(Q22));
            ps.setString(25, Integer.toString(Q23));
            ps.setString(26, Integer.toString(Q24));
            ps.setString(27, Integer.toString(Q25));
            ps.setString(28, Integer.toString(Q26));
            ps.setString(29, D12.getText());
            ps.setString(30, D22.getText());
            ps.setString(31, D32.getText());
            ps.setString(32, D42.getText());
            ps.setString(33, Integer.toString(totsemua));
            ps.setString(34, NULL);
            ps.executeUpdate();   
            ii = 0;
            JOptionPane.showMessageDialog(null, "tersimpan");
            refresh_data();
            }catch(Exception e){
            System.out.println("Error : " +e.getMessage());
            }
    }
    
    private void cek(){
        try {
        ps = koneksi.prepareStatement("select count(*) from penilaianwho where no_rawat like ?");
        ps.setString(1, tbList.getValueAt(tbList.getSelectedRow(),0).toString());
            if (ps.getMaxRows()==0){
                simpan();
            }else{
                ps = koneksi.prepareStatement(NULL);
            }
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    
    
    
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if(ii==0){
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol hitung");
        }else if(ii==1){
               simpan();
        }
    }//GEN-LAST:event_btSimpanActionPerformed

private void refresh_data(){
    try {
    ps = koneksi.prepareStatement("SELECT no_rawat, tanggal, Q1, Q2, Q3, Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,\n" +
         "Q20,Q21,Q22,Q23,Q24,Q25,Q26,totdom1,totdom2,totdom3,totdom4,hsltot FROM penilaianwho where no_rawat like ?");
    ps.setString(1, NoRwt.getText());
    rs = ps.executeQuery();
    tabMode.setRowCount(0);
    while(rs.next()){
        tabMode.addRow(new Object[]{
        rs.getString("no_rawat"),rs.getString("tanggal"),rs.getString("Q1"),rs.getString("Q2"),rs.getString("Q3"),rs.getString("Q4"),rs.getString("Q5"),rs.getString("Q6"),
            rs.getString("Q7"),rs.getString("Q8"),rs.getString("Q9"),rs.getString("Q10"),rs.getString("Q11"),rs.getString("Q12"),rs.getString("Q13"),rs.getString("Q14"),
            rs.getString("Q15"),rs.getString("Q16"),rs.getString("Q17"),rs.getString("Q18"),rs.getString("Q19"),rs.getString("Q20"),rs.getString("Q21"),rs.getString("Q22"),
            rs.getString("Q23"),rs.getString("Q24"),rs.getString("Q25"),rs.getString("Q26"),rs.getString("totdom1"),rs.getString("totdom2"),rs.getString("totdom3"),rs.getString("totdom4"),
            rs.getString("hsltot")
        });
    }
    }catch(Exception e){
        System.out.println("Error : "+e.getMessage());
    }
}    

private void pasang(){
//    totdom1=22;
    switch(totdom1){
        case 7: grandtotdom1 = 0;break;
        case 8: 
        case 9: grandtotdom1 = 6;break;
        case 10: 
        case 11: grandtotdom1 = 13;break;
        case 12:
        case 13: grandtotdom1 = 19;break;
        case 14: grandtotdom1 = 25;break;
        case 15: 
        case 16: grandtotdom1 = 31;break;
        case 17: 
        case 18: grandtotdom1 = 38;break;
        case 19: 
        case 20: grandtotdom1 = 44;break;
        case 21: grandtotdom1 = 50;break;
        case 22: 
        case 23: grandtotdom1 = 56;break;
        case 24: 
        case 25: grandtotdom1 = 63;break;
        case 26: 
        case 27: grandtotdom1 = 69;break;
        case 28: grandtotdom1 = 75;break;
        case 29: 
        case 30: grandtotdom1 = 81;break;
        case 31: 
        case 32: grandtotdom1 = 88;break;
        case 33: 
        case 34: grandtotdom1 = 94;break;
        case 35: grandtotdom1 = 100;break;
    }
//    totdom2=26;
    switch(totdom2){
        case 6: grandtotdom2 = 0;break;
        case 7: 
        case 8: grandtotdom2 = 6;break;
        case 9: grandtotdom2 = 13;break;
        case 10: 
        case 11: grandtotdom2 = 19;break;
        case 12: grandtotdom2 = 25;break;
        case 13: 
        case 14: grandtotdom2 = 31;break;
        case 15: grandtotdom2 = 38;break;
        case 16: 
        case 17: grandtotdom2 = 44;break;
        case 18: grandtotdom2 = 50;break;
        case 19: 
        case 20: grandtotdom2 = 63;break;
        case 21: 
        case 22: 
        case 23: grandtotdom2 = 69;break;
        case 24: grandtotdom2 = 75;break;
        case 25: 
        case 26: grandtotdom2 = 81;break;
        case 27: grandtotdom2 = 88;break;
        case 28: 
        case 29: grandtotdom2 = 94;break;
        case 30: grandtotdom2 = 100;break;
    }
//    totdom3=11;
    switch(totdom3){
        case 3: grandtotdom3 = 0;break;
        case 4: grandtotdom3 = 6;break;
        case 5: grandtotdom3 = 19;break;
        case 6: grandtotdom3 = 25;break;
        case 7: grandtotdom3 = 31;break;
        case 8: grandtotdom3 = 44;break;
        case 9: grandtotdom3 = 50;break;
        case 10: grandtotdom3 = 56;break;
        case 11: grandtotdom3 = 69;break;
        case 12: grandtotdom3 = 75;break;
        case 13: grandtotdom3 = 81;break;
        case 14: grandtotdom2 = 94;break;
        case 15: grandtotdom2 = 100;break;
    }
//    totdom4=33;
    switch(totdom4){
//        case 7: grandtotdom4 = 0;
        case 8: grandtotdom4 = 0;break;
        case 9: 
        case 10: grandtotdom4 = 6;break;
        case 11: 
        case 12: grandtotdom4 = 13;break;
        case 13: 
        case 14: grandtotdom4 = 19;break;
        case 15: 
        case 16: grandtotdom4 = 25;break;
        case 17: 
        case 18: grandtotdom4 = 31;break;
        case 19: 
        case 20: grandtotdom4 = 38;break;
        case 21: 
        case 22: grandtotdom4 = 44;break;
        case 23: 
        case 24: grandtotdom4 = 50;break;
        case 25: 
        case 26: grandtotdom4 = 56;break;
        case 27: 
        case 28: grandtotdom4 = 63;break;
        case 29: 
        case 30: grandtotdom4 = 69;break;
        case 31: 
        case 32: grandtotdom4 = 75;break;
        case 33: 
        case 34: grandtotdom4 = 81;break;
        case 35: 
        case 36: grandtotdom4 = 88;break;
        case 37: 
        case 38: grandtotdom4 = 94;break; 
        case 39: 
        case 40: grandtotdom4 = 100;break;
    }
}

    private void btHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHitungActionPerformed
//        Skor 0. - 55 = Kurang
//        56 - 75 = Cukup
//        76 - 100.  = baik
        ii=1;
        hitungQ1();
        hitungQ2();
        hitungQ3();
        hitungQ4();
        hitungQ5();
        hitungQ6();
        hitungQ7();
        hitungQ8();
        hitungQ9();
        hitungQ10();
        hitungQ11();
        hitungQ12();
        hitungQ13();
        hitungQ14();
        hitungQ15();
        hitungQ16();
        hitungQ17();
        hitungQ18();
        hitungQ19();
        hitungQ20();
        hitungQ21();
        hitungQ22();
        hitungQ23();
        hitungQ24();
        hitungQ25();
        hitungQ26();
        hitungtot();
        pasang();
        D12.setText(Integer.toString(totdom1));
        D22.setText(Integer.toString(totdom2));
        D32.setText(Integer.toString(totdom3));
        D42.setText(Integer.toString(totdom4));
        JOptionPane.showMessageDialog(null, grandtotdom1+" "+grandtotdom2+" "+grandtotdom3+" "+grandtotdom4);
            if(grandtotdom1>=0 && grandtotdom1<=55 ){
                 hsldom2.setForeground(Color.red);
                 hsldom2.setText("KURANG");
            }else if(grandtotdom1>=56 && grandtotdom1<=75){
                hsldom2.setForeground(Color.orange);
                hsldom2.setText("CUKUP");
            }else if (grandtotdom1>=76 && grandtotdom1<=100){
                hsldom2.setForeground(Color.green);
                hsldom2.setText("BAIK");
            }
            
            if(grandtotdom2>=0 && grandtotdom2<=55 ){
                 hsldom4.setForeground(Color.red);
                 hsldom4.setText("KURANG");
            }else if(grandtotdom2>=56 && grandtotdom2<=75){
                hsldom4.setForeground(Color.orange);
                hsldom4.setText("CUKUP");
            }else if (grandtotdom2>=76 && grandtotdom2<=100){
                hsldom4.setForeground(Color.green);
                hsldom4.setText("BAIK");
            }
            if(grandtotdom3>=0 && grandtotdom3<=55 ){
                 hsldom6.setForeground(Color.red);
                 hsldom6.setText("KURANG");
            }else if(grandtotdom3>=56 && grandtotdom3<=75){
                hsldom6.setForeground(Color.ORANGE);
                hsldom6.setText("CUKUP");
            }else if (grandtotdom3>=76 && grandtotdom3<=100){
                hsldom6.setForeground(Color.GREEN);
                hsldom6.setText("BAIK");
            }   
            if(grandtotdom4>=0 && grandtotdom4<=55 ){
                 hsldom8.setForeground(Color.red);
                 hsldom8.setText("KURANG");
            }else if(grandtotdom4>=56 && grandtotdom4<=75){
                hsldom8.setForeground(Color.ORANGE);
                hsldom8.setText("CUKUP");
            }else if (grandtotdom4>=76 && grandtotdom4<=100){
                hsldom8.setForeground(Color.GREEN);
                hsldom8.setText("BAIK");
            }    
//        JOptionPane.showMessageDialog(null, Integer.toString(totdom1)+" "+Integer.toString(totdom2)+" "+Integer.toString(totdom3)+" "+Integer.toString(totdom4));
    }//GEN-LAST:event_btHitungActionPerformed
    public String bcTombol(ButtonGroup bg1) {
        for (Enumeration<AbstractButton> buttons = bg1.getElements(); buttons.hasMoreElements();) {
      AbstractButton button = buttons.nextElement();
      if (button.isSelected()) {
        return button.getText();
                }
            }
    return "<Harap diisi>";
    }
    
        
    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
    try {
        ps = koneksi.prepareStatement("delete from penilaianwho where no_rawat like ? and tanggal like ?");
    ps.setString(1, tbList.getValueAt(tbList.getSelectedRow(), 0).toString());
    ps.setString(2, tbList.getValueAt(tbList.getSelectedRow(), 1).toString());
    ps.executeUpdate();
    JOptionPane.showMessageDialog(null, "Dihapus");
    }catch(Exception e){
        System.out.println("Error : "+e.getMessage());
    }
      
     
    }//GEN-LAST:event_button10ActionPerformed

    private void nmPasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmPasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPasActionPerformed

    private void hitungtot() {
        totdom1 = (6-Q3)+(6-Q4)+Q10+Q15+Q16+Q17+Q18;
        totdom2 = Q5+Q6+Q7+Q11+Q19+(6-Q26);
        totdom3 = Q20+Q21+Q22;
        totdom4 = Q8+Q9+Q12+Q13+Q14+Q23+Q24+Q25;
        totsemua = totdom1+totdom2+totdom3+totdom4;
    }
    
    private void hitungQ1() {
        if (C11.isSelected()==true){
            Q1 = 1;
        }else if(C12.isSelected()==true){
            Q1 = 2;
        }else if(C13.isSelected()==true){
            Q1 = 3;
        }else if(C14.isSelected()==true){
            Q1 = 4;
        }else if(C15.isSelected()==true){
            Q1 = 5;
        }
    }
    
    private void hitungQ2() {
        if (C21.isSelected()==true){
            Q2 = 1;
        }else if(C22.isSelected()==true){
            Q2 = 2;
        }else if(C23.isSelected()==true){
            Q2 = 3;
        }else if(C24.isSelected()==true){
            Q2 = 4;
        }else if(C25.isSelected()==true){
            Q2 = 5;
        }
    }
    
    private void hitungQ3() {
        if (C31.isSelected()==true){
            Q3 = 1;
        }else if(C32.isSelected()==true){
            Q3 = 2;
        }else if(C33.isSelected()==true){
            Q3 = 3;
        }else if(C34.isSelected()==true){
            Q3 = 4;
        }else if(C35.isSelected()==true){
            Q3 = 5;
        }
    }
    
    private void hitungQ4() {
        if (C41.isSelected()==true){
            Q4 = 1;
        }else if(C42.isSelected()==true){
            Q4 = 2;
        }else if(C43.isSelected()==true){
            Q4 = 3;
        }else if(C44.isSelected()==true){
            Q4 = 4;
        }else if(C45.isSelected()==true){
            Q4 = 5;
        }
    }
    
    private void hitungQ5() {
        if (C51.isSelected()==true){
            Q5 = 1;
        }else if(C52.isSelected()==true){
            Q5 = 2;
        }else if(C53.isSelected()==true){
            Q5 = 3;
        }else if(C54.isSelected()==true){
            Q5 = 4;
        }else if(C55.isSelected()==true){
            Q5 = 5;
        }
    }
    
    private void hitungQ6() {
        if (C61.isSelected()==true){
            Q6 = 1;
        }else if(C62.isSelected()==true){
            Q6 = 2;
        }else if(C63.isSelected()==true){
            Q6 = 3;
        }else if(C64.isSelected()==true){
            Q6 = 4;
        }else if(C65.isSelected()==true){
            Q6 = 5;
        }
    }
    
    private void hitungQ7() {
        if (C71.isSelected()==true){
            Q7 = 1;
        }else if(C72.isSelected()==true){
            Q7 = 2;
        }else if(C73.isSelected()==true){
            Q7 = 3;
        }else if(C74.isSelected()==true){
            Q7 = 4;
        }else if(C75.isSelected()==true){
            Q7 = 5;
        }
    }
    
    private void hitungQ8() {
        if (C81.isSelected()==true){
            Q8 = 1;
        }else if(C82.isSelected()==true){
            Q8 = 2;
        }else if(C83.isSelected()==true){
            Q8 = 3;
        }else if(C84.isSelected()==true){
            Q8 = 4;
        }else if(C85.isSelected()==true){
            Q8 = 5;
        }
    }
    
    private void hitungQ9() {
        if (C91.isSelected()==true){
            Q9 = 1;
        }else if(C92.isSelected()==true){
            Q9 = 2;
        }else if(C93.isSelected()==true){
            Q9 = 3;
        }else if(C94.isSelected()==true){
            Q9 = 4;
        }else if(C95.isSelected()==true){
            Q9 = 5;
        }
    }
    
    private void hitungQ10() {
        if (C101.isSelected()==true){
            Q10 = 1;
        }else if(C102.isSelected()==true){
            Q10 = 2;
        }else if(C103.isSelected()==true){
            Q10 = 3;
        }else if(C104.isSelected()==true){
            Q10 = 4;
        }else if(C105.isSelected()==true){
            Q10 = 5;
        }
    }
    
    private void hitungQ11() {
        if (C111.isSelected()==true){
            Q11 = 1;
        }else if(C112.isSelected()==true){
            Q11 = 2;
        }else if(C113.isSelected()==true){
            Q11 = 3;
        }else if(C114.isSelected()==true){
            Q11 = 4;
        }else if(C115.isSelected()==true){
            Q11 = 5;
        }
    }
    
    private void hitungQ12() {
        if (C121.isSelected()==true){
            Q12 = 1;
        }else if(C122.isSelected()==true){
            Q12 = 2;
        }else if(C123.isSelected()==true){
            Q12 = 3;
        }else if(C124.isSelected()==true){
            Q12 = 4;
        }else if(C125.isSelected()==true){
            Q12 = 5;
        }
    }
    
    private void hitungQ13() {
        if (C131.isSelected()==true){
            Q13 = 1;
        }else if(C132.isSelected()==true){
            Q13 = 2;
        }else if(C133.isSelected()==true){
            Q13 = 3;
        }else if(C134.isSelected()==true){
            Q13 = 4;
        }else if(C135.isSelected()==true){
            Q13 = 5;
        }
    }
    
    private void hitungQ14() {
        if (C141.isSelected()==true){
            Q14 = 1;
        }else if(C142.isSelected()==true){
            Q14 = 2;
        }else if(C143.isSelected()==true){
            Q14 = 3;
        }else if(C144.isSelected()==true){
            Q14 = 4;
        }else if(C145.isSelected()==true){
            Q14 = 5;
        }
    }
    
    private void hitungQ15() {
        if (C151.isSelected()==true){
            Q15 = 1;
        }else if(C152.isSelected()==true){
            Q15 = 2;
        }else if(C153.isSelected()==true){
            Q15 = 3;
        }else if(C154.isSelected()==true){
            Q15 = 4;
        }else if(C155.isSelected()==true){
            Q15 = 5;
        }
    }
    
    private void hitungQ16() {
        if (C161.isSelected()==true){
            Q16 = 1;
        }else if(C162.isSelected()==true){
            Q16 = 2;
        }else if(C163.isSelected()==true){
            Q16 = 3;
        }else if(C164.isSelected()==true){
            Q16 = 4;
        }else if(C165.isSelected()==true){
            Q16 = 5;
        }
    }
    
    private void hitungQ17() {
        if (C171.isSelected()==true){
            Q17 = 1;
        }else if(C172.isSelected()==true){
            Q17 = 2;
        }else if(C173.isSelected()==true){
            Q17 = 3;
        }else if(C174.isSelected()==true){
            Q17 = 4;
        }else if(C175.isSelected()==true){
            Q17 = 5;
        }
    }
    
    private void hitungQ18() {
        if (C181.isSelected()==true){
            Q18 = 1;
        }else if(C182.isSelected()==true){
            Q18 = 2;
        }else if(C183.isSelected()==true){
            Q18 = 3;
        }else if(C184.isSelected()==true){
            Q18 = 4;
        }else if(C185.isSelected()==true){
            Q18 = 5;
        }
    }
    
    private void hitungQ19() {
        if (C191.isSelected()==true){
            Q19 = 1;
        }else if(C192.isSelected()==true){
            Q19 = 2;
        }else if(C193.isSelected()==true){
            Q19 = 3;
        }else if(C194.isSelected()==true){
            Q19 = 4;
        }else if(C195.isSelected()==true){
            Q19 = 5;
        }
    }
    
    private void hitungQ20() {
        if (C201.isSelected()==true){
            Q20 = 1;
        }else if(C202.isSelected()==true){
            Q20 = 2;
        }else if(C203.isSelected()==true){
            Q20 = 3;
        }else if(C204.isSelected()==true){
            Q20 = 4;
        }else if(C205.isSelected()==true){
            Q20 = 5;
        }
    }
    
    private void hitungQ21() {
        if (C211.isSelected()==true){
            Q21 = 1;
        }else if(C212.isSelected()==true){
            Q21 = 2;
        }else if(C213.isSelected()==true){
            Q21 = 3;
        }else if(C214.isSelected()==true){
            Q21 = 4;
        }else if(C215.isSelected()==true){
            Q21 = 5;
        }
    }
    
    private void hitungQ22() {
        if (C221.isSelected()==true){
            Q22 = 1;
        }else if(C222.isSelected()==true){
            Q22 = 2;
        }else if(C223.isSelected()==true){
            Q22 = 3;
        }else if(C224.isSelected()==true){
            Q22 = 4;
        }else if(C225.isSelected()==true){
            Q22 = 5;
        }
    }
    
    private void hitungQ23() {
        if (C231.isSelected()==true){
            Q23 = 1;
        }else if(C232.isSelected()==true){
            Q23 = 2;
        }else if(C233.isSelected()==true){
            Q23 = 3;
        }else if(C234.isSelected()==true){
            Q23 = 4;
        }else if(C235.isSelected()==true){
            Q23 = 5;
        }
    }
    
    private void hitungQ24() {
        if (C241.isSelected()==true){
            Q24 = 1;
        }else if(C242.isSelected()==true){
            Q24 = 2;
        }else if(C243.isSelected()==true){
            Q24 = 3;
        }else if(C244.isSelected()==true){
            Q24 = 4;
        }else if(C245.isSelected()==true){
            Q24 = 5;
        }
    }
    
    private void hitungQ25() {
        if (C251.isSelected()==true){
            Q25 = 1;
        }else if(C252.isSelected()==true){
            Q25 = 2;
        }else if(C253.isSelected()==true){
            Q25 = 3;
        }else if(C254.isSelected()==true){
            Q25 = 4;
        }else if(C255.isSelected()==true){
            Q25 = 5;
        }
    }
    
    private void hitungQ26() {
        if (C261.isSelected()==true){
            Q26 = 1;
        }else if(C262.isSelected()==true){
            Q26 = 2;
        }else if(C263.isSelected()==true){
            Q26 = 3;
        }else if(C264.isSelected()==true){
            Q26 = 4;
        }else if(C265.isSelected()==true){
            Q26 = 5;
        }
    }
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
            java.util.logging.Logger.getLogger(WHOQoL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WHOQoL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WHOQoL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WHOQoL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WHOQoL dialog = new WHOQoL(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.RadioButton C101;
    private widget.RadioButton C102;
    private widget.RadioButton C103;
    private widget.RadioButton C104;
    private widget.RadioButton C105;
    private widget.RadioButton C11;
    private widget.RadioButton C111;
    private widget.RadioButton C112;
    private widget.RadioButton C113;
    private widget.RadioButton C114;
    private widget.RadioButton C115;
    private widget.RadioButton C12;
    private widget.RadioButton C121;
    private widget.RadioButton C122;
    private widget.RadioButton C123;
    private widget.RadioButton C124;
    private widget.RadioButton C125;
    private widget.RadioButton C13;
    private widget.RadioButton C131;
    private widget.RadioButton C132;
    private widget.RadioButton C133;
    private widget.RadioButton C134;
    private widget.RadioButton C135;
    private widget.RadioButton C14;
    private widget.RadioButton C141;
    private widget.RadioButton C142;
    private widget.RadioButton C143;
    private widget.RadioButton C144;
    private widget.RadioButton C145;
    private widget.RadioButton C15;
    private widget.RadioButton C151;
    private widget.RadioButton C152;
    private widget.RadioButton C153;
    private widget.RadioButton C154;
    private widget.RadioButton C155;
    private widget.RadioButton C161;
    private widget.RadioButton C162;
    private widget.RadioButton C163;
    private widget.RadioButton C164;
    private widget.RadioButton C165;
    private widget.RadioButton C171;
    private widget.RadioButton C172;
    private widget.RadioButton C173;
    private widget.RadioButton C174;
    private widget.RadioButton C175;
    private widget.RadioButton C181;
    private widget.RadioButton C182;
    private widget.RadioButton C183;
    private widget.RadioButton C184;
    private widget.RadioButton C185;
    private widget.RadioButton C191;
    private widget.RadioButton C192;
    private widget.RadioButton C193;
    private widget.RadioButton C194;
    private widget.RadioButton C195;
    private widget.RadioButton C201;
    private widget.RadioButton C202;
    private widget.RadioButton C203;
    private widget.RadioButton C204;
    private widget.RadioButton C205;
    private widget.RadioButton C21;
    private widget.RadioButton C211;
    private widget.RadioButton C212;
    private widget.RadioButton C213;
    private widget.RadioButton C214;
    private widget.RadioButton C215;
    private widget.RadioButton C22;
    private widget.RadioButton C221;
    private widget.RadioButton C222;
    private widget.RadioButton C223;
    private widget.RadioButton C224;
    private widget.RadioButton C225;
    private widget.RadioButton C23;
    private widget.RadioButton C231;
    private widget.RadioButton C232;
    private widget.RadioButton C233;
    private widget.RadioButton C234;
    private widget.RadioButton C235;
    private widget.RadioButton C24;
    private widget.RadioButton C241;
    private widget.RadioButton C242;
    private widget.RadioButton C243;
    private widget.RadioButton C244;
    private widget.RadioButton C245;
    private widget.RadioButton C25;
    private widget.RadioButton C251;
    private widget.RadioButton C252;
    private widget.RadioButton C253;
    private widget.RadioButton C254;
    private widget.RadioButton C255;
    private widget.RadioButton C261;
    private widget.RadioButton C262;
    private widget.RadioButton C263;
    private widget.RadioButton C264;
    private widget.RadioButton C265;
    private widget.RadioButton C31;
    private widget.RadioButton C32;
    private widget.RadioButton C33;
    private widget.RadioButton C34;
    private widget.RadioButton C35;
    private widget.RadioButton C41;
    private widget.RadioButton C42;
    private widget.RadioButton C43;
    private widget.RadioButton C44;
    private widget.RadioButton C45;
    private widget.RadioButton C51;
    private widget.RadioButton C52;
    private widget.RadioButton C53;
    private widget.RadioButton C54;
    private widget.RadioButton C55;
    private widget.RadioButton C61;
    private widget.RadioButton C62;
    private widget.RadioButton C63;
    private widget.RadioButton C64;
    private widget.RadioButton C65;
    private widget.RadioButton C71;
    private widget.RadioButton C72;
    private widget.RadioButton C73;
    private widget.RadioButton C74;
    private widget.RadioButton C75;
    private widget.RadioButton C81;
    private widget.RadioButton C82;
    private widget.RadioButton C83;
    private widget.RadioButton C84;
    private widget.RadioButton C85;
    private widget.RadioButton C91;
    private widget.RadioButton C92;
    private widget.RadioButton C93;
    private widget.RadioButton C94;
    private widget.RadioButton C95;
    private widget.Label D11;
    private widget.Label D12;
    private widget.Label D21;
    private widget.Label D22;
    private widget.Label D31;
    private widget.Label D32;
    private widget.Label D41;
    private widget.Label D42;
    private widget.TextBox NoRwt;
    private javax.swing.ButtonGroup bg1;
    private javax.swing.ButtonGroup bg10;
    private javax.swing.ButtonGroup bg11;
    private javax.swing.ButtonGroup bg12;
    private javax.swing.ButtonGroup bg13;
    private javax.swing.ButtonGroup bg14;
    private javax.swing.ButtonGroup bg15;
    private javax.swing.ButtonGroup bg16;
    private javax.swing.ButtonGroup bg17;
    private javax.swing.ButtonGroup bg18;
    private javax.swing.ButtonGroup bg19;
    private javax.swing.ButtonGroup bg2;
    private javax.swing.ButtonGroup bg20;
    private javax.swing.ButtonGroup bg21;
    private javax.swing.ButtonGroup bg22;
    private javax.swing.ButtonGroup bg23;
    private javax.swing.ButtonGroup bg24;
    private javax.swing.ButtonGroup bg25;
    private javax.swing.ButtonGroup bg26;
    private javax.swing.ButtonGroup bg3;
    private javax.swing.ButtonGroup bg4;
    private javax.swing.ButtonGroup bg5;
    private javax.swing.ButtonGroup bg6;
    private javax.swing.ButtonGroup bg7;
    private javax.swing.ButtonGroup bg8;
    private javax.swing.ButtonGroup bg9;
    private widget.Button btHitung;
    private widget.Button btSimpan;
    private widget.Button button10;
    private widget.Button button11;
    private widget.Button button12;
    private widget.Label hsldom1;
    private widget.Label hsldom2;
    private widget.Label hsldom3;
    private widget.Label hsldom4;
    private widget.Label hsldom5;
    private widget.Label hsldom6;
    private widget.Label hsldom7;
    private widget.Label hsldom8;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private widget.Label label1;
    private widget.Label label133;
    private widget.Label label134;
    private widget.Label label135;
    private widget.Label label136;
    private widget.Label label137;
    private widget.Label label138;
    private widget.Label label139;
    private widget.Label label140;
    private widget.Label label141;
    private widget.Label label142;
    private widget.Label label143;
    private widget.Label label144;
    private widget.Label label145;
    private widget.Label label146;
    private widget.Label label147;
    private widget.Label label148;
    private widget.Label label149;
    private widget.Label label150;
    private widget.Label label151;
    private widget.Label label152;
    private widget.Label label153;
    private widget.Label label154;
    private widget.Label label155;
    private widget.Label label156;
    private widget.Label label157;
    private widget.Label label158;
    private widget.Label label159;
    private widget.Label label161;
    private widget.Label label162;
    private widget.Label label163;
    private widget.Label label164;
    private widget.Label label165;
    private widget.Label label166;
    private widget.Label label168;
    private widget.Label label170;
    private widget.Label label171;
    private widget.Label label173;
    private widget.Label label175;
    private widget.Label label176;
    private widget.Label label178;
    private widget.Label label27;
    private widget.TextBox nmPas;
    private widget.TextBox noRM;
    private widget.PanelGray panelGray1;
    private widget.PanelGray panelGray2;
    private widget.PanelGray panelGray3;
    private widget.ScrollPane scrollPane1;
    private widget.Tanggal tanggal1;
    private widget.Table tbList;
    // End of variables declaration//GEN-END:variables
}
