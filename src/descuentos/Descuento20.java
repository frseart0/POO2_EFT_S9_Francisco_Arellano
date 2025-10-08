package descuentos;

public class Descuento20 extends DescuentoDecorator {
    public Descuento20(Descuento descuento) {
        super(descuento);
    }

    @Override
    public double aplicarDescuento(double precioBase) {
        double base = super.aplicarDescuento(precioBase);
        return base * 0.8; // 20% menos
    }
}
