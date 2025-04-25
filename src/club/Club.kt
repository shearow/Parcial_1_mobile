package club

class Club(
    var nombre: String
){
    private var socios = mutableSetOf<Socio>()
    private var disciplinas = mutableSetOf<Disciplina>();

    fun agregarSocio(socio: Socio){
        if(this.socioYaRegistrado(socio.dni, socio.sexo)){
            throw Exception("El socio ya se encontraba registrado.")
        }
        this.socios.add(socio)
    }

    private fun socioYaRegistrado(dni: String, sexo: Sexo): Boolean {
        return this.socios.any { it.dni === dni && it.sexo === sexo}
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
            throw Exception("El nombre de la disciplina ya se encontraba creada en la lista.");
        }
        this.disciplinas.add(disciplina);
    }

    private fun disciplinaYaRegistrada(nombre: String): Boolean {
        return this.disciplinas.any { it.nombre === nombre}
    }

    fun tomarDisciplinas(): MutableSet<Disciplina> {
        return this.disciplinas
    }
}