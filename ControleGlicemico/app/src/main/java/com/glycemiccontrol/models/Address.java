package com.glycemiccontrol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Address {
    @SerializedName("cep")
    @Expose
    private String cep;
    @SerializedName("rua")
    @Expose
    private String rua;
    @SerializedName("numero")
    @Expose
    private Integer numero;
    @SerializedName("bairro")
    @Expose
    private String bairro;
    @SerializedName("complemento")
    @Expose
    private String complemento;
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("pais")
    @Expose
    private Integer pais;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getPais() {
        return pais;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }
}
