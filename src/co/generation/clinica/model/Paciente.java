package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;

public class Paciente implements Registrable {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede ser vacío");
        }
        this.cedula = cedula.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser vacío");
        }
        this.apellido = apellido.trim();
    }

    public void setTelefono(String telefono) {
        telefono = telefono.trim();
        if (telefono.isEmpty() || !telefono.matches("^[0-9]{7,10}$")) {
            throw new IllegalArgumentException("El teléfono debe contener entre 7 y 10 dígitos numéricos");
        }
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object nuevoPaciente) {
        if (this == nuevoPaciente) return true;
        if (!(nuevoPaciente instanceof Paciente)) return false;
        Paciente nuevo = (Paciente) nuevoPaciente;
        return cedula.equals(nuevo.cedula);
    }

    public String toString() {
        return String.join(" - ", nombre, apellido, cedula, telefono);
    }
    public String getDatosRegistro() {
        return toString();
    }
    public boolean esValido() {
        return cedula != null && !cedula.trim().isEmpty() && nombre != null && !nombre.trim().isEmpty() && telefono != null && telefono.matches("^[0-9]{7,10}$"
        );
    }
}
