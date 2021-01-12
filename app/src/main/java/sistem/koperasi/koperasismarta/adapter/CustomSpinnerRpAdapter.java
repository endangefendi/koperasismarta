package sistem.koperasi.koperasismarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.activity.PengajuanActivity;
import sistem.koperasi.koperasismarta.util.Constant;

public class CustomSpinnerRpAdapter extends BaseAdapter {
    Context context;
    long[] countryNames;
    LayoutInflater inflter;

    public CustomSpinnerRpAdapter(Context applicationContext, long[] countryNames) {
        this.context = applicationContext;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spiner_item_tenor, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(Constant.formatRupiah(countryNames[i]));
//        names.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }


}