package animales;

public class Animal {
    protected void hacerSonido(){
        System.out.println("El animal hace un sonido");
    }
}

class Perro extends Animal{
    @Override
     protected void hacerSonido(){
        System.out.println("Guau!!!");
    }

    protected void dormir(){
        System.out.println("Duermo 15 horas al dia");
    }

}

class Gato extends Animal{
    @Override
    protected void hacerSonido() {
        System.out.println("Miau!!!");
    }
}

class PruebaAnimal{
    static void imprimirSonido(Animal animal){
        animal.hacerSonido();
    }

    public static void main(String[] args) {
    var animal = new Animal();
    imprimirSonido(animal);
    }
}
