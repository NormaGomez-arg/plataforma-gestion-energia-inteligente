package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.List;

public class AuditoriaEnergia implements ObservadorBateria {

    private List<String> movimientos;

    public AuditoriaEnergia() {
        this.movimientos = new ArrayList<>();
    }

    @Override
    public void actualizar(Bateria bateria, String movimiento, double cantidad) {
        String registro = bateria.getIdentificador() + " - " + movimiento + " - " + cantidad + " kWh - saldo: " + bateria.getNivelEnergia();
        this.movimientos.add(registro);
    }

    public List<String> getMovimientos() {
        return movimientos;
    }

    public int cantidadMovimientos() {
        return this.movimientos.size();
    }
}
