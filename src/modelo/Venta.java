package modelo;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private String rutCliente;
    private int idEquipo;
    private LocalDateTime fechaHora;
    private double descuento;
    private double total;

    public Venta() {}

    public Venta(int idVenta, String rutCliente, int idEquipo, LocalDateTime fechaHora, double descuento, double total) {
        this.idVenta = idVenta;
        this.rutCliente = rutCliente;
        this.idEquipo = idEquipo;
        this.fechaHora = fechaHora;
        this.descuento = descuento;
        this.total = total;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public String getRutCliente() { return rutCliente; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }

    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
