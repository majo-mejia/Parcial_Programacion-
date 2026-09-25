package co.edu.uniquindio.poo.parcial.model;

import java.time.LocalDate;

public class Cliente implements Nombrable {

    private String nombre;
    private String documento;
    private String telefono;
    private String correoElectronico;
    private int edad;
    private LocalDate fechaRegistro;

    public Cliente(String nombre, String documento, String telefono,
                   String correoElectronico, int edad, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public boolean evaluarNumeroPerfecto() {
        return NumeroPerfectoUtil.esNumeroPerfecto(telefono);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }
}