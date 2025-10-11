package command;

import modelo.Equipo;

public class EliminarEquipoCommand implements Command {
    private Carrito carrito;
    private Equipo equipo;

    public EliminarEquipoCommand(Carrito carrito, Equipo equipo) {
        this.carrito = carrito;
        this.equipo = equipo;
    }

    @Override
    public void execute() {
        carrito.eliminarEquipo(equipo);
    }
}
