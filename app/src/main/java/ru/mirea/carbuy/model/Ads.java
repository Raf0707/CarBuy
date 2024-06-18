package ru.mirea.carbuy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ads")
public class Ads implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    private String adsTitle;
    @ColumnInfo(name = "mark")
    private String markCar;

    @ColumnInfo(name = "info")
    private String infoCar;
    @ColumnInfo(name = "price")
    private String priceCar;
    @ColumnInfo(name = "telephone")
    private String numTelephone;

    public Ads(String adsTitle, String markCar, String infoCar, String priceCar, String numTelephone) {
        this.adsTitle = adsTitle;
        this.markCar = markCar;
        this.infoCar = infoCar;
        this.priceCar = priceCar;
        this.numTelephone = numTelephone;
    }

    public String getMarkCar() {
        return markCar;
    }

    public void setMarkCar(String markCar) {
        this.markCar = markCar;
    }

    public String getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(String priceCar) {
        this.priceCar = priceCar;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getAdsTitle() {
        return adsTitle;
    }

    public void setAdsTitle(String adsTitle) {
        this.adsTitle = adsTitle;
    }

    public String getInfoCar() {
        return infoCar;
    }

    public void setInfoCar(String infoCar) {
        this.infoCar = infoCar;
    }

    /*
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/addNewAdsFab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/plus_ic"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.954"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.976" />
     */
}
