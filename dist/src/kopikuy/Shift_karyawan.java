/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopikuy;

import config.koneksi;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FASIH
 */
public class Shift_karyawan extends javax.swing.JFrame {
private koneksi db = new koneksi();
    /**
     * Creates new form Shift_karyawan
     */
    public Shift_karyawan() {
        initComponents();
        version.setText(UserSession.GetVersion());
        jTable1.getTableHeader().setBackground(new Color(169,124,80));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.setRowHeight(25);
        TableAddRow("");
        cmb_karyawan();
        cmb_shift();
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
        tbl.addColumn("Nama Karyawan");
        tbl.addColumn("Shift");
        tbl.addColumn("Mulai");
        tbl.addColumn("Akhir");
        jTable1.setModel(tbl);
        try {
            if (keyword != "") {
                String sql = "SELECT *,karyawan.name as nama_karyawan, shift.name as nama_shift FROM shift_karyawan JOIN karyawan ON karyawan.karyawanID = shift_karyawan.karyawanID JOIN shift ON shift.shiftID = shift_karyawan.shiftID WHERE shift_karyawan.id_shift_karyawan  LIKE '%"+keyword+"%' OR karyawan.name LIKE '%"+keyword+"%' OR shift.name LIKE '%"+keyword+"%' ";  
                 PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int no = 1;
                while (rs.next()) {                
                tbl.addRow(new Object[] {
                    no++,
                    rs.getString("id_shift_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("nama_shift"),
                    rs.getString("startTime"),
                    rs.getString("endTime")
                });
                jTable1.setModel(tbl);
            }
            }else{
                String sql = "SELECT *,karyawan.name as nama_karyawan, shift.name as nama_shift FROM shift_karyawan JOIN karyawan ON karyawan.karyawanID = shift_karyawan.karyawanID JOIN shift ON shift.shiftID = shift_karyawan.shiftID";   
                    PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int no = 1;
                    while (rs.next()) {                
                    tbl.addRow(new Object[] {
                    no++,
                    rs.getString("id_shift_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("nama_shift"),
                    rs.getString("startTime"),
                    rs.getString("endTime")
                });
                jTable1.setModel(tbl);
            }
            }
           db.koneksidb().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "query table shift_kayawan salah "+e);
        }
    }
    
    public void cmb_karyawan(){
        try {
            String sql = "select * from karyawan";
            PreparedStatement ps = db.koneksidb().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                cmb_karyawan.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "query karyawan salah");
        }
    }
    
