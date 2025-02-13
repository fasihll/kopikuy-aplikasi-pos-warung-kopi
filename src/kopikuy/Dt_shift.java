/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopikuy;

import config.koneksi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.lang.String;


/**
 *
 * @author FASIH
 */
public class Dt_shift extends javax.swing.JFrame {
private koneksi db = new koneksi();
    /**
     * Creates new form Dashboard
     */
    public Dt_shift() {
        initComponents();
        version.setText(UserSession.GetVersion());
        jTable1.getTableHeader().setBackground(new Color(169,124,80));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.setRowHeight(25);
        TableAddRow(""); 
        hakAkses();
    }
    
     private void hakAkses(){
        String level = UserSession.getUserLogin();
        System.out.println(level);
        if (level.equals("Admin")) {//admin
            mnmaster.setVisible(true);
            mntransaction.setVisible(true);
            mnlaporan.setVisible(true);
        }else if(level.equals("Owner")){//owner
            mnmaster.setVisible(false);
            mntransaction.setVisible(false);
            mnlaporan.setVisible(true);
        }else if(level.equals("Kasir")){//kasir
            mnmaster.setVisible(false);
            mntransaction.setVisible(true);
            mnlaporan.setVisible(false);
            mn_shiftKaryawan.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Session gagal");
        }
    }
    
