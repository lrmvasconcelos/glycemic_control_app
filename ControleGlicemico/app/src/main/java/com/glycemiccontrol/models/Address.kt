package com.glycemiccontrol.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address {
    @SerializedName("cep")
    @Expose
    var cep: String? = null
    @SerializedName("rua")
    @Expose
    var rua: String? = null
    @SerializedName("numero")
    @Expose
    var numero: Int? = null
    @SerializedName("bairro")
    @Expose
    var bairro: String? = null
    @SerializedName("complemento")
    @Expose
    var complemento: String? = null
    @SerializedName("cidade")
    @Expose
    var cidade: String? = null
    @SerializedName("estado")
    @Expose
    var estado: Int? = null
    @SerializedName("pais")
    @Expose
    var pais: Int? = null
}
