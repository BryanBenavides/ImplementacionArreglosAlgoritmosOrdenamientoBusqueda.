package ActaDeNotas;

import java.util.Scanner;
import java.util.Arrays;

public class ActasDeNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos Generales
        System.out.println("Ingrese los Datos Generales:");

        System.out.print("Nombre del curso: ");
        String nombreCurso = scanner.nextLine();

        System.out.print("Período lectivo: ");
        String periodoLectivo = scanner.nextLine();

        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();

        System.out.print("Modalidad: ");
        String modalidad = scanner.nextLine();

        System.out.print("Código del curso: ");
        String codigoCurso = scanner.nextLine();

        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();

        System.out.print("Código de asignatura: ");
        String codigoAsignatura = scanner.nextLine();

        System.out.print("Código de programa de asignatura: ");
        String codigoProgramaAsignatura = scanner.nextLine();

        // Cantidad de estudiantes
        int cantidadEstudiantes = 0;
        boolean entradaValida = false;

        // Validación de entrada para cantidad de estudiantes
        while (!entradaValida) {
            try {
                System.out.print("Cantidad de estudiantes: ");
                cantidadEstudiantes = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para la cantidad de estudiantes.");
            }
        }

        // Datos de Estudiantes y Notas
        System.out.println("\nIngrese los Datos de Estudiantes y sus Notas:");
        

        // Arrays para almacenar los datos
        String[] numerosCarnet = new String[cantidadEstudiantes];
        String[] nombresApellidos = new String[cantidadEstudiantes];
        double[] primerParciales = new double[cantidadEstudiantes];
        double[] sistematicos = new double[cantidadEstudiantes];
        double[] segundosParciales = new double[cantidadEstudiantes];
        double[] notasFinales = new double[cantidadEstudiantes];
        double[] examenesPrimeraConvocatoria = new double[cantidadEstudiantes];
        double[] notasFinalesPrimeraConvocatoria = new double[cantidadEstudiantes];
        double[] examenesSegundaConvocatoria = new double[cantidadEstudiantes];
        boolean[] aprobados = new boolean[cantidadEstudiantes];
        boolean[] deserciones = new boolean[cantidadEstudiantes];

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("\nEstudiante #" + (i + 1) + ":");

            System.out.print("Nombres: ");
            String nombres = obtenerTexto("Ingrese solo letras para Nombres: ", scanner);

            System.out.print("Apellidos: ");
            String apellidos = obtenerTexto("Ingrese solo letras para Apellidos: ", scanner);

            // Combinar nombres y apellidos en una sola cadena
            nombresApellidos[i] = nombres + " " + apellidos;

            // Notas
            primerParciales[i] = obtenerNota("IP", 35.0, scanner);
            sistematicos[i] = obtenerNota("Sistemáticos", 30.0, scanner);

            // Proyecto de curso
            boolean llevaProyecto = obtenerRespuestaBooleana("¿La asignatura lleva proyecto de curso? (Si/No): ", scanner);

            if (llevaProyecto) {
                // Si hay proyecto, pedir nota de proyecto
                segundosParciales[i] = obtenerNota("Proyecto", 35.0, scanner);
            } else {
                // Si no hay proyecto, pedir nota del segundo parcial
                segundosParciales[i] = obtenerNota("IIP", 35.0, scanner);
            }

            // Calcular Nota Final
            notasFinales[i] = primerParciales[i] + sistematicos[i] + segundosParciales[i];

            // Evaluar si el estudiante debe presentar examen de primera convocatoria
            if (notasFinales[i] < 60.0) {
                examenesPrimeraConvocatoria[i] = obtenerNota("EXA CONV I", 70.0, scanner);
                notasFinalesPrimeraConvocatoria[i] = sistematicos[i] + examenesPrimeraConvocatoria[i];
                aprobados[i] = notasFinalesPrimeraConvocatoria[i] >= 60.0;
            } else {
                aprobados[i] = true;
            }

            // Evaluar si el estudiante debe presentar examen de segunda convocatoria
            if (!aprobados[i]) {
                examenesSegundaConvocatoria[i] = obtenerNota("CONV II", 100.0, scanner);
                notasFinales[i] = sistematicos[i] + examenesSegundaConvocatoria[i];
            }

            // Determinar si hay deserción
            deserciones[i] = obtenerRespuestaBooleana("¿El estudiante desertó el curso? (Si/No): ", scanner);
        }

        // Ordenar estudiantes por nota final ascendente
        ordenarEstudiantesPorNotaFinalAscendente(nombresApellidos, numerosCarnet, primerParciales, sistematicos,
                segundosParciales, notasFinales, examenesPrimeraConvocatoria, notasFinalesPrimeraConvocatoria,
                examenesSegundaConvocatoria, aprobados, deserciones);

        // Mostrar la matriz o tabla al final del programa
        System.out.println("\nMatriz o Tabla de Datos:");

        System.out.printf("%-5s %-15s %-25s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-10s %-10s\n", "No.", "Carnet",
                "Apellidos y Nombres", "IP", "Sistematicos", "IIP", "Nota final N.F.", "EXA CONV I", "N.F. I CONV",
                "CONV II", "Aprobado", "Desercion");

        // Línea divisoria
        System.out.println(
                "=====================================================================================================================================================================================");

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.printf("%-5d %-15s %-25s %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-10s %-10s\n",
                    i + 1, numerosCarnet[i], nombresApellidos[i], primerParciales[i], sistematicos[i],
                    segundosParciales[i], notasFinales[i], examenesPrimeraConvocatoria[i],
                    notasFinalesPrimeraConvocatoria[i], examenesSegundaConvocatoria[i], aprobados[i] ? "Sí" : "No",
                    deserciones[i] ? "Sí" : "No");

            // Línea divisoria
            System.out.println(
                    "====================================================================================================================================================================================");
        }

        // Mostrar reporte de datos estadísticos
        System.out.println("\nReporte de Datos Estadísticos:");

        int matriculaInicial = cantidadEstudiantes;
        int desercionesContador = 0;
        int cantidadAprobados = 0;
        int cantidadReprobados = 0;
        double sumaNotas = 0.0;
        double notaMinima = Double.MAX_VALUE;
        double notaMaxima = Double.MIN_VALUE;

        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (deserciones[i]) {
                desercionesContador++;
            }

            if (aprobados[i]) {
                cantidadAprobados++;
            } else {
                cantidadReprobados++;
            }

            sumaNotas += notasFinales[i];

            if (notasFinales[i] < notaMinima) {
                notaMinima = notasFinales[i];
            }

            if (notasFinales[i] > notaMaxima) {
                notaMaxima = notasFinales[i];
            }
        }

        int matriculaEfectiva = matriculaInicial - desercionesContador;
        double porcentajeAprobados = (double) cantidadAprobados / matriculaEfectiva * 100.0;
        double porcentajeReprobados = (double) cantidadReprobados / matriculaEfectiva * 100.0;
        double promedioNotas = sumaNotas / matriculaEfectiva;

        System.out.println("Matrícula inicial: " + matriculaInicial);
        System.out.println("Matrícula efectiva: " + matriculaEfectiva);
        System.out.println("Número de deserciones: " + desercionesContador);
        System.out.println("Cantidad de aprobados: " + cantidadAprobados);
        System.out.printf("%% de aprobados: %.2f%%\n", Math.min(porcentajeAprobados, 100.0));
        System.out.println("Cantidad de reprobados: " + cantidadReprobados);
        System.out.printf("%% de reprobados: %.2f%%\n", Math.min(porcentajeReprobados, 100.0));
        System.out.println("Nota mínima: " + notaMinima);
        System.out.println("Nota máxima: " + notaMaxima);
        System.out.printf("Promedio de notas: %.2f\n", Math.min(promedioNotas, 100.0));
    }

    // Métodos auxiliares
    private static double obtenerNota(String tipoNota, double maximo, Scanner scanner) {
        boolean entradaValida = false;
        double nota = 0.0;

        while (!entradaValida) {
            try {
                System.out.print(tipoNota + " (máximo " + maximo + "): ");
                nota = Double.parseDouble(scanner.nextLine());

                if (nota >= 0 && nota <= maximo) {
                    entradaValida = true;
                } else {
                    System.out.println("Error: Ingrese una nota válida entre 0 y " + maximo + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para la nota.");
            }
        }

        return nota;
    }

    private static void ordenarEstudiantesPorNotaFinalAscendente(String[] nombresApellidos, String[] numerosCarnet,
            double[] primerParciales, double[] sistematicos, double[] segundosParciales, double[] notasFinales,
            double[] examenesPrimeraConvocatoria, double[] notasFinalesPrimeraConvocatoria,
            double[] examenesSegundaConvocatoria, boolean[] aprobados, boolean[] deserciones) {

        int n = nombresApellidos.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (notasFinales[j] > notasFinales[j + 1]) {
                    // Intercambiar elementos
                    intercambiarElementos(nombresApellidos, j, j + 1);
                    intercambiarElementos(numerosCarnet, j, j + 1);
                    intercambiarElementos(primerParciales, j, j + 1);
                    intercambiarElementos(sistematicos, j, j + 1);
                    intercambiarElementos(segundosParciales, j, j + 1);
                    intercambiarElementos(notasFinales, j, j + 1);
                    intercambiarElementos(examenesPrimeraConvocatoria, j, j + 1);
                    intercambiarElementos(notasFinalesPrimeraConvocatoria, j, j + 1);
                    intercambiarElementos(examenesSegundaConvocatoria, j, j + 1);
                    intercambiarElementos(aprobados, j, j + 1);
                    intercambiarElementos(deserciones, j, j + 1);
                }
            }
        }
    }

    private static void intercambiarElementos(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void intercambiarElementos(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void intercambiarElementos(boolean[] array, int i, int j) {
        boolean temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static boolean obtenerRespuestaBooleana(String mensaje, Scanner scanner) {
        boolean respuestaValida = false;
        boolean respuesta = false;

        while (!respuestaValida) {
            System.out.print(mensaje);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("si")) {
                respuesta = true;
                respuestaValida = true;
            } else if (input.equals("no")) {
                respuestaValida = true;
            } else {
                System.out.println("Error: Ingrese 'Si' o 'No'.");
            }
        }

        return respuesta;
    }

    private static String obtenerTexto(String mensaje, Scanner scanner) {
        boolean entradaValida = false;
        String texto = "";

        while (!entradaValida) {
            System.out.print(mensaje);
            texto = scanner.nextLine();

            if (texto.matches("[a-zA-Z ]+")) {
                entradaValida = true;
            } else {
                System.out.println("Error: Ingrese solo letras para el texto.");
            }
        }

        return texto;
    }
}
