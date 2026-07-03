package ar.edu.unahur.obj2.energia;

public class ConsumoEnergia implements OperacionEnergia {

    private Bateria bateria;
    private double cantidad;

    public ConsumoEnergia(Bateria bateria, double cantidad) {
        if (cantidad <= 0) {
            throw new ValorEnergiaInvalidoException("La cantidad debe ser mayor a cero");
        }
        this.bateria = bateria;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() throws ReservaInsuficienteException {
        this.bateria.consumir(this.cantidad);
    }

    @Override
    public void deshacer() {
        this.bateria.cargar(this.cantidad);
    }
}
