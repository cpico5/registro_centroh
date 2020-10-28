package mx.gob.cdmx.registro_centroh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mx.gob.cdmx.registro_centroh.db.Anotaciones.PrimaryKey;

public class candidatos_cdmx implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    public String id;
    @SerializedName("alcaldia")
    public String alcaldia;
    @SerializedName("partido_alcalde")
    public String partido_alcalde;
    @SerializedName("paterno_alcalde")
    public String paterno_alcalde;
    @SerializedName("materno_alcalde")
    public String materno_alcalde;
    @SerializedName("nombre_alcalde")
    public String nombre_alcalde;
    @SerializedName("nombre_completo_alcalde")
    public String nombre_completo_alcalde;
    @SerializedName("distrito_federal")
    public String distrito_federal;
    @SerializedName("partido_federal")
    public String partido_federal;
    @SerializedName("paterno_federal")
    public String paternofederale;
    @SerializedName("materno_federal")
    public String materno_federal;
    @SerializedName("nombre_federal")
    public String nombre_federal;
    @SerializedName("nombre_completo_federal")
    public String nombre_completo_federal;
    @SerializedName("distrito_local")
    public String distrito_local;
    @SerializedName("partido_local")
    public String partido_local;
    @SerializedName("paterno_local")
    public String paterno_local;
    @SerializedName("materno_local")
    public String materno_local;
    @SerializedName("nombre_local")
    public String nombre_local;
    @SerializedName("nombre_completo_local")
    public String nombre_completo_local;
    @SerializedName("seccion")
    public String seccion;

    public candidatos_cdmx() {
    }

    public candidatos_cdmx(String id, String alcaldia, String partido_alcalde, String paterno_alcalde, String materno_alcalde, String nombre_alcalde, String nombre_completo_alcalde, String distrito_federal, String partido_federal, String paternofederale, String materno_federal, String nombre_federal, String nombre_completo_federal, String distrito_local, String partido_local, String paterno_local, String materno_local, String nombre_local, String nombre_completo_local, String seccion) {
        this.id = id;
        this.alcaldia = alcaldia;
        this.partido_alcalde = partido_alcalde;
        this.paterno_alcalde = paterno_alcalde;
        this.materno_alcalde = materno_alcalde;
        this.nombre_alcalde = nombre_alcalde;
        this.nombre_completo_alcalde = nombre_completo_alcalde;
        this.distrito_federal = distrito_federal;
        this.partido_federal = partido_federal;
        this.paternofederale = paternofederale;
        this.materno_federal = materno_federal;
        this.nombre_federal = nombre_federal;
        this.nombre_completo_federal = nombre_completo_federal;
        this.distrito_local = distrito_local;
        this.partido_local = partido_local;
        this.paterno_local = paterno_local;
        this.materno_local = materno_local;
        this.nombre_local = nombre_local;
        this.nombre_completo_local = nombre_completo_local;
        this.seccion = seccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getPartido_alcalde() {
        return partido_alcalde;
    }

    public void setPartido_alcalde(String partido_alcalde) {
        this.partido_alcalde = partido_alcalde;
    }

    public String getPaterno_alcalde() {
        return paterno_alcalde;
    }

    public void setPaterno_alcalde(String paterno_alcalde) {
        this.paterno_alcalde = paterno_alcalde;
    }

    public String getMaterno_alcalde() {
        return materno_alcalde;
    }

    public void setMaterno_alcalde(String materno_alcalde) {
        this.materno_alcalde = materno_alcalde;
    }

    public String getNombre_alcalde() {
        return nombre_alcalde;
    }

    public void setNombre_alcalde(String nombre_alcalde) {
        this.nombre_alcalde = nombre_alcalde;
    }

    public String getNombre_completo_alcalde() {
        return nombre_completo_alcalde;
    }

    public void setNombre_completo_alcalde(String nombre_completo_alcalde) {
        this.nombre_completo_alcalde = nombre_completo_alcalde;
    }

    public String getDistrito_federal() {
        return distrito_federal;
    }

    public void setDistrito_federal(String distrito_federal) {
        this.distrito_federal = distrito_federal;
    }

    public String getPartido_federal() {
        return partido_federal;
    }

    public void setPartido_federal(String partido_federal) {
        this.partido_federal = partido_federal;
    }

    public String getPaternofederale() {
        return paternofederale;
    }

    public void setPaternofederale(String paternofederale) {
        this.paternofederale = paternofederale;
    }

    public String getMaterno_federal() {
        return materno_federal;
    }

    public void setMaterno_federal(String materno_federal) {
        this.materno_federal = materno_federal;
    }

    public String getNombre_federal() {
        return nombre_federal;
    }

    public void setNombre_federal(String nombre_federal) {
        this.nombre_federal = nombre_federal;
    }

    public String getNombre_completo_federal() {
        return nombre_completo_federal;
    }

    public void setNombre_completo_federal(String nombre_completo_federal) {
        this.nombre_completo_federal = nombre_completo_federal;
    }

    public String getDistrito_local() {
        return distrito_local;
    }

    public void setDistrito_local(String distrito_local) {
        this.distrito_local = distrito_local;
    }

    public String getPartido_local() {
        return partido_local;
    }

    public void setPartido_local(String partido_local) {
        this.partido_local = partido_local;
    }

    public String getPaterno_local() {
        return paterno_local;
    }

    public void setPaterno_local(String paterno_local) {
        this.paterno_local = paterno_local;
    }

    public String getMaterno_local() {
        return materno_local;
    }

    public void setMaterno_local(String materno_local) {
        this.materno_local = materno_local;
    }

    public String getNombre_local() {
        return nombre_local;
    }

    public void setNombre_local(String nombre_local) {
        this.nombre_local = nombre_local;
    }

    public String getNombre_completo_local() {
        return nombre_completo_local;
    }

    public void setNombre_completo_local(String nombre_completo_local) {
        this.nombre_completo_local = nombre_completo_local;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
