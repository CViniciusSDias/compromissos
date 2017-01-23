package compromissos;

import java.io.IOException;
import java.util.*;
import compromissos.dados.Agenda;
import compromissos.ui.*;

public class App
{
    public static void main(String[] s)
    {
        int opcao = 1;
        Agenda a = new Agenda();
        Ui ui = new Swing(a);

        do {
            opcao = ui.menu();

            switch (opcao) {
                case 1:
                    ui.inserir();
                    break;
                case 2:
                    ui.remover();
                    break;
                case 3:
                    ui.listar();
                    break;
                case 4:
                    ui.listarPorAssunto();
                    break;
                case 5:
                    ui.listarPorPeriodo();
                    break;
                case 6:
                    ui.listarDeHoje();
                    break;
                case 7:
                    ui.listarProximos();
                    break;
            }
        } while (opcao > 0 && opcao < 8);

        System.exit(0);
    }
}