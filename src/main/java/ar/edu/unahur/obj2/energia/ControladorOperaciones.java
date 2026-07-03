package ar.edu.unahur.obj2.energia;

public class ControladorOperaciones {

    public void ejecutarOperacion(OperacionEnergia operacion) throws ReservaInsuficienteException {
        operacion.ejecutar();
    }

}
