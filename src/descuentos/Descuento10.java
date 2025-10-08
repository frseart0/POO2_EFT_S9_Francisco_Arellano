package descuentos;

public class Descuento10 extends DescuentoDecorator {
    public Descuento10(Descuento descuento) {
        super(descuento);
    }

    @Override
    public double aplicarDescuento(double precioBase) {
        double base = super.aplicarDescuento(precioBase);
        return base * 0.9; // 10% menos
    }
}
