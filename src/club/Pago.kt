package club

import java.time.LocalDate
import java.time.Month
import java.util.UUID

class Pago(
    val disciplina: Disciplina,
    val anioCuota: Int,
    val mesCuota: Month,
    var monto: Double
){
    val id: String = UUID.randomUUID().toString()
    val fechaDePago: LocalDate = LocalDate.now()
}