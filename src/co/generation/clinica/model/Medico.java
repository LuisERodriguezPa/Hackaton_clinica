package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;

public class Medico implements Registrable {
        private int id;
        private String nombre;
        private String apellido;
        private Especialidad especialidad;
        // Constructor CON id → desde CSV
        public Medico(int id, String nombre, String apellido, Especialidad especialidad) {
            this.id = id;
            setNombre(nombre);
            setApellido(apellido);
            setEspecialidad(especialidad);
        }
        // Constructor SIN id → desde menú
        public Medico(String nombre, String apellido, Especialidad especialidad) {
            setNombre(nombre);
            setApellido(apellido);
            setEspecialidad(especialidad);
        }
        public int getId()                  { return id; }
        public String getNombre()           { return nombre; }
        public String getApellido()         { return apellido; }
        public Especialidad getEspecialidad(){ return especialidad; }

        public void setId(int id) { this.id = id; }
        public void setNombre(String nombre) {
            if (nombre == null || nombre.isBlank())
                throw new IllegalArgumentException("El nombre del médico no puede ser nulo ni vacío.");
            this.nombre = nombre.trim();
        }
        public void setApellido(String apellido) {
            if (apellido == null || apellido.isBlank())
                throw new IllegalArgumentException("El apellido del médico no puede ser nulo ni vacío.");
            this.apellido = apellido.trim();
        }
        public void setEspecialidad(Especialidad especialidad) {
            if (especialidad == null)
                throw new IllegalArgumentException("La especialidad no puede ser nula.");
            this.especialidad = especialidad;
        }

        @Override
        public String getDatosRegistro() {
            return toString();
        }
        @Override
        public boolean esValido() {
            return nombre != null && !nombre.isBlank()
                    && apellido != null && !apellido.isBlank()
                    && especialidad != null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Medico)) return false;
            Medico m = (Medico) o;
            return this.nombre.equalsIgnoreCase(m.nombre)
                    && this.apellido.equalsIgnoreCase(m.apellido);
        }
        @Override
        public int hashCode() {
            return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
        }

        @Override
        public String toString() {
            return "Dr. " + nombre + " " + apellido + " - " + especialidad;
        }


}
