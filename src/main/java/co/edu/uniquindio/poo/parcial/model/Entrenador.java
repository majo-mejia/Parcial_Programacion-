package co.edu.uniquindio.poo.parcial.model;

public class Entrenador  {

    private String documento;
    private String nombre;
    private String telefono;
    private TipoEspecialidad especialidad;
    private double tarifaSesion;

    public Entrenador(String documento, String nombre, String telefono,
                      TipoEspecialidad especialidad, double tarifaSesion) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.tarifaSesion = tarifaSesion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TipoEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    Entrenador entrenador = new Entrenador("12345", "Carlos", "3001234567", TipoEspecialidad.DEFINICION, 20000.0);

    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }
}