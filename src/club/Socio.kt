package club

import java.util.*

class Socio(
    val nombre: String,
    val dni: Int,
    val sexo: Sexo,
    var email: String? = null
){
    val id: String = UUID.randomUUID().toString()
    private var inscripciones = mutableSetOf<Inscripcion>()
    private var pagos = mutableListOf<Pago>()
    private var deudas = mutableListOf<Deuda>()

    fun agregarInscripcion(disciplina: Disciplina){
        val insc = this.inscripciones.find { it.disciplina == disciplina }
        if(insc != null){
            insc.estado = EstadoInscripcion.ACTIVA
            return
        }
        this.inscripciones.add(Inscripcion(disciplina))
    }

    fun eliminarInscripcion(disciplina: Disciplina){
        val inscripcion = this.inscripciones.find { it.disciplina == disciplina }
        if (inscripcion != null){
            this.inscripciones.remove(inscripcion)
        }
    }

    fun mostrarDatos(){
        println("-Nombre: ${this.nombre}, DNI: ${this.dni}, Sexo: ${this.sexo}")
    }

    fun tomarInscripciones(): MutableSet<Inscripcion> {
        return this.inscripciones
    }

    fun tomarDeudaEnDisciplina(disciplina: Disciplina?): Deuda? {
        return this.deudas.find { it.disciplina == disciplina }
    }

    fun agregarDeuda(deuda: Deuda){
        this.deudas.add(deuda)
    }

    fun eliminarDeuda(deuda: Deuda){
        this.deudas.remove(deuda)
    }

    fun agregarPago(pago: Pago){
        this.pagos.add(pago)
    }

    fun estaInscriptoEnDisciplina(disciplina: Disciplina): Boolean {
        return this.inscripciones.any { it.disciplina == disciplina && it.estado != EstadoInscripcion.INACTIVA }
    }

    fun tieneInscripcion(disciplina: Disciplina): Boolean {
        return this.inscripciones.any { it.disciplina == disciplina }
    }

    fun tieneDeudas(): Boolean {
        return this.deudas.isNotEmpty()
    }

    fun obtenerDeudas(): List<Deuda> {
        return deudas
    }

    fun obtenerPagos(): List<Pago> {
        return pagos
    }
}