package mx.gob.cdmx.centroh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Catalogos implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("municipality_id")
    public String municipality_id;

    @SerializedName("catalog")
    public String catalog;

    public Catalogos() {
    }

    public Catalogos(int id, String name, String municipality_id, String catalog) {
        this.id = id;
        this.name = name;
        this.municipality_id = municipality_id;
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMunicipality_id() {
        return municipality_id;
    }

    public void setMunicipality_id(String municipality_id) {
        this.municipality_id = municipality_id;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
