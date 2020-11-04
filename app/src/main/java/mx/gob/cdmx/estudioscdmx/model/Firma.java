package mx.gob.cdmx.estudioscdmx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Firma implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("uuid")
    public UUID uuid;

    @SerializedName("firmaPath")
    public String firmaPath;

    @SerializedName("type_photo")
    public String type_photo;

    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    public Firma() {
    }

    public Firma(int id, UUID uuid, String firmaPath, String type_photo, Double latitude, Double longitude) {
        this.id = id;
        this.uuid = uuid;
        this.firmaPath = firmaPath;
        this.type_photo = type_photo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType_photo() {
        return type_photo;
    }

    public void setType_photo(String type_photo) {
        this.type_photo = type_photo;
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
