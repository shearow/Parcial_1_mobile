import club.*

fun main() {

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


        /*************** CLUB -  ***************/


}

