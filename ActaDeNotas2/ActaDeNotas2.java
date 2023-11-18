package ActaDeNotas2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActaDeNotas2 {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        // Solicitar información de datos generales
        String nombreCurso = "";
        while (true) {
            System.out.print("Nombre del curso (solo letras y números): ");
            nombreCurso = lector.nextLine();
            if (nombreCurso.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("No se permiten caracteres especiales, ingrese solo letras y números.");
            }
        }

        String periodoLectivo = "";
        while (true) {
            System.out.print("Período lectivo (solo letras y números): ");
            periodoLectivo = lector.nextLine();
            if (periodoLectivo.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("No se permiten caracteres especiales, ingrese solo letras y números.");
            }
        }

        String carrera = "";
        while (true) {
            System.out.print("Carrera (solo letras y números): ");
            carrera = lector.nextLine();
            if (carrera.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("No se permiten caracteres especiales, ingrese solo letras y números.");
            }
        }

        String modalidad = "";
        while (true) {
            System.out.print("Modalidad (solo letras y números): ");
            modalidad = lector.nextLine();
            if (modalidad.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("No se permiten caracteres especiales, ingrese solo letras y números.");
            }
        }

        String codigoAsignatura = "";
        while (true) {
            System.out.print("Código de asignatura (solo letras y números): ");
            codigoAsignatura = lector.nextLine();
            if (codigoAsignatura.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("No se permiten caracteres especiales, ingrese solo letras y números.");
            }
        }

        String codigoCurso = "";
        while (true) {
            System.out.print("Código del curso (solo números): ");
            codigoCurso = lector.nextLine();
            if (codigoCurso.matches("\\d+")) {
                break;
            } else {
                System.out.println("Por favor, ingrese solo números para el código del curso.");
            }
        }

        System.out.print("Grupo: ");

        String codigoProgramaAsignatura = "";
        while (true) {
            System.out.print("Código de programa de asignatura (solo números): ");
            codigoProgramaAsignatura = lector.nextLine();
            if (codigoProgramaAsignatura.matches("\\d+")) {
                break;
            } else {
                System.out.println("Por favor, ingrese solo números para el código de programa de asignatura.");
            }
        }

        System.out.print("Cantidad de estudiantes: ");
        int cantidadEstudiantes = lector.nextInt();
        lector.nextLine(); // Consumir el salto de línea

        // Solicitar información de cada estudiante y almacenarla en una lista de estudiantes
        ArrayList<Estudiante2> estudiantes = new ArrayList<>();
        for (int i = 0; i < cantidadEstudiantes; i++) {
            String carnet = "";
            while (true) {
                System.out.print("Número de carnet del estudiante " + (i + 1) + " (formato 20XX-XXXXX): ");
                carnet = lector.next();
                if (carnet.matches("^20\\d{2}-\\d{5}$")) {
                    break;
                } else {
                    System.out.println("Formato incorrecto. Ingrese el número de carnet en el formato 20XX-XXXXX.");
                }
            }

            lector.nextLine(); // Consumir el salto de línea

            String nombresApellidos = "";
            while (true) {
                System.out.print("Nombres y Apellidos del estudiante " + (i + 1) + " (solo letras): ");
                nombresApellidos = lector.nextLine();
                if (nombresApellidos.matches("^[a-zA-Z ]+$")) {
                    break;
                } else {
                    System.out.println("No se permiten caracteres especiales ni números. Ingrese solo letras.");
                }
            }

            double primerParcial;
            while (true) {
                System.out.print("Primer parcial (máximo 35.00) del estudiante " + (i + 1) + ": ");
                String primerParcialStr = lector.next();
                if (esNumeroValido(primerParcialStr, 35.0)) {
                    primerParcial = Double.parseDouble(primerParcialStr);
                    break;
                } else {
                    System.out.println("Por favor, ingrese un número válido que no exceda 35.00.");
                }
            }

            double segundoParcial = 0.0;
            double proyecto = 0.0;

            char opcionProyecto;
            while (true) {
                System.out.print("¿Lleva proyecto de curso? (S/N): ");
                opcionProyecto = lector.next().toUpperCase().charAt(0);
                if (opcionProyecto == 'S' || opcionProyecto == 'N') {
                    break;
                } else {
                    System.out.println("Caracter no válido. Por favor, ingrese 'S' para sí o 'N' para no.");
                }
            }

            if (opcionProyecto == 'S') {
                while (true) {
                    System.out.print("Nota del proyecto (máximo 35.00) del estudiante " + (i + 1) + ": ");
                    String proyectoStr = lector.next();
                    if (esNumeroValido(proyectoStr, 35.0)) {
                        proyecto = Double.parseDouble(proyectoStr);
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número válido que no exceda 35.00.");
                    }
                }
            } else {
                while (true) {
                    System.out.print("Segundo parcial (máximo 35.00) del estudiante " + (i + 1) + ": ");
                    String segundoParcialStr = lector.next();
                    if (esNumeroValido(segundoParcialStr, 35.0)) {
                        segundoParcial = Double.parseDouble(segundoParcialStr);
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número válido que no exceda 35.00.");
                    }
                }
            }

            double sistematicos;
            while (true) {
                System.out.print("Sistemáticos (máximo 30.00) del estudiante " + (i + 1) + ": ");
                String sistematicosStr = lector.next();
                if (esNumeroValido(sistematicosStr, 30.0)) {
                    sistematicos = Double.parseDouble(sistematicosStr);
                    break;
                } else {
                    System.out.println("Por favor, ingrese un número válido que no exceda 30.00.");
                }
            }

            estudiantes.add(new Estudiante2(carnet, nombresApellidos, primerParcial, segundoParcial, proyecto, sistematicos));
        }

        // Ordenar a los estudiantes alfabéticamente por sus apellidos
        Collections.sort(estudiantes, Comparator.comparing(estudiante -> {
            String[] parts = estudiante.nombresApellidos.split(" ");
            return parts[parts.length - 1]; // Último apellido
        }));

        // Imprimir la información recopilada en una tabla mejor formateada
        System.out.println("=================================================================================================================================================");
        System.out.printf("|%-15s|%-30s|%-18s|%-18s|%-15s|%-15s|%-18s|%-18s|%-18s|%-15s|%-15s|%-20s|%-15s|%n", "No. Carnet", "Apellidos y Nombres", "Primer Parcial (IP)", "Segundo Parcial (IIP)", "Sistemáticos", "Proyecto", "Nota Final (N.F.)", "Examen I Convo", "Nota Final Convo I", "Examen II Convo", "Aprobó", "Deserción");
        System.out.println("=================================================================================================================================================");

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante2 estudiante = estudiantes.get(i);

            double primerParcial = estudiante.primerParcial;
            double segundoParcial = estudiante.segundoParcial;
            double proyecto = estudiante.proyecto;
            double sistematicos = estudiante.sistematicos;

            double total = primerParcial + segundoParcial + proyecto + sistematicos;
            double promedio = total / 100.0;
            String resultado = (promedio >= 0.5) ? "Sí" : "No";

            if (promedio < 0.6) {
                double notaExamen1;
                System.out.print("Examen de primera convocatoria (máximo 70.00) para el estudiante " + estudiante.nombresApellidos + ": ");
                while (true) {
                    String notaExamen1Str = lector.next();
                    if (esNumeroValido(notaExamen1Str, 70.0)) {
                        notaExamen1 = Double.parseDouble(notaExamen1Str);
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número válido que no exceda 70.00.");
                    }
                }

                double notaFinalConvocatoria1 = notaExamen1 + sistematicos;
                if (notaFinalConvocatoria1 < 60.0) {
                    double notaExamen2;
                    System.out.print("Examen de segunda convocatoria (máximo 100.00) para el estudiante " + estudiante.nombresApellidos + ": ");
                    while (true) {
                        String notaExamen2Str = lector.next();
                        if (esNumeroValido(notaExamen2Str, 100.0)) {
                            notaExamen2 = Double.parseDouble(notaExamen2Str);
                            break;
                        } else {
                            System.out.println("Por favor, ingrese un número válido que no exceda 100.00.");
                        }
                    }
                    resultado = (notaExamen2 >= 60.0) ? "Sí" : "No";
                    System.out.print("¿Desertó el estudiante " + estudiante.nombresApellidos + "? (Sí/No): ");
                    String desercion = lector.next();
                    if (desercion.equalsIgnoreCase("Sí")) {
                        resultado = "Desertó";
                    }
                }
            }

            System.out.printf("|%-15s|%-30s|%-18.2f|%-18.2f|%-15.2f|%-15.2f|%-18.2f|%-18.2f|%-18.2f|%-15s|%-15s|%-20s|%-15s|%n",
                    estudiante.carnet, estudiante.nombresApellidos, estudiante.primerParcial, estudiante.segundoParcial, estudiante.sistematicos,
                    estudiante.proyecto, total, 0.0, 0.0, 0.0, resultado, "");
        }

        System.out.println("=================================================================================================================================================");
    }

    private static boolean esNumeroValido(String numeroStr, double maximo) {
        try {
            double numero = Double.parseDouble(numeroStr);
            return numero >= 0.0 && numero <= maximo;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
