package sistem.koperasi.koperasismarta.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.activity.AboutUsActivity;
import sistem.koperasi.koperasismarta.activity.BeritaActivity;
import sistem.koperasi.koperasismarta.activity.FAQActivity;
import sistem.koperasi.koperasismarta.activity.InboxActivity;
import sistem.koperasi.koperasismarta.activity.KalkulatorActivity;
import sistem.koperasi.koperasismarta.activity.PengajuanActivity;
import sistem.koperasi.koperasismarta.activity.PengurusActivity;
import sistem.koperasi.koperasismarta.activity.PinjamanActivity;
import sistem.koperasi.koperasismarta.activity.SimpananKoperasiActivity;
import sistem.koperasi.koperasismarta.activity.TokoOnlineActivity;
import sistem.koperasi.koperasismarta.activity.UnduhanActivity;
import sistem.koperasi.koperasismarta.adapter.MenuKoperasiAdapter;
import sistem.koperasi.koperasismarta.adapter.TopUpTagihanAdapter;
import sistem.koperasi.koperasismarta.model.HomeKoperasiModel;
import sistem.koperasi.koperasismarta.model.TopUpTagihanModel;
import sistem.koperasi.koperasismarta.util.CustomGridView;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MenuKoperasiAdapter.OnItemClickListener,
        TopUpTagihanAdapter.OnItemClickListener{

    final static String TAG= "HomeFragment";
    private MenuKoperasiAdapter adapterMenu;
    private TopUpTagihanAdapter adapterTopUpTagihan;
    private CustomGridView grdKoperasi;
    private CustomGridView grdTopUp;
    List<HomeKoperasiModel> homesMenu = new ArrayList<>();
    List<TopUpTagihanModel> topUpMenu = new ArrayList<>();

    private SwipeRefreshLayout refreshLayout;
    private ImageView ciutMenuTopUp;
    private ImageView ciutMenuHome;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        grdKoperasi = root.findViewById(R.id.grdKoperasi);
        grdTopUp = root.findViewById(R.id.grdTopUpTagihan);
        refreshLayout = root.findViewById(R.id.refresh);
        ciutMenuTopUp = root.findViewById(R.id.ciutKanTopUp);
        ciutMenuHome = root.findViewById(R.id.ciutKanMenu);
        ciutMenuHome.setVisibility(View.GONE);
        ciutMenuTopUp.setVisibility(View.GONE);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManagerKategori = new LinearLayoutManager(getActivity());
        adapterMenu = new MenuKoperasiAdapter(getContext(), this);
        adapterTopUpTagihan = new TopUpTagihanAdapter(getContext(), this);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        addMenuKoperasi();
        addMenuTopUpTagihan();


        ciutMenuTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenuTopUpTagihan();
                ciutMenuTopUp.setVisibility(View.GONE);
            }
        });

        ciutMenuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenuKoperasi();
                ciutMenuHome.setVisibility(View.GONE);
            }
        });
    }


    private void addMenuKoperasi() {
        homesMenu.clear();
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.tagihan_bpjs, "Simpanan Koperasi"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pinjaman, "Pinjaman"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_overview_pages_1, "Pengajuan"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_tags, "Berita"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.message, "Inbox"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.ic_toko, "Toko Online"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.app, "More"));
        adapterMenu.addList(homesMenu);
        grdKoperasi.setAdapter(adapterMenu);
        refreshLayout.setRefreshing(false);
    }

    private void addMenuKoperasiLagi() {
        homesMenu.clear();
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.tagihan_bpjs, "Simpanan Koperasi"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pinjaman, "Pinjaman"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_overview_pages_1, "Pengajuan"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_tags, "Berita"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.message, "Inbox"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.ic_toko, "Toko Online"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pngwave, "Pengurus"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.calcu, "Kalkulator"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.us, "About US"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dwh, "Unduhan"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.faq, "FAQ"));
        adapterMenu.addList(homesMenu);
        grdKoperasi.setAdapter(adapterMenu);
        refreshLayout.setRefreshing(false);
        ciutMenuHome.setVisibility(View.VISIBLE);
        grdKoperasi.requestFocus();
    }


    private void addMenuTopUpTagihan() {
        topUpMenu.clear();
        topUpMenu.add(new TopUpTagihanModel(R.drawable.bpjs, "Tagihan BPJS"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_listrik, "Tagihan Listrik"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.odo, "OVO"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.paypay, "Gopay"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_air, "Tagihan PDAM"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_telpon, "Telepon"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.wifi, "Voucher Wifi"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.app, "More"));
        adapterTopUpTagihan.addList(topUpMenu);
        grdTopUp.setAdapter(adapterTopUpTagihan);
        refreshLayout.setRefreshing(false);
    }

    private void addMenuTopUpTagihanLagi() {
        topUpMenu.clear();
        topUpMenu.add(new TopUpTagihanModel(R.drawable.dashboards, "DashBoard"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.bpjs, "Tagihan BPJS"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_listrik, "Tagihan Listrik"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.odo, "OVO"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.paypay, "Gopay"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_air, "Tagihan PDAM"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.wifi, "Voucher Wifi"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_telpon, "Telepon"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.game, "Voucher Game"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_multi, "E-money"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.token_pln, "Token Listrik"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.etoll, "E-Toll"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.freefire, "Diamond Free Fire"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.gplay, "Voucher Google Play"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.asuransi, "Asuransi"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_tv, "Tagihan Tv"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.bpjs, "Pulsa"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.itunes, "Itunes Gift"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.tagihan_multi1, "Zakat"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.cash, "Multi finance"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.pubg, "Pubg Mobile"));
        adapterTopUpTagihan.addList(topUpMenu);
        grdTopUp.setAdapter(adapterTopUpTagihan);
        refreshLayout.setRefreshing(false);
        ciutMenuTopUp.setVisibility(View.VISIBLE);
        grdKoperasi.requestFocus();
    }

    @Override
    public void onItemClicked(int position, HomeKoperasiModel item) {
        Intent intent = null;
        switch (item.title) {
            case "More": {
                addMenuKoperasiLagi();
            }
            break;
            case "DashBoard": {
                Log.e(TAG, item.title + " clicked");
            }
            break;
            case "Simpanan Koperasi": {
                intent = new Intent(getActivity(), SimpananKoperasiActivity.class);
                startActivity(intent);
            }
            break;
            case "Pinjaman": {
                intent = new Intent(getActivity(), PinjamanActivity.class);
                startActivity(intent);
            }
            break;
            case "Pengajuan": {
                intent = new Intent(getActivity(), PengajuanActivity.class);
                startActivity(intent);
            }
            break;
            case "Berita": {
                intent = new Intent(getActivity(), BeritaActivity.class);
                startActivity(intent);
            }
            break;
            case "Inbox": {
                intent = new Intent(getActivity(), InboxActivity.class);
                startActivity(intent);
            }
            break;
            case "Toko Online": {
                intent = new Intent(getActivity(), TokoOnlineActivity.class);
                startActivity(intent);
            }
            break;
            case "Pengurus": {
                intent = new Intent(getActivity(), PengurusActivity.class);
                startActivity(intent);
            }
            break;
            case "Kalkulator": {
                intent = new Intent(getActivity(), KalkulatorActivity.class);
                startActivity(intent);
            }
            break;
            case "About US": {
                intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
            break;
            case "Unduhan": {
                intent = new Intent(getActivity(), UnduhanActivity.class);
                startActivity(intent);
            }
            break;
            case "FAQ": {
                intent = new Intent(getActivity(), FAQActivity.class);
                startActivity(intent);
            }
            break;

        }
    }

    @Override
    public void onItemClicked(int position, TopUpTagihanModel item) {
        if (item.title.equalsIgnoreCase("More")){
            addMenuTopUpTagihanLagi();
        }
    }

    @Override
    public void onRefresh() {
        if (ciutMenuTopUp.getVisibility() == View.VISIBLE){
            addMenuTopUpTagihanLagi();
        }else {
            addMenuTopUpTagihan();
        }

        if (ciutMenuHome.getVisibility() == View.VISIBLE){
            addMenuKoperasiLagi();
        }else {
            addMenuKoperasi();
        }
    }
}