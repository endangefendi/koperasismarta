package sistem.koperasi.koperasismarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.List;

import sistem.koperasi.koperasismarta.R;
import sistem.koperasi.koperasismarta.model.HomeBannerModel;

public class HomeBannerAdapter extends PagerAdapter {

    private static final String TAG = "HomeBannerAdapter";
    private Context context;
    private List<HomeBannerModel> items;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, HomeBannerModel obj);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public HomeBannerAdapter(Context context, List<HomeBannerModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    public HomeBannerModel getItem(int pos) {
        return items.get(pos);
    }

    public void setItems(List<HomeBannerModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final HomeBannerModel o = items.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_banner_home, container, false);
        ImageView image = v.findViewById(R.id.image);
        MaterialRippleLayout lyt_parent = v.findViewById(R.id.lyt_parent);

        Glide.with(context).load(o.getSlider_image())
                .placeholder(R.drawable.pt)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.5f)
                .into(image);

        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, o);
                }
            }
        });

        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);

    }

}
