package co.generation.clinica.interfaces;

import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;

import java.time.LocalDate;
import java.util.List;

public interface Consultable {
    public List listarTurnosDelDia(LocalDate fecha);
    public List buscarPorMedico(Medico medico);
    public List buscarPorPaciente(Paciente paciente);
}
