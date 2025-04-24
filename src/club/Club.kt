package club

class Club(
    var nombre: String
){
    private var socios = mutableListOf<Socio>();

    fun agregarSocio(socio: Socio){
        if(this.socioYaRegistrado(socio.dni, socio.sexo)){
            throw Exception("El socio ya se encontraba registrado.");
        };
        this.socios.add(socio);
    };

    fun socioYaRegistrado(dni: String, sexo: Sexo): Boolean {
        return this.socios.any {it -> it.dni === dni && it.sexo === sexo};
    };

    fun tomarSocios(): kotlin.collections.MutableList<Socio> {
        return this.socios;
    };

    fun pintarInformacionSocios(){
        println("\n•Socios inscriptos en el club");
        this.socios.forEach({it ->
            println("Id: ${it.id}, Nombre: ${it.nombre}, Dni: ${it.dni}, Sexo: ${it.sexo}${it.email?.let { email -> ", Email: $email" } ?: ""}");
        });
    };
}