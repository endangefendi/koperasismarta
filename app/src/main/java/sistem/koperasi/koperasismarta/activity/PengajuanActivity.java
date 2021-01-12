package sistem.koperasi.koperasismarta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.adapter.CustomSpinnerRpAdapter;
import sistem.koperasi.koperasismarta.util.Constant;

public class PengajuanActivity extends AppCompatActivity {
    private static String TAG = "PengajuanActivity";
    public TextView tv_ajukan ;
    public TextView tv_admin_fee;
    public TextView tv_biaya_lain;
    public TextView tv_jumlah_diterima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_ajukan = findViewById(R.id.jumlah_ajuan);
        tv_admin_fee = findViewById(R.id.jumlah_admin);
        tv_biaya_lain = findViewById(R.id.jumlah_lain);
        tv_jumlah_diterima = findViewById(R.id.jumlah_diterima);

        Spinner spinner_tenor = findViewById(R.id.list_tenor);
        ArrayAdapter adapter_tenor = ArrayAdapter.createFromResource(
                this,
                R.array.daftar_tenor,
                R.layout.spiner_item_tenor);
        adapter_tenor.setDropDownViewResource(R.layout.spiner_item_tenor);
        spinner_tenor.setAdapter(adapter_tenor);

        Spinner spinner_tujuan = findViewById(R.id.list_tujuan);
        ArrayAdapter adapter_tujuan = ArrayAdapter.createFromResource(
                this,
                R.array.daftar_tujuan,
                R.layout.spiner_item_tenor);

        adapter_tujuan.setDropDownViewResource(R.layout.spiner_item_tenor);
        spinner_tujuan.setAdapter(adapter_tujuan);

        Spinner spinner_jumlah = findViewById(R.id.list_jumlah);

        final long[] countryNames={1000000,5000000,10000000,15000000,115000000};
        CustomSpinnerRpAdapter customSpinnerRpAdapter =new CustomSpinnerRpAdapter(getApplicationContext(),countryNames);
        spinner_jumlah.setAdapter(customSpinnerRpAdapter);
        spinner_jumlah.setSelected(false);
//        spinner_jumlah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long nominal) {
//                tv_ajukan.setText(Constant.formatRupiah(nominal));
//                if (nominal<=1000000){
//                    long b = 98 * nominal;
//                    long totaldiskon = b / 100;
//                    long biaya_admin = nominal - totaldiskon;
//                    tv_admin_fee.setText(Constant.formatRupiah(biaya_admin));
//                    tv_biaya_lain.setText(Constant.formatRupiah(biaya_admin));
//                    tv_jumlah_diterima.setText(Constant.formatRupiah(nominal-biaya_admin));
//                }
//            }
//        });

//        spinner_jumlah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long nominal) {
//                Log.e(TAG, i+ "=i------"+nominal+" nominal");
//                tv_ajukan.setText(Constant.formatRupiah(nominal));
//                if (nominal<=1000000){
//                    long b = 98 * nominal;
//                    long totaldiskon = b / 100;
//                    long biaya_admin = nominal - totaldiskon;
//                    tv_admin_fee.setText(Constant.formatRupiah(biaya_admin));
//                    tv_biaya_lain.setText(Constant.formatRupiah(biaya_admin));
//                    tv_jumlah_diterima.setText(Constant.formatRupiah(nominal-biaya_admin));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
////                long n = 0;
////                tv_ajukan.setText(Constant.formatRupiah(n));
////                tv_admin_fee.setText(Constant.formatRupiah(n));
////                tv_biaya_lain.setText(Constant.formatRupiah(n));
//            }
//        });


        spinner_jumlah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                long nominal = countryNames[position];
                Log.e(TAG, position+ "=i------"+nominal+" nominal");
                tv_ajukan.setText(Constant.formatRupiah(nominal));
                if (nominal!=0){
                    long b = 98 * nominal;
                    long totaldiskon = b / 100;
                    long biaya_admin = nominal - totaldiskon;
                    tv_admin_fee.setText(Constant.formatRupiah(biaya_admin));
                    tv_biaya_lain.setText(Constant.formatRupiah(biaya_admin));
                    tv_jumlah_diterima.setText(Constant.formatRupiah(nominal-biaya_admin-biaya_admin));
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent){
                long n = 0;
                tv_ajukan.setText(Constant.formatRupiah(n));
                tv_admin_fee.setText(Constant.formatRupiah(n));
                tv_biaya_lain.setText(Constant.formatRupiah(n));
                tv_jumlah_diterima.setText(Constant.formatRupiah(n));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}