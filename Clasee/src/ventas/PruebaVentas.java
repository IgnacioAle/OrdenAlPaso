package ventas;

public class PruebaVentas {

    public static void main(String[] args) {
        System.out.println("*** Sistema de Ventas ***");
        var producto1 = new Producto("Computador", 530990);
        var producto2 = new Producto("Reloj", 14200);

        var orden1 = new Orden();
        orden1.agregarProducto(producto1);
        orden1.agregarProducto(producto2);
        orden1.mostrarOrden();

        var orden2 = new Orden();
        orden2.agregarProducto(new Producto("Gato", 23500));
        orden2.agregarProducto(producto1);
        orden2.agregarProducto(producto2);
        orden2.mostrarOrden();


    }
}
