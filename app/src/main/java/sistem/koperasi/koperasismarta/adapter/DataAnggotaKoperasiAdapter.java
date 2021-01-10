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
import sistem.koperasi.koperasismarta.util.Constant;

public class DataAnggotaKoperasiAdapter extends RecyclerView.Adapter<DataAnggotaKoperasiAdapter.ViewHolder>{

    private Context context;
    private List<DataAnggotaKoperasiModel> list;
    private OnItemClickListener listener;

    public DataAnggotaKoperasiAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no;
        TextView tv_nama_anggota;
        TextView tv_nama_instalasi;
        TextView tv_date;
        TextView tv_simpanan_wajib;
        TextView tv_simpanan_pokok;
        TextView tv_simpanan_sukarela;
        public ViewHolder(View v) {
            super(v);
            tv_no = v.findViewById(R.id.no_urut);
            tv_nama_anggota = v.findViewById(R.id.nama_anggota);
            tv_nama_instalasi = v.findViewById(R.id.nama_instlasali);
            tv_date = v.findViewById(R.id.date);
            tv_simpanan_wajib = v.findViewById(R.id.simpanan_wajib);
            tv_simpanan_pokok = v.findViewById(R.id.simpanan_pokok);
            tv_simpanan_sukarela = v.findViewById(R.id.simpanan_sukarela);
        }
    }
    @NonNull
    @Override
    public DataAnggotaKoperasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item__data_anggota, parent, false);
        DataAnggotaKoperasiAdapter.ViewHolder vh = new DataAnggotaKoperasiAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAnggotaKoperasiAdapter.ViewHolder holder, final int position) {
        final DataAnggotaKoperasiModel item = list.get(position);

        holder.tv_no.setText("#"+item.getNo());
        holder.tv_nama_anggota.setText(item.getNama());
        holder.tv_nama_instalasi.setText(item.getInstalasi());
        holder.tv_date.setText(item.getDate());
        holder.tv_simpanan_wajib.setText(Constant.formatRupiah(item.getSimpanan_wajib()));
        holder.tv_simpanan_pokok.setText(Constant.formatRupiah(item.getSimpanan_pokok()));
        holder.tv_simpanan_sukarela.setText(Constant.formatRupiah(item.getSimpanan_sukarela()));

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
    public void addList(List<DataAnggotaKoperasiModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClicked(int position, DataAnggotaKoperasiModel item);
    }
    public void addOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}

