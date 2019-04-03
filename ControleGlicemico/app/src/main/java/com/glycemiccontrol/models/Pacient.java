package com.glycemiccontrol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pacient {
    @SerializedName("dataNascimento")
    @Expose
    private String dataNascimento;
    @SerializedName("sexo")
    @Expose
    private Integer sexo;
    @SerializedName("endereco")
    @Expose
    private Address endereco;
    @SerializedName("exames")
    @Expose
    private List<Test> exames = null;
    @SerializedName("medico")
    @Expose
    private Doctor medico;
    @SerializedName("acompanhantes")
    @Expose
    private List<Object> acompanhantes = null;
    @SerializedName("dataInscricao")
    @Expose
    private String dataInscricao;
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("sobrenome")
    @Expose
    private String sobrenome;
    @SerializedName("id")
    @Expose
    private String id;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public List<Test> getExames() {
        return exames;
    }

    public void setExames(List<Test> exames) {
        this.exames = exames;
    }

    public Doctor getMedico() {
        return medico;
    }

    public void setMedico(Doctor medico) {
        this.medico = medico;
    }

    public List<Object> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(List<Object> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public String getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(String dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
