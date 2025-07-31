package aritmetica;

public class Aritmetica {

    private int num1;
    private int num2;

    public Aritmetica(int op1, int op2){
        System.out.println("Ejecutando constructor");
        this.num1 = op1;
        this.num2 = op2;
    }

    public void sumar(){
        var resultado = this.num1 + num2;
        System.out.println("Resultado de la suma: " + resultado);
    }

    public void restar(){
        var resultado = num1 - num2;
        System.out.println("Resultado de la resta :" + resultado);
    }

    public int getNum1(){
        return this.num1;
    }

    public void setNum1(int num1){
        this.num1 = num1;
    }

    public int getNum2(){
        return this.num2;
    }

    public void setNum2(int num2){
        this.num2 = num2;
    }






}
