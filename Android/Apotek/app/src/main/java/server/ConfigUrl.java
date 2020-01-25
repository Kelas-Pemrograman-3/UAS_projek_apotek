package server;

public class ConfigUrl {
  public static String baseurl = "http://192.168.43.243:5000";
//    public static String baseurl = "http://192.168.1.64:5000";

//      USER
    public static String register = baseurl + "/user/register";
    public static String login = baseurl + "/user/login";

//    OBAT
//    public static String postobat = baseurl + "/obat/postobat";
    public static String getallobat = baseurl + "/obat/getallobat";

//    PESAN OBAT
    public static String postpesanan = baseurl + "/pesanan/postpesanan";
    public static String getallpesanan = baseurl + "/pesanan/getallpesanan";
    public static String deletepesanan = baseurl + "/pesanan/deletepesanan";
    public static String updatepesanan = baseurl + "/pesanan/updatepesanan";

}
