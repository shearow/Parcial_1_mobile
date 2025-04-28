import club.*

fun main() {

        val club = Club("Los Siberianos")
        println("Bienvenido al club ${club.nombre} 🏆")

        while (true) {
                println("\n=== Menú Principal ===")
                println("1. Agregar Socio")
                println("2. Agregar Disciplina")
                println("3. Listar Socios")
                println("4. Listar Disciplinas")
                println("5. Inscribir Socio a Disciplina")
                println("6. Socios Inscriptos en Disciplina")
                println("7. Ver Deudas de un Socio")
                println("8. Pago de una deuda")
                println("9. Ver Pagos de un Socio")
                println("10. Eliminar Disciplina")
                println("11. Desinscribir Socio en Disciplina")
                println("12. Actualizar mes (SE DEBERIA EJECUTAR EL PRIMER DIA DE CADA MES DE FORMA AUTOMATICA a PRIMERA HORA) - (Agrega cuotas mensuales a los socios inscriptos y liberando socios inactivos en disciplinas)")
                println("13. Salir")
                print("Ingrese una opción: ")

                when (readlnOrNull()?.toIntOrNull()) {
                        1 -> {
                                val nombre = ingresarNombre()
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()
                                val email = ingresarEmail()

                                try {
                                        club.agregarSocio(Socio(nombre, dni, sexo, email))
                                        println("Socio $nombre agregado correctamente.")
                                } catch (e: Exception) {
                                        println("Error: ${e.message}")
                                }
                        }
                        2 -> {
                                val nombre = ingresarDisciplina()
                                val tipo = ingresarTipoDisciplina()
                                val capacidad = ingresarCapacidadMaxima()
                                val costo = ingresarCostoMensual()

                                try {
                                        club.agregarDisciplina(Disciplina(nombre, tipo, capacidad, costo))
                                        println("Disciplina $nombre agregada correctamente.")
                                } catch (e: Exception) {
                                        println("Error: ${e.message}")
                                }
                        }
                        3 -> {
                                println("\n**Socios Registrados**")
                                club.tomarSocios().forEach { it.mostrarDatos() }
                        }
                        4 -> {
                                println("\n**Disciplinas Disponibles**")
                                club.tomarDisciplinas().forEach { println("- ${it.nombre} (Capacidad: ${it.capacidadMaxima})") }
                        }
                        5 -> {
                                println("Ingrese los datos del socio: ")
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()
                                val nombreDisciplina = ingresarDisciplina()

                                val socio = club.buscarSocioPorDniYSexo(dni, sexo)
                                val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                if (socio != null && disciplina != null) {
                                        try {
                                                club.inscribirSocioADisciplina(socio, disciplina)
                                                println("Inscripción realizada.")
                                        } catch (e: Exception) {
                                                println("Error: ${e.message}")
                                        }
                                } else if (socio == null){
                                        println("Error: El socio con DNI $dni y sexo $sexo no existe.")
                                } else {
                                        println("Error: La disciplina $nombreDisciplina no existe.")
                                }
                        }
                        6 -> {
                                println("Ingrese el nombre de la disciplina:")
                                val nombreDisciplina = ingresarDisciplina()
                                val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                if (disciplina != null) {
                                        println("Socios inscriptos en la disciplina: ${disciplina.nombre}")
                                        val sociosInscriptos = disciplina.tomarSociosInscriptos()

                                        if (sociosInscriptos.isNotEmpty()) {
                                                sociosInscriptos.forEach {
                                                        it.mostrarDatos()
                                                }
                                        } else {
                                                println("No hay socios inscriptos en esta disciplina.")
                                        }
                                } else {
                                        println("No existe una disciplina con el nombre '$nombreDisciplina'.")
                                }
                        }
                        7 -> {
                                println("Ingrese los datos del socio:")
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()

                                val socio = club.buscarSocioPorDniYSexo(dni, sexo)

                                if (socio != null) {
                                        val deudas = socio.obtenerDeudas()
                                        if (deudas.isNotEmpty()) {
                                                println("Deudas del socio ${socio.nombre}:")
                                                deudas.forEach { deuda ->
                                                        println("- ${deuda.mes} ${deuda.anio} | ${deuda.disciplina.nombre} | \$${deuda.monto} | ${deuda.razonDeuda} ${deuda.detalles ?: ""}")
                                                }
                                        } else {
                                                println("El socio no tiene deudas.")
                                        }
                                } else {
                                        println("No existe el socio con DNI $dni y sexo $sexo.")
                                }
                        }
                        8 -> {
                                println("Ingrese los datos del socio:")
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()
                                val nombreDisciplina = ingresarDisciplina()

                                val socio = club.buscarSocioPorDniYSexo(dni, sexo)
                                val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                if (socio != null && disciplina != null) {
                                        try {
                                                val deudaEncontrada = socio.tomarDeudaEnDisciplina(disciplina)

                                                if (deudaEncontrada != null) {
                                                        club.pagarDeuda(socio, deudaEncontrada)
                                                        println("Deuda pagada correctamente.")
                                                } else {
                                                        println("No hay deuda pendiente para esa disciplina.")
                                                }
                                        } catch (e: Exception) {
                                                println("Error: ${e.message}")
                                        }
                                } else if (socio == null){
                                        println("Error: El socio con DNI $dni y sexo $sexo no existe.")
                                } else {
                                        println("Error: La disciplina $nombreDisciplina no existe.")
                                }
                        }
                        9 -> {
                                println("Ingrese los datos del socio:")
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()

                                val socio = club.buscarSocioPorDniYSexo(dni, sexo)

                                if (socio != null) {
                                        val pagos = socio.obtenerPagos()
                                        if (pagos.isNotEmpty()) {
                                                println("Pagos del socio ${socio.nombre}:")
                                                pagos.forEach { pago ->
                                                        println("- ${pago.anioCuota} | ${pago.mesCuota} | \$${pago.monto}")
                                                }
                                        } else {
                                                println("El socio no tiene pagos registrados.")
                                        }
                                } else {
                                        println("No existe el socio con DNI $dni y sexo $sexo.")
                                }
                        }
                        10 -> {
                                val nombreDisciplina = ingresarDisciplina()

                                try {
                                        club.eliminarDisciplina(nombreDisciplina)
                                } catch (e: Exception) {
                                        println("Error: ${e.message}")
                                }
                        }
                        11 -> {
                                println("Ingrese los datos del socio: ")
                                val dni = ingresarDNI()
                                val sexo = ingresarSexo()
                                val nombreDisciplina = ingresarDisciplina()

                                val socio = club.buscarSocioPorDniYSexo(dni, sexo)
                                val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                if (socio != null && disciplina != null) {
                                        try {
                                                club.cancelarInscripcion(socio, disciplina)
                                                println("Inscripción cancelada correctamente.")
                                        } catch (e: Exception) {
                                                println("Error al cancelar la inscripción: ${e.message}")
                                        }
                                } else if (socio == null){
                                        println("Error: El socio con DNI $dni y sexo $sexo no existe.")
                                } else {
                                        println("Error: La disciplina $nombreDisciplina no existe.")
                                }
                        }
                        12 -> {
                                println("Actualizando mes en el sistema")
                                club.procesarMesNuevo()
                        }
                        13 -> {
                                println("Cerrando el sistema")
                                break
                        }
                        else -> println("Opción inválida. Intente de nuevo.")
                }
        }
}

