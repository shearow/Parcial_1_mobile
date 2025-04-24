package club

import java.util.UUID;

class Socio(
    val nombre: String,
    val dni: String,
    val sexo: Sexo,
    var email: String? = null
){
    val id: String = UUID.randomUUID().toString();
    var inscripciones = mutableSetOf<Inscripcion>();
    var pagos = mutableListOf<Pago>();

    fun tomarInscripciones(): kotlin.collections.MutableSet<Inscripcion> {
        return this.inscripciones;
    }
}