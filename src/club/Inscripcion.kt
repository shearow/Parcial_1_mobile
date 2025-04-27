package club

import java.time.LocalDate
import java.util.UUID

class Inscripcion(
    val disciplina: Disciplina,
    var estado: EstadoInscripcion = EstadoInscripcion.ACTIVA
){
    val fechaInscripcion: LocalDate = LocalDate.now()
    val id: String = UUID.randomUUID().toString()
}