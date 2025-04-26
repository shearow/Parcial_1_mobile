package club

import java.time.LocalDate
import java.util.UUID

class Inscripcion(
    val disciplina: Disciplina
){
    val fechaInscripcion: LocalDate = LocalDate.now()
    val id: String = UUID.randomUUID().toString()
    var estado: EstadoInscripcion = EstadoInscripcion.ACTIVA
}