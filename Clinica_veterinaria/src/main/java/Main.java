public class Main {

    public static void main(String[] args) {

        // == PARTE B: Generación masiva de datos ==
        Dueno[] duenos = new Dueno[500];
        Paciente[] pacientes = new Paciente[1000];

        // Crear dueños
        for (int i = 0; i < duenos.length; i++) {
            long id = i + 1;
            String nombre = "Dueno" + id;

            // Generar número de teléfono con mezcla determinística
            int base = (i * 73 + i * i * 19) % 1000000;
            String telefono = String.format("+502 %08d", 55000000 + base);

            String email = "dueno" + id + "@mail.com";
            String direccion = "Calle " + id;

            if (i % 2 == 0) {
                duenos[i] = new Dueno(id, nombre, telefono, email, direccion);
            } else {
                duenos[i] = new Dueno(id, nombre, telefono);
            }
        }

        // Crear mascotas
        for (int i = 0; i < pacientes.length; i++) {
            long id = i + 1;
            String especie = (i % 2 == 0) ? "perro" : "gato";
            String nombre = "Mascota" + id;
            int edadMeses = (i % 120) + 1;
            double pesoKg = 1.0 + (i % 30);

            pacientes[i] = new Paciente(id, nombre, especie, "mestizo", edadMeses, pesoKg);
        }

        // Verificación rápida
        System.out.println("=== Verificación: primeros 3 dueños ===");
        for (int i = 0; i < 3; i++) {
            System.out.println(duenos[i].resumen());
        }

        System.out.println("\n=== Verificación: primeras 6 mascotas ===");
        for (int i = 0; i < 6; i++) {
            System.out.println(pacientes[i].resumen());
        }

        // Guardar en BD estática
        BD.duenos = duenos;
        BD.pacientes = pacientes;

        // == PARTE C: Reportes ==
        System.out.println("\n=== PARTE C: REPORTES ===");

        // 1) Conteo de cachorros
        int totalCachorros = 0;
        for (Paciente p : BD.pacientes) {
            if (p.isEsCachorro()) totalCachorros++;
        }
        System.out.println("1) Total de cachorros: " + totalCachorros);

        // 2) Distribución por especie y 3) sumas para promedio de peso
        int totalPerros = 0, totalGatos = 0;
        double sumaPesoPerros = 0, sumaPesoGatos = 0;

        for (Paciente p : BD.pacientes) {
            if (p.getEspecie().equalsIgnoreCase("perro")) {
                totalPerros++;
                sumaPesoPerros += p.getPesoKg();
            } else {
                totalGatos++;
                sumaPesoGatos += p.getPesoKg();
            }
        }

        System.out.println("2) Distribución: Perros=" + totalPerros + ", Gatos=" + totalGatos);

        double promedioPerros = (totalPerros > 0) ? sumaPesoPerros / totalPerros : 0;
        double promedioGatos = (totalGatos > 0) ? sumaPesoGatos / totalGatos : 0;

        System.out.printf("3) Peso promedio -> Perros: %.2f kg, Gatos: %.2f kg%n",
                promedioPerros, promedioGatos);

        // 4) Top 5 mascotas más longevas
        System.out.println("4) Top 5 mascotas más longevas:");
        Paciente[] top5 = new Paciente[5];

        for (Paciente p : BD.pacientes) {
            for (int j = 0; j < top5.length; j++) {
                if (top5[j] == null || p.getEdadMeses() > top5[j].getEdadMeses()) {
                    // desplazar hacia abajo
                    for (int k = top5.length - 1; k > j; k--) {
                        top5[k] = top5[k - 1];
                    }
                    top5[j] = p;
                    break;
                }
            }
        }

        for (int i = 0; i < top5.length; i++) {
            if (top5[i] != null) {
                System.out.println("   " + (i + 1) + ") " + top5[i].resumen());
            }
        }

        // == PARTE D: Citas ==
        System.out.println("\n=== PARTE D: CITAS ===");

        Cita[] citas = new Cita[40];
        int indicePaciente = 0;

        for (int i = 0; i < citas.length; i++) {
            Dueno dueno = BD.duenos[i / 2]; // 2 citas por dueño
            Paciente paciente = BD.pacientes[indicePaciente++];
            String fecha = "2025-09-" + String.format("%02d", (i % 30) + 1);
            String motivo = "Consulta general";

            Cita cita = new Cita(i + 1, dueno, paciente, fecha, motivo);

            // Reglas de estado (orden de prioridad)
            if (i % 5 == 0) {
                cita.setEstado(EstadoCita.CANCELADA);
            } else if (i % 3 == 0) {
                cita.setEstado(EstadoCita.REAGENDADA);
            } else if (i % 2 == 0) {
                cita.setEstado(EstadoCita.ATENDIDA);
            }

            citas[i] = cita;
        }

        // Encabezado de la tabla
        System.out.printf("%-5s %-12s %-20s %-15s %-10s %-12s%n",
                "ID", "FECHA", "DUEÑO", "MASCOTA", "ESPECIE", "ESTADO");
        System.out.println("--------------------------------------------------------------------------");

// Filas de la tabla
        for (Cita c : citas) {
            System.out.printf("%-5d %-12s %-20s %-15s %-10s %-12s%n",
                    c.getId(),
                    c.getFecha(),
                    c.getDueno().getNombreCompleto(),
                    c.getPaciente().getNombre(),
                    c.getPaciente().getEspecie(),
                    c.getEstado());
        }

    }
    }

// Clase para mantener los arreglos accesibles
class BD {
    public static Dueno[] duenos;
    public static Paciente[] pacientes;
}
