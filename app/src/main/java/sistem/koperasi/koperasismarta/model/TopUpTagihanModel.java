package sistem.koperasi.koperasismarta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TopUpTagihanModel implements Parcelable {

    public int image;
    public String title;

    public TopUpTagihanModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    protected TopUpTagihanModel(Parcel in) {
        image = in.readInt();
        title = in.readString();
    }

    public static final Creator<TopUpTagihanModel> CREATOR = new Creator<TopUpTagihanModel>() {
        @Override
        public TopUpTagihanModel createFromParcel(Parcel in) {
            return new TopUpTagihanModel(in);
        }

        @Override
        public TopUpTagihanModel[] newArray(int size) {
            return new TopUpTagihanModel[size];
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