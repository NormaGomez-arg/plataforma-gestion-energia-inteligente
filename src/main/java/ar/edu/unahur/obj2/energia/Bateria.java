package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.List;

public class Bateria {

    private String identificador;
    private double nivelEnergia;
    private List<ObservadorBateria> observadores;

    public Bateria(String identificador, double nivelEnergia) {
        this.identificador = identificador;
        this.nivelEnergia = nivelEnergia;
        this.observadores = new ArrayList<>();
    }

    public String getIdentificador() {
        return identificador;
    }

    public double getNivelEnergia() {
        return nivelEnergia;
    }

    public void cargar(double cantidad) {
        this.nivelEnergia += cantidad;
        this.avisar("CARGA", cantidad);
    }

    public void consumir(double cantidad) throws ReservaInsuficienteException {
        if (this.nivelEnergia - cantidad < -5000) {
            throw new ReservaInsuficienteException("La batería supera el límite de reserva");
        }

        this.nivelEnergia -= cantidad;
        this.avisar("CONSUMO", cantidad);
    }

    public void agregarObservador(ObservadorBateria observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(ObservadorBateria observador) {
        this.observadores.remove(observador);
    }

    private void avisar(String movimiento, double cantidad) {
        for (ObservadorBateria observador : this.observadores) {
            observador.actualizar(this, movimiento, cantidad);
        }
    }
}
