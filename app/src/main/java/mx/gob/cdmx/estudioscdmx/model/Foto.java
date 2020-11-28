package mx.gob.cdmx.estudioscdmx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

import mx.gob.cdmx.estudioscdmx.db.Anotaciones.AutoIncrement;
import mx.gob.cdmx.estudioscdmx.db.Anotaciones.PrimaryKey;

public class Foto implements Serializable {

    @AutoIncrement
    @PrimaryKey
    public int id;

    @SerializedName("uuid")
    public UUID uuid;

    @SerializedName("fotoPath")
    public String fotoPath;

    @SerializedName("type_photo")
    public String type_photo;

    @SerializedName("id_type_photo")
    public int id_type_photo;

    @SerializedName("latitude")
    public Double latitude;

    @SerializedName("longitude")
    public Double longitude;

    public Foto() {
    }

    public Foto(int id, UUID uuid, String fotoPath, String type_photo, int id_type_photo, Double latitude, Double longitude) {
        this.id = id;
        this.uuid = uuid;
        this.fotoPath = fotoPath;
        this.type_photo = type_photo;
        this.id_type_photo = id_type_photo;
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

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public String getType_photo() {
        return type_photo;
    }

    public void setType_photo(String type_photo) {
        this.type_photo = type_photo;
    }

    public int getId_type_photo() {
        return id_type_photo;
    }

    public void setId_type_photo(int id_type_photo) {
        this.id_type_photo = id_type_photo;
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
