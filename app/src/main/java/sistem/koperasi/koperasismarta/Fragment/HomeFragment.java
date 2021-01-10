package sistem.koperasi.koperasismarta.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

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
import sistem.koperasi.koperasismarta.adapter.HomeBannerAdapter;
import sistem.koperasi.koperasismarta.adapter.MenuKoperasiAdapter;
import sistem.koperasi.koperasismarta.adapter.TopUpTagihanAdapter;
import sistem.koperasi.koperasismarta.model.HomeBannerModel;
import sistem.koperasi.koperasismarta.model.HomeKoperasiModel;
import sistem.koperasi.koperasismarta.model.TopUpTagihanModel;
import sistem.koperasi.koperasismarta.util.CustomGridView;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MenuKoperasiAdapter.OnItemClickListener,
        TopUpTagihanAdapter.OnItemClickListener{

    final private static String TAG= "HomeFragment";
    private MenuKoperasiAdapter adapterMenu;
    private TopUpTagihanAdapter adapterTopUpTagihan;
    private CustomGridView grdKoperasi;
    private CustomGridView grdTopUp;
    List<HomeKoperasiModel> homesMenu = new ArrayList<>();
    List<TopUpTagihanModel> topUpMenu = new ArrayList<>();

    private SwipeRefreshLayout refreshLayout;
    private ImageView ciutMenuTopUp;
    private ImageView ciutMenuHome;

    //Slider
    private List<HomeBannerModel> list_banner= new ArrayList<>();;
    private ViewPager viewPager;
    private Handler handler = new Handler();
    private Runnable runnableCode = null;
    private HomeBannerAdapter adapter_banner;
    private TextView features_news_title;
    private View lyt_main_content;
    private LinearLayout layout_dots;

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


        lyt_main_content = root.findViewById(R.id.lyt_cart);
        features_news_title = root.findViewById(R.id.featured_news_title);
        layout_dots = root.findViewById(R.id.layout_dots);
        viewPager = root.findViewById(R.id.pager);
        adapter_banner = new HomeBannerAdapter(getActivity(), new ArrayList<HomeBannerModel>());

        //next or prev button image
        ImageButton bt_previous = root.findViewById(R.id.bt_previous);
        ImageButton bt_next = root.findViewById(R.id.bt_next);
        bt_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevAction();
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextAction();
            }
        });
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
        addBanner();


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

    private void addBanner() {
        list_banner.clear();
        HomeBannerModel item1 = new HomeBannerModel(1, 1, getResources().getString(R.string.app_name),
                getResources().getDrawable(R.drawable.pt), "PT");
        HomeBannerModel item2 = new HomeBannerModel(1, 1, "Promo Bpjs",
                getResources().getDrawable(R.drawable.bpjs), "Promo");
        list_banner.add(item1);
        list_banner.add(item2);
        displayResultData(list_banner);
    }

    private void prevAction() {
        handler.removeCallbacksAndMessages(null);
        int pos = viewPager.getCurrentItem();
        pos = pos - 1;
        if (pos < 0) pos = adapter_banner.getCount();
        viewPager.setCurrentItem(pos);
    }

    private void nextAction() {
        handler.removeCallbacksAndMessages(null);
        int pos = viewPager.getCurrentItem();
        pos = pos + 1;
        if (pos >= adapter_banner.getCount()) pos = 0;
        viewPager.setCurrentItem(pos);
    }

    private void displayResultData(List<HomeBannerModel> items) {
        adapter_banner.setItems(items);
        viewPager.setAdapter(adapter_banner);

        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = getFeaturedNewsImageHeight(getActivity());
        viewPager.setLayoutParams(params);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        features_news_title.setText(adapter_banner.getItem(0).getSlider_judul());
        addBottomDots(layout_dots, adapter_banner.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                HomeBannerModel cur = adapter_banner.getItem(pos);
                features_news_title.setText(cur.getSlider_judul());
                addBottomDots(layout_dots, adapter_banner.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapter_banner.getCount());
        adapter_banner.setOnItemClickListener(new HomeBannerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, HomeBannerModel obj) {
                Snackbar.make(view, obj.getSlider_judul()+" clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
        lyt_main_content.setVisibility(View.VISIBLE);
    }

    private void startAutoSlider(final int count) {
        runnableCode = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnableCode, 5000);
            }
        };
        handler.postDelayed(runnableCode, 5000);
    }
    @Override
    public void onDestroy() {
        if (runnableCode != null) handler.removeCallbacks(runnableCode);
        super.onDestroy();
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 10;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.ic_shape);
            dots[i].setColorFilter(ContextCompat.getColor(getActivity(), R.color.darkOverlaySoft));
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getActivity(), R.color.blue));
        }
    }

    private static int getFeaturedNewsImageHeight(Activity activity) {
        float w_ratio = 2, h_ratio = 1; // we use 2:1 ratio
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float screenWidth = displayMetrics.widthPixels - 10;
        float resHeight = (screenWidth * h_ratio) / w_ratio;
        return Math.round(resHeight);
    }



    private void addMenuKoperasi() {
        homesMenu.clear();
//        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.tagihan_bpjs, "Simpanan Koperasi"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pinjaman, "Pinjaman & Bayar Pinjaman"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_overview_pages_1, "Pengajuan"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.icons_tags, "Berita"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.message, "Inbox"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.ic_toko, "Toko Online"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pngwave, "Pengurus"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.app, "More"));
        adapterMenu.addList(homesMenu);
        grdKoperasi.setAdapter(adapterMenu);
        refreshLayout.setRefreshing(false);
    }

    private void addMenuKoperasiLagi() {
        homesMenu.clear();
//        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.tagihan_bpjs, "Simpanan Koperasi"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.pinjaman, "Pinjaman & Bayar Pinjaman"));
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
        topUpMenu.add(new TopUpTagihanModel(R.drawable.cash, "Multi Finance"));
        topUpMenu.add(new TopUpTagihanModel(R.drawable.pubg, "Pubg Mobile"));
        adapterTopUpTagihan.addList(topUpMenu);
        grdTopUp.setAdapter(adapterTopUpTagihan);
        refreshLayout.setRefreshing(false);
        ciutMenuTopUp.setVisibility(View.VISIBLE);
        grdKoperasi.requestFocus();
    }

    @Override
    public void onItemClicked(int position, HomeKoperasiModel item) {
        Intent intent;
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
        }else {
            Log.e(TAG,item.title+" clicked");
            Toast.makeText(getActivity(),item.title+" clicked",Toast.LENGTH_SHORT).show();
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

        if (runnableCode != null) handler.removeCallbacks(runnableCode);
        addBanner();
    }

}