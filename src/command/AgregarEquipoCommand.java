package command;

import modelo.Equipo;

public class AgregarEquipoCommand implements Command {
    private Carrito carrito;
    private Equipo equipo;

    public AgregarEquipoCommand(Carrito carrito, Equipo equipo) {
        this.carrito = carrito;
        this.equipo = equipo;
    }

    @Override
    public void execute() {
        carrito.agregarEquipo(equipo);
    }
}
