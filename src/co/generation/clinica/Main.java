package co.generation.clinica;

import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.services.ClinicaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClinicaService clinica = new ClinicaService();
        Scanner entrada = new Scanner(System.in);
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
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
