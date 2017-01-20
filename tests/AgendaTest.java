package tests;

import compromissos.dados.Agenda;
import compromissos.dados.Compromisso;
import org.junit.*;

public class AgendaTest
{
    private Agenda a;

    @Before
    public void iniciaAgenda()
    {
        a = new Agenda();

        a.inserir(new Compromisso());
        a.inserir(new Compromisso());
        a.inserir(new Compromisso());
    }

    @Test
    public void insereCompromissos()
    {
        Assert.assertEquals(3, a.listarTodos().size());
    }
}