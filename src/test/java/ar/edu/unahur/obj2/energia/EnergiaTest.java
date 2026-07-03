package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnergiaTest {

    @Test
    public void cargarEnergia() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        CargaEnergia carga = new CargaEnergia(bateria, 500);

        carga.ejecutar();

        assertEquals(1500, bateria.getNivelEnergia(), 0.01);
    }
}
