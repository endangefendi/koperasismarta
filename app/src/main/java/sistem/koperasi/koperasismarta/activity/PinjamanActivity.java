package sistem.koperasi.koperasismarta.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.adapter.DataAnggotaKoperasiAdapter;
import sistem.koperasi.koperasismarta.adapter.DataPinjamanAdapter;
import sistem.koperasi.koperasismarta.adapter.HistoriPembayaranAdapter;
import sistem.koperasi.koperasismarta.model.DataAnggotaKoperasiModel;
import sistem.koperasi.koperasismarta.model.DataPinjamanModel;
import sistem.koperasi.koperasismarta.model.HistoriPembayaranModel;
import sistem.koperasi.koperasismarta.util.Constant;

public class PinjamanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
            DataPinjamanAdapter.OnItemClickListener,HistoriPembayaranAdapter.OnItemClickListener{
        final private static String TAG = "PinjamanActivity";
        private SwipeRefreshLayout refreshLayout;
        private List<DataPinjamanModel> list_data_pinjaman= new ArrayList<>();;
        private DataPinjamanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initialVariabel();

        addDataList();
    }

    private void addDataList() {
        list_data_pinjaman.clear();
        list_data_pinjaman.add(new DataPinjamanModel(1,12, "Pinjaman Pendidikan", 10000000, "2 Feb 2021", 1200000) );
        list_data_pinjaman.add(new DataPinjamanModel(2, 6, "Pinjaman Pendidikan", 5000000, "2 Feb 2021", 1000000) );
        list_data_pinjaman.add(new DataPinjamanModel(3, 12, "Pinjaman Biasa", 10000000, "2 Feb 2021", 1000000) );
        list_data_pinjaman.add(new DataPinjamanModel(4, 6, "Pinjaman Biasa", 5000000, "2 Feb 2021", 1000000) );
        list_data_pinjaman.add(new DataPinjamanModel(5, 12, "Pinjaman Usaha", 10000000, "2 Feb 2021", 1000000) );
        list_data_pinjaman.add(new DataPinjamanModel(6, 6, "Pinjaman Usaha", 5000000, "2 Feb 2021", 1000000) );
        adapter.addList(list_data_pinjaman);
        refreshLayout.setRefreshing(false);
    }

    private void initialVariabel() {

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        RecyclerView recyclerView = findViewById(R.id.list_data_anggota);
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new DataPinjamanAdapter(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    List<HistoriPembayaranModel> list_histori ;
    HistoriPembayaranAdapter adapter_histori;
    ProgressDialog progressDialog;
    TextView text_tenor, text_setoran, text_sisa;
    private void popUpProfile(DataPinjamanModel itemPinjaman) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        final View dialogView = inflater.inflate(R.layout.dialog_detail_pinjaman, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(true);
        b.setCancelable(false);
        b.show();

        text_tenor = dialogView.findViewById(R.id.tenor);
        text_setoran = dialogView.findViewById(R.id.angsuran);
        text_sisa = dialogView.findViewById(R.id.sisa_pembayaran);

        setViewPopUp(12, 1500000,4000000);
        Button btn_bayar = dialogView.findViewById(R.id.bayar);
        btn_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PinjamanActivity.this,"Menuju Proses Bayar",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView iv_close = dialogView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        RecyclerView recyclerView = dialogView.findViewById(R.id.list_data_histori);
        list_histori =new ArrayList<>();
        adapter_histori= new HistoriPembayaranAdapter(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_histori);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Preparing data..");
        progressDialog.show();

        load_histori_bayar(itemPinjaman);
    }

    private void setViewPopUp(int tenor, long setoran, long sisa) {
        text_tenor.setText(tenor+" Bulan");
        text_setoran.setText(Constant.formatRupiah(setoran));
        text_sisa.setText(Constant.formatRupiah(sisa));
    }

    private void load_histori_bayar(DataPinjamanModel itemPinjaman) {
        list_histori.clear();
        list_histori.add(new HistoriPembayaranModel("1/12", 0,"dibayar"));
        list_histori.add(new HistoriPembayaranModel("2/12", 0,"dibayar"));
        list_histori.add(new HistoriPembayaranModel("3/12", 0,"dibayar"));
        adapter_histori.addList(list_histori);
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
        addDataList();
    }

    @Override
    public void onItemClicked(int position, DataPinjamanModel itemPinjaman) {
        popUpProfile(itemPinjaman);
    }

    @Override
    public void onItemClicked(int position, HistoriPembayaranModel item) {

    }
}