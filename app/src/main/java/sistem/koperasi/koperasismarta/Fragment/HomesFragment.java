package sistem.koperasi.koperasismarta.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.adapter.MenuKoperasiAdapter;
import sistem.koperasi.koperasismarta.model.HomeKoperasiModel;

public class HomesFragment extends Fragment implements MenuKoperasiAdapter.OnItemClickListener{

    private ImageButton btnDown, btnUp, btnDown2, btnUp2;
    private TextView textMore, textMore2, textLess, textLess2;

    private LinearLayout panel1, panel2;
    private MenuKoperasiAdapter adapter;
    private GridView grdKoperasi;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_homes, container, false);

        grdKoperasi = root.findViewById(R.id.grdKoperasi);
        btnDown = root.findViewById(R.id.btnDown);
        btnDown2 = root.findViewById(R.id.btnDown2);
        btnUp = root.findViewById(R.id.btnUp);
        btnUp2 = root.findViewById(R.id.btnUp2);
        textMore = root.findViewById(R.id.textMore);
        textMore2 = root.findViewById(R.id.textMore2);
        textLess = root.findViewById(R.id.textLess);
        textLess2 = root.findViewById(R.id.textLess2);
        panel1 = root.findViewById(R.id.panel1);
        panel2 = root.findViewById(R.id.panel2);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel1.setVisibility(View.VISIBLE);
                btnDown.setVisibility(View.GONE);
                textMore.setVisibility(View.GONE);
                btnUp.setVisibility(View.VISIBLE);
                textLess.setVisibility(View.VISIBLE);
            }
        });

        btnDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel2.setVisibility(View.VISIBLE);
                btnDown2.setVisibility(View.GONE);
                textMore2.setVisibility(View.GONE);
                btnUp2.setVisibility(View.VISIBLE);
                textLess2.setVisibility(View.VISIBLE);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel1.setVisibility(View.GONE);
                btnDown.setVisibility(View.VISIBLE);
                textMore.setVisibility(View.VISIBLE);
                btnUp.setVisibility(View.GONE);
                textLess.setVisibility(View.GONE);
            }
        });

        btnUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel2.setVisibility(View.GONE);
                btnDown2.setVisibility(View.VISIBLE);
                textMore2.setVisibility(View.VISIBLE);
                btnUp2.setVisibility(View.GONE);
                textLess2.setVisibility(View.GONE);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LinearLayoutManager layoutManagerKategori = new LinearLayoutManager(getActivity());
        adapter = new MenuKoperasiAdapter(getContext(), this);
        addMenuKoperasi();
    }

    private void addMenuKoperasi() {
        List<HomeKoperasiModel> homesMenu = new ArrayList<>();
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        homesMenu.add(new HomeKoperasiModel(R.drawable.dashboards, "DashBoard"));
        adapter.addList(homesMenu);
        grdKoperasi.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(int position, HomeKoperasiModel item) {

    }
}