package club

import exceptions.CuposCompletosException
import exceptions.SocioConDeudasException
import exceptions.UsuarioInscriptoException
import java.time.LocalDate

class Club(
    var nombre: String
){
    private var socios = mutableSetOf<Socio>()
    private var disciplinas = mutableSetOf<Disciplina>()

    fun agregarSocio(socio: Socio){
        if(this.socioYaRegistrado(socio.dni, socio.sexo)){
            throw Exception("El socio ${socio.nombre} ya se encontraba registrado.")
        }
        this.socios.add(socio)
    }

    private fun socioYaRegistrado(dni: String, sexo: Sexo): Boolean {
        return this.socios.any { it.dni == dni && it.sexo == sexo}
    }

    fun tomarSocios(): MutableSet<Socio> {
        return this.socios
    }

    fun pintarInformacionSocios(){
        println("\n•Socios inscriptos en el club")
        this.socios.forEach {
            println("Id: ${it.id}, Nombre: ${it.nombre}, Dni: ${it.dni}, Sexo: ${it.sexo}${it.email?.let { email -> ", Email: $email" } ?: ""}")
        }
    }

    fun agregarDisciplina(disciplina: Disciplina) {
        if(this.disciplinaYaRegistrada(disciplina.nombre)){
            throw Exception("La disciplina ${disciplina.nombre} ya se encontraba creada en la lista.")
        }
        this.disciplinas.add(disciplina)
    }

    private fun disciplinaYaRegistrada(nombre: String): Boolean {
        return this.disciplinas.any { it.nombre == nombre}
    }

    fun tomarDisciplinas(): MutableSet<Disciplina> {
        return this.disciplinas
    }

    fun inscribirSocioADisciplina(socio: Socio, disciplina: Disciplina){
        if(socio.tieneDeudas()){
            throw SocioConDeudasException("El socio ${socio.nombre} tiene deudas, por lo que no puede inscribirse a nuevas disciplinas.")
        }
        if(socio.estaInscriptoEnDisciplina(disciplina)){
            throw UsuarioInscriptoException("El socio ${socio.nombre} ya se encontraba inscripto en la disciplina ${disciplina.nombre}.")
        }
        if(!disciplina.tieneCuposDisponibles()){
            throw CuposCompletosException("La disciplina ${disciplina.nombre} no tiene cupos disponibles.")
        }
        socio.agregarInscripcion(disciplina)
        disciplina.agregarInscripcion(socio)

        val diaActual = LocalDate.now()
        val deudita = Deuda(disciplina,diaActual.year, diaActual.month, disciplina.costoMensual, RazonDeuda.CUOTA_MENSUAL)
        socio.agregarDeuda(deudita)
    }

    fun pagarDeuda(socio: Socio, deuda: Deuda){
        if (!socio.tomarDeudas().contains(deuda)) {
            throw Exception("La deuda seleccionada no pertenece al socio ${socio.nombre}.")
        }
        socio.eliminarDeuda(deuda)
        socio.agregarPago(Pago(deuda.disciplina, deuda.anio, deuda.mes, deuda.monto))
    }

    fun cancelarInscripcion(socio: Socio, disciplina: Disciplina){
        val inscripcionBuscada = socio.tomarInscripciones().find { it.disciplina == disciplina }

        if(inscripcionBuscada?.estado != EstadoInscripcion.ACTIVA){
            return
        }
        inscripcionBuscada.estado = EstadoInscripcion.CANCELADA
    }

    fun procesarMesNuevo() {
        val diaActual = LocalDate.now()

        this.socios.forEach { socio ->
            val inscripcionesProcesables = socio.tomarInscripciones().filter {
                it.estado == EstadoInscripcion.ACTIVA || it.estado == EstadoInscripcion.CANCELADA
            }

            inscripcionesProcesables.forEach { inscripcion ->
                val disciplina = inscripcion.disciplina

                when (inscripcion.estado) {
                    EstadoInscripcion.CANCELADA -> {
                        inscripcion.estado = EstadoInscripcion.INACTIVA
                        disciplina.eliminarInscripcion(socio)
                    }
                    EstadoInscripcion.ACTIVA -> {
                        val mesAnterior = diaActual.minusMonths(1)
                        val deudaMesAnterior = socio.tomarDeudas().any {
                            it.disciplina == disciplina &&
                            it.mes == mesAnterior.month &&
                            it.anio == mesAnterior.year
                        }

                        if(deudaMesAnterior){
                            inscripcion.estado = EstadoInscripcion.INACTIVA
                            disciplina.eliminarInscripcion(socio)
                        }else{
                            val nuevaDeuda = Deuda(disciplina, diaActual.year, diaActual.month, disciplina.costoMensual, RazonDeuda.CUOTA_MENSUAL)
                            socio.agregarDeuda(nuevaDeuda)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}