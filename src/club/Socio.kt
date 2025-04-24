package club

import java.util.UUID

class Socio(
    val nombre: String,
    val dni: String,
    val sexo: Sexo,
    var email: String? = null
){
    val id: String = UUID.randomUUID().toString()
    private var inscripciones = mutableSetOf<Inscripcion>()
    var pagos = mutableListOf<Pago>()

    fun tomarInscripciones(): MutableSet<Inscripcion> {
        return this.inscripciones
    }
}