package command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private List<Command> historial = new ArrayList<>();

    public void ejecutarComando(Command comando) {
        comando.execute();
        historial.add(comando);
    }

    public void mostrarHistorial() {
        System.out.println("Historial: " + historial.size());
    }
}
