import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    private static Conversor conversor = new Conversor();
    private static Historial historial = new Historial();
    private static Map<String, Moneda> monedas = new HashMap<>();

    static {
        monedas.put("ARS", new Moneda("ARS", "Peso argentino"));
        monedas.put("BOB", new Moneda("BOB", "Boliviano boliviano"));
        monedas.put("BRL", new Moneda("BRL", "Real brasileño"));
        monedas.put("CLP", new Moneda("CLP", "Peso chileno"));
        monedas.put("COP", new Moneda("COP", "Peso colombiano"));
        monedas.put("USD", new Moneda("USD", "Dólar estadounidense"));
        monedas.put("GTQ", new Moneda("GTQ", "Quetzal guatemalteco"));
        monedas.put("MXN", new Moneda("MXN", "Peso mexicano"));
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarConversion(scanner);
                    break;
                case 2:
                    mostrarHistorial();
                    break;
                case 3:
                    System.out.println("¡Finalizando Programa....!");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, intenta nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Menú ===");
        System.out.println("1. Convertir moneda");
        System.out.println("2. Ver historial de conversiones");
        System.out.println("3. Salir");
        System.out.print("Ingrese el número de opción: ");
    }

    private static void realizarConversion(Scanner scanner) {
        System.out.println("Monedas disponibles:");
        int i = 1;
        for (Moneda moneda : monedas.values()) {
            System.out.println(i + ". " + moneda.getNombre() + " (" + moneda.getCodigo() + ")");
            i++;
        }

        System.out.print("Ingrese el código de la moneda de origen: ");
        String codigoOrigen = scanner.nextLine().toUpperCase();
        System.out.print("Ingrese el código de la moneda de destino: ");
        String codigoDestino = scanner.nextLine().toUpperCase();
        System.out.print("Ingrese la cantidad a convertir: ");
        double cantidad = scanner.nextDouble();

        Moneda monedaOrigen = monedas.get(codigoOrigen);
        Moneda monedaDestino = monedas.get(codigoDestino);

        if (monedaOrigen != null && monedaDestino != null) {
            try {
                double resultado = conversor.convertirMoneda(monedaOrigen.getCodigo(), monedaDestino.getCodigo(), cantidad);
                System.out.println("Resultado: " + cantidad + " " + monedaOrigen.getNombre() + " = " + resultado + " " + monedaDestino.getNombre());

                Conversion conversion = new Conversion(monedaOrigen, monedaDestino, cantidad, resultado);
                historial.agregarConversion(conversion);
            } catch (Exception e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        } else {
            System.out.println("Código de moneda inválido.");
        }
    }

    private static void mostrarHistorial() {
        System.out.println("========== Historial de Conversiones ==========");
        for (Conversion conversion : historial.getConversiones()) {
            System.out.println("En la fecha y hora [" + conversion.getFechaFormateada() + "]. Se realizo la siguiente conversion: (" + conversion.getMonto() + " " +
                               conversion.getMonedaOrigen().getNombre() + " = " +
                               conversion.getResultado() + " " + conversion.getMonedaDestino().getNombre() + ")");
        }
        System.out.println();
    }
}