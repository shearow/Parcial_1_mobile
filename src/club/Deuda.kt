package club

import java.time.Month
import java.util.UUID

class Deuda(
    val disciplina: Disciplina,
    val anio: Int,
    val mes: Month,
    val monto: Double,
    val razonDeuda: RazonDeuda,
    val detalles: String? = null
){
    val id: String = UUID.randomUUID().toString()
}
