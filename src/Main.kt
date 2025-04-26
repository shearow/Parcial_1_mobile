import club.*
import exceptions.SexoInvalidoException
import exceptions.TipoDisciplinaInvalidoException

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
                println("6. Ver Deudas de un Socio")
                println("7. Pago de una deuda")
                println("8. Ver Pagos de un Socio")
                println("9. Salir")
                print("Ingrese una opción: ")

                when (readLine()?.toIntOrNull()) {
                        1 -> {
                                print("Nombre del socio: ")
                                val nombre = readLine() ?: ""
                                print("DNI del socio: ")
                                val dni = readLine() ?: ""
                                print("Sexo (MASCULINO/FEMENINO/OTRO): ")
                                val sexoInput = readLine() ?: ""

                                val sexo = try {
                                        Sexo.valueOf(sexoInput.uppercase())
                                } catch (e: IllegalArgumentException) {
                                        throw SexoInvalidoException()
                                }

                                print("Email: ")
                                val email = readLine().takeIf { it?.isNotBlank() == true }

                                val socio = Socio(nombre, dni, sexo, email)
                                try {
                                        club.agregarSocio(socio)
                                        println("Socio agregado")
                                } catch (e: Exception) {
                                        println("Error: ${e.message}")
                                }
                        }

                        2 -> {
                                print("Nombre de la disciplina: ")
                                val nombre = readLine() ?: ""
                                print("Tipo (EQUIPO/INDIVIDUAL/OPTATIVA): ")
                                val tipoInput = readLine() ?: ""

                                val tipo = try {
                                        TipoDisciplina.valueOf(tipoInput.uppercase())
                                } catch (e: IllegalArgumentException) {
                                        throw TipoDisciplinaInvalidoException()
                                }

                                print("Capacidad máxima: ")
                                val capacidad = readLine()?.toIntOrNull() ?: 0
                                print("Costo mensual: ")
                                val costo = readLine()?.toDoubleOrNull() ?: 0.0

                                val disciplina = Disciplina(nombre, tipo, capacidad, costo)
                                try {
                                        club.agregarDisciplina(disciplina)
                                        println("Disciplina agregada.")
                                } catch (e: Exception) {
                                        println("Error: ${e.message}")
                                }
                        }

                        3 -> {
                                println("\n**Socios Registrados**")
                                club.tomarSocios().forEach { println("- ${it.nombre} (DNI: ${it.dni})") }
                        }

                        4 -> {
                                println("\n**Disciplinas Disponibles**")
                                club.tomarDisciplinas().forEach { println("- ${it.nombre} (Capacidad: ${it.capacidadMaxima})") }
                        }

                        5 -> {
                                println("Ingrese el DNI del socio:")
                                val dniSocio = readLine() ?: ""
                                println("Ingrese nombre de la disciplina:")
                                val nombreDisciplina = readLine() ?: ""

                                val socio = club.buscarSocioPorDni(dniSocio)
                                val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                if (socio != null && disciplina != null) {
                                        try {
                                                club.inscribirSocioADisciplina(socio, disciplina)
                                                println("Inscripción realizada")
                                        } catch (e: Exception) {
                                                println("Error: ${e.message}")
                                        }
                                } else {
                                        println("No se encontró socio o disciplina.")
                                }
                        }

                        6 -> {
                                println("Ingrese el DNI del socio:")
                                val dniSocio = readLine() ?: ""

                                val socio = club.buscarSocioPorDni(dniSocio)

                                if (socio != null) {
                                        val deudas = socio.obtenerDeudas()  // suponiendo que Socio tiene una lista 'deudas'
                                        if (deudas.isNotEmpty()) {
                                                println("Deudas del socio ${socio.nombre}:")
                                                deudas.forEach { deuda ->
                                                        println("- ${deuda.mes} ${deuda.anio} | ${deuda.disciplina.nombre} | \$${deuda.monto} | ${deuda.razonDeuda} ${deuda.detalles ?: ""}")
                                                }
                                        } else {
                                                println("El socio no tiene deudas.")
                                        }
                                } else {
                                        println("No se encontró el socio.")
                                }
                        }

                        7 -> {
                                println("Ingrese el DNI del socio:")
                                val dniSocio = readLine() ?: ""
                                val socio = club.buscarSocioPorDni(dniSocio)

                                if (socio != null) {
                                        println("Ingrese el nombre de la disciplina para pagar la deuda:")
                                        val nombreDisciplina = readLine() ?: ""
                                        val disciplina = club.buscarDisciplinaPorNombre(nombreDisciplina)

                                        if (disciplina != null) {
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
                                        } else {
                                                println("No se encontró la disciplina.")
                                        }
                                } else {
                                        println("No se encontró el socio.")
                                }
                        }

                        8 -> {
                                println("Ingrese el DNI del socio:")
                                val dniSocio = readLine() ?: ""

                                val socio = club.buscarSocioPorDni(dniSocio)

                                if (socio != null) {
                                        val pagos = socio.obtenerPagos()  // suponiendo que Socio tiene una lista 'pagos'
                                        if (pagos.isNotEmpty()) {
                                                println("Pagos del socio ${socio.nombre}:")
                                                pagos.forEach { pago ->
                                                        println("- ${pago.anioCuota} | ${pago.mesCuota} | \$${pago.monto}")
                                                }
                                        } else {
                                                println("El socio no tiene pagos registrados.")
                                        }
                                } else {
                                        println("No se encontró el socio.")
                                }

                        }
                        9 -> {
                                println("Cerrando el sistema")
                                break
                        }
                        else -> println("Opción inválida. Intente de nuevo.")
                }
        }

        /* EJEMPLO DE EJECUCION

        /*************** SOCIO - Crear Socio ***************/
        val socioTest1 = Socio("Jorge", "321231", Sexo.MASCULINO, "emailTest@gmail.com")
        val socioTest2 = Socio("Maria", "3123", Sexo.FEMENINO)


        /*************** DISCIPLINA - Crear disciplina ***************/
        val disciplina1 = Disciplina("Futbol", TipoDisciplina.EQUIPO, 2, 15000.0)
        val disciplina2 = Disciplina("Basquet", TipoDisciplina.EQUIPO, 10, 20000.0)
        val disciplina3 = Disciplina("Natacion", TipoDisciplina.OPTATIVA, 30, 10000.0)
        val disciplina4 = Disciplina("Gimnasio", TipoDisciplina.INDIVIDUAL, 50, 20000.0)



        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// DISCIPLINA ////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** DISCIPLINA - Atributos y funcionalidad ***************/
        println("\n•Disciplina atributos:")
        println("Id: ${disciplina1.id}, Nombre: ${disciplina1.nombre}, Tipo: ${disciplina1.tipo}, Capacidad maxima: ${disciplina1.capacidadMaxima}, costo mensual: ${disciplina1.costoMensual}")
        println("Cantidad de cupos disponibles: ${disciplina1.cantidadCuposDisponibles()}")
        println("Hay cupo disponible? ${disciplina1.tieneCuposDisponibles()}")


        /*************** DISCIPLINA - Agregar inscripto ***************/
        println("\n•Agregar inscripto:")
        println("Agregando al usuario1:")
        try{
                disciplina1.agregarInscripcion(socioTest1)
                println("Usuario agregado exitosamente en la disciplina ${disciplina1.nombre}.")
        }catch(e: Exception){
                println("El usuario ${socioTest1.nombre} no pudo ser anotado en ${disciplina1.nombre} por la siguiente razòn: ${e.message}.")
        }

        println("\nAgregando al usuario1 a la misma disciplina:")
        try{
                disciplina1.agregarInscripcion(socioTest1)
                println("Usuario agregado exitosamente en la disciplina ${disciplina1.nombre}")
        }catch(e: Exception){
                println("El usuario ${socioTest1.nombre} no pudo ser anotado en ${disciplina1.nombre} por la siguiente razon: ${e.message}")
        }

        println("\nAgregando al usuario2:")
        try{
                disciplina1.agregarInscripcion(socioTest2)
                println("Usuario agregado exitosamente en la disciplina ${disciplina1.nombre}")
        }catch(e: Exception){
                println("El usuario ${socioTest2.nombre} no pudo ser anotado en ${disciplina1.nombre} por la siguiente razon: ${e.message}")
        }


        /*************** DISCIPLINA - Tomar todos los inscriptos ***************/
        println("\n•Recorriendo socios inscriptos en la disciplina ${disciplina1.nombre}:")
        disciplina1.tomarSociosInscriptos().forEach { println("Nombre: ${it.nombre}, Id: ${it.id}") }


        /*************** DISCIPLINA - Eliminar inscripto ***************/
        println("\n•Eliminando al socio1 (${socioTest1.nombre}): ")

        if(disciplina1.eliminarInscripcion(socioTest1)){
                println("${socioTest1.nombre} eliminado correctamente")
        }else{
                println("${socioTest1.nombre} no estaba inscripto en la disciplina ${disciplina1.nombre}")
        }


        /*************** DISCIPLINA - Cambiar precio ***************/
        println("\n•Cambiando costo mensual de la disciplina ${disciplina1.nombre}: " +
                "\nPrecio viejo: ${disciplina1.nombre}. ")
        disciplina1.costoMensual = 10000.0
        println("Precio actual: ${disciplina1.costoMensual}")


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// CLUB ///////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////


        /*************** CLUB - Instanciar Club ***************/
        val club = Club("Los Siberianos")
        println("\nBienvenidos al club ${club.nombre}")

        /*************** CLUB - Agregar socio ***************/
        println("\n•Agregando socios al club.")
        try{
                club.agregarSocio(socioTest1)
                println("-Nuevo socio ${socioTest1.nombre} agregado correctamente al club.")

                club.agregarSocio(socioTest2)
                println("Nuevo socio ${socioTest2.nombre} agregado correctamente al club.")

                club.agregarSocio(socioTest1)
        }catch(e : Exception){
                println(e.message)
        }


        /*************** CLUB - Pintar datos de los socios ***************/
        println("Datos de los socios:")
        club.pintarInformacionSocios()


        /*************** CLUB - Tomar colección de todos los socios ***************/
        println("\n•Cantidad de Socios totales registrados: ${club.tomarSocios().size}.")


        /*************** CLUB - Agregar disciplina al club ***************/
        println("\n•Agregando disciplina ${disciplina1.nombre} al club:")
        try{
                club.agregarDisciplina(disciplina1)
                println("Se agrego correctamente la disciplina ${disciplina1.nombre}, cupos disponibles: ${disciplina1.cantidadCuposDisponibles()}")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Agregando disciplina ${disciplina2.nombre} al club:")
        try{
                club.agregarDisciplina(disciplina2)
                println("Se agrego correctamente la disciplina ${disciplina2.nombre}, cupos disponibles: ${disciplina2.cantidadCuposDisponibles()}")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Agregando disciplina ${disciplina1.nombre} al club: ")
        try{
                club.agregarDisciplina(disciplina1)
                println("NO DEBERIA PONER AGREGARSE LA DISCIPLINA ${disciplina1.nombre} ya que se agrego anteriormente")
        }catch(e: Exception){
                println(e.message)
        }


        /*************** CLUB - Tomar todas las disciplinas ***************/
        println("\n•Tomar todas las disciplinas del club:")
        club.tomarDisciplinas().forEach {println("-Nombre: ${it.nombre}, cupos: (${it.capacidadMaxima - it.cantidadCuposDisponibles()}/${it.capacidadMaxima})")}


        /*************** CLUB - Inscribir socio a disciplina ***************/
        println("\n•Inscribiendo socio a disciplinas")
        println("\n-Agregando socio1 a disciplina1:")
        try{
                club.inscribirSocioADisciplina(socioTest1, disciplina1)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Agregando socio1 a disciplina1 por segunda vez: DEBERIA FALLAR")
        try{
                club.inscribirSocioADisciplina(socioTest1, disciplina1)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }


        /*************** CLUB - Deudas y Pagos ***************/

        println("\n•Testeando deudas:")
        println("-Inscribiendo al socio2 en la disciplina2:")
        try{
                club.inscribirSocioADisciplina(socioTest2, disciplina2)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Todas las deudas del socio2: ")
        socioTest2.tomarDeudas().forEach { println("Disciplina: ${it.disciplina.nombre}, Mes: ${it.mes}, Anio: ${it.anio}, Monto: ${it.monto}") }

        println("\n-Inscribiendo al socio2 en la disciplina2: NO DEBERIA DEJAR INSCRIBIRSE YA QUE TIENE UNA DEUDA Y ENCIMA ES LA MISMA DISCIPLINA ANOTADA")
        try{
                club.inscribirSocioADisciplina(socioTest2, disciplina2)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Pagando deuda de la disciplina2 (${disciplina2.nombre}): ")
        try {
                val deudaEncontrada = socioTest2.tomarDeudaEnDisciplina(disciplina2)

                if(deudaEncontrada != null){
                        club.pagarDeuda(socioTest2, deudaEncontrada)
                        println("Deuda pagada correctamente.")
                }else{
                        println("No hay deuda pendiente para esa disciplina.")
                }
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Inscribiendo al socio2 en la disciplina2: NO DEBERIA DEJAR INSCRIBIRSE YA QUE ES LA MISMA DISCIPLINA ANOTADA")
        try{
                club.inscribirSocioADisciplina(socioTest2, disciplina2)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Inscribiendo al socio2 en la disciplina3 (${disciplina3.nombre}): DEBERIA DEJAR YA QUE NO TIENE DEUDAS Y NO ESTA ANOTADA")
        try{
                club.inscribirSocioADisciplina(socioTest2, disciplina3)
                println("Se inscribio correctamente a la disciplina")
        }catch(e: Exception){
                println(e.message)
        }

        socioTest2.tomarDeudas()
        println("\n-Todas las deudas del socio2: ")
        socioTest2.tomarDeudas().forEach { println("Disciplina: ${it.disciplina.nombre}, Mes: ${it.mes}, Anio: ${it.anio}, Monto: ${it.monto}") }

        println("\n-Todas los pagos del socio2: ")
        socioTest2.tomarPagos().forEach { println("Disciplina: ${it.disciplina.nombre}, Mes: ${it.mesCuota}, Anio: ${it.anioCuota}, Monto: ${it.monto}, Fecha de pago: ${it.fechaDePago}") }


        */


}

