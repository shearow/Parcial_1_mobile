    Parcial Programación de Aplicaciones Móviles

    Descripción: Programa de gestión de socios e inscripciones de un club.

En este proyecto se realizó la creación de un programa el cual permita la gestión administrativa de los socios de un club. Además, el mismo  se encarga de la creación y gestión de disciplinas dentro del club. Nuestro objetivo era crear un programa fácil de utilizar en el cuál se puedan realizar las operacones básicas como pagos, inscripciones y algunas consultas a cerca de datos varios.

    INFORMACIÓN GENERAL

El programa cuenta con 10 clases, de las cuales 4 son enums utilizados para el registro de datos específicos (Estados de inscripción, sexo, tipo de disciplina y razón de deuda). Las demás clases se dividen en:

• Inscripción: Clase creada para registrar la inscripción de un socio en determinada disciplina. Esta luego se guardará en una variable encargada de almacenar todas las inscripciones de un socio en partticular; dentro de la clase socio. No posee métodos.

• Deuda: Clase encargada de crear las deudas que se le generan a los socios a medida que el tiempo avanza. Estas se almacenaran en una variable de Socio.

• Pago: Clase encargada de crear los pagos realizados, es decir, las deudas saldadas. Al igual que estas, se almacenan en una variable de Socio.

• Dicsciplina: Clase para crear todo tipo de disciplinas que luego serán guardadas en una variable dentro del club. Estas tendran un nombre, un tipo de disciplina del enum creado, capacidad máxima, costo mensual y una lista de socios inscriptos. Esta clase es capaz de agregar/eliminar/ver todo lo relacionado con los socios inscriptos.

• Socios: Clase encargada de creación de Socios, con un nombre completo, DNI, sexo y email (optativo). A su vez almacena en 3 variables los pagos realizados, deudas pendientes e inscripciones a disciplinas de un socio. Desde esta clase se puede gestionar todo lo relacionado con la información a cerca de deudas y pagos de un socio.

• Club: Clase principal encargada de gestionar casi todas las acciones que un usuario puede realizar. Desde aquí se crea un club con un nombre. A su vez posee dos variables que almacenan disciplinas y socios que estén anotados en el club. Un socio puede estar anotado en el club simplemente, sin necesidad de estar inscripto a una disciplina. Desde aquí se crean y eliminan socios y disciplinas, se inscriben a los socios en disciplinas y se validan que existan los mismos, al momento de realizar determinada acción.

    FUNCIONAMIENTO

El proyecto cuenta con un Main el cual al ejecutarse posee un menú de accioón automático. Desde allí se visualizan todas las acciones que el usuario puede realizar. Al momento de tener que ingresar un dato, el programa especificará cual debe ser, y en caso de ser incorrecto, este volverá al menú o pedirá que sea reingresado el mismo.
Lo primero que se debería hacer es la creación de socios, ya que la creación de un club es automática. Posteriormente la creación de disciplinas a las cuales añadir a los socios. Al inscribir a un socio a determinada discipina, automáticamente se generará una deuda que debe pagar, si no lo hace el mismo no podrá anotarse a otras disciplinas. Los pagos y las deudas de un socio se pueden observar en una lista con fecha y monto. Se pueden ver listas de socios del club, disciplinas y socios inscriptos en una determinada disciplina. Además los socios se pueden dar de baja de las disciplinas y las disciplinas pueden ser eliminadas. Otra de las funciones que tiene el programa es la de procesar un nuevo mes. Esta función tiene como objetivo marcar el avance de un mes en el ciclo del programa, lo que genera que todos los socios pasen a tener una deuda, debido a que deben abonar la siguiente cuota del mes, y por otro lado, si estos ya debían una cuota, pasarán a tener su estado de inscripción cancelada. Por último, se enceuntra la opcióin de salir la cual finalza el ciclo de ejecución.

    REALIZACIÓN

El trabajo fue realizado Por Tomas Curien, Nicolas Gracia Bietti y Benjamin Manzi. Cada integrante trabajó en conjunto para realizar cada una de las partes, comunicándonos vía Discord y mensajes. La gran mayoría del trabajo fue realizada en conjunto.
