package sistem.koperasi.koperasismarta.model;

public class DataPinjamanModel {
    private int no;
    private int tenor;
    private String jenis_pinjaman, tanggal_tempo;
    private long angsuran, jumlah_pinjaman;

    public DataPinjamanModel(int no, int tenor, String jenis_pinjaman, long jumlah_pinjaman, String tanggal_tempo, long angsuran) {
        this.no = no;
        this.jenis_pinjaman = jenis_pinjaman;
        this.tanggal_tempo = tanggal_tempo;
        this.tenor = tenor;
        this.angsuran = angsuran;
        this.jumlah_pinjaman = jumlah_pinjaman;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public String getjenis_pinjaman() {
        return jenis_pinjaman;
    }

    public void setJenis_pinjaman(String jenis_pinjaman) {
        this.jenis_pinjaman = jenis_pinjaman;
    }

    public String getTanggal_tempo() {
        return tanggal_tempo;
    }

    public void setTanggal_tempo(String tanggal_tempo) {
        this.tanggal_tempo = tanggal_tempo;
    }

    public long getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(long angsuran_berikutnya) {
        this.angsuran = angsuran_berikutnya;
    }

    public long getJumlah_pinjaman() {
        return jumlah_pinjaman;
    }

    public void setJumlah_pinjaman(long jumlah_pinjaman) {
        this.jumlah_pinjaman = jumlah_pinjaman;
    }
}