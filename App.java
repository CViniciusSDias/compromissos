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
        Ui ui = new Cmd();
        Agenda a = new Agenda();

        do {
            try {
                opcao = ui.menu();

                switch (opcao) {
                    case 1:
                        ui.inserir(a);
                        break;
                    case 2:
                        ui.remover(a);
                        break;
                    case 3:
                        ui.listar(a);
                        break;
                    case 4:
                        ui.listarPorAssunto(a);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um numero entre 1 e 7.");
            }
        } while (opcao > 0 && opcao < 8);
    }
}