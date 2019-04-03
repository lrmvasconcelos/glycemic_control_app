package com.glycemiccontrol.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Doctor {


    @SerializedName("crm")
    @Expose
    var crm: String? = null
    @SerializedName("atuacao")
    @Expose
    var atuacao: String? = null
    @SerializedName("localTrabalho")
    @Expose
    var localTrabalho: String? = null
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
