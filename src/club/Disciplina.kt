package club

import java.util.UUID

class Disciplina(
    var nombre: String,
    var tipo: TipoDisciplina,
    capacidadMaxima: Int,
    var costoMensual: Double
){
    val id: String = UUID.randomUUID().toString()
    var capacidadMaxima: Int = if(capacidadMaxima < 0) 0 else capacidadMaxima
    private var sociosInscriptos = mutableSetOf<Socio>()

    private fun validarSocioYaInscripto(socio: Socio): Boolean {
        return this.sociosInscriptos.any { it == socio }
    }

    fun tieneCuposDisponibles(): Boolean {
        return capacidadMaxima > this.sociosInscriptos.size
    }

    fun cantidadCuposDisponibles(): Int {
        return capacidadMaxima - this.sociosInscriptos.size
    }

    fun agregarInscripcion(socio: Socio){
        this.sociosInscriptos.add(socio)
    }

    fun eliminarInscripcion(socio: Socio): Boolean {
        return this.sociosInscriptos.remove(socio)
    }

    fun tomarSociosInscriptos(): MutableSet<Socio> {
        return this.sociosInscriptos
    }
}