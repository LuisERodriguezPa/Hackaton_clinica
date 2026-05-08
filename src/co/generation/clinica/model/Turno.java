package co.generation.clinica.model;

import java.time.LocalDateTime;

public class Turno {
    Paciente paciente1 = new Paciente();
    Medico medico1 = new Medico();
        private int id;
        private Paciente paciente;
        private Medico medico;
        private LocalDateTime fechaHora;
        private EstadoTurno estado;

        // Constructor desde el menu (sin id)
        public Turno(Paciente paciente, Medico medico, LocalDateTime fechaHora) {
            setPaciente(paciente);
            setMedico(medico);
            setFechaHora(fechaHora);
            this.estado = EstadoTurno.PENDIENTE;
        }

        // Constructor con id (desde csv)
        public Turno(int id, Paciente paciente, Medico medico,
                     LocalDateTime fechaHora, EstadoTurno estado) {
            this.id = id;
            setPaciente(paciente);
            setMedico(medico);
            setFechaHora(fechaHora);
            setEstado(estado);
        }

        // Getters

        public int getId() {
            return id;
        }
        public Paciente getPaciente() {
            return paciente;
        }
        public Medico getMedico() {
            return medico;
        }
        public LocalDateTime getFechaHora() {
            return fechaHora;
        }
        public EstadoTurno getEstado() {
            return estado;
        }

        // Setters

        public void setId(int id) {
            this.id = id;
        }
        public void setPaciente(Paciente paciente) {
            if (paciente == null) {
                throw new IllegalArgumentException("El paciente es obligatorio.");
            }
            this.paciente = paciente;
        }
        public void setMedico(Medico medico) {
            if (medico == null) {
                throw new IllegalArgumentException("El medico es obligatorio.");
            }
            this.medico = medico;
        }
        public void setFechaHora(LocalDateTime fechaHora) {
            if (fechaHora == null) {
                throw new IllegalArgumentException("La fecha y hora no pueden ser nulas.");
            }
            this.fechaHora = fechaHora; // ← línea que faltaba
        }
        public void setEstado(EstadoTurno estado) {
            this.estado = estado;
        }

        // equals: si mismo medico + misma fechaHora = conflicto

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Turno)) return false;
            Turno otro = (Turno) obj;
            return this.medico.equals(otro.medico)
                    && this.fechaHora.equals(otro.fechaHora);
        }

        // toString

        @Override
        public String toString() {
            return "[" + estado + "] "
                    + paciente.getNombre() + " " + paciente.getApellido()
                    + " Dr. " + medico.getNombre() + " " + medico.getApellido()
                    + " (" + medico.getEspecialidad() + ")"
                    + " - " + fechaHora;
        }

}
