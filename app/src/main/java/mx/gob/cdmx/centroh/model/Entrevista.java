package mx.gob.cdmx.centroh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mx.gob.cdmx.centroh.db.Anotaciones.AutoIncrement;
import mx.gob.cdmx.centroh.db.Anotaciones.PrimaryKey;

public class Entrevista implements Serializable {

    @AutoIncrement
    @PrimaryKey
    public int id;

    @SerializedName("fecha")
    public String fecha;

    @SerializedName("suscribe")
    public String suscribe;

    @SerializedName("caracter")
    public String caracter;

    @SerializedName("inmueble")
    public String inmueble;

    @SerializedName("noOficial")
    public String noOficial;

    @SerializedName("noInterior")
    public String noInterior;

    @SerializedName("colonia")
    public int colonia;

    @SerializedName("alcaldia")
    public int alcaldia;

    @SerializedName("cp")
    public int cp;

    @SerializedName("cuentaPredial")
    public String cuentaPredial;

    @SerializedName("telefono")
    public String telefono;

    @SerializedName("observaciones")
    public String observaciones;

    @SerializedName("firma")
    public Firma firma;

    @SerializedName("foto")
    public Foto foto;

    public Entrevista() {
    }

    public Entrevista(int id, String fecha, String suscribe, String caracter, String inmueble, String noOficial, String noInterior, int colonia, int alcaldia, int cp, String cuentaPredial, String telefono, String observaciones, Firma firma, Foto foto) {
        this.id = id;
        this.fecha = fecha;
        this.suscribe = suscribe;
        this.caracter = caracter;
        this.inmueble = inmueble;
        this.noOficial = noOficial;
        this.noInterior = noInterior;
        this.colonia = colonia;
        this.alcaldia = alcaldia;
        this.cp = cp;
        this.cuentaPredial = cuentaPredial;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.firma = firma;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSuscribe() {
        return suscribe;
    }

    public void setSuscribe(String suscribe) {
        this.suscribe = suscribe;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public String getInmueble() {
        return inmueble;
    }

    public void setInmueble(String inmueble) {
        this.inmueble = inmueble;
    }

    public String getNoOficial() {
        return noOficial;
    }

    public void setNoOficial(String noOficial) {
        this.noOficial = noOficial;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public int getColonia() {
        return colonia;
    }

    public void setColonia(int colonia) {
        this.colonia = colonia;
    }

    public int getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(int alcaldia) {
        this.alcaldia = alcaldia;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getCuentaPredial() {
        return cuentaPredial;
    }

    public void setCuentaPredial(String cuentaPredial) {
        this.cuentaPredial = cuentaPredial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
}
