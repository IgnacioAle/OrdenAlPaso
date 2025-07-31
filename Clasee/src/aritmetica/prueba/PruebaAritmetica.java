package aritmetica.prueba;

import aritmetica.Aritmetica;

public class PruebaAritmetica {

    public static void main(String[] args) {
        System.out.println("*** Ejemplo aritmetica.Aritmetica ***");

        var aritmetica1 = new Aritmetica(5, 7);

        aritmetica1.sumar();
        aritmetica1.restar();
    }
}
