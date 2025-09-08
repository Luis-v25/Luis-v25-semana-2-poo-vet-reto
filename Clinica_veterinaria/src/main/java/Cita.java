public class Cita {
    private long id;
    private Dueno dueno;
    private Paciente paciente;
    private String fecha;
    private String motivo;
    private EstadoCita estado;

    public Cita(long id, Dueno dueno, Paciente paciente, String fecha, String motivo) {
        this.id = id;
        this.dueno = dueno;
        this.paciente = paciente;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = EstadoCita.PENDIENTE; // por defecto
    }

    // Getters y Setters
    public long getId() { return id; }
    public Dueno getDueno() { return dueno; }
    public Paciente getPaciente() { return paciente; }
    public String getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
    public EstadoCita getEstado() { return estado; }

    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public String resumen() {
        return "Cita[" + id + "] " + fecha + " - " + motivo +
                " | Dueño: " + dueno.getNombreCompleto() +
                " | Mascota: " + paciente.getNombre() +
                " | Estado: " + estado;
    }
}
