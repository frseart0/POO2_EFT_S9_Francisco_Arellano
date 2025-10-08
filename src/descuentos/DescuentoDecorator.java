package descuentos;

public abstract class DescuentoDecorator implements Descuento {
    protected Descuento descuento;

    public DescuentoDecorator(Descuento descuento) {
        this.descuento = descuento;
    }

    @Override
    public double aplicarDescuento(double precioBase) {
        return descuento.aplicarDescuento(precioBase);
    }
}