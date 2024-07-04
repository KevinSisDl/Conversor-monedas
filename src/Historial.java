import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Conversion> conversiones;

    public Historial() {
        this.conversiones = new ArrayList<>();
    }

    public void agregarConversion(Conversion conversion) {
        this.conversiones.add(conversion);
    }

    public List<Conversion> getConversiones() {
        return this.conversiones;
    }
}


