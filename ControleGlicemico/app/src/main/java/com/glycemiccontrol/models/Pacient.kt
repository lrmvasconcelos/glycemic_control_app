package com.glycemiccontrol.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pacient {
    @SerializedName("dataNascimento")
    @Expose
    var dataNascimento: String? = null
    @SerializedName("sexo")
    @Expose
    var sexo: Int? = null
    @SerializedName("endereco")
    @Expose
    var endereco: Address? = null
    @SerializedName("exames")
    @Expose
    var exames: List<Test>? = null
    @SerializedName("medico")
    @Expose
    var medico: Doctor? = null
    @SerializedName("acompanhantes")
    @Expose
    var acompanhantes: List<Any>? = null
    @SerializedName("dataInscricao")
    @Expose
    var dataInscricao: String? = null
    @SerializedName("matricula")
    @Expose
    var matricula: String? = null
    @SerializedName("nome")
    @Expose
    var nome: String? = null
    @SerializedName("sobrenome")
    @Expose
    var sobrenome: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}
