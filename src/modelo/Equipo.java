package modelo;

public class Equipo {
    private int idEquipo;
    private String modelo;
    private String cpu;
    private int discoMB;
    private int ramGB;
    private double precio;
    private String tipo;
    private String potenciaFuente;
    private String factorForma;
    private double pantallaPulgadas;
    private boolean touch;
    private int puertosUSB;

    // Constructor vacío
    public Equipo() {}

    // Constructor general
    public Equipo(int idEquipo, String modelo, String cpu, int discoMB, int ramGB, double precio, String tipo,
                  String potenciaFuente, String factorForma, double pantallaPulgadas, boolean touch, int puertosUSB) {
        this.idEquipo = idEquipo;
        this.modelo = modelo;
        this.cpu = cpu;
        this.discoMB = discoMB;
        this.ramGB = ramGB;
        this.precio = precio;
        this.tipo = tipo;
        this.potenciaFuente = potenciaFuente;
        this.factorForma = factorForma;
        this.pantallaPulgadas = pantallaPulgadas;
        this.touch = touch;
        this.puertosUSB = puertosUSB;
    }

    // Getters y Setters
    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getCpu() { return cpu; }
    public void setCpu(String cpu) { this.cpu = cpu; }

    public int getDiscoMB() { return discoMB; }
    public void setDiscoMB(int discoMB) { this.discoMB = discoMB; }

    public int getRamGB() { return ramGB; }
    public void setRamGB(int ramGB) { this.ramGB = ramGB; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getPotenciaFuente() { return potenciaFuente; }
    public void setPotenciaFuente(String potenciaFuente) { this.potenciaFuente = potenciaFuente; }

    public String getFactorForma() { return factorForma; }
    public void setFactorForma(String factorForma) { this.factorForma = factorForma; }

    public double getPantallaPulgadas() { return pantallaPulgadas; }
    public void setPantallaPulgadas(double pantallaPulgadas) { this.pantallaPulgadas = pantallaPulgadas; }

    public boolean isTouch() { return touch; }
    public void setTouch(boolean touch) { this.touch = touch; }

    public int getPuertosUSB() { return puertosUSB; }
    public void setPuertosUSB(int puertosUSB) { this.puertosUSB = puertosUSB; }
}
