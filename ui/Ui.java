package compromissos.ui;

import compromissos.dados.*;

public interface Ui
{
    void listar(Agenda a);
    void inserir(Agenda a);
    void listarPorAssunto(Agenda a);
    void remover(Agenda a);
    /**
     * Deve exibir um menu e retornar uma das seguintes opções:
     * 1 - Inserir Compromisso
     * 2 - Remover Compromisso
     * 3 - Listar Todos os Compromissos
     * 4 - Listar Compromissos por Assunto
     * 5 - Listar Compromissos de Periodo
     * 6 - Listar Proximos Compromissos
     * 7 - Listar Compromissos de Periodo
     * 8 - Sair
     *
     * @return int
     */
    int menu();
}