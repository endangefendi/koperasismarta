package sistem.koperasi.koperasismarta.model;

import android.graphics.drawable.Drawable;

public class HomeBannerModel {
    private int no;
    private int slider_id;
    private String slider_judul;
    private Drawable slider_image;
    private String slider_tipe;


    public HomeBannerModel(int no, int slider_id, String slider_judul, Drawable slider_image, String slider_tipe) {
        this.no=no;
        this.slider_id=slider_id;
        this.slider_judul=slider_judul;
        this.slider_image=slider_image;
        this.slider_tipe=slider_tipe;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public String getSlider_judul() {
        return slider_judul;
    }

    public void setSlider_judul(String slider_judul) {
        this.slider_judul = slider_judul;
    }

    public Drawable getSlider_image() {
        return slider_image;
    }

    public void setSlider_image(Drawable slider_image) {
        this.slider_image = slider_image;
    }

    public String getSlider_tipe() {
        return slider_tipe;
    }

    public void setSlider_tipe(String slider_tipe) {
        this.slider_tipe = slider_tipe;
    }

}

