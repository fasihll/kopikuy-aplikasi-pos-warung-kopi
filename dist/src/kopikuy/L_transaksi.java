/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopikuy;

import config.koneksi;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author FASIH
 */
public class L_transaksi extends javax.swing.JFrame {
private koneksi db = new koneksi();
int baris;
    /**
     * Creates new form L_transaksi
     */
    public L_transaksi() {
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
        tbl.addColumn("NO_Faktur");
        tbl.addColumn("Customer Name");
        tbl.addColumn("Karyawan Name");
        tbl.addColumn("Total Item");
        tbl.addColumn("Total Harga");
        tbl.addColumn("Bayar");
        tbl.addColumn("Kembali");
        tbl.addColumn("Tanggal");
        jTable1.setModel(tbl);
        try {
            if (keyword != "") {
                String sql = "SELECT\n" +
                            "    category.categoryID AS category_categoryID,\n" +
                            "    category.name AS category_name,\n" +
                            "    category.description AS category_description,\n" +
                            "    detile_transaction.orderID AS detile_transaction_orderID,\n" +
                            "    detile_transaction.transactionID AS detile_transaction_transactionID,\n" +
                            "    detile_transaction.foodID AS detile_transaction_foodID,\n" +
                            "    detile_transaction.qty AS detile_transaction_qty,\n" +
                            "    detile_transaction.created_at AS detile_transaction_created_at,\n" +
                            "    detile_transaction.updated_at AS detile_transaction_updated_at,\n" +
                            "    foods.foodID AS foods_foodID,\n" +
                            "    foods.name AS foods_name,\n" +
                            "    foods.categoryID AS foods_categoryID,\n" +
                            "    foods.price AS foods_price,\n" +
                            "    foods.food_stock AS foods_food_stock,\n" +
                            "    foods.created_at AS foods_created_at,\n" +
                            "    foods.updated_at AS foods_updated_at,\n" +
                            "    karyawan.karyawanID AS karyawan_karyawanID,\n" +
                            "    karyawan.name AS karyawan_name,\n" +
                            "    karyawan.jenis_kelamin AS karyawan_jenis_kelamin,\n" +
                            "    karyawan.tgl_lahir AS karyawan_tgl_lahir,\n" +
                            "    karyawan.alamat AS karyawan_alamat,\n" +
                            "    karyawan.no_telp AS karyawan_no_telp,\n" +
                            "    transaction.transactionID AS transaction_transactionID,\n" +
                            "    transaction.no_faktur AS transaction_no_faktur,\n" +
                            "    transaction.buyer_name AS transaction_buyer_name,\n" +
                            "    transaction.total AS transaction_total,\n" +
                            "    transaction.total_item AS transaction_total_item,\n" +
                            "    transaction.bayar AS transaction_bayar,\n" +
                            "    transaction.kembalian AS transaction_kembalian,\n" +
                            "    transaction.karyawanID AS transaction_karyawanID,\n" +
                            "    transaction.created_at AS transaction_created_at,\n" +
                            "    transaction.updated_at AS transaction_updated_at\n" +
                            "FROM\n" +
                            "    category\n" +
                            "INNER JOIN foods ON\n" +
                            "    category.categoryID = foods.categoryID\n" +
                            "INNER JOIN  detile_transaction ON\n" +
                            "    foods.foodID = detile_transaction.foodID\n" +
                            "INNER JOIN transaction ON\n" +
                            "    detile_transaction.transactionID = transaction.transactionID\n" +
                            "INNER JOIN karyawan ON \n" +
                            "	transaction.karyawanID = karyawan.karyawanID\n" +
                            "    WHERE transaction.no_faktur LIKE '%"+keyword+"%' OR transaction.buyer_name LIKE '%"+keyword+"%' OR karyawan.name LIKE '%"+keyword+"%' OR transaction.created_at LIKE '%"+keyword+"%'  " +
                            "    GROUP BY transaction_no_faktur ORDER BY transaction.created_at ASC";
                 PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int no = 1;
                while (rs.next()) {                
                tbl.addRow(new Object[] {
                    no++,
                    rs.getString("transaction_no_faktur"),
                    rs.getString("transaction_buyer_name"),
                    rs.getString("karyawan_name"),
                    rs.getString("transaction_total_item"),
                    rs.getString("transaction_total"),
                    rs.getString("transaction_bayar"),
                    rs.getString("transaction_kembalian"),
                    rs.getString("transaction_created_at")
                });
                jTable1.setModel(tbl);
            }
            }else{
                String sql = "SELECT\n" +
                            "    category.categoryID AS category_categoryID,\n" +
                            "    category.name AS category_name,\n" +
                            "    category.description AS category_description,\n" +
                            "    detile_transaction.orderID AS detile_transaction_orderID,\n" +
                            "    detile_transaction.transactionID AS detile_transaction_transactionID,\n" +
                            "    detile_transaction.foodID AS detile_transaction_foodID,\n" +
                            "    detile_transaction.qty AS detile_transaction_qty,\n" +
                            "    detile_transaction.created_at AS detile_transaction_created_at,\n" +
                            "    detile_transaction.updated_at AS detile_transaction_updated_at,\n" +
                            "    foods.foodID AS foods_foodID,\n" +
                            "    foods.name AS foods_name,\n" +
                            "    foods.categoryID AS foods_categoryID,\n" +
                            "    foods.price AS foods_price,\n" +
                            "    foods.food_stock AS foods_food_stock,\n" +
                            "    foods.created_at AS foods_created_at,\n" +
                            "    foods.updated_at AS foods_updated_at,\n" +
                            "    karyawan.karyawanID AS karyawan_karyawanID,\n" +
                            "    karyawan.name AS karyawan_name,\n" +
                            "    karyawan.jenis_kelamin AS karyawan_jenis_kelamin,\n" +
                            "    karyawan.tgl_lahir AS karyawan_tgl_lahir,\n" +
                            "    karyawan.alamat AS karyawan_alamat,\n" +
                            "    karyawan.no_telp AS karyawan_no_telp,\n" +
                            "    transaction.transactionID AS transaction_transactionID,\n" +
                            "    transaction.no_faktur AS transaction_no_faktur,\n" +
                            "    transaction.buyer_name AS transaction_buyer_name,\n" +
                            "    transaction.total AS transaction_total,\n" +
                            "    transaction.total_item AS transaction_total_item,\n" +
                            "    transaction.bayar AS transaction_bayar,\n" +
                            "    transaction.kembalian AS transaction_kembalian,\n" +
                            "    transaction.karyawanID AS transaction_karyawanID,\n" +
                            "    transaction.created_at AS transaction_created_at,\n" +
                            "    transaction.updated_at AS transaction_updated_at\n" +
                            "FROM\n" +
                            "    category\n" +
                            "INNER JOIN foods ON\n" +
                            "    category.categoryID = foods.categoryID\n" +
                            "INNER JOIN  detile_transaction ON\n" +
                            "    foods.foodID = detile_transaction.foodID\n" +
                            "INNER JOIN transaction ON\n" +
                            "    detile_transaction.transactionID = transaction.transactionID\n" +
                            "INNER JOIN karyawan ON \n" +
                            "	transaction.karyawanID = karyawan.karyawanID\n" +
                            "    GROUP BY transaction_no_faktur ORDER BY transaction.created_at ASC";   
                    PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int no = 1;
                    while (rs.next()) {                
                    tbl.addRow(new Object[] {
                        no++,
                       rs.getString("transaction_no_faktur"),
                       rs.getString("transaction_buyer_name"),
                       rs.getString("karyawan_name"),
                       rs.getString("transaction_total_item"),
                       rs.getString("transaction_total"),
                       rs.getString("transaction_bayar"),
                       rs.getString("transaction_kembalian"),
                       rs.getString("transaction_created_at")
                });
                jTable1.setModel(tbl);
            }
            }
           db.koneksidb().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "query table transaction salah "+e);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        keyword = new javax.swing.JTextField();
        btn_cetak_laporan = new javax.swing.JButton();
        btn_cetak_struk = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        setTitle("Laporan Transaksi");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel2.setText("Serach");

        keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keywordKeyReleased(evt);
            }
        });

        btn_cetak_laporan.setBackground(new java.awt.Color(0, 174, 239));
        btn_cetak_laporan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_cetak_laporan.setForeground(new java.awt.Color(255, 255, 255));
        btn_cetak_laporan.setText("Cetak Laporan");
        btn_cetak_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetak_laporanActionPerformed(evt);
            }
        });

        btn_cetak_struk.setBackground(new java.awt.Color(0, 174, 239));
        btn_cetak_struk.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_cetak_struk.setForeground(new java.awt.Color(255, 255, 255));
        btn_cetak_struk.setText("Cetak Struk");
        btn_cetak_struk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetak_strukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cetak_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cetak_struk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cetak_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetak_struk, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 80));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setText("Laporan / Laporan Transaksi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(909, 909, 909))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 42));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cetak_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetak_laporanActionPerformed
        String monthName[] = {"January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December"
                            };
        
        JTextField Tahun = new JTextField(5);
        
        JTextField Bulan = new JTextField(2);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Tahun"));
        myPanel.add(Tahun);
        myPanel.add(Box.createHorizontalStrut(0));
        myPanel.add(new JLabel("Bulan"));
        myPanel.add(Bulan);
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Cetak Laporan", JOptionPane.OK_CANCEL_OPTION);
        
        int no_bulan = Integer.parseInt(Bulan.getText())-1;
        
            if (result == JOptionPane.OK_OPTION) {
                
                try {
                    String sql= "SELECT\n" +
                                "	COUNT(transaction.no_faktur) as row,\n" +
                                "	SUM(transaction.total) as total_pendapatan,\n" +
                                "    transaction.transactionID AS transaction_transactionID,\n" +
                                "    transaction.no_faktur AS transaction_no_faktur,\n" +
                                "    transaction.buyer_name AS transaction_buyer_name,\n" +
                                "    transaction.total AS transaction_total,\n" +
                                "    transaction.total_item AS transaction_total_item,\n" +
                                "    transaction.bayar AS transaction_bayar,\n" +
                                "    transaction.kembalian AS transaction_kembalian,\n" +
                                "    transaction.karyawanID AS transaction_karyawanID,\n" +
                                "    transaction.created_at AS transaction_created_at\n" +
                                "FROM\n" +
                                "    transaction\n" +
                                "\n" +
                                "    WHERE SUBSTRING(transaction.created_at,1,7)='"+Tahun.getText()+"-"+Bulan.getText()+"'\n" +
                                "     GROUP BY SUBSTRING(transaction.created_at,1,7)\n" +
                                "    ORDER BY transaction.created_at ASC";
                    PreparedStatement ps = db.koneksidb().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    


                    String appDirectory = new File(System.getProperty("user.dir")).getAbsolutePath();

                    // Periksa apakah aplikasi dijalankan dari "app" folder
                    File appFolder = new File(appDirectory, "app");
                    if (appFolder.exists()) {
                        appDirectory = new File(appDirectory, "app").getAbsolutePath(); // Sesuaikan jika berjalan dari installer
                    }

                    // Menyusun jalur file laporan untuk Lap_transaksi.jasper dengan pemisah direktori yang benar
                    String path = appDirectory + File.separator + "src" + File.separator + "kopikuy" + File.separator + "Lap_transaksi.jasper";
   // letak file report (dalam format jasper)
                    Map parameter = new HashMap();
                    parameter.put("bulanTahun",Tahun.getText()+"-"+Bulan.getText());  
                    parameter.put("monthName",monthName[no_bulan].toString());  
                    parameter.put("Tahun",Tahun.getText());  
                    parameter.put("pendapatan_bulan",rs.getDouble("total_pendapatan"));  
                    parameter.put("row",rs.getInt("row"));  
                    JasperPrint print = JasperFillManager.fillReport(path,parameter,db.koneksidb()); //teskoneksi adalah class koneksi ke database
                    JasperViewer.viewReport(print, false);
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
                }
            }
        
        
    }//GEN-LAST:event_btn_cetak_laporanActionPerformed

    private void btn_cetak_strukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetak_strukActionPerformed
        String no_faktur = jTable1.getValueAt(baris, 1).toString();
        try {
            String appDirectory = new File(System.getProperty("user.dir")).getAbsolutePath();

            // Periksa apakah aplikasi dijalankan dari "app" folder
            File appFolder = new File(appDirectory, "app");
            if (appFolder.exists()) {
                appDirectory = new File(appDirectory, "app").getAbsolutePath(); // Sesuaikan jika berjalan dari installer
            }

            // Menyusun jalur file laporan untuk Struk.jasper dengan pemisah direktori yang benar
            String path = appDirectory + File.separator + "src" + File.separator + "kopikuy" + File.separator + "Struk.jasper";

            Map parameter = new HashMap();
            parameter.put("no_faktur",no_faktur);  
            JasperPrint print = JasperFillManager.fillReport(path,parameter,db.koneksidb()); //teskoneksi adalah class koneksi ke database
            JasperViewer.viewReport(print, false);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }//GEN-LAST:event_btn_cetak_strukActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        baris = jTable1.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(L_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(L_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(L_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(L_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new L_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem L_karyawan;
    private javax.swing.JMenuItem L_stok;
    private javax.swing.JMenuItem L_transaksi;
    private javax.swing.JButton btn_cetak_laporan;
    private javax.swing.JButton btn_cetak_struk;
    private javax.swing.JMenuItem dt_category;
    private javax.swing.JMenuItem dt_karyawan;
    private javax.swing.JMenuItem dt_makanan;
    private javax.swing.JMenuItem dt_shift;
    private javax.swing.JMenuItem dt_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