    public void TableAddRow(String keyword){
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("ID");
        tbl.addColumn("Nama shift");
        tbl.addColumn("Jam Mulai");
        tbl.addColumn("Jam Akhir");
        jTable1.setModel(tbl);
        try {
            if (keyword != "") {
                String sql = "select * from shift where name LIKE '%"+keyword+"%'";  
                 PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int no = 1;
                while (rs.next()) {                
                tbl.addRow(new Object[] {
                    no++,
                    rs.getString("shiftID"),
                    rs.getString("name"),
                    rs.getString("startTime"),
                    rs.getString("endTime")
                });
                jTable1.setModel(tbl);
            }
            }else{
                String sql = "select * from shift";   
                    PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int no = 1;
                while (rs.next()) {                
                tbl.addRow(new Object[] {
                    no++,
                    rs.getString("shiftID"),
                    rs.getString("name"),
                    rs.getString("startTime"),
                    rs.getString("endTime")
                });
                jTable1.setModel(tbl);
            }
            }
           db.koneksidb().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "query table shift salah"+e);
        }
    }
    

    
    public void refresh(){
        txt_no.setText("");
        txt_nama.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        keyword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        txt_no = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmb_jamMulai = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmb_jamAkhir = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        version = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mndashboard = new javax.swing.JMenu();
        mnmaster = new javax.swing.JMenu();
        dt_makanan = new javax.swing.JMenuItem();
        dt_category = new javax.swing.JMenuItem();
        dt_shift = new javax.swing.JMenuItem();
        dt_karyawan = new javax.swing.JMenuItem();
        dt_user = new javax.swing.JMenuItem();
        mntransaction = new javax.swing.JMenu();
        mn_penjualan = new javax.swing.JMenuItem();
        mn_shiftKaryawan = new javax.swing.JMenuItem();
        mnlaporan = new javax.swing.JMenu();
        L_transaksi = new javax.swing.JMenuItem();
        L_karyawan = new javax.swing.JMenuItem();
        L_stok = new javax.swing.JMenuItem();
        mnlogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Shift");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 80));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setText("Master / Data Shift");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(934, 934, 934))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO", "ID", "NAME", "Jam mulai", "Jam Akhir"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keywordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keywordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keywordKeyTyped(evt);
            }
        });

        jLabel2.setText("Search");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(142, 142, 142))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Nama Shift");

        jLabel5.setText("Jam mulai");

        btn_tambah.setBackground(new java.awt.Color(57, 181, 74));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(244, 116, 33));
        btn_update.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(239, 71, 35));
        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        txt_no.setEditable(false);

        jLabel7.setText("ID");

        cmb_jamMulai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01:00:00", "01:30:00", "02:00:00", "02:30:00", "03:00:00", "03:30:00", "04:00:00", "04:30:00", "05:00:00", "05:30:00", "06:00:00", "06:30:00", "07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00", "23:30:00", "00:00:00", "00:30:00" }));

        jLabel6.setText("Jam akhir");

        cmb_jamAkhir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01:00:00", "01:30:00", "02:00:00", "02:30:00", "03:00:00", "03:30:00", "04:00:00", "04:30:00", "05:00:00", "05:30:00", "06:00:00", "06:30:00", "07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00", "23:30:00", "00:00:00", "00:30:00" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmb_jamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txt_nama)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txt_no, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmb_jamMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_no, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_jamMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_jamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addGap(18, 18, 18)
                .addComponent(btn_refresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 42));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(version, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 150, 42));

        jMenuBar1.setBackground(new java.awt.Color(60, 36, 21));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(347, 35));

        mndashboard.setBackground(new java.awt.Color(51, 29, 17));
        mndashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mndashboard.setForeground(new java.awt.Color(255, 255, 255));
        mndashboard.setText("   Dashboard");
        mndashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mndashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mndashboard.setPreferredSize(new java.awt.Dimension(100, 0));
        mndashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mndashboardMouseClicked(evt);
            }
        });
        mndashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mndashboardActionPerformed(evt);
            }
        });
        jMenuBar1.add(mndashboard);

        mnmaster.setBackground(new java.awt.Color(255, 255, 255));
        mnmaster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mnmaster.setForeground(new java.awt.Color(255, 255, 255));
        mnmaster.setText("        Master");
        mnmaster.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnmaster.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnmaster.setPreferredSize(new java.awt.Dimension(100, 0));

        dt_makanan.setBackground(new java.awt.Color(255, 255, 255));
        dt_makanan.setText("Data Makanan");
        dt_makanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dt_makananActionPerformed(evt);
            }
        });
        mnmaster.add(dt_makanan);

        dt_category.setBackground(new java.awt.Color(255, 255, 255));
        dt_category.setText("Data Category");
        dt_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dt_categoryActionPerformed(evt);
            }
        });
        mnmaster.add(dt_category);

        dt_shift.setBackground(new java.awt.Color(255, 255, 255));
        dt_shift.setText("Data Shift");
        dt_shift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dt_shiftActionPerformed(evt);
            }
        });
        mnmaster.add(dt_shift);

        dt_karyawan.setBackground(new java.awt.Color(255, 255, 255));
        dt_karyawan.setText("Data Karyawan");
        dt_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dt_karyawanActionPerformed(evt);
            }
        });
        mnmaster.add(dt_karyawan);

        dt_user.setBackground(new java.awt.Color(255, 255, 255));
        dt_user.setText("Data User");
        dt_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dt_userActionPerformed(evt);
            }
        });
        mnmaster.add(dt_user);

        jMenuBar1.add(mnmaster);

        mntransaction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mntransaction.setForeground(new java.awt.Color(255, 255, 255));
        mntransaction.setText("   Transaction");
        mntransaction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mntransaction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mntransaction.setPreferredSize(new java.awt.Dimension(100, 0));

        mn_penjualan.setBackground(new java.awt.Color(255, 255, 255));
        mn_penjualan.setText("Penjualan");
        mn_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_penjualanActionPerformed(evt);
            }
        });
        mntransaction.add(mn_penjualan);

        mn_shiftKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        mn_shiftKaryawan.setText("Shift karyawan");
        mn_shiftKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_shiftKaryawanActionPerformed(evt);
            }
        });
        mntransaction.add(mn_shiftKaryawan);

        jMenuBar1.add(mntransaction);

        mnlaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mnlaporan.setForeground(new java.awt.Color(255, 255, 255));
        mnlaporan.setText("      Laporan");
        mnlaporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnlaporan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnlaporan.setPreferredSize(new java.awt.Dimension(100, 0));

        L_transaksi.setBackground(new java.awt.Color(255, 255, 255));
        L_transaksi.setText("Laporan transaksi");
        L_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L_transaksiActionPerformed(evt);
            }
        });
        mnlaporan.add(L_transaksi);

        L_karyawan.setBackground(new java.awt.Color(255, 255, 255));
        L_karyawan.setText("Laporan karyawan");
        L_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L_karyawanActionPerformed(evt);
            }
        });
        mnlaporan.add(L_karyawan);

        L_stok.setBackground(new java.awt.Color(255, 255, 255));
        L_stok.setText("Laporan Stok Makanan");
        L_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L_stokActionPerformed(evt);
            }
        });
        mnlaporan.add(L_stok);

        jMenuBar1.add(mnlaporan);

        mnlogout.setBackground(new java.awt.Color(255, 102, 102));
        mnlogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mnlogout.setForeground(new java.awt.Color(255, 51, 51));
        mnlogout.setText("       Logout");
        mnlogout.setToolTipText("");
        mnlogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnlogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnlogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnlogout.setPreferredSize(new java.awt.Dimension(100, 0));
        mnlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnlogoutMouseClicked(evt);
            }
        });
        mnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnlogoutActionPerformed(evt);
            }
        });
        jMenuBar1.add(mnlogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         // menampilkan data kedalam form pengisian:
        int baris = jTable1.rowAtPoint(evt.getPoint());
        
        String kode = jTable1.getValueAt(baris, 1).toString();
        txt_no.setText(kode);
        
        String nama = jTable1.getValueAt(baris, 2).toString();
        txt_nama.setText(nama);
        
        String jamMulai = jTable1.getValueAt(baris,3).toString();
        cmb_jamMulai.setSelectedItem(jamMulai);
        
        String jamAkhir = jTable1.getValueAt(baris,4).toString();
        cmb_jamAkhir.setSelectedItem(jamAkhir);

    }//GEN-LAST:event_jTable1MouseClicked

    private void keywordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyPressed
    
    }//GEN-LAST:event_keywordKeyPressed

    private void keywordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyTyped
        
    }//GEN-LAST:event_keywordKeyTyped

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        if (txt_nama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong");
        }else{
            String nama = txt_nama.getText();
            String jamMulai = cmb_jamMulai.getModel().getSelectedItem().toString();
            String jamAkhir = cmb_jamAkhir.getModel().getSelectedItem().toString();

            try {
                String sql = "INSERT INTO shift (name,startTime,endTime) VALUES ('"+nama+"','"+jamMulai+"','"+jamAkhir+"')";
                PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di simpan!");
                TableAddRow("");
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di simpan!"+e);
            }
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        refresh();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (txt_no.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "pilih data terlebih dahulu");
        }else{
            int opsi = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?");
        if (opsi == JOptionPane.YES_OPTION) {
            String shiftID = txt_no.getText();
            try {
                String sql = "DELETE FROM shift WHERE shiftID='"+txt_no.getText()+"'";
                PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Delete");
                TableAddRow("");
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Delete "+e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data Tidak Di hapus!");
        }
        }
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (txt_no.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu");
        }else{
            String nama = txt_nama.getText();
            String jamMulai = cmb_jamMulai.getModel().getSelectedItem().toString();
            String jamAkhir = cmb_jamAkhir.getModel().getSelectedItem().toString();
            try {

                String sql = "update shift set name='"+nama+"',startTime='"+jamMulai+"' where endTime='"+jamAkhir+"'";
                PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
                TableAddRow("");
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Update"+e);

            }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void keywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyReleased

        TableAddRow(keyword.getText());
    }//GEN-LAST:event_keywordKeyReleased

    private void mndashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mndashboardMouseClicked
        Dashboard lg = new Dashboard();
        lg.setVisible(true);
        lg.pack();
        lg.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_mndashboardMouseClicked

    private void mndashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mndashboardActionPerformed

    }//GEN-LAST:event_mndashboardActionPerformed

    private void dt_makananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dt_makananActionPerformed
        menuController.data_makanan();
        dispose();
    }//GEN-LAST:event_dt_makananActionPerformed

    private void dt_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dt_categoryActionPerformed
        menuController.data_category();
        dispose();
    }//GEN-LAST:event_dt_categoryActionPerformed

    private void dt_shiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dt_shiftActionPerformed
        menuController.data_shift();
        dispose();
    }//GEN-LAST:event_dt_shiftActionPerformed

    private void dt_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dt_karyawanActionPerformed
        menuController.data_karyawan();
        dispose();
    }//GEN-LAST:event_dt_karyawanActionPerformed

    private void dt_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dt_userActionPerformed
        menuController.data_user();
        dispose();
    }//GEN-LAST:event_dt_userActionPerformed

    private void mn_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_penjualanActionPerformed
        menuController.penjualan();
        dispose();
    }//GEN-LAST:event_mn_penjualanActionPerformed

    private void mn_shiftKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_shiftKaryawanActionPerformed
        menuController.shiftKaryawan();
        dispose();
    }//GEN-LAST:event_mn_shiftKaryawanActionPerformed

    private void L_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L_transaksiActionPerformed
        menuController.L_transaksi();
        dispose();
    }//GEN-LAST:event_L_transaksiActionPerformed

    private void L_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L_karyawanActionPerformed
        menuController.L_karyawan();
        dispose();
    }//GEN-LAST:event_L_karyawanActionPerformed

    private void L_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L_stokActionPerformed
        menuController.L_stok();
        dispose();
    }//GEN-LAST:event_L_stokActionPerformed

    private void mnlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnlogoutMouseClicked
        int opsi = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?");
        if (opsi == 0) {
            UserSession.setUserLogin("");
            Login lg = new Login();
            lg.setVisible(true);
            lg.pack();
            lg.setLocationRelativeTo(null);
            dispose();
        }
    }//GEN-LAST:event_mnlogoutMouseClicked

    private void mnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnlogoutActionPerformed

    }//GEN-LAST:event_mnlogoutActionPerformed

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
            java.util.logging.Logger.getLogger(Dt_shift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dt_shift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dt_shift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dt_shift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dt_shift().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem L_karyawan;
    private javax.swing.JMenuItem L_stok;
    private javax.swing.JMenuItem L_transaksi;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_jamAkhir;
    private javax.swing.JComboBox<String> cmb_jamMulai;
    private javax.swing.JMenuItem dt_category;
    private javax.swing.JMenuItem dt_karyawan;
    private javax.swing.JMenuItem dt_makanan;
    private javax.swing.JMenuItem dt_shift;
    private javax.swing.JMenuItem dt_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField keyword;
    private javax.swing.JMenuItem mn_penjualan;
    private javax.swing.JMenuItem mn_shiftKaryawan;
    private javax.swing.JMenu mndashboard;
    private javax.swing.JMenu mnlaporan;
    public static javax.swing.JMenu mnlogout;
    private javax.swing.JMenu mnmaster;
    private javax.swing.JMenu mntransaction;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_no;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
