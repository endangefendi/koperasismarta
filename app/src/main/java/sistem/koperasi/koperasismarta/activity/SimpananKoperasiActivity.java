package sistem.koperasi.koperasismarta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.adapter.DataAnggotaKoperasiAdapter;
import sistem.koperasi.koperasismarta.model.DataAnggotaKoperasiModel;
import sistem.koperasi.koperasismarta.util.Constant;

public class SimpananKoperasiActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener,
        DataAnggotaKoperasiAdapter.OnItemClickListener {
    final private static String TAG = "SimpananKoperasiActivity";
    private SwipeRefreshLayout refreshLayout;
    private List<DataAnggotaKoperasiModel> list_data_anggota= new ArrayList<>();;
    private DataAnggotaKoperasiAdapter adapter;
    private TextView tv_total_wajib, tv_total_pokok, tv_total_sukarela, tv_shu_tahun1, tv_shu_tahun2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpanan_koperasi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initialVariabel();

        addDataList();
        setTotal(2135000,100000,0,0,0);
    }

    private void setTotal(long wajib, long pokok, long sukarela, long shu1, long shu2) {
        tv_total_wajib.setText(Constant.formatRupiah(wajib));
        tv_total_pokok.setText(Constant.formatRupiah(pokok));
        tv_total_sukarela.setText(Constant.formatRupiah(sukarela));
        tv_shu_tahun1.setText(Constant.formatRupiah(shu1));
        tv_shu_tahun1.setText(Constant.formatRupiah(shu2));
    }

    private void addDataList() {
        list_data_anggota.clear();
        list_data_anggota.add(new DataAnggotaKoperasiModel(1,"nama", "instalasi", "date", 50000, 0, 0) );
        list_data_anggota.add(new DataAnggotaKoperasiModel(2, "nama", "instalasi", "date", 50000, 0, 0) );
        list_data_anggota.add(new DataAnggotaKoperasiModel(3, "nama", "instalasi", "date", 50000, 0,0) );
        list_data_anggota.add(new DataAnggotaKoperasiModel(4, "nama", "instalasi", "date", 50000, 0,0) );
        list_data_anggota.add(new DataAnggotaKoperasiModel(5, "nama", "instalasi", "date", 50000, 0,0) );
        list_data_anggota.add(new DataAnggotaKoperasiModel(6, "nama", "instalasi", "date", 50000, 0,0) );
        adapter.addList(list_data_anggota);
        refreshLayout.setRefreshing(false);
    }

    private void initialVariabel() {
        tv_shu_tahun1 = findViewById(R.id.shu_tahun1);
        tv_shu_tahun2 = findViewById(R.id.shu_tahun2);
        tv_total_pokok = findViewById(R.id.total_pokok);
        tv_total_wajib = findViewById(R.id.total_wajib);
        tv_total_sukarela = findViewById(R.id.total_sukarela);

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        RecyclerView recyclerView = findViewById(R.id.list_data_anggota);
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new DataAnggotaKoperasiAdapter(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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
    public void onItemClicked(int position, DataAnggotaKoperasiModel item) {
        Toast.makeText(this, item.getNama()+" Clicekd", Toast.LENGTH_SHORT).show();
    }
}