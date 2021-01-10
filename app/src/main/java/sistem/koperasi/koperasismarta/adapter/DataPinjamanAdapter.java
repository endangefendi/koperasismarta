package sistem.koperasi.koperasismarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.model.DataAnggotaKoperasiModel;
import sistem.koperasi.koperasismarta.model.DataPinjamanModel;
import sistem.koperasi.koperasismarta.util.Constant;

public class DataPinjamanAdapter extends RecyclerView.Adapter<DataPinjamanAdapter.ViewHolder>{

    private Context context;
    private List<DataPinjamanModel> list;
    private OnItemClickListener listener;

    public DataPinjamanAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no;
        TextView tv_jenis_pinjaman;
        TextView tv_jumlah_pinjaman;
        TextView tv_tenor;
        TextView tv_angsuran;
        TextView tv_jatuh_tempo;
        public ViewHolder(View v) {
            super(v);
            tv_no = v.findViewById(R.id.no_urut);
            tv_jenis_pinjaman = v.findViewById(R.id.jenis_pinjaman);
            tv_jumlah_pinjaman = v.findViewById(R.id.jumlah_pinjaman);
            tv_tenor = v.findViewById(R.id.tenor);
            tv_angsuran = v.findViewById(R.id.angsuran);
            tv_jatuh_tempo = v.findViewById(R.id.jatuh_tempo);
        }
    }
    @NonNull
    @Override
    public DataPinjamanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_pinjaman, parent, false);
        DataPinjamanAdapter.ViewHolder vh = new DataPinjamanAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataPinjamanAdapter.ViewHolder holder, final int position) {
        final DataPinjamanModel item = list.get(position);

        holder.tv_no.setText("#"+item.getNo());
        holder.tv_jenis_pinjaman.setText(item.getjenis_pinjaman());
        holder.tv_jumlah_pinjaman.setText(Constant.formatRupiah(item.getJumlah_pinjaman()));
        holder.tv_tenor.setText(String.valueOf(item.getTenor()));
        holder.tv_angsuran.setText(Constant.formatRupiah(item.getAngsuran()));
        holder.tv_jatuh_tempo.setText(item.getTanggal_tempo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onItemClicked(position, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }
    public void addList(List<DataPinjamanModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClicked(int position, DataPinjamanModel item);
    }
    public void addOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}

