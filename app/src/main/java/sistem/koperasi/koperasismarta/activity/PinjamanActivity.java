package sistem.koperasi.koperasismarta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.adapter.DataAnggotaKoperasiAdapter;
import sistem.koperasi.koperasismarta.adapter.DataPinjamanAdapter;
import sistem.koperasi.koperasismarta.model.DataAnggotaKoperasiModel;
import sistem.koperasi.koperasismarta.model.DataPinjamanModel;

public class PinjamanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
            DataPinjamanAdapter.OnItemClickListener {
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
    public void onItemClicked(int position, DataPinjamanModel item) {

    }
}