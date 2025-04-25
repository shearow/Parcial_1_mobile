import club.*

fun main() {

        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// SOCIO /////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** SOCIO - Registrar Socio ***************/
        val socioTest1 = Socio("Jorge", "321231", Sexo.MASCULINO, "emailTest@gmail.com")
        val socioTest2 = Socio("Maria", "3123", Sexo.FEMENINO)


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// DISCIPLINA ////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** DISCIPLINA - Crear disciplina ***************/
        val disciplina1 = Disciplina("Futbol", TipoDisciplina.EQUIPO, 2)
        val disciplina2 = Disciplina("Basquet", TipoDisciplina.EQUIPO, 10)
        val disciplina3 = Disciplina("Natacion", TipoDisciplina.OPTATIVA, 30)
        val disciplina4 = Disciplina("Gimnasio", TipoDisciplina.INDIVIDUAL, 50)


        /*************** DISCIPLINA - Atributos y funcionalidad ***************/
        println("\n•Disciplina atributos:")
        println("Id: ${disciplina1.id}, Nombre: ${disciplina1.nombre}, Tipo: ${disciplina1.tipo}, Capacidad maxima: ${disciplina1.capacidadMaxima}")
        println("Cantidad de cupos disponibles: ${disciplina1.cantidadCuposDisponibles()}")
        println("Hay cupo disponible? ${disciplina1.validarDisponibilidadCupos()}")


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
        println("Eliminando al socio1 (${socioTest1.nombre}): ")

        if(disciplina1.eliminarInscripcion(socioTest1)){
                println("${socioTest1.nombre} eliminado correctamente")
        }else{
                println("${socioTest1.nombre} no estaba inscripto en la disciplina ${disciplina1.nombre}")
        }


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// INSCRIPCION ////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** INSCRIPCION - Crear inscripcion ***************/
        var inscripcionTest1 = Inscripcion(socioTest1, disciplina1);
        var inscripcionTest2 = Inscripcion(socioTest1, disciplina1);

        println("\n•Inscripcion test1 de la disciplina ${inscripcionTest1.disciplina.nombre}.")
        println("Fecha de inscripcion del usuario ${inscripcionTest1.socio.nombre}: ${inscripcionTest1.fechaInscripcion}, estado: ${inscripcionTest1.estado}");

        println("\n•Inscripcion test2 de la disciplina ${inscripcionTest2.disciplina.nombre}.")
        println("Fecha de inscripcion del usuario ${inscripcionTest2.socio.nombre}: ${inscripcionTest2.fechaInscripcion}, estado: ${inscripcionTest2.estado}");


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// CLUB ///////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** CLUB - Instanciar Club ***************/
        val club = Club("Los Siberianos")
        println("Bienvenido al Club deportivo ${club.nombre}")


        /*************** CLUB - Agregar Socio ***************/
        try{
                // Se deberia agregar correctamente.
                club.agregarSocio(socioTest1)

                // Se deberia agregar correctamente.
                club.agregarSocio(socioTest2)

                // Deberia ejecutarse la excepción ya que el socio existe en el club.
                club.agregarSocio(socioTest1)
        }catch(e : Exception){
                println(e.message)
        }


        /*************** CLUB - Pintar datos de los socios ***************/
        println("Datos de los socios:")
        club.pintarInformacionSocios()


        /*************** CLUB - Tomar colección de todos los socios ***************/
        println("\n•Cantidad de Socios totales registrados: ${club.tomarSocios().size}.")


        /*************** CLUB - Agregar disciplina ***************/
        println("\n•Agregando disciplina ${disciplina1.nombre} al club:")
        try{
                club.agregarDisciplina(disciplina1)
                println("Se agrego correctamente la disciplina ${disciplina1.nombre}, cupos disponibles: ${disciplina1.cantidadCuposDisponibles()}");
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Agregando disciplina ${disciplina2.nombre} al club:")
        try{
                club.agregarDisciplina(disciplina2)
                println("Se agrego correctamente la disciplina ${disciplina2.nombre}, cupos disponibles: ${disciplina2.cantidadCuposDisponibles()}");
        }catch(e: Exception){
                println(e.message)
        }

        println("\n-Agregando disciplina ${disciplina1.nombre} al club: ")
        try{
                club.agregarDisciplina(disciplina1)
                println("NO DEBERIA PONER AGREGARSE LA DISCIPLINA ${disciplina1.nombre} ya que se agrego anteriormente");
        }catch(e: Exception){
                println(e.message)
        }

        /*************** CLUB - Tomar todas las disciplinas ***************/
        println("\n•Tomar todas las disciplinas del club:")
        club.tomarDisciplinas().forEach {println("-Nombre: ${it.nombre}, cupos:(${it.capacidadMaxima - it.cantidadCuposDisponibles()}/${it.capacidadMaxima})")}


        /*************** CLUB - ***************/
}