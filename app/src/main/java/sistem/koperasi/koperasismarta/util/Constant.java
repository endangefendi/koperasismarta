package sistem.koperasi.koperasismarta.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Constant {

    public static String formatRupiah(Long number){
        NumberFormat formatRupiah = new DecimalFormat("#,###");
        double nominal = Double.valueOf(number);
        return "Rp. "+formatRupiah.format(nominal);
    }
}