    public void cmb_shift(){
        try {
            String sql = "select * from shift";
            PreparedStatement ps = db.koneksidb().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                cmb_shift.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "query shift salah");
        }
    }
    
    
    public void refresh(){
        txt_id.setText("");
        cmb_karyawan.setSelectedItem(null);
        cmb_shift.setSelectedItem(null);
        txt_keyword.setText("");
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_keyword = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmb_karyawan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmb_shift = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
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
        setTitle("Shift Karyawan");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 80));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setText("Transaction / Shift Karyawan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(900, 900, 900))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Search");

        txt_keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_keywordKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txt_id.setEditable(false);
        txt_id.setToolTipText("");

        jLabel3.setText("ID");

        jLabel4.setText("Nama karyawan");

        jLabel5.setText("Nama Shift");

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_karyawan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_shift, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_shift, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addGap(18, 18, 18)
                .addComponent(btn_refresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 42));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        version.setToolTipText("");
        jPanel4.add(version, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 150, 42));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        refresh();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        String id =  txt_id.getText();
        String nama_karyawan = cmb_karyawan.getSelectedItem().toString();
        String nama_shift = cmb_shift.getSelectedItem().toString();
        try {   
            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "ID Tidak boleh kosong");
                      
            }else{
                String sql = "select * from karyawan where name='"+nama_karyawan+"'";
                PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ResultSet rs = ps.executeQuery(); rs.next();
                String karyawanID = rs.getString("karyawanID");

                String sql1 = "select * from shift where name='"+nama_shift+"'";
                PreparedStatement ps1 = db.koneksidb().prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery(); rs1.next();
                String shiftID = rs1.getString("shiftID");
                
                String sql0 = "select count(*) as row_count from shift_karyawan where karyawanID='"+karyawanID+"' AND shiftID='"+shiftID+"' ";
                PreparedStatement ps0 = db.koneksidb().prepareStatement(sql0);
                ResultSet rs0 = ps0.executeQuery(); rs0.next();
                int cek_shift = rs0.getInt("row_count");
                
                    if (cek_shift == 0) {
                        String sql2 = "UPDATE shift_karyawan SET karyawanID='"+karyawanID+"',shiftID='"+shiftID+"' where id_shift_karyawan='"+id+"' ";
                        PreparedStatement ps2 = db.koneksidb().prepareStatement(sql2);
                        ps2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Telah Di ubah");
                        TableAddRow("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Shift Telah di pilih");
                    } 
            }   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal di tambahkan "+e);
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (txt_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu!!");
        }else{
            int option = JOptionPane.showConfirmDialog(null, "apakah anda yakin?");
            if (option == 0) {
                String id = txt_id.getText();
                try {
                    String sql = "delete from shift_karyawan where id_shift_karyawan='"+id+"'";
                    PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "Data telah di hapus");
                    TableAddRow("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "data gagal di hapus");
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_keywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_keywordKeyReleased
        TableAddRow(txt_keyword.getText());
    }//GEN-LAST:event_txt_keywordKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        
        String id = jTable1.getValueAt(baris, 1).toString();
        txt_id.setText(id);
        String nama_karyawan = jTable1.getValueAt(baris, 2).toString();
        cmb_karyawan.setSelectedItem(nama_karyawan);
        String nama_shift = jTable1.getValueAt(baris, 3).toString();
        cmb_shift.setSelectedItem(nama_shift);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String id =  txt_id.getText();
        String nama_karyawan = cmb_karyawan.getSelectedItem().toString();
        String nama_shift = cmb_shift.getSelectedItem().toString();
        try {   
            if (nama_karyawan != "") {
                String sql = "select * from karyawan where name='"+nama_karyawan+"'";
                PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ResultSet rs = ps.executeQuery(); rs.next();
                String karyawanID = rs.getString("karyawanID");

                String sql1 = "select * from shift where name='"+nama_shift+"'";
                PreparedStatement ps1 = db.koneksidb().prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery(); rs1.next();
                String shiftID = rs1.getString("shiftID");
                
                String sql0 = "select count(*) as row_count from shift_karyawan where karyawanID='"+karyawanID+"' AND shiftID='"+shiftID+"' ";
                PreparedStatement ps0 = db.koneksidb().prepareStatement(sql0);
                ResultSet rs0 = ps0.executeQuery(); rs0.next();
                int cek_shift = rs0.getInt("row_count");
                
                    if (cek_shift >= 1) {
                        JOptionPane.showMessageDialog(null, "Shift Telah di pilih");
                    }else{
                        String sql2 = "insert into shift_karyawan (karyawanID,shiftID) VALUES ('"+karyawanID+"','"+shiftID+"')";
                        PreparedStatement ps2 = db.koneksidb().prepareStatement(sql2);
                        ps2.execute();
                        JOptionPane.showMessageDialog(null, "Data Telah Di tambahkan");
                        TableAddRow("");
                    }       
            }else{
                JOptionPane.showMessageDialog(null, "Data Tidak boleh kosong");
            }   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal di tambahkan "+e);
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

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
            java.util.logging.Logger.getLogger(Shift_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Shift_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Shift_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Shift_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shift_karyawan().setVisible(true);
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
    private javax.swing.JComboBox<String> cmb_karyawan;
    private javax.swing.JComboBox<String> cmb_shift;
    private javax.swing.JMenuItem dt_category;
    private javax.swing.JMenuItem dt_karyawan;
    private javax.swing.JMenuItem dt_makanan;
    private javax.swing.JMenuItem dt_shift;
    private javax.swing.JMenuItem dt_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem mn_penjualan;
    private javax.swing.JMenuItem mn_shiftKaryawan;
    private javax.swing.JMenu mndashboard;
    private javax.swing.JMenu mnlaporan;
    public static javax.swing.JMenu mnlogout;
    private javax.swing.JMenu mnmaster;
    private javax.swing.JMenu mntransaction;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_keyword;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
