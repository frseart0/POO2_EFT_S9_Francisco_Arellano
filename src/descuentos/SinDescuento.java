package descuentos;

public class SinDescuento implements Descuento {
    @Override
    public double aplicarDescuento(double precioBase) {
        return precioBase; // no se modifica el precio
    }
}