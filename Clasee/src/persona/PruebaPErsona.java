package persona;

public class PruebaPErsona {
    public static void main(String[] args) {
        System.out.println("***Creacion de Clase y Objeto ***");
        var persona1 = new Persona("Ignacio", "Chavez");

        System.out.println(persona1.toSring());
    }
}
