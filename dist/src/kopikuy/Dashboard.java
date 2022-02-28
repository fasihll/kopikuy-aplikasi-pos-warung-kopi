/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopikuy;

import config.koneksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Year;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author FASIH
 */
public class Dashboard extends javax.swing.JFrame {
private koneksi db = new koneksi();
    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setTitle("Welcome "+ UserSession.getUserLogin());
        version.setText(UserSession.GetVersion());
        hakAkses();
        showLineChart(Year.now().toString());
        total_karyawan();
        total_shift();
        total_transaction();
        cmb_linechart();
    }
    //*-------------------------------Hak Akses---------------------------------------*//
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
    //*-------------------------------Line Chart---------------------------------------*//
    public void showLineChart(String tahun){
       String monthName[] = {"January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December"
                            };
        try {
        String year = tahun;
        String sql = "SELECT *,SUBSTRING(created_at,6,2) as month,SUM(total) as month_total FROM transaction WHERE YEAR(created_at) = "+year+" GROUP BY SUBSTRING(created_at,6,2) ORDER BY created_at ASC;";
        PreparedStatement pst = db.koneksidb().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
                    
//              //create dataset for the graph
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                while (rs.next()) {
                    dataset.setValue(rs.getInt("month_total"), "Amount", monthName[rs.getInt("month")-1]);
                }

        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("STATISTIK PENJUALAN "+year,"monthly","amount", 
        dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //*-------------------------------total karyawan---------------------------------------*//
    public void total_karyawan(){
        try {
            String sql = "SELECT COUNT(karyawanID) as total_karyawan from karyawan";
            PreparedStatement pst = db.koneksidb().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            total_karyawan.setText(rs.getString("total_karyawan"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query karyawan salah");
        }
    }
    //*-------------------------------total transaction bulan ini---------------------------------------*//
    public void total_transaction(){
          String monthName[] = {"January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December"
                            };
        try {
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            int year = today.getYear();
            String sql = "SELECT COUNT(transactionID) as total_transaction from transaction where YEAR(created_at) = "+year+" AND SUBSTRING(created_at,6,2) = "+month+"";
            PreparedStatement pst = db.koneksidb().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            total_month_transaction.setText(rs.getString("total_transaction"));
            judul_total_month_transaction.setText("Total Transaction Bulan "+ monthName[month-1]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query total_transaction salah"+e);
        }
    }
    //*-------------------------------total shift---------------------------------------*//
    public void total_shift(){
         try {
            String sql = "SELECT COUNT(shiftID) as total_shift from shift";
            PreparedStatement pst = db.koneksidb().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            total_shift.setText(rs.getString("total_shift"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query total_shift salah");

        }
    }
    
    public void cmb_linechart(){
         try {
            String query = "SELECT SUBSTRING(created_at,1,4) as tahun FROM transaction GROUP BY YEAR(created_at) ORDER BY tahun DESC";
            PreparedStatement ps = db.koneksidb().prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            
            while (rs.next()) {                
                cmb_tahun_linechart.addItem(rs.getString("tahun"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query salah"+e);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        version = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();
        total_karyawan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        total_month_transaction = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        judul_total_month_transaction = new javax.swing.JLabel();
        total_shift = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_tahun_linechart = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        setTitle("Dashboard");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 52));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setText("Dashboard");
        jLabel1.setAlignmentX(0.5F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 42));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(version, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLineChart.setBackground(new java.awt.Color(185, 201, 231));
        panelLineChart.setPreferredSize(new java.awt.Dimension(1280, 300));
        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel5.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 1112, 300));

        total_karyawan.setEditable(false);
        total_karyawan.setBackground(new java.awt.Color(185, 201, 231));
        total_karyawan.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        total_karyawan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_karyawan.setBorder(null);
        total_karyawan.setOpaque(false);
        jPanel5.add(total_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 330, 180));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard/Asset 64.png"))); // NOI18N
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel2.setText("Total Karyawan");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        total_month_transaction.setEditable(false);
        total_month_transaction.setBackground(new java.awt.Color(185, 201, 231));
        total_month_transaction.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        total_month_transaction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_month_transaction.setBorder(null);
        total_month_transaction.setOpaque(false);
        jPanel5.add(total_month_transaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 340, 180));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard/Asset 64.png"))); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 340, -1));

        judul_total_month_transaction.setText("Total Transaction Bulan ");
        jPanel5.add(judul_total_month_transaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, -1));

        total_shift.setEditable(false);
        total_shift.setBackground(new java.awt.Color(185, 201, 231));
        total_shift.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        total_shift.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_shift.setBorder(null);
        total_shift.setOpaque(false);
        jPanel5.add(total_shift, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 340, 180));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard/Asset 64.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 340, -1));

        jLabel3.setText("Total Shift");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, -1, -1));

        cmb_tahun_linechart.setBackground(new java.awt.Color(185, 201, 231));
        cmb_tahun_linechart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_tahun_linechartItemStateChanged(evt);
            }
        });
        jPanel5.add(cmb_tahun_linechart, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 153, -1));

        jLabel4.setText("Pilih Tahun");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard/Asset 76.png"))); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, -350, 1970, 1160));

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
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(618, 618, 618)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_tahun_linechartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_tahun_linechartItemStateChanged
        showLineChart(cmb_tahun_linechart.getSelectedItem().toString());
    }//GEN-LAST:event_cmb_tahun_linechartItemStateChanged

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem L_karyawan;
    private javax.swing.JMenuItem L_stok;
    private javax.swing.JMenuItem L_transaksi;
    private javax.swing.JComboBox<String> cmb_tahun_linechart;
    private javax.swing.JMenuItem dt_category;
    private javax.swing.JMenuItem dt_karyawan;
    private javax.swing.JMenuItem dt_makanan;
    private javax.swing.JMenuItem dt_shift;
    private javax.swing.JMenuItem dt_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel judul_total_month_transaction;
    private javax.swing.JMenuItem mn_penjualan;
    private javax.swing.JMenuItem mn_shiftKaryawan;
    private javax.swing.JMenu mndashboard;
    private javax.swing.JMenu mnlaporan;
    public static javax.swing.JMenu mnlogout;
    private javax.swing.JMenu mnmaster;
    private javax.swing.JMenu mntransaction;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JTextField total_karyawan;
    private javax.swing.JTextField total_month_transaction;
    private javax.swing.JTextField total_shift;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
