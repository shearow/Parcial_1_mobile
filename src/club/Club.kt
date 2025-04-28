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
            throw Exception("El socio con DNI: ${socio.dni} y sexo: ${socio.sexo} ya se encontraba registrado.")
        }
        this.socios.add(socio)
    }

    private fun socioYaRegistrado(dni: Int, sexo: Sexo): Boolean {
        return this.socios.any { it.dni == dni && it.sexo == sexo}
    }

    fun tomarSocios(): MutableSet<Socio> {
        return this.socios
    }

    fun agregarDisciplina(disciplina: Disciplina) {
        if(this.disciplinaYaRegistrada(disciplina.nombre)){
            throw Exception("La disciplina ${disciplina.nombre} ya se encontraba creada en la lista.")
        }
        this.disciplinas.add(disciplina)
    }

    fun eliminarDisciplina(disciplina: String) {
        if(this.disciplinaYaRegistrada(disciplina)) {
            val disciplinaAEliminar = buscarDisciplinaPorNombre(disciplina)

            if(disciplinaAEliminar != null){
                this.socios.forEach { socio ->
                    socio.eliminarInscripcion(disciplinaAEliminar)
                }
                this.disciplinas.remove(disciplinaAEliminar)
                println("Disciplina eliminada correctamente.")
            }else {
                throw Exception("No existe la disciplina $disciplina.")
            }
        } else {
            throw Exception("No existe la disciplina $disciplina.")
        }
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
        if (!socio.obtenerDeudas().contains(deuda)) {
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
                        val deudaMesAnterior = socio.obtenerDeudas().any {
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

    fun buscarSocioPorDniYSexo(dni: Int, sexo: Sexo): Socio? {
        return socios.find { it.dni == dni && it.sexo == sexo}
    }

    fun buscarDisciplinaPorNombre(nombre: String): Disciplina? {
        return disciplinas.find { it.nombre.equals(nombre, ignoreCase = true) }
    }
}