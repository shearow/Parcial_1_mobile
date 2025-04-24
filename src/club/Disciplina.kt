package club

import java.util.UUID
import exceptions.UsuarioInscriptoException
import exceptions.CuposCompletosException

class Disciplina(
    var nombre: String,
    var tipo: TipoDisciplina,
    capacidadMaxima: Int
){
    val id: String = UUID.randomUUID().toString()
    var capacidadMaxima: Int = if(capacidadMaxima < 0) 0 else capacidadMaxima
    private var sociosInscriptos = mutableSetOf<Socio>()

    private fun validarSocioYaInscripto(socio: Socio): Boolean {
        return this.sociosInscriptos.any { it === socio }
    }

    fun validarDisponibilidadCupos(): Boolean {
        return capacidadMaxima > this.sociosInscriptos.size
    }

    fun cantidadCuposDisponibles(): Int {
        return capacidadMaxima - this.sociosInscriptos.size
    }

    fun agregarInscripcion(socio: Socio){
        if(this.validarSocioYaInscripto(socio)){
            throw UsuarioInscriptoException("El usuario ${socio.nombre} ya se encontraba inscripto en la disciplina ${this.nombre}")
        }
        if(!validarDisponibilidadCupos()){
            throw CuposCompletosException("Cupos agotados en la disciplina ${this.nombre}")
        }
        this.sociosInscriptos.add(socio)
    }

    fun eliminarInscripcion(socio: Socio): Boolean {
        return this.sociosInscriptos.remove(socio)
    }

    fun tomarSociosInscriptos(): MutableSet<Socio> {
        return this.sociosInscriptos
    }
}