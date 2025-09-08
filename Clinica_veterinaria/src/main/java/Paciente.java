public class Paciente {
    private long id;
    private String nombre;
    private String especie;
    private String raza;
    private int edadMeses;
    private double pesoKg;
    private boolean esCachorro;

    // Constructor completo
    public Paciente(long id, String nombre, String especie, String raza, int edadMeses, double pesoKg) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edadMeses = edadMeses;
        this.pesoKg = pesoKg;
        this.esCachorro = edadMeses < 12;
    }

    // Constructor sobrecargado
    public Paciente(long id, String nombre, String especie) {
        this(id, nombre, especie, "mestizo", 1, 1.0);
    }

    // Getters y setters
    public long getId() { return id; }
    public void setId(long id) { if (id > 0) this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty())
            this.nombre = nombre;
    }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdadMeses() { return edadMeses; }
    public void setEdadMeses(int edadMeses) {
        if (edadMeses > 0) this.edadMeses = edadMeses;
        this.esCachorro = edadMeses < 12;
    }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) {
        if (pesoKg > 0) this.pesoKg = pesoKg;
    }

    public boolean isEsCachorro() { return esCachorro; }

    public String resumen() {
        return "Mascota[" + id + "] " + nombre + " (" + especie + ", " + raza + ", " + edadMeses + "m, " + pesoKg + "kg)";
    }
}
