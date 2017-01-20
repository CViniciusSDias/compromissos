package compromissos.ui;

import compromissos.dados.*;

public interface Ui
{
    void listar(Agenda a);
    void inserir(Agenda a);
    void listarPorAssunto(Agenda a);
    void remover(Agenda a);
    int menu();
}