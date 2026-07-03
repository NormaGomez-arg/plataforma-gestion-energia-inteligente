package ar.edu.unahur.obj2.energia;

public interface ObservadorBateria {

    void actualizar(Bateria bateria, String movimiento, double cantidad);

}