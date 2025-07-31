import java.util.*;

public class TorneoTaekwondo {

    static class Luchador {
        String nombre;
        int edad;
        double peso;
        String cinturon;

        public Luchador(String nombre, int edad, double peso, String cinturon) {
            this.nombre = nombre;
            this.edad = edad;
            this.peso = peso;
            this.cinturon = cinturon;
        }

        public int getNivelCinturon() {
            return switch (cinturon.toLowerCase()) {
                case "blanco" -> 1;
                case "amarillo" -> 2;
                case "verde" -> 3;
                case "azul" -> 4;
                case "rojo" -> 5;
                case "negro" -> 6;
                default -> 0;
            };
        }

        public String getCategoria() {
            return edad < 18 ? "Juvenil" : "Adulto";
        }

        @Override
        public String toString() {
            return nombre + " (Edad: " + edad + ", Peso: " + peso + "kg, Cinturón: " + cinturon + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Luchador> luchadores = new ArrayList<>();

        System.out.print("¿Cuántos luchadores quieres ingresar? ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- Luchador #" + (i + 1) + " ---");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());

            System.out.print("Peso (en kg): ");
            double peso = Double.parseDouble(scanner.nextLine());

            System.out.print("Cinturón (blanco, amarillo, verde, azul, rojo, negro): ");
            String cinturon = scanner.nextLine();

            luchadores.add(new Luchador(nombre, edad, peso, cinturon));
        }

        // Separar por categoría
        List<Luchador> juveniles = new ArrayList<>();
        List<Luchador> adultos = new ArrayList<>();

        for (Luchador l : luchadores) {
            if (l.getCategoria().equals("Juvenil")) {
                juveniles.add(l);
            } else {
                adultos.add(l);
            }
        }

        System.out.println("\n🧑‍🦱 CATEGORÍA JUVENIL:");
        emparejarLuchadores(juveniles, true);

        System.out.println("\n🧔 CATEGORÍA ADULTO:");
        emparejarLuchadores(adultos, false);
    }

    public static void emparejarLuchadores(List<Luchador> luchadores, boolean esJuvenil) {
        if (luchadores.isEmpty()) {
            System.out.println("⚠️ No hay luchadores en esta categoría.");
            return;
        }

        int edadMaximaDiferencia = esJuvenil ? 2 : 100;
        List<Luchador[]> paresValidos = new ArrayList<>();

        // Generar todos los pares válidos
        for (int i = 0; i < luchadores.size(); i++) {
            for (int j = i + 1; j < luchadores.size(); j++) {
                Luchador a = luchadores.get(i);
                Luchador b = luchadores.get(j);

                double diferenciaPeso = Math.abs(a.peso - b.peso);
                int diferenciaCinturon = Math.abs(a.getNivelCinturon() - b.getNivelCinturon());
                int diferenciaEdad = Math.abs(a.edad - b.edad);

                if (diferenciaPeso <= 5 && diferenciaCinturon <= 1 && diferenciaEdad <= edadMaximaDiferencia) {
                    paresValidos.add(new Luchador[]{a, b});
                }
            }
        }

        // Elegir la mejor combinación posible (máximo de parejas sin repetir)
        List<List<Luchador[]>> combinaciones = generarCombinaciones(paresValidos, luchadores.size() / 2);
        List<Luchador[]> mejorSet = new ArrayList<>();

        for (List<Luchador[]> combinacion : combinaciones) {
            Set<Luchador> usados = new HashSet<>();
            boolean valida = true;

            for (Luchador[] par : combinacion) {
                if (usados.contains(par[0]) || usados.contains(par[1])) {
                    valida = false;
                    break;
                }
                usados.add(par[0]);
                usados.add(par[1]);
            }

            if (valida && combinacion.size() > mejorSet.size()) {
                mejorSet = combinacion;
            }
        }

        Set<Luchador> emparejados = new HashSet<>();
        System.out.println("📋 Emparejamientos:");

        for (Luchador[] par : mejorSet) {
            System.out.println("🔸 " + par[0].nombre + " VS " + par[1].nombre);
            emparejados.add(par[0]);
            emparejados.add(par[1]);
        }

        for (Luchador l : luchadores) {
            if (!emparejados.contains(l)) {
                System.out.println("⚠️ Luchador sin pareja: " + l.nombre + " (queda libre esta ronda)");
            }
        }
    }

    public static List<List<Luchador[]>> generarCombinaciones(List<Luchador[]> pares, int max) {
        List<List<Luchador[]>> resultados = new ArrayList<>();
        generar(pares, new ArrayList<>(), 0, resultados, max);
        return resultados;
    }

    private static void generar(List<Luchador[]> pares, List<Luchador[]> actual, int index,
                                List<List<Luchador[]>> resultados, int max) {
        if (actual.size() > max) return;
        if (index >= pares.size()) {
            resultados.add(new ArrayList<>(actual));
            return;
        }

        Luchador[] par = pares.get(index);
        boolean conflict = false;
        for (Luchador[] ya : actual) {
            if (ya[0] == par[0] || ya[0] == par[1] || ya[1] == par[0] || ya[1] == par[1]) {
                conflict = true;
                break;
            }
        }

        if (!conflict) {
            actual.add(par);
            generar(pares, actual, index + 1, resultados, max);
            actual.remove(actual.size() - 1);
        }

        generar(pares, actual, index + 1, resultados, max);
    }


}


