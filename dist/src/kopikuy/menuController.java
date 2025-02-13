/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopikuy;

/**
 *
 * @author FASIH
 */
public class menuController {
    
    //------------------------------------master
    public static void data_makanan(){
        Dt_makanan dm = new Dt_makanan();
        dm.setVisible(true);
        dm.pack();
        dm.setLocationRelativeTo(null);
        dm.setDefaultCloseOperation(Dt_makanan.EXIT_ON_CLOSE);
        
    }
    public static void data_category(){
        Dt_category dc = new Dt_category();
        dc.setVisible(true);
        dc.pack();
        dc.setLocationRelativeTo(null);
        dc.setDefaultCloseOperation(Dt_category.EXIT_ON_CLOSE); 
    }
    public static void data_shift(){
        Dt_shift ds = new Dt_shift();
        ds.setVisible(true);
        ds.pack();
        ds.setLocationRelativeTo(null);
        ds.setDefaultCloseOperation(Dt_shift.EXIT_ON_CLOSE); 
    }
    public static void data_karyawan(){
        Dt_karyawan dk = new Dt_karyawan();
        dk.setVisible(true);
        dk.pack();
        dk.setLocationRelativeTo(null);
        dk.setDefaultCloseOperation(Dt_karyawan.EXIT_ON_CLOSE); 
    }
    public static void data_user(){
        Dt_users du = new Dt_users();
        du.setVisible(true);
        du.pack();
        du.setLocationRelativeTo(null);
        du.setDefaultCloseOperation(Dt_users.EXIT_ON_CLOSE); 
    }
    
    
    //------------------------------------transaction
    public static void penjualan(){
        Penjualan pj = new Penjualan();
        pj.setVisible(true);
        pj.pack();
        pj.setLocationRelativeTo(null);
        pj.setDefaultCloseOperation(Penjualan.EXIT_ON_CLOSE); 
    }
    public static void shiftKaryawan(){
        Shift_karyawan sk = new Shift_karyawan();
        sk.setVisible(true);
        sk.pack();
        sk.setLocationRelativeTo(null);
        sk.setDefaultCloseOperation(Shift_karyawan.EXIT_ON_CLOSE); 
    }
    
    //------------------------------------laporan
    public static void L_transaksi(){
        L_transaksi sk = new L_transaksi();
        sk.setVisible(true);
        sk.pack();
        sk.setLocationRelativeTo(null);
        sk.setDefaultCloseOperation(L_transaksi.EXIT_ON_CLOSE); 
    }
    public static void L_karyawan(){
        L_karyawan sk = new L_karyawan();
        sk.setVisible(true);
        sk.pack();
        sk.setLocationRelativeTo(null);
        sk.setDefaultCloseOperation(L_karyawan.EXIT_ON_CLOSE); 
    }
    public static void L_stok(){
        L_stokMakanan sk = new L_stokMakanan();
        sk.setVisible(true);
        sk.pack();
        sk.setLocationRelativeTo(null);
        sk.setDefaultCloseOperation(L_stokMakanan.EXIT_ON_CLOSE); 
    }
}
