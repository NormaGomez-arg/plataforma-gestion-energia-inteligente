package ar.edu.unahur.obj2.energia;

public interface OperacionEnergia {

    void ejecutar() throws ReservaInsuficienteException;

    void deshacer() throws ReservaInsuficienteException;

}
