package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EnergiaTest {

    @Test
    public void cargarEnergia() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        CargaEnergia carga = new CargaEnergia(bateria, 500);

        carga.ejecutar();

        assertEquals(1500, bateria.getNivelEnergia(), 0.01);
    }
    @Test
    public void consumirEnergia() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        ConsumoEnergia consumo = new ConsumoEnergia(bateria, 300);

        consumo.ejecutar();

        assertEquals(700, bateria.getNivelEnergia(), 0.01);
}
    @Test
    public void deshacerCarga() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        CargaEnergia carga = new CargaEnergia(bateria, 500);

        carga.ejecutar();
        carga.deshacer();

        assertEquals(1000, bateria.getNivelEnergia(), 0.01);
    }
    @Test
    public void deshacerConsumo() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        ConsumoEnergia consumo = new ConsumoEnergia(bateria, 300);

        consumo.ejecutar();
        consumo.deshacer();

        assertEquals(1000, bateria.getNivelEnergia(), 0.01);
    }
    @Test
    public void cargaConValorInvalido() {

        Bateria bateria = new Bateria("BAT1", 1000);

        assertThrows(ValorEnergiaInvalidoException.class,
                () -> new CargaEnergia(bateria, 0));
    }
    @Test
    public void superaLimiteReserva() {

        Bateria bateria = new Bateria("BAT1", 1000);
        ConsumoEnergia consumo = new ConsumoEnergia(bateria, 7000);

        assertThrows(ReservaInsuficienteException.class,
                () -> consumo.ejecutar());
    }
    @Test
    public void ejecutarRutina() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);

        CargaEnergia carga = new CargaEnergia(bateria, 500);
        ConsumoEnergia consumo = new ConsumoEnergia(bateria, 200);

        RutinaEnergia rutina = new RutinaEnergia();
        rutina.agregarOperacion(carga);
        rutina.agregarOperacion(consumo);

        rutina.ejecutar();

        assertEquals(1300, bateria.getNivelEnergia(), 0.01);
        assertEquals(0, rutina.cantidadOperaciones());
    }
    @Test
    public void rollbackRutina() {

        Bateria bateria = new Bateria("BAT1", 1000);

        CargaEnergia carga = new CargaEnergia(bateria, 500);
        ConsumoEnergia consumo1 = new ConsumoEnergia(bateria, 200);
        ConsumoEnergia consumo2 = new ConsumoEnergia(bateria, 7000);

        RutinaEnergia rutina = new RutinaEnergia();
        rutina.agregarOperacion(carga);
        rutina.agregarOperacion(consumo1);
        rutina.agregarOperacion(consumo2);

        assertThrows(ReservaInsuficienteException.class,
                () -> rutina.ejecutar());

        assertEquals(1000, bateria.getNivelEnergia(), 0.01);
    }
    @Test
    public void auditoriaRegistraMovimiento() {

        Bateria bateria = new Bateria("BAT1", 1000);
        AuditoriaEnergia auditoria = new AuditoriaEnergia();

        bateria.agregarObservador(auditoria);

        bateria.cargar(500);

        assertEquals(1, auditoria.cantidadMovimientos());
    }
    @Test
    public void notificaAdministrador() {

        Bateria bateria = new Bateria("BAT1", 1000);
        NotificacionAdministrador notificacion = new NotificacionAdministrador();

        bateria.agregarObservador(notificacion);

        bateria.cargar(500);

        assertEquals("Se han cargado 500.0 kWh en su bateria", notificacion.getMensaje());
    }
    @Test
    public void activaAlarma() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 100);
        AlarmaReservaCritica alarma = new AlarmaReservaCritica();

        bateria.agregarObservador(alarma);

        bateria.consumir(200);

        assertTrue(alarma.isAlarmaActivada());
    }
    @Test
    public void controladorEjecutaOperacion() throws ReservaInsuficienteException {

        Bateria bateria = new Bateria("BAT1", 1000);
        CargaEnergia carga = new CargaEnergia(bateria, 500);

        ControladorOperaciones controlador = new ControladorOperaciones();

        controlador.ejecutarOperacion(carga);

        assertEquals(1500, bateria.getNivelEnergia(), 0.01);
    }
}
