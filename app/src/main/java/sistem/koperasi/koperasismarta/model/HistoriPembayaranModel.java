package sistem.koperasi.koperasismarta.model;

import android.graphics.drawable.Drawable;

public class HistoriPembayaranModel {
    private String tenor;
    private long denda;
    private String tgl_tempo;


    public HistoriPembayaranModel(String tenor, long denda, String tgl_tempo) {
        this.tenor=tenor;
        this.denda=denda;
        this.tgl_tempo=tgl_tempo;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public long getDenda() {
        return denda;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }

    public String getTgl_tempo() {
        return tgl_tempo;
    }

    public void setTgl_tempo(String tgl_tempo) {
        this.tgl_tempo = tgl_tempo;
    }
}

