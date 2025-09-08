public class Dueno {
    private long id;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String direccion;

    // Constructor completo
    public Dueno(long id, String nombreCompleto, String telefono, String email, String direccion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // Constructor sobrecargado (email y direccion por defecto)
    public Dueno(long id, String nombreCompleto, String telefono) {
        this(id, nombreCompleto, telefono, null, "sin dirección");
    }

    // Getters y setters
    public long getId() { return id; }
    public void setId(long id) { if (id > 0) this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto != null && !nombreCompleto.isEmpty())
            this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String resumen() {
        return "Dueño[" + id + "] " + nombreCompleto + " (" + telefono + ")";
    }
}
