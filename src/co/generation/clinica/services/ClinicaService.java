package co.generation.clinica.services;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.util.List;
import java.util.ArrayList;

public class ClinicaService implements Consultable {
    private List<Paciente> pacientes = new ArrayList<>();
    private  List<Medico> medicos = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();
}
