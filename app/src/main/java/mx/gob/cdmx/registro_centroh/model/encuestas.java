package mx.gob.cdmx.registro_centroh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mx.gob.cdmx.registro_centroh.db.Anotaciones.AutoIncrement;
import mx.gob.cdmx.registro_centroh.db.Anotaciones.PrimaryKey;

public class encuestas implements Serializable {

    @PrimaryKey
    @AutoIncrement
    public int id;
    @SerializedName("consecutivo_diario ")  public String consecutivo_diario ;
    @SerializedName("equipo ")  public String equipo ;
    @SerializedName("usuario ")  public String usuario ;
    @SerializedName("nombre_encuesta ")  public String nombre_encuesta ;
    @SerializedName("fecha ")  public String fecha ;
    @SerializedName("hora ")  public String hora ;
    @SerializedName("imei ")  public String imei ;
    @SerializedName("entidad ")  public String entidad ;
    @SerializedName("alcaldia ")  public String alcaldia ;
    @SerializedName("dtto_federal ")  public String dtto_federal ;
    @SerializedName("dtto_local ")  public String dtto_local ;
    @SerializedName("seccion ")  public String seccion ;
    @SerializedName("latitud ")  public String latitud ;
    @SerializedName("longitud ")  public String longitud ;
    @SerializedName("pregunta_1 ")  public String pregunta_1 ;
    @SerializedName("pregunta_2 ")  public String pregunta_2 ;
    @SerializedName("pregunta_3 ")  public String pregunta_3 ;
    @SerializedName("pregunta_4 ")  public String pregunta_4 ;
    @SerializedName("pregunta_5 ")  public String pregunta_5 ;
    @SerializedName("pregunta_6 ")  public String pregunta_6 ;
    @SerializedName("pregunta_7 ")  public String pregunta_7 ;
    @SerializedName("pregunta_pc ")  public String pregunta_pc ;
    @SerializedName("pregunta_pc_1 ")  public String pregunta_pc_1 ;
    @SerializedName("pregunta_pc_2 ")  public String pregunta_pc_2 ;
    @SerializedName("pregunta_pc_3 ")  public String pregunta_pc_3 ;
    @SerializedName("pregunta_pc_4 ")  public String pregunta_pc_4 ;
    @SerializedName("pregunta_8 ")  public String pregunta_8 ;
    @SerializedName("pregunta_9 ")  public String pregunta_9 ;
    @SerializedName("pregunta_10 ")  public String pregunta_10 ;
    @SerializedName("pregunta_11 ")  public String pregunta_11 ;
    @SerializedName("pregunta_12 ")  public String pregunta_12 ;
    @SerializedName("pregunta_13 ")  public String pregunta_13 ;
    @SerializedName("pregunta_14 ")  public String pregunta_14 ;
    @SerializedName("pregunta_15 ")  public String pregunta_15 ;
    @SerializedName("pregunta_16 ")  public String pregunta_16 ;
    @SerializedName("pregunta_16a ")  public String pregunta_16a ;
    @SerializedName("pregunta_16b ")  public String pregunta_16b ;
    @SerializedName("pregunta_17 ")  public String pregunta_17 ;
    @SerializedName("pregunta_17a ")  public String pregunta_17a ;
    @SerializedName("pregunta_18 ")  public String pregunta_18 ;
    @SerializedName("pregunta_19 ")  public String pregunta_19 ;
    @SerializedName("pregunta_19a ")  public String pregunta_19a ;
    @SerializedName("pregunta_19b ")  public String pregunta_19b ;
    @SerializedName("pregunta_20 ")  public String pregunta_20 ;
    @SerializedName("pregunta_21 ")  public String pregunta_21 ;
    @SerializedName("pregunta_22 ")  public String pregunta_22 ;
    @SerializedName("pregunta_23 ")  public String pregunta_23 ;
    @SerializedName("pregunta_24 ")  public String pregunta_24 ;
    @SerializedName("pregunta_24_1 ")  public String pregunta_24_1 ;
    @SerializedName("pregunta_24_1a ")  public String pregunta_24_1a ;
    @SerializedName("pregunta_24_1b ")  public String pregunta_24_1b ;
    @SerializedName("pregunta_24_2 ")  public String pregunta_24_2 ;
    @SerializedName("pregunta_24_2a ")  public String pregunta_24_2a ;
    @SerializedName("pregunta_24_2b ")  public String pregunta_24_2b ;
    @SerializedName("pregunta_24_3 ")  public String pregunta_24_3 ;
    @SerializedName("pregunta_24_3a ")  public String pregunta_24_3a ;
    @SerializedName("pregunta_24_3b ")  public String pregunta_24_3b ;
    @SerializedName("pregunta_24_4 ")  public String pregunta_24_4 ;
    @SerializedName("pregunta_24_4a ")  public String pregunta_24_4a ;
    @SerializedName("pregunta_24_4b ")  public String pregunta_24_4b ;
    @SerializedName("pregunta_24_5 ")  public String pregunta_24_5 ;
    @SerializedName("pregunta_24_5a ")  public String pregunta_24_5a ;
    @SerializedName("pregunta_24_5b ")  public String pregunta_24_5b ;
    @SerializedName("pregunta_24_6 ")  public String pregunta_24_6 ;
    @SerializedName("pregunta_24_6a ")  public String pregunta_24_6a ;
    @SerializedName("pregunta_24_6b ")  public String pregunta_24_6b ;
    @SerializedName("pregunta_24_7 ")  public String pregunta_24_7 ;
    @SerializedName("pregunta_24_7a ")  public String pregunta_24_7a ;
    @SerializedName("pregunta_24_7b ")  public String pregunta_24_7b ;
    @SerializedName("pregunta_24_8 ")  public String pregunta_24_8 ;
    @SerializedName("pregunta_24_8a ")  public String pregunta_24_8a ;
    @SerializedName("pregunta_24_8b ")  public String pregunta_24_8b ;
    @SerializedName("pregunta_24_9 ")  public String pregunta_24_9 ;
    @SerializedName("pregunta_24_9a ")  public String pregunta_24_9a ;
    @SerializedName("pregunta_24_9b ")  public String pregunta_24_9b ;
    @SerializedName("pregunta_24_10 ")  public String pregunta_24_10 ;
    @SerializedName("pregunta_24_10a ")  public String pregunta_24_10a ;
    @SerializedName("pregunta_24_10b ")  public String pregunta_24_10b ;
    @SerializedName("pregunta_25 ")  public String pregunta_25 ;
    @SerializedName("pregunta_25_1 ")  public String pregunta_25_1 ;
    @SerializedName("pregunta_25_1a ")  public String pregunta_25_1a ;
    @SerializedName("pregunta_25_1b ")  public String pregunta_25_1b ;
    @SerializedName("pregunta_25_1c ")  public String pregunta_25_1c ;
    @SerializedName("pregunta_25_1d ")  public String pregunta_25_1d ;
    @SerializedName("pregunta_25_2 ")  public String pregunta_25_2 ;
    @SerializedName("pregunta_25_2a ")  public String pregunta_25_2a ;
    @SerializedName("pregunta_25_2b ")  public String pregunta_25_2b ;
    @SerializedName("pregunta_25_2c ")  public String pregunta_25_2c ;
    @SerializedName("pregunta_25_2d ")  public String pregunta_25_2d ;
    @SerializedName("pregunta_25_3 ")  public String pregunta_25_3 ;
    @SerializedName("pregunta_25_3a ")  public String pregunta_25_3a ;
    @SerializedName("pregunta_25_3b ")  public String pregunta_25_3b ;
    @SerializedName("pregunta_25_3c ")  public String pregunta_25_3c ;
    @SerializedName("pregunta_25_3d ")  public String pregunta_25_3d ;
    @SerializedName("pregunta_25_4 ")  public String pregunta_25_4 ;
    @SerializedName("pregunta_25_4a ")  public String pregunta_25_4a ;
    @SerializedName("pregunta_25_4b ")  public String pregunta_25_4b ;
    @SerializedName("pregunta_25_4c ")  public String pregunta_25_4c ;
    @SerializedName("pregunta_25_4d ")  public String pregunta_25_4d ;
    @SerializedName("pregunta_25_5 ")  public String pregunta_25_5 ;
    @SerializedName("pregunta_25_5a ")  public String pregunta_25_5a ;
    @SerializedName("pregunta_25_5b ")  public String pregunta_25_5b ;
    @SerializedName("pregunta_25_5c ")  public String pregunta_25_5c ;
    @SerializedName("pregunta_25_5d ")  public String pregunta_25_5d ;
    @SerializedName("pregunta_25_6 ")  public String pregunta_25_6 ;
    @SerializedName("pregunta_25_6a ")  public String pregunta_25_6a ;
    @SerializedName("pregunta_25_6b ")  public String pregunta_25_6b ;
    @SerializedName("pregunta_25_6c ")  public String pregunta_25_6c ;
    @SerializedName("pregunta_25_6d ")  public String pregunta_25_6d ;
    @SerializedName("pregunta_25_7 ")  public String pregunta_25_7 ;
    @SerializedName("pregunta_25_7a ")  public String pregunta_25_7a ;
    @SerializedName("pregunta_25_7b ")  public String pregunta_25_7b ;
    @SerializedName("pregunta_25_7c ")  public String pregunta_25_7c ;
    @SerializedName("pregunta_25_7d ")  public String pregunta_25_7d ;
    @SerializedName("pregunta_25_8 ")  public String pregunta_25_8 ;
    @SerializedName("pregunta_25_8a ")  public String pregunta_25_8a ;
    @SerializedName("pregunta_25_8b ")  public String pregunta_25_8b ;
    @SerializedName("pregunta_25_8c ")  public String pregunta_25_8c ;
    @SerializedName("pregunta_25_8d ")  public String pregunta_25_8d ;
    @SerializedName("pregunta_25_9 ")  public String pregunta_25_9 ;
    @SerializedName("pregunta_25_9a ")  public String pregunta_25_9a ;
    @SerializedName("pregunta_25_9b ")  public String pregunta_25_9b ;
    @SerializedName("pregunta_25_9c ")  public String pregunta_25_9c ;
    @SerializedName("pregunta_25_9d ")  public String pregunta_25_9d ;
    @SerializedName("pregunta_25_10 ")  public String pregunta_25_10 ;
    @SerializedName("pregunta_25_10a ")  public String pregunta_25_10a ;
    @SerializedName("pregunta_25_10b ")  public String pregunta_25_10b ;
    @SerializedName("pregunta_25_10c ")  public String pregunta_25_10c ;
    @SerializedName("pregunta_25_10d ")  public String pregunta_25_10d ;
    @SerializedName("pregunta_26 ")  public String pregunta_26 ;
    @SerializedName("pregunta_27 ")  public String pregunta_27 ;
    @SerializedName("aporta  ")  public String aporta  ;
    @SerializedName("ocupacion  ")  public String ocupacion  ;
    @SerializedName("cuantos_coches  ")  public String cuantos_coches  ;
    @SerializedName("cuartos  ")  public String cuartos  ;
    @SerializedName("cuartos_dormir  ")  public String cuartos_dormir  ;
    @SerializedName("focos  ")  public String focos  ;
    @SerializedName("banos  ")  public String banos  ;
    @SerializedName("regadera  ")  public String regadera  ;
    @SerializedName("estufa  ")  public String estufa  ;
    @SerializedName("edad  ")  public String edad  ;
    @SerializedName("genero  ")  public String genero  ;
    @SerializedName("tipo_vivienda  ")  public String tipo_vivienda  ;
    @SerializedName("tipo_piso  ")  public String tipo_piso  ;
    @SerializedName("abandono ")  public String abandono ;
    @SerializedName("suma ")  public String suma ;
    @SerializedName("status ")  public String status ;
    @SerializedName("tiempo ")  public String tiempo ;
    @SerializedName("tipo_captura ")  public String tipo_captura ;
    @SerializedName("enviado ")  public String enviado ;


