package mx.gob.cdmx.centroh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mx.gob.cdmx.centroh.db.Anotaciones.AutoIncrement;
import mx.gob.cdmx.centroh.db.Anotaciones.PrimaryKey;

public class Usuario implements Serializable {

    @PrimaryKey
    @AutoIncrement
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("paterno")
    public String paterno;

    @SerializedName("materno")
    public String materno;

    @SerializedName("email")
    public String email;

    @SerializedName("user_id")
    public int user_id;

    @SerializedName("access_token")
    public String access_token;

    @SerializedName("token_type")
    public String token_type;

    @SerializedName("role")
    public String role;


    public Usuario() {
    }

    public Usuario(int id, String name, String paterno, String materno, String email, int user_id, String access_token, String token_type, String role) {
        this.id = id;
        this.name = name;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
        this.user_id = user_id;
        this.access_token = access_token;
        this.token_type = token_type;
        this.role = role;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
