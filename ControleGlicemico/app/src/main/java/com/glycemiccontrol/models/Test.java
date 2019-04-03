package com.glycemiccontrol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test {

    @SerializedName("dataAvaliacao")
    @Expose
    private String dataAvaliacao;
    @SerializedName("valorGlicemia")
    @Expose
    private Integer valorGlicemia;
    @SerializedName("observacoes")
    @Expose
    private String observacoes;
    @SerializedName("id")
    @Expose
    private String id;

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getValorGlicemia() {
        return valorGlicemia;
    }

    public void setValorGlicemia(Integer valorGlicemia) {
        this.valorGlicemia = valorGlicemia;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