    public encuestas() {
    }

    public encuestas(int id, String consecutivo_diario, String equipo, String usuario, String nombre_encuesta, String fecha, String hora, String imei, String entidad, String alcaldia, String dtto_federal, String dtto_local, String seccion, String latitud, String longitud, String pregunta_1, String pregunta_2, String pregunta_3, String pregunta_4, String pregunta_5, String pregunta_6, String pregunta_7, String pregunta_pc, String pregunta_pc_1, String pregunta_pc_2, String pregunta_pc_3, String pregunta_pc_4, String pregunta_8, String pregunta_9, String pregunta_10, String pregunta_11, String pregunta_12, String pregunta_13, String pregunta_14, String pregunta_15, String pregunta_16, String pregunta_16a, String pregunta_16b, String pregunta_17, String pregunta_17a, String pregunta_18, String pregunta_19, String pregunta_19a, String pregunta_19b, String pregunta_20, String pregunta_21, String pregunta_22, String pregunta_23, String pregunta_24, String pregunta_24_1, String pregunta_24_1a, String pregunta_24_1b, String pregunta_24_2, String pregunta_24_2a, String pregunta_24_2b, String pregunta_24_3, String pregunta_24_3a, String pregunta_24_3b, String pregunta_24_4, String pregunta_24_4a, String pregunta_24_4b, String pregunta_24_5, String pregunta_24_5a, String pregunta_24_5b, String pregunta_24_6, String pregunta_24_6a, String pregunta_24_6b, String pregunta_24_7, String pregunta_24_7a, String pregunta_24_7b, String pregunta_24_8, String pregunta_24_8a, String pregunta_24_8b, String pregunta_24_9, String pregunta_24_9a, String pregunta_24_9b, String pregunta_24_10, String pregunta_24_10a, String pregunta_24_10b, String pregunta_25, String pregunta_25_1, String pregunta_25_1a, String pregunta_25_1b, String pregunta_25_1c, String pregunta_25_1d, String pregunta_25_2, String pregunta_25_2a, String pregunta_25_2b, String pregunta_25_2c, String pregunta_25_2d, String pregunta_25_3, String pregunta_25_3a, String pregunta_25_3b, String pregunta_25_3c, String pregunta_25_3d, String pregunta_25_4, String pregunta_25_4a, String pregunta_25_4b, String pregunta_25_4c, String pregunta_25_4d, String pregunta_25_5, String pregunta_25_5a, String pregunta_25_5b, String pregunta_25_5c, String pregunta_25_5d, String pregunta_25_6, String pregunta_25_6a, String pregunta_25_6b, String pregunta_25_6c, String pregunta_25_6d, String pregunta_25_7, String pregunta_25_7a, String pregunta_25_7b, String pregunta_25_7c, String pregunta_25_7d, String pregunta_25_8, String pregunta_25_8a, String pregunta_25_8b, String pregunta_25_8c, String pregunta_25_8d, String pregunta_25_9, String pregunta_25_9a, String pregunta_25_9b, String pregunta_25_9c, String pregunta_25_9d, String pregunta_25_10, String pregunta_25_10a, String pregunta_25_10b, String pregunta_25_10c, String pregunta_25_10d, String pregunta_26, String pregunta_27, String aporta, String ocupacion, String cuantos_coches, String cuartos, String cuartos_dormir, String focos, String banos, String regadera, String estufa, String edad, String genero, String tipo_vivienda, String tipo_piso, String abandono, String suma, String status, String tiempo, String tipo_captura, String enviado) {
        this.id = id;
        this.consecutivo_diario = consecutivo_diario;
        this.equipo = equipo;
        this.usuario = usuario;
        this.nombre_encuesta = nombre_encuesta;
        this.fecha = fecha;
        this.hora = hora;
        this.imei = imei;
        this.entidad = entidad;
        this.alcaldia = alcaldia;
        this.dtto_federal = dtto_federal;
        this.dtto_local = dtto_local;
        this.seccion = seccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.pregunta_1 = pregunta_1;
        this.pregunta_2 = pregunta_2;
        this.pregunta_3 = pregunta_3;
        this.pregunta_4 = pregunta_4;
        this.pregunta_5 = pregunta_5;
        this.pregunta_6 = pregunta_6;
        this.pregunta_7 = pregunta_7;
        this.pregunta_pc = pregunta_pc;
        this.pregunta_pc_1 = pregunta_pc_1;
        this.pregunta_pc_2 = pregunta_pc_2;
        this.pregunta_pc_3 = pregunta_pc_3;
        this.pregunta_pc_4 = pregunta_pc_4;
        this.pregunta_8 = pregunta_8;
        this.pregunta_9 = pregunta_9;
        this.pregunta_10 = pregunta_10;
        this.pregunta_11 = pregunta_11;
        this.pregunta_12 = pregunta_12;
        this.pregunta_13 = pregunta_13;
        this.pregunta_14 = pregunta_14;
        this.pregunta_15 = pregunta_15;
        this.pregunta_16 = pregunta_16;
        this.pregunta_16a = pregunta_16a;
        this.pregunta_16b = pregunta_16b;
        this.pregunta_17 = pregunta_17;
        this.pregunta_17a = pregunta_17a;
        this.pregunta_18 = pregunta_18;
        this.pregunta_19 = pregunta_19;
        this.pregunta_19a = pregunta_19a;
        this.pregunta_19b = pregunta_19b;
        this.pregunta_20 = pregunta_20;
        this.pregunta_21 = pregunta_21;
        this.pregunta_22 = pregunta_22;
        this.pregunta_23 = pregunta_23;
        this.pregunta_24 = pregunta_24;
        this.pregunta_24_1 = pregunta_24_1;
        this.pregunta_24_1a = pregunta_24_1a;
        this.pregunta_24_1b = pregunta_24_1b;
        this.pregunta_24_2 = pregunta_24_2;
        this.pregunta_24_2a = pregunta_24_2a;
        this.pregunta_24_2b = pregunta_24_2b;
        this.pregunta_24_3 = pregunta_24_3;
        this.pregunta_24_3a = pregunta_24_3a;
        this.pregunta_24_3b = pregunta_24_3b;
        this.pregunta_24_4 = pregunta_24_4;
        this.pregunta_24_4a = pregunta_24_4a;
        this.pregunta_24_4b = pregunta_24_4b;
        this.pregunta_24_5 = pregunta_24_5;
        this.pregunta_24_5a = pregunta_24_5a;
        this.pregunta_24_5b = pregunta_24_5b;
        this.pregunta_24_6 = pregunta_24_6;
        this.pregunta_24_6a = pregunta_24_6a;
        this.pregunta_24_6b = pregunta_24_6b;
        this.pregunta_24_7 = pregunta_24_7;
        this.pregunta_24_7a = pregunta_24_7a;
        this.pregunta_24_7b = pregunta_24_7b;
        this.pregunta_24_8 = pregunta_24_8;
        this.pregunta_24_8a = pregunta_24_8a;
        this.pregunta_24_8b = pregunta_24_8b;
        this.pregunta_24_9 = pregunta_24_9;
        this.pregunta_24_9a = pregunta_24_9a;
        this.pregunta_24_9b = pregunta_24_9b;
        this.pregunta_24_10 = pregunta_24_10;
        this.pregunta_24_10a = pregunta_24_10a;
        this.pregunta_24_10b = pregunta_24_10b;
        this.pregunta_25 = pregunta_25;
        this.pregunta_25_1 = pregunta_25_1;
        this.pregunta_25_1a = pregunta_25_1a;
        this.pregunta_25_1b = pregunta_25_1b;
        this.pregunta_25_1c = pregunta_25_1c;
        this.pregunta_25_1d = pregunta_25_1d;
        this.pregunta_25_2 = pregunta_25_2;
        this.pregunta_25_2a = pregunta_25_2a;
        this.pregunta_25_2b = pregunta_25_2b;
        this.pregunta_25_2c = pregunta_25_2c;
        this.pregunta_25_2d = pregunta_25_2d;
        this.pregunta_25_3 = pregunta_25_3;
        this.pregunta_25_3a = pregunta_25_3a;
        this.pregunta_25_3b = pregunta_25_3b;
        this.pregunta_25_3c = pregunta_25_3c;
        this.pregunta_25_3d = pregunta_25_3d;
        this.pregunta_25_4 = pregunta_25_4;
        this.pregunta_25_4a = pregunta_25_4a;
        this.pregunta_25_4b = pregunta_25_4b;
        this.pregunta_25_4c = pregunta_25_4c;
        this.pregunta_25_4d = pregunta_25_4d;
        this.pregunta_25_5 = pregunta_25_5;
        this.pregunta_25_5a = pregunta_25_5a;
        this.pregunta_25_5b = pregunta_25_5b;
        this.pregunta_25_5c = pregunta_25_5c;
        this.pregunta_25_5d = pregunta_25_5d;
        this.pregunta_25_6 = pregunta_25_6;
        this.pregunta_25_6a = pregunta_25_6a;
        this.pregunta_25_6b = pregunta_25_6b;
        this.pregunta_25_6c = pregunta_25_6c;
        this.pregunta_25_6d = pregunta_25_6d;
        this.pregunta_25_7 = pregunta_25_7;
        this.pregunta_25_7a = pregunta_25_7a;
        this.pregunta_25_7b = pregunta_25_7b;
        this.pregunta_25_7c = pregunta_25_7c;
        this.pregunta_25_7d = pregunta_25_7d;
        this.pregunta_25_8 = pregunta_25_8;
        this.pregunta_25_8a = pregunta_25_8a;
        this.pregunta_25_8b = pregunta_25_8b;
        this.pregunta_25_8c = pregunta_25_8c;
        this.pregunta_25_8d = pregunta_25_8d;
        this.pregunta_25_9 = pregunta_25_9;
        this.pregunta_25_9a = pregunta_25_9a;
        this.pregunta_25_9b = pregunta_25_9b;
        this.pregunta_25_9c = pregunta_25_9c;
        this.pregunta_25_9d = pregunta_25_9d;
        this.pregunta_25_10 = pregunta_25_10;
        this.pregunta_25_10a = pregunta_25_10a;
        this.pregunta_25_10b = pregunta_25_10b;
        this.pregunta_25_10c = pregunta_25_10c;
        this.pregunta_25_10d = pregunta_25_10d;
        this.pregunta_26 = pregunta_26;
        this.pregunta_27 = pregunta_27;
        this.aporta = aporta;
        this.ocupacion = ocupacion;
        this.cuantos_coches = cuantos_coches;
        this.cuartos = cuartos;
        this.cuartos_dormir = cuartos_dormir;
        this.focos = focos;
        this.banos = banos;
        this.regadera = regadera;
        this.estufa = estufa;
        this.edad = edad;
        this.genero = genero;
        this.tipo_vivienda = tipo_vivienda;
        this.tipo_piso = tipo_piso;
        this.abandono = abandono;
        this.suma = suma;
        this.status = status;
        this.tiempo = tiempo;
        this.tipo_captura = tipo_captura;
        this.enviado = enviado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsecutivo_diario() {
        return consecutivo_diario;
    }

    public void setConsecutivo_diario(String consecutivo_diario) {
        this.consecutivo_diario = consecutivo_diario;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre_encuesta() {
        return nombre_encuesta;
    }

    public void setNombre_encuesta(String nombre_encuesta) {
        this.nombre_encuesta = nombre_encuesta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getDtto_federal() {
        return dtto_federal;
    }

    public void setDtto_federal(String dtto_federal) {
        this.dtto_federal = dtto_federal;
    }

    public String getDtto_local() {
        return dtto_local;
    }

    public void setDtto_local(String dtto_local) {
        this.dtto_local = dtto_local;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPregunta_1() {
        return pregunta_1;
    }

    public void setPregunta_1(String pregunta_1) {
        this.pregunta_1 = pregunta_1;
    }

    public String getPregunta_2() {
        return pregunta_2;
    }

    public void setPregunta_2(String pregunta_2) {
        this.pregunta_2 = pregunta_2;
    }

    public String getPregunta_3() {
        return pregunta_3;
    }

    public void setPregunta_3(String pregunta_3) {
        this.pregunta_3 = pregunta_3;
    }

    public String getPregunta_4() {
        return pregunta_4;
    }

    public void setPregunta_4(String pregunta_4) {
        this.pregunta_4 = pregunta_4;
    }

    public String getPregunta_5() {
        return pregunta_5;
    }

    public void setPregunta_5(String pregunta_5) {
        this.pregunta_5 = pregunta_5;
    }

    public String getPregunta_6() {
        return pregunta_6;
    }

    public void setPregunta_6(String pregunta_6) {
        this.pregunta_6 = pregunta_6;
    }

    public String getPregunta_7() {
        return pregunta_7;
    }

    public void setPregunta_7(String pregunta_7) {
        this.pregunta_7 = pregunta_7;
    }

    public String getPregunta_pc() {
        return pregunta_pc;
    }

    public void setPregunta_pc(String pregunta_pc) {
        this.pregunta_pc = pregunta_pc;
    }

    public String getPregunta_pc_1() {
        return pregunta_pc_1;
    }

    public void setPregunta_pc_1(String pregunta_pc_1) {
        this.pregunta_pc_1 = pregunta_pc_1;
    }

    public String getPregunta_pc_2() {
        return pregunta_pc_2;
    }

    public void setPregunta_pc_2(String pregunta_pc_2) {
        this.pregunta_pc_2 = pregunta_pc_2;
    }

    public String getPregunta_pc_3() {
        return pregunta_pc_3;
    }

    public void setPregunta_pc_3(String pregunta_pc_3) {
        this.pregunta_pc_3 = pregunta_pc_3;
    }

    public String getPregunta_pc_4() {
        return pregunta_pc_4;
    }

    public void setPregunta_pc_4(String pregunta_pc_4) {
        this.pregunta_pc_4 = pregunta_pc_4;
    }

    public String getPregunta_8() {
        return pregunta_8;
    }

    public void setPregunta_8(String pregunta_8) {
        this.pregunta_8 = pregunta_8;
    }

    public String getPregunta_9() {
        return pregunta_9;
    }

    public void setPregunta_9(String pregunta_9) {
        this.pregunta_9 = pregunta_9;
    }

    public String getPregunta_10() {
        return pregunta_10;
    }

    public void setPregunta_10(String pregunta_10) {
        this.pregunta_10 = pregunta_10;
    }

    public String getPregunta_11() {
        return pregunta_11;
    }

    public void setPregunta_11(String pregunta_11) {
        this.pregunta_11 = pregunta_11;
    }

    public String getPregunta_12() {
        return pregunta_12;
    }

    public void setPregunta_12(String pregunta_12) {
        this.pregunta_12 = pregunta_12;
    }

    public String getPregunta_13() {
        return pregunta_13;
    }

    public void setPregunta_13(String pregunta_13) {
        this.pregunta_13 = pregunta_13;
    }

    public String getPregunta_14() {
        return pregunta_14;
    }

    public void setPregunta_14(String pregunta_14) {
        this.pregunta_14 = pregunta_14;
    }

    public String getPregunta_15() {
        return pregunta_15;
    }

    public void setPregunta_15(String pregunta_15) {
        this.pregunta_15 = pregunta_15;
    }

    public String getPregunta_16() {
        return pregunta_16;
    }

    public void setPregunta_16(String pregunta_16) {
        this.pregunta_16 = pregunta_16;
    }

    public String getPregunta_16a() {
        return pregunta_16a;
    }

    public void setPregunta_16a(String pregunta_16a) {
        this.pregunta_16a = pregunta_16a;
    }

    public String getPregunta_16b() {
        return pregunta_16b;
    }

    public void setPregunta_16b(String pregunta_16b) {
        this.pregunta_16b = pregunta_16b;
    }

    public String getPregunta_17() {
        return pregunta_17;
    }

    public void setPregunta_17(String pregunta_17) {
        this.pregunta_17 = pregunta_17;
    }

    public String getPregunta_17a() {
        return pregunta_17a;
    }

    public void setPregunta_17a(String pregunta_17a) {
        this.pregunta_17a = pregunta_17a;
    }

    public String getPregunta_18() {
        return pregunta_18;
    }

    public void setPregunta_18(String pregunta_18) {
        this.pregunta_18 = pregunta_18;
    }

    public String getPregunta_19() {
        return pregunta_19;
    }

    public void setPregunta_19(String pregunta_19) {
        this.pregunta_19 = pregunta_19;
    }

    public String getPregunta_19a() {
        return pregunta_19a;
    }

    public void setPregunta_19a(String pregunta_19a) {
        this.pregunta_19a = pregunta_19a;
    }

    public String getPregunta_19b() {
        return pregunta_19b;
    }

    public void setPregunta_19b(String pregunta_19b) {
        this.pregunta_19b = pregunta_19b;
    }

    public String getPregunta_20() {
        return pregunta_20;
    }

    public void setPregunta_20(String pregunta_20) {
        this.pregunta_20 = pregunta_20;
    }

    public String getPregunta_21() {
        return pregunta_21;
    }

    public void setPregunta_21(String pregunta_21) {
        this.pregunta_21 = pregunta_21;
    }

    public String getPregunta_22() {
        return pregunta_22;
    }

    public void setPregunta_22(String pregunta_22) {
        this.pregunta_22 = pregunta_22;
    }

    public String getPregunta_23() {
        return pregunta_23;
    }

    public void setPregunta_23(String pregunta_23) {
        this.pregunta_23 = pregunta_23;
    }

    public String getPregunta_24() {
        return pregunta_24;
    }

    public void setPregunta_24(String pregunta_24) {
        this.pregunta_24 = pregunta_24;
    }

    public String getPregunta_24_1() {
        return pregunta_24_1;
    }

    public void setPregunta_24_1(String pregunta_24_1) {
        this.pregunta_24_1 = pregunta_24_1;
    }

    public String getPregunta_24_1a() {
        return pregunta_24_1a;
    }

    public void setPregunta_24_1a(String pregunta_24_1a) {
        this.pregunta_24_1a = pregunta_24_1a;
    }

    public String getPregunta_24_1b() {
        return pregunta_24_1b;
    }

    public void setPregunta_24_1b(String pregunta_24_1b) {
        this.pregunta_24_1b = pregunta_24_1b;
    }

    public String getPregunta_24_2() {
        return pregunta_24_2;
    }

    public void setPregunta_24_2(String pregunta_24_2) {
        this.pregunta_24_2 = pregunta_24_2;
    }

    public String getPregunta_24_2a() {
        return pregunta_24_2a;
    }

    public void setPregunta_24_2a(String pregunta_24_2a) {
        this.pregunta_24_2a = pregunta_24_2a;
    }

    public String getPregunta_24_2b() {
        return pregunta_24_2b;
    }

    public void setPregunta_24_2b(String pregunta_24_2b) {
        this.pregunta_24_2b = pregunta_24_2b;
    }

    public String getPregunta_24_3() {
        return pregunta_24_3;
    }

    public void setPregunta_24_3(String pregunta_24_3) {
        this.pregunta_24_3 = pregunta_24_3;
    }

    public String getPregunta_24_3a() {
        return pregunta_24_3a;
    }

    public void setPregunta_24_3a(String pregunta_24_3a) {
        this.pregunta_24_3a = pregunta_24_3a;
    }

    public String getPregunta_24_3b() {
        return pregunta_24_3b;
    }

    public void setPregunta_24_3b(String pregunta_24_3b) {
        this.pregunta_24_3b = pregunta_24_3b;
    }

    public String getPregunta_24_4() {
        return pregunta_24_4;
    }

    public void setPregunta_24_4(String pregunta_24_4) {
        this.pregunta_24_4 = pregunta_24_4;
    }

    public String getPregunta_24_4a() {
        return pregunta_24_4a;
    }

    public void setPregunta_24_4a(String pregunta_24_4a) {
        this.pregunta_24_4a = pregunta_24_4a;
    }

    public String getPregunta_24_4b() {
        return pregunta_24_4b;
    }

    public void setPregunta_24_4b(String pregunta_24_4b) {
        this.pregunta_24_4b = pregunta_24_4b;
    }

    public String getPregunta_24_5() {
        return pregunta_24_5;
    }

    public void setPregunta_24_5(String pregunta_24_5) {
        this.pregunta_24_5 = pregunta_24_5;
    }

    public String getPregunta_24_5a() {
        return pregunta_24_5a;
    }

    public void setPregunta_24_5a(String pregunta_24_5a) {
        this.pregunta_24_5a = pregunta_24_5a;
    }

    public String getPregunta_24_5b() {
        return pregunta_24_5b;
    }

    public void setPregunta_24_5b(String pregunta_24_5b) {
        this.pregunta_24_5b = pregunta_24_5b;
    }

    public String getPregunta_24_6() {
        return pregunta_24_6;
    }

    public void setPregunta_24_6(String pregunta_24_6) {
        this.pregunta_24_6 = pregunta_24_6;
    }

    public String getPregunta_24_6a() {
        return pregunta_24_6a;
    }

    public void setPregunta_24_6a(String pregunta_24_6a) {
        this.pregunta_24_6a = pregunta_24_6a;
    }

    public String getPregunta_24_6b() {
        return pregunta_24_6b;
    }

    public void setPregunta_24_6b(String pregunta_24_6b) {
        this.pregunta_24_6b = pregunta_24_6b;
    }

    public String getPregunta_24_7() {
        return pregunta_24_7;
    }

    public void setPregunta_24_7(String pregunta_24_7) {
        this.pregunta_24_7 = pregunta_24_7;
    }

    public String getPregunta_24_7a() {
        return pregunta_24_7a;
    }

    public void setPregunta_24_7a(String pregunta_24_7a) {
        this.pregunta_24_7a = pregunta_24_7a;
    }

    public String getPregunta_24_7b() {
        return pregunta_24_7b;
    }

    public void setPregunta_24_7b(String pregunta_24_7b) {
        this.pregunta_24_7b = pregunta_24_7b;
    }

    public String getPregunta_24_8() {
        return pregunta_24_8;
    }

    public void setPregunta_24_8(String pregunta_24_8) {
        this.pregunta_24_8 = pregunta_24_8;
    }

    public String getPregunta_24_8a() {
        return pregunta_24_8a;
    }

    public void setPregunta_24_8a(String pregunta_24_8a) {
        this.pregunta_24_8a = pregunta_24_8a;
    }

    public String getPregunta_24_8b() {
        return pregunta_24_8b;
    }

    public void setPregunta_24_8b(String pregunta_24_8b) {
        this.pregunta_24_8b = pregunta_24_8b;
    }

    public String getPregunta_24_9() {
        return pregunta_24_9;
    }

    public void setPregunta_24_9(String pregunta_24_9) {
        this.pregunta_24_9 = pregunta_24_9;
    }

    public String getPregunta_24_9a() {
        return pregunta_24_9a;
    }

    public void setPregunta_24_9a(String pregunta_24_9a) {
        this.pregunta_24_9a = pregunta_24_9a;
    }

    public String getPregunta_24_9b() {
        return pregunta_24_9b;
    }

    public void setPregunta_24_9b(String pregunta_24_9b) {
        this.pregunta_24_9b = pregunta_24_9b;
    }

    public String getPregunta_24_10() {
        return pregunta_24_10;
    }

    public void setPregunta_24_10(String pregunta_24_10) {
        this.pregunta_24_10 = pregunta_24_10;
    }

    public String getPregunta_24_10a() {
        return pregunta_24_10a;
    }

    public void setPregunta_24_10a(String pregunta_24_10a) {
        this.pregunta_24_10a = pregunta_24_10a;
    }

    public String getPregunta_24_10b() {
        return pregunta_24_10b;
    }

    public void setPregunta_24_10b(String pregunta_24_10b) {
        this.pregunta_24_10b = pregunta_24_10b;
    }

    public String getPregunta_25() {
        return pregunta_25;
    }

    public void setPregunta_25(String pregunta_25) {
        this.pregunta_25 = pregunta_25;
    }

    public String getPregunta_25_1() {
        return pregunta_25_1;
    }

    public void setPregunta_25_1(String pregunta_25_1) {
        this.pregunta_25_1 = pregunta_25_1;
    }

    public String getPregunta_25_1a() {
        return pregunta_25_1a;
    }

    public void setPregunta_25_1a(String pregunta_25_1a) {
        this.pregunta_25_1a = pregunta_25_1a;
    }

    public String getPregunta_25_1b() {
        return pregunta_25_1b;
    }

    public void setPregunta_25_1b(String pregunta_25_1b) {
        this.pregunta_25_1b = pregunta_25_1b;
    }

    public String getPregunta_25_1c() {
        return pregunta_25_1c;
    }

    public void setPregunta_25_1c(String pregunta_25_1c) {
        this.pregunta_25_1c = pregunta_25_1c;
    }

    public String getPregunta_25_1d() {
        return pregunta_25_1d;
    }

    public void setPregunta_25_1d(String pregunta_25_1d) {
        this.pregunta_25_1d = pregunta_25_1d;
    }

    public String getPregunta_25_2() {
        return pregunta_25_2;
    }

    public void setPregunta_25_2(String pregunta_25_2) {
        this.pregunta_25_2 = pregunta_25_2;
    }

    public String getPregunta_25_2a() {
        return pregunta_25_2a;
    }

    public void setPregunta_25_2a(String pregunta_25_2a) {
        this.pregunta_25_2a = pregunta_25_2a;
    }

    public String getPregunta_25_2b() {
        return pregunta_25_2b;
    }

    public void setPregunta_25_2b(String pregunta_25_2b) {
        this.pregunta_25_2b = pregunta_25_2b;
    }

    public String getPregunta_25_2c() {
        return pregunta_25_2c;
    }

    public void setPregunta_25_2c(String pregunta_25_2c) {
        this.pregunta_25_2c = pregunta_25_2c;
    }

    public String getPregunta_25_2d() {
        return pregunta_25_2d;
    }

    public void setPregunta_25_2d(String pregunta_25_2d) {
        this.pregunta_25_2d = pregunta_25_2d;
    }

    public String getPregunta_25_3() {
        return pregunta_25_3;
    }

    public void setPregunta_25_3(String pregunta_25_3) {
        this.pregunta_25_3 = pregunta_25_3;
    }

    public String getPregunta_25_3a() {
        return pregunta_25_3a;
    }

    public void setPregunta_25_3a(String pregunta_25_3a) {
        this.pregunta_25_3a = pregunta_25_3a;
    }

    public String getPregunta_25_3b() {
        return pregunta_25_3b;
    }

    public void setPregunta_25_3b(String pregunta_25_3b) {
        this.pregunta_25_3b = pregunta_25_3b;
    }

    public String getPregunta_25_3c() {
        return pregunta_25_3c;
    }

    public void setPregunta_25_3c(String pregunta_25_3c) {
        this.pregunta_25_3c = pregunta_25_3c;
    }

    public String getPregunta_25_3d() {
        return pregunta_25_3d;
    }

    public void setPregunta_25_3d(String pregunta_25_3d) {
        this.pregunta_25_3d = pregunta_25_3d;
    }

    public String getPregunta_25_4() {
        return pregunta_25_4;
    }

    public void setPregunta_25_4(String pregunta_25_4) {
        this.pregunta_25_4 = pregunta_25_4;
    }

    public String getPregunta_25_4a() {
        return pregunta_25_4a;
    }

    public void setPregunta_25_4a(String pregunta_25_4a) {
        this.pregunta_25_4a = pregunta_25_4a;
    }

    public String getPregunta_25_4b() {
        return pregunta_25_4b;
    }

    public void setPregunta_25_4b(String pregunta_25_4b) {
        this.pregunta_25_4b = pregunta_25_4b;
    }

    public String getPregunta_25_4c() {
        return pregunta_25_4c;
    }

    public void setPregunta_25_4c(String pregunta_25_4c) {
        this.pregunta_25_4c = pregunta_25_4c;
    }

    public String getPregunta_25_4d() {
        return pregunta_25_4d;
    }

    public void setPregunta_25_4d(String pregunta_25_4d) {
        this.pregunta_25_4d = pregunta_25_4d;
    }

    public String getPregunta_25_5() {
        return pregunta_25_5;
    }

    public void setPregunta_25_5(String pregunta_25_5) {
        this.pregunta_25_5 = pregunta_25_5;
    }

    public String getPregunta_25_5a() {
        return pregunta_25_5a;
    }

    public void setPregunta_25_5a(String pregunta_25_5a) {
        this.pregunta_25_5a = pregunta_25_5a;
    }

    public String getPregunta_25_5b() {
        return pregunta_25_5b;
    }

    public void setPregunta_25_5b(String pregunta_25_5b) {
        this.pregunta_25_5b = pregunta_25_5b;
    }

    public String getPregunta_25_5c() {
        return pregunta_25_5c;
    }

    public void setPregunta_25_5c(String pregunta_25_5c) {
        this.pregunta_25_5c = pregunta_25_5c;
    }

    public String getPregunta_25_5d() {
        return pregunta_25_5d;
    }

    public void setPregunta_25_5d(String pregunta_25_5d) {
        this.pregunta_25_5d = pregunta_25_5d;
    }

    public String getPregunta_25_6() {
        return pregunta_25_6;
    }

    public void setPregunta_25_6(String pregunta_25_6) {
        this.pregunta_25_6 = pregunta_25_6;
    }

    public String getPregunta_25_6a() {
        return pregunta_25_6a;
    }

    public void setPregunta_25_6a(String pregunta_25_6a) {
        this.pregunta_25_6a = pregunta_25_6a;
    }

    public String getPregunta_25_6b() {
        return pregunta_25_6b;
    }

    public void setPregunta_25_6b(String pregunta_25_6b) {
        this.pregunta_25_6b = pregunta_25_6b;
    }

    public String getPregunta_25_6c() {
        return pregunta_25_6c;
    }

    public void setPregunta_25_6c(String pregunta_25_6c) {
        this.pregunta_25_6c = pregunta_25_6c;
    }

    public String getPregunta_25_6d() {
        return pregunta_25_6d;
    }

    public void setPregunta_25_6d(String pregunta_25_6d) {
        this.pregunta_25_6d = pregunta_25_6d;
    }

    public String getPregunta_25_7() {
        return pregunta_25_7;
    }

    public void setPregunta_25_7(String pregunta_25_7) {
        this.pregunta_25_7 = pregunta_25_7;
    }

    public String getPregunta_25_7a() {
        return pregunta_25_7a;
    }

    public void setPregunta_25_7a(String pregunta_25_7a) {
        this.pregunta_25_7a = pregunta_25_7a;
    }

    public String getPregunta_25_7b() {
        return pregunta_25_7b;
    }

    public void setPregunta_25_7b(String pregunta_25_7b) {
        this.pregunta_25_7b = pregunta_25_7b;
    }

    public String getPregunta_25_7c() {
        return pregunta_25_7c;
    }

    public void setPregunta_25_7c(String pregunta_25_7c) {
        this.pregunta_25_7c = pregunta_25_7c;
    }

    public String getPregunta_25_7d() {
        return pregunta_25_7d;
    }

    public void setPregunta_25_7d(String pregunta_25_7d) {
        this.pregunta_25_7d = pregunta_25_7d;
    }

    public String getPregunta_25_8() {
        return pregunta_25_8;
    }

    public void setPregunta_25_8(String pregunta_25_8) {
        this.pregunta_25_8 = pregunta_25_8;
    }

    public String getPregunta_25_8a() {
        return pregunta_25_8a;
    }

    public void setPregunta_25_8a(String pregunta_25_8a) {
        this.pregunta_25_8a = pregunta_25_8a;
    }

    public String getPregunta_25_8b() {
        return pregunta_25_8b;
    }

    public void setPregunta_25_8b(String pregunta_25_8b) {
        this.pregunta_25_8b = pregunta_25_8b;
    }

    public String getPregunta_25_8c() {
        return pregunta_25_8c;
    }

    public void setPregunta_25_8c(String pregunta_25_8c) {
        this.pregunta_25_8c = pregunta_25_8c;
    }

    public String getPregunta_25_8d() {
        return pregunta_25_8d;
    }

    public void setPregunta_25_8d(String pregunta_25_8d) {
        this.pregunta_25_8d = pregunta_25_8d;
    }

    public String getPregunta_25_9() {
        return pregunta_25_9;
    }

    public void setPregunta_25_9(String pregunta_25_9) {
        this.pregunta_25_9 = pregunta_25_9;
    }

    public String getPregunta_25_9a() {
        return pregunta_25_9a;
    }

    public void setPregunta_25_9a(String pregunta_25_9a) {
        this.pregunta_25_9a = pregunta_25_9a;
    }

    public String getPregunta_25_9b() {
        return pregunta_25_9b;
    }

    public void setPregunta_25_9b(String pregunta_25_9b) {
        this.pregunta_25_9b = pregunta_25_9b;
    }

    public String getPregunta_25_9c() {
        return pregunta_25_9c;
    }

    public void setPregunta_25_9c(String pregunta_25_9c) {
        this.pregunta_25_9c = pregunta_25_9c;
    }

    public String getPregunta_25_9d() {
        return pregunta_25_9d;
    }

    public void setPregunta_25_9d(String pregunta_25_9d) {
        this.pregunta_25_9d = pregunta_25_9d;
    }

    public String getPregunta_25_10() {
        return pregunta_25_10;
    }

    public void setPregunta_25_10(String pregunta_25_10) {
        this.pregunta_25_10 = pregunta_25_10;
    }

    public String getPregunta_25_10a() {
        return pregunta_25_10a;
    }

    public void setPregunta_25_10a(String pregunta_25_10a) {
        this.pregunta_25_10a = pregunta_25_10a;
    }

    public String getPregunta_25_10b() {
        return pregunta_25_10b;
    }

    public void setPregunta_25_10b(String pregunta_25_10b) {
        this.pregunta_25_10b = pregunta_25_10b;
    }

    public String getPregunta_25_10c() {
        return pregunta_25_10c;
    }

    public void setPregunta_25_10c(String pregunta_25_10c) {
        this.pregunta_25_10c = pregunta_25_10c;
    }

    public String getPregunta_25_10d() {
        return pregunta_25_10d;
    }

    public void setPregunta_25_10d(String pregunta_25_10d) {
        this.pregunta_25_10d = pregunta_25_10d;
    }

    public String getPregunta_26() {
        return pregunta_26;
    }

    public void setPregunta_26(String pregunta_26) {
        this.pregunta_26 = pregunta_26;
    }

    public String getPregunta_27() {
        return pregunta_27;
    }

    public void setPregunta_27(String pregunta_27) {
        this.pregunta_27 = pregunta_27;
    }

    public String getAporta() {
        return aporta;
    }

    public void setAporta(String aporta) {
        this.aporta = aporta;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCuantos_coches() {
        return cuantos_coches;
    }

    public void setCuantos_coches(String cuantos_coches) {
        this.cuantos_coches = cuantos_coches;
    }

    public String getCuartos() {
        return cuartos;
    }

    public void setCuartos(String cuartos) {
        this.cuartos = cuartos;
    }

    public String getCuartos_dormir() {
        return cuartos_dormir;
    }

    public void setCuartos_dormir(String cuartos_dormir) {
        this.cuartos_dormir = cuartos_dormir;
    }

    public String getFocos() {
        return focos;
    }

    public void setFocos(String focos) {
        this.focos = focos;
    }

    public String getBanos() {
        return banos;
    }

    public void setBanos(String banos) {
        this.banos = banos;
    }

    public String getRegadera() {
        return regadera;
    }

    public void setRegadera(String regadera) {
        this.regadera = regadera;
    }

    public String getEstufa() {
        return estufa;
    }

    public void setEstufa(String estufa) {
        this.estufa = estufa;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(String tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public String getTipo_piso() {
        return tipo_piso;
    }

    public void setTipo_piso(String tipo_piso) {
        this.tipo_piso = tipo_piso;
    }

    public String getAbandono() {
        return abandono;
    }

    public void setAbandono(String abandono) {
        this.abandono = abandono;
    }

    public String getSuma() {
        return suma;
    }

    public void setSuma(String suma) {
        this.suma = suma;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipo_captura() {
        return tipo_captura;
    }

    public void setTipo_captura(String tipo_captura) {
        this.tipo_captura = tipo_captura;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }
}
