package mx.gob.cdmx.estudioscdmx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Foto implements Serializable {

    @SerializedName("uuid")
    public UUID uuid;

    @SerializedName("fotoPath")
    public String fotoPath;

    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    public Foto() {
    }

    public Foto(UUID uuid, String fotoPath, Double latitude, Double longitude) {
        this.uuid = uuid;
        this.fotoPath = fotoPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
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
