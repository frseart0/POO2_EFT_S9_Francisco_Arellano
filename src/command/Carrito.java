package command;

import modelo.Equipo;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Equipo> equipos = new ArrayList<>();

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
        System.out.println("Equipo agregado al carrito:" + equipo.getModelo());
    }

    public void eliminarEquipo(Equipo equipo) {
        equipos.remove(equipo);
        System.out.println("Equipo eliminado del carrito:" + equipo.getModelo());
    }

    public void mostrarCarrito() {
        System.out.println("Contenido del carrito:");
        for (Equipo e : equipos) {
            System.out.println(" - " + e.getModelo() + " | $" + e.getPrecio());
        }
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
}
