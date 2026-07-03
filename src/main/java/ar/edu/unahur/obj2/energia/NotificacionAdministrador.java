package ar.edu.unahur.obj2.energia;

public class NotificacionAdministrador implements ObservadorBateria {

    private String mensaje;

    public NotificacionAdministrador() {
        this.mensaje = "";
    }

    @Override
    public void actualizar(Bateria bateria, String movimiento, double cantidad) {
        if (movimiento.equals("CARGA")) {
            this.mensaje = "Se han cargado " + cantidad + " kWh en su bateria";
        } else {
            this.mensaje = "Se han consumido " + cantidad + " kWh en su bateria";
        }
    }

    public String getMensaje() {
        return mensaje;
    }
}
