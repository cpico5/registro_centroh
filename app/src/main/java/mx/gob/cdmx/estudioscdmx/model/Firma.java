package mx.gob.cdmx.estudioscdmx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Firma implements Serializable {

    @SerializedName("uuid")
    public UUID uuid;

    @SerializedName("firmaPath")
    public String firmaPath;

    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    public Firma() {
    }

    public Firma(UUID uuid, String firmaPath, Double latitude, Double longitude) {
        this.uuid = uuid;
        this.firmaPath = firmaPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirmaPath() {
        return firmaPath;
    }

    public void setFirmaPath(String firmaPath) {
        this.firmaPath = firmaPath;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
