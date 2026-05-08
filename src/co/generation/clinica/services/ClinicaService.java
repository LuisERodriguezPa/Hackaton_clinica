package co.generation.clinica.services;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.*;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ClinicaService implements Consultable {
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();

    //Leysi
    public void registrarPaciente (Paciente p) {
        if (!p.esValido()) {
            System.out.println("Los datos indicados no son válidos");
            return;
        }
        if (pacientes.contains(p)) {
            System.out.println("Paciente duplicado");
            return;
        }

        int maxId = 0;
        for (Paciente pc : pacientes) {
            if (pc.getId() > maxId) {
                maxId = pc.getId();
            }
        }
        int nuevoId = maxId + 1;
        p.setId(nuevoId);
        pacientes.add(p);
        System.out.println("Paciente registrado exitosamente: " + p);
        return;
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente p : pacientes) {
            if(p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    // Getters para DatosCSV
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    // Método 4: listar pacientes
    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        // Ordena una copia de la lista por apellido y luego por nombre
        List<Paciente> copiaOrdenada = new ArrayList<>(pacientes);
        copiaOrdenada.sort(
                Comparator.comparing(Paciente::getApellido)
                        .thenComparing(Paciente::getNombre)
        );

        System.out.println("-- Lista de Pacientes --");
        for (Paciente p : copiaOrdenada) {
            System.out.println(p.toString()); // usa toString() de Paciente
        }
    }



// desde aqui metodos turnos

    // metodo asignar citas
    public void asignarTurno(Turno t) {
        if (!pacientes.contains(t.getPaciente())) {
            System.out.println("Paciente no existe");
            return;
        }
        if (!medicos.contains(t.getMedico())) {
            System.out.println("Medico no existe");
            return;
        }
        if (turnos.contains(t)) {
            System.out.println("Ese horario no esta disponible");
            return;
        }
        t.setId(turnos.size() + 1);
        turnos.add(t);
        System.out.println("Turno asignado: " + t);
    }

    // metodo para cancerla el turno
    public void cancelarTurno(int idTurno) {
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                if (t.getEstado() == EstadoTurno.CANCELADO) {
                    System.out.println("El turno ya estaba cancelado");
                    return;
                }
                if (t.getEstado() == EstadoTurno.ATENDIDO) {
                    System.out.println("No se puede cancelar un turno atendido");
                    return;
                }
                t.setEstado(EstadoTurno.CANCELADO);
                System.out.println("Turno cancelado");
                return;
            }
        }
        System.out.println("Turno no encontrado");
    }
    //    metodo para cambiar el estado del turno
    public void cambiarEstadoTurno(int idTurno, EstadoTurno nuevo) {
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                t.setEstado(nuevo);
                System.out.println("Estado actualizado");
                return;
            }
        }
        System.out.println("Turno no encontrado");
    }

    public void registrarMedico(Medico m) {
        if (!m.esValido()) {
            System.out.println("⚠ Datos del médico inválidos.");
            return;
        }
        if (medicos.contains(m)) {
            System.out.println("⚠ Ya existe un médico con ese nombre y apellido.");
            return;
        }
        int nuevoId = medicos.isEmpty() ? 1
                : medicos.stream().mapToInt(Medico::getId).max().getAsInt() + 1;
        m.setId(nuevoId);
        medicos.add(m);
        System.out.println("✔ Médico registrado: " + m);
    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        for (Medico m : medicos) {
            if (m.getNombre().equalsIgnoreCase(nombre)
                    && m.getApellido().equalsIgnoreCase(apellido)) return m;
        }
        return null;
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        List<Medico> copia = new ArrayList<>(medicos);
        copia.sort(Comparator.comparing(Medico::getEspecialidad)
                .thenComparing(Medico::getApellido));
        System.out.println("── Lista de médicos ────────────────────────");
        for (Medico m : copia) System.out.println("  " + m);
    }

    @Override
    public List listarTurnosDelDia(LocalDate fecha){
        List <Turno> turnos = new ArrayList<>();
        for(Turno turno:turnos){
            if(turno.getFechaHora().toLocalDate()== fecha){
                turnos.add(turno);
            }
        }
        return turnos;
    }

    @Override
    public List buscarPorMedico(Medico medico){
        List<Turno> turnos = new ArrayList<>();
        for(Turno turno:turnos){
            if(turno.getMedico().equals(medico)){
                turnos.add(turno);
            }
        }
        return turnos;
    }

    @Override
    public List buscarPorPaciente(Paciente paciente){
        List<Turno> turnos = new ArrayList<>();
        for(Turno turno:turnos){
            if(turno.getPaciente().equals(paciente)){
                turnos.add(turno);
            }
        }
        return turnos;
    }
}
