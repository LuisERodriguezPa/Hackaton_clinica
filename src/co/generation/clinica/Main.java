package co.generation.clinica;

import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.services.ClinicaService;
import co.generation.clinica.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClinicaService clinica = new ClinicaService();
        Scanner entrada = new Scanner(System.in);
        Medico medicoDePrueba = new Medico("Pedro", "Paramo", Especialidad.PEDIATRIA);
        int opcion;
        DatosCSV.cargar(clinica);
        // bucle while con Scanner para el menú...
        String menu = """
                 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                          ■ CLINICAAPP — MENÚ ■
                 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                 ■ 1. Registrar paciente ■
                 ■ 2. Registrar médico ■
                 ■ 3. Asignar turno ■
                 ■ 4. Listar turnos del día ■
                 ■ 5. Cancelar turno ■
                 ■ 6. Ver turnos por médico ■
                 ■ 7. Ver turnos por paciente ■
                 ■ 8. Cambiar estado de turno ■
                 ■ 9. Listar pacientes ■
                 ■ 10. Listar médicos ■
                 ■ 0. Salir ■
                 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                """;
        do{
            System.out.println(menu);
            System.out.println("Ingrese la opcion que desea realizar:");
            opcion = entrada.nextInt();
            switch (opcion){
                case 1:
                    System.out.print("Ingrese la cédula del paciente: ");
                    String cedula = entrada.next();
                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombre = entrada.next();
                    System.out.print("Ingrese el apellido del paciente: ");
                    String apellido = entrada.next();
                    System.out.print("Ingrese el Teléfono del paciente: ");
                    String telefono = entrada.next();
                    Paciente nuevoPaciente = new Paciente(cedula, nombre, apellido, telefono);
                    clinica.registrarPaciente(nuevoPaciente);
                    break;
                case 2:
                    System.out.println("***Registrar Medico***");
                    System.out.print("Ingrese el nombre del medico: ");
                    String nombreMedico= entrada.next();
                    System.out.print("Ingrese el apellido del medico: ");
                    String apellidoMedico = entrada.next();
                    Especialidad especialidad = Especialidad.CARDIOLOGIA;

                    Medico nuevoMedico = new Medico(nombreMedico, apellidoMedico, especialidad);
                    clinica.registrarMedico(nuevoMedico);

                    break;
                case 3:
                    System.out.print("Cedula paciente: ");
                    String cedulaPaciente = entrada.next();
                    Paciente paciente = clinica.buscarPorCedula(cedulaPaciente);

                    if (paciente == null) {
                        System.out.println("Paciente no encontrado");
                        break;
                    }

                    System.out.print("Nombre medico: ");
                    String nombreMedico2 = entrada.next();
                    System.out.print("Apellido medico: ");
                    String apellidoMedico2 = entrada.next();
                    Medico medico = clinica.buscarPorNombreApellido(nombreMedico2, apellidoMedico2);

                    if (medico == null) {
                        System.out.println("Medico no encontrado");
                        break;
                    }

                    System.out.print("Año: ");
                    int anio = entrada.nextInt();
                    System.out.print("Mes: ");
                    int mes = entrada.nextInt();
                    System.out.print("Dia: ");
                    int dia = entrada.nextInt();
                    System.out.print("Hora: ");
                    int hora = entrada.nextInt();
                    System.out.print("Minuto: ");
                    int minuto = entrada.nextInt();

                    LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, minuto);

                    Turno turno = new Turno(paciente, medico, fechaHora);
                    clinica.asignarTurno(turno);
                    System.out.println("Turno asignado");
                    break;
                case 4:
                    String fechaString;
                    LocalDate fecha;
                    System.out.println("***Listar todos los turnos****");
                    System.out.println("Por favor Ingrese la fecha con este formato (yyyy-mm-dd):");
                    fechaString = entrada.nextLine();
                    fecha = LocalDate.parse(fechaString);
                    for(Object turnoT:clinica.listarTurnosDelDia(fecha)){
                        System.out.println(turnoT);
                    }
                    break;
                case 5:
                    System.out.println("***Cancelar Turno****");
                    int id_Turno;
                    System.out.println("Ingrese el id del turno: ");
                    id_Turno = entrada.nextInt();
                    clinica.cancelarTurno(id_Turno);
                    break;
                case 6:
                    System.out.print("Ingrese el nombre del médico: ");
                    String nombreMedico3 = entrada.next();
                    System.out.print("Ingrese el apellido del médico: ");
                    String apellidoMedico3 = entrada.next();
                    clinica.buscarPorMedico(medicoDePrueba);
                    break;
                case 7:
                    System.out.println("Buscar todas las citas del paciente");
                    System.out.print("Ingrese la cédula del paciente: ");
                    String cedula2 = entrada.next();
                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombre2 = entrada.next();
                    System.out.print("Ingrese el apellido del paciente: ");
                    String apellido2 = entrada.next();
                    System.out.print("Ingrese el Teléfono del paciente: ");
                    String telefono2 = entrada.next();
                    Paciente nuevoPaciente2 = new Paciente(cedula2, nombre2, apellido2, telefono2);
                    clinica.buscarPorPaciente(nuevoPaciente2);
                    break;
                case 8:
                    System.out.println("***Cambiar estado de turno***");
                    int idTurno;
                    String nuevoEstado;
                    System.out.println("Ingrese el id del turno:");
                    idTurno = entrada.nextInt();
                    System.out.println("Ingrese el nuevo estado: ");
                    nuevoEstado = entrada.nextLine();
                    EstadoTurno estado = EstadoTurno.valueOf(nuevoEstado.toUpperCase());
                    clinica.cambiarEstadoTurno(idTurno,estado);

                    break;
                case 9:
                    clinica.listarPacientes();
                    break;
                case 10:
                    clinica.listarMedicos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion Invalida Ingrese una opcion correcta");
            }
        }while(opcion !=0);


        // Al salir (opción 0):
        DatosCSV.guardar(clinica);
        System.out.println("Hasta pronto. Datos guardados.");

    }
}
