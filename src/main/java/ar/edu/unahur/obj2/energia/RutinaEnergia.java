package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.List;

public class RutinaEnergia implements OperacionEnergia {

    private List<OperacionEnergia> operaciones;

    public RutinaEnergia() {
        this.operaciones = new ArrayList<>();
    }

    public void agregarOperacion(OperacionEnergia operacion) {
        this.operaciones.add(operacion);
    }

    public int cantidadOperaciones() {
        return this.operaciones.size();
    }

    @Override
    public void ejecutar() throws ReservaInsuficienteException {

        int i = 0;

        try {

            while (i < this.operaciones.size()) {
                this.operaciones.get(i).ejecutar();
                i = i + 1;
            }

            this.operaciones.clear();

        } catch (ReservaInsuficienteException e) {

            for (int j = i - 1; j >= 0; j--) {
                this.operaciones.get(j).deshacer();
            }

            throw e;
        }
    }

    @Override
    public void deshacer() throws ReservaInsuficienteException {

        for (int i = this.operaciones.size() - 1; i >= 0; i--) {
            this.operaciones.get(i).deshacer();
        }
    }
}