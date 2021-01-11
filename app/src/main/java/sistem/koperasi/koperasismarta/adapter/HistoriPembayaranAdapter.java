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
import sistem.koperasi.koperasismarta.model.DataPinjamanModel;
import sistem.koperasi.koperasismarta.model.HistoriPembayaranModel;
import sistem.koperasi.koperasismarta.util.Constant;

public class HistoriPembayaranAdapter extends RecyclerView.Adapter<HistoriPembayaranAdapter.ViewHolder>{

    private Context context;
    private List<HistoriPembayaranModel> list;
    private OnItemClickListener listener;

    public HistoriPembayaranAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenor;
        TextView tv_denda;
        TextView tv_jatuh_tempo;
        public ViewHolder(View v) {
            super(v);
            tv_tenor = v.findViewById(R.id.tenor);
            tv_denda = v.findViewById(R.id.denda);
            tv_jatuh_tempo = v.findViewById(R.id.tgl_tempo);
        }
    }
    @NonNull
    @Override
    public HistoriPembayaranAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_histori_pembayaran, parent, false);
        HistoriPembayaranAdapter.ViewHolder vh = new HistoriPembayaranAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriPembayaranAdapter.ViewHolder holder, final int position) {
        final HistoriPembayaranModel item = list.get(position);

        holder.tv_tenor.setText(item.getTenor());
        holder.tv_denda.setText(Constant.formatRupiah(item.getDenda()));
        holder.tv_jatuh_tempo.setText(item.getTgl_tempo());

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
    public void addList(List<HistoriPembayaranModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClicked(int position, HistoriPembayaranModel item);
    }
    public void addOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}

