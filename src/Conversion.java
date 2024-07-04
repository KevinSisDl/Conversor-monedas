import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private Moneda monedaOrigen;
    private Moneda monedaDestino;
    private double monto;
    private double resultado;
    private LocalDateTime fecha;

    public Conversion(Moneda monedaOrigen, Moneda monedaDestino, double monto, double resultado) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.monto = monto;
        this.resultado = resultado;
        this.fecha = LocalDateTime.now();
    }

    public Moneda getMonedaOrigen() {
        return monedaOrigen;
    }

    public Moneda getMonedaDestino() {
        return monedaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public double getResultado() {
        return resultado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaFormateada(){
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