fun ingresarNombre(): String {
        val nombre: String
        while (true) {
                print("Nombre del socio: ")
                val nombreInput = readlnOrNull()?.trim() ?: ""

                if (nombreInput.isEmpty()) {
                        println("Debes completar el nombre para continuar.")
                } else if (nombreInput.any { it.isDigit() }) {
                        println("El nombre no debe contener números.")
                } else {
                        nombre = nombreInput
                        break
                }
        }
        return nombre
}

fun ingresarDNI(): Int {
        val dni: Int
        while (true) {
                print("DNI del socio: ")
                val input = readlnOrNull()
                val dniInput = input?.toIntOrNull()

                if (dniInput == null) {
                        println("Debes ingresar un número de DNI válido.")
                } else {
                        dni = dniInput
                        break
                }
        }
        return dni
}

fun ingresarSexo(): Sexo {
        val sexo: Sexo
        while (true) {
                print("Sexo (${Sexo.entries.joinToString("/") { it.name }}): ")
                val sexoInput = readlnOrNull()?.trim()?.uppercase() ?: ""

                if (Sexo.entries.any { it.name == sexoInput }) {
                        sexo = Sexo.valueOf(sexoInput)
                        break
                } else {
                        println("Sexo inválido. Debe ser ${Sexo.entries.joinToString("/") { it.name }}.")
                }
        }
        return sexo
}

fun ingresarEmail(): String? {
        val email: String?
        while (true) {
                print("Email (dejar vacío si no desea agregar uno): ")
                val emailInput = readlnOrNull()?.trim()

                if (emailInput.isNullOrEmpty()) {
                        email = null
                        break
                } else if (Regex("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$").matches(emailInput)) {
                        email = emailInput
                        break
                } else {
                        println("Formato de email inválido. Ejemplo válido: correo@ejemplo.com")
                }
        }
        return email
}

fun ingresarDisciplina(): String {
        var nombre: String
        while (true) {
                print("Nombre de la disciplina: ")
                nombre = readlnOrNull()?.trim() ?: ""

                if (nombre.isEmpty()) {
                        println("El nombre de la disciplina no puede estar vacío. Por favor, ingrésalo nuevamente.")
                }else {
                        break
                }
        }
        return nombre
}

fun ingresarTipoDisciplina(): TipoDisciplina {
        val tipo: TipoDisciplina
        while (true) {
                print("Tipo (${TipoDisciplina.entries.joinToString("/") { it.name }}): ")
                val tipoInput = readlnOrNull()?.trim()?.uppercase() ?: ""

                if (TipoDisciplina.entries.any { it.name == tipoInput }) {
                        tipo = TipoDisciplina.valueOf(tipoInput)
                        break
                } else {
                        println("Tipo inválido. Debe ser uno de los siguientes: ${TipoDisciplina.entries.joinToString("/") { it.name }}.")
                }
        }
        return tipo
}

fun ingresarCapacidadMaxima(): Int{
        var capacidad: Int
        while (true) {
                print("Capacidad máxima: ")
                capacidad = readlnOrNull()?.toIntOrNull() ?: -1

                if (capacidad >= 0) {
                        break
                } else {
                        println("La capacidad máxima debe ser mayor o igual a 0. Inténtalo nuevamente.")
                }
        }
        return capacidad
}

fun ingresarCostoMensual(): Double {
        var costo: Double
        while (true) {
                print("Costo mensual: ")
                costo = readlnOrNull()?.toDoubleOrNull() ?: -1.0

                if (costo >= 0.0) {
                        break
                } else {
                        println("El costo mensual debe ser mayor o igual a 0. Inténtalo nuevamente.")
                }
        }
        return costo
}