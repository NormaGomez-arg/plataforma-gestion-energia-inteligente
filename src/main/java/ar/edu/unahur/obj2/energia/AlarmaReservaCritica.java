package ar.edu.unahur.obj2.energia;

public class AlarmaReservaCritica implements ObservadorBateria {

    private boolean alarmaActivada;

    public AlarmaReservaCritica() {
        this.alarmaActivada = false;
    }

    @Override
    public void actualizar(Bateria bateria, String movimiento, double cantidad) {
        this.alarmaActivada = bateria.getNivelEnergia() < 0;
    }

    public boolean isAlarmaActivada() {
        return alarmaActivada;
    }
}
