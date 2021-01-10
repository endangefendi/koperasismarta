package sistem.koperasi.koperasismarta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataAnggotaKoperasiModel {
    private int no;
    private String nama, instalasi, date;
    private long simpanan_wajib, simpanan_sukarela, simpanan_pokok;

    public DataAnggotaKoperasiModel(int no, String nama, String instalasi, String date, long simpanan_wajib, long simpanan_sukarela, long simpanan_pokok) {
        this.no = no;
        this.nama = nama;
        this.instalasi = instalasi;
        this.date = date;
        this.simpanan_wajib = simpanan_wajib;
        this.simpanan_sukarela = simpanan_sukarela;
        this.simpanan_pokok = simpanan_pokok;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getInstalasi() {
        return instalasi;
    }

    public void setInstalasi(String instalasi) {
        this.instalasi = instalasi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSimpanan_wajib() {
        return simpanan_wajib;
    }

    public void setSimpanan_wajib(long simpanan_wajib) {
        this.simpanan_wajib = simpanan_wajib;
    }

    public long getSimpanan_sukarela() {
        return simpanan_sukarela;
    }

    public void setSimpanan_sukarela(long simpanan_sukarela) {
        this.simpanan_sukarela = simpanan_sukarela;
    }

    public long getSimpanan_pokok() {
        return simpanan_pokok;
    }

    public void setSimpanan_pokok(long simpanan_pokok) {
        this.simpanan_pokok = simpanan_pokok;
    }
}