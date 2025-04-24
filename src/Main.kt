import club.*

fun main() {

        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// SOCIO /////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /************* REGISTRAR SOCIO *************/
        val socioTest1 = Socio("Jorge", "321231", Sexo.MASCULINO, "emailTest@gmail.com")
        val socioTest2 = Socio("Maria", "3123", Sexo.FEMENINO)


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// CLUB ///////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /************* INSTANCIAR CLUB *************/
        val club = Club("Los Siberianos")
        println("Bienvenido al Club deportivo ${club.nombre}")


        /************* CLUB - Agregar socio *************/
        try{
                club.agregarSocio(socioTest1)
                club.agregarSocio(socioTest2)
                club.agregarSocio(socioTest1)
        }catch(e : Exception){
                println(e.message)
        }


        /*************** CLUB - Pintar datos de los socios ***************************/
        println("Datos de los socios:")
        club.pintarInformacionSocios()


        /*************** CLUB - Tomar colección de todos los socios ***************************/
        println("\n•Cantidad de Socios totales registrados: ${club.tomarSocios().size}.")


        /////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// DISCIPLINA ////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** CREAR DISCIPLINA ***************************/
        val disciplina1 = Disciplina("Futbol", TipoDisciplina.EQUIPO, 2)


        /*************** DISCIPLINA - Atributos y funcionalidad ***************************/
        println("\n•Disciplina atributos:")
        println("Id: ${disciplina1.id}, Nombre: ${disciplina1.nombre}, Tipo: ${disciplina1.tipo}, Capacidad maxima: ${disciplina1.capacidadMaxima}")
        println("Cantidad de cupos disponibles: ${disciplina1.cantidadCuposDisponibles()}")
        println("Hay cupo disponible? ${disciplina1.validarDisponibilidadCupos()}")


        /*************** DISCIPLINA - Agregar inscripto ***************************/
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


        /*************** DISCIPLINA - Tomar todos los inscriptos ***************************/
        println("\n•Recorriendo socios inscriptos en la disciplina ${disciplina1.nombre}:")
        disciplina1.tomarSociosInscriptos().forEach { println("Nombre: ${it.nombre}, Id: ${it.id}") }


        /*************** DISCIPLINA - Eliminar inscripto ***************************/
        println("Eliminando al socio1 (${socioTest1.nombre}): ")

        if(disciplina1.eliminarInscripcion(socioTest1)){
                println("${socioTest1.nombre} eliminado correctamente")
        }else{
                println("${socioTest1.nombre} no estaba inscripto en la disciplina ${disciplina1.nombre}")
        }


        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////  ////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        /*************** ***************************/
}