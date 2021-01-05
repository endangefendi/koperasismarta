package sistem.koperasi.koperasismarta.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.model.HomeKoperasiModel;

public class MenuKoperasiAdapter extends BaseAdapter {

    private Context context;
    private List<HomeKoperasiModel> list;
    private OnItemClickListener listener;

    public MenuKoperasiAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if (list == null)return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null)return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.item_menu_koperasi, parent, false);
        final HomeKoperasiModel item = list.get(position);
        ImageView imgView = view.findViewById(R.id.iv_icon);
        TextView txtTitle =  view.findViewById(R.id.txt_title);
        imgView.setImageResource(item.image);
        txtTitle.setText(item.title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) listener.onItemClicked(position, item);
            }
        });

        return view;
    }

    public void addList(List<HomeKoperasiModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClicked(int position, HomeKoperasiModel item);
    }

}

