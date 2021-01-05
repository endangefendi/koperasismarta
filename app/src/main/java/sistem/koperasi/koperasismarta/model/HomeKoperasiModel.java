package sistem.koperasi.koperasismarta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeKoperasiModel implements Parcelable {

    public int image;
    public String title;

    public HomeKoperasiModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    protected HomeKoperasiModel(Parcel in) {
        image = in.readInt();
        title = in.readString();
    }

    public static final Creator<HomeKoperasiModel> CREATOR = new Creator<HomeKoperasiModel>() {
        @Override
        public HomeKoperasiModel createFromParcel(Parcel in) {
            return new HomeKoperasiModel(in);
        }

        @Override
        public HomeKoperasiModel[] newArray(int size) {
            return new HomeKoperasiModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(title);
    }

}