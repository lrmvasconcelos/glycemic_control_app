package com.glycemiccontrol.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GlycemicTest {

    @SerializedName("IdPaciente")
    @Expose
    private var idPaciente: String? = null
    @SerializedName("ValorGlicemia")
    @Expose
    private var valorGlicemia: Int? = null
    @SerializedName("Observacoes")
    @Expose
    private var observacoes: String? = null

    fun getIdPaciente(): String? {
        return idPaciente
    }

    fun setIdPaciente(idPaciente: String) {
        this.idPaciente = idPaciente
    }

    fun getValorGlicemia(): Int? {
        return valorGlicemia
    }

    fun setValorGlicemia(valorGlicemia: Int?) {
        this.valorGlicemia = valorGlicemia
    }

    fun getObservacoes(): String? {
        return observacoes
    }

    fun setObservacoes(observacoes: String) {
        this.observacoes = observacoes
    }
}