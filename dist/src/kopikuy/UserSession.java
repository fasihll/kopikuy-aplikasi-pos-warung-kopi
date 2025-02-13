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
public class UserSession {
    private static String userLogin,KaryawanLogin,Version;
    
    public static String GetVersion(){
        return "Kopikuy-V.1.1";
    }
    
    public static void setUserLogin(String userLogin){
        UserSession.userLogin = userLogin;
    }
    
    public static String getUserLogin(){
        return userLogin;
    }
    
    public static void setKaryawanLogin(String karyawanLogin){
        UserSession.KaryawanLogin = karyawanLogin;
    }
    
    public static String getKaryawanLogin(){
        return KaryawanLogin;
    }
}
