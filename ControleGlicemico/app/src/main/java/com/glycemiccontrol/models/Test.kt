package com.glycemiccontrol.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Test {

    @SerializedName("dataAvaliacao")
    @Expose
    var dataAvaliacao: String? = null
    @SerializedName("valorGlicemia")
    @Expose
    var valorGlicemia: Int? = null
    @SerializedName("observacoes")
    @Expose
    var observacoes: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}
