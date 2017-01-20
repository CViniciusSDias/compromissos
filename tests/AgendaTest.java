package tests;

import compromissos.dados.Agenda;
import compromissos.dados.Compromisso;
import org.junit.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendaTest
{
    private Agenda a;

    @Before
    public void iniciaAgenda()
    {
        a = new Agenda();

        a.inserir(new Compromisso(1, "", "Descrição", LocalDateTime.now(), "Consulta"));
        a.inserir(new Compromisso(2, "", "Descrição", LocalDateTime.of(2017, 2, 28, 11, 0), "Teste"));
        a.inserir(new Compromisso(3, "", "Descrição", LocalDateTime.of(2017, 1, 30, 12, 30), "Outro"));
        a.inserir(new Compromisso(4, "", "Descrição", LocalDateTime.now(), "Consulta"));
    }

    @Test
    public void insereEListaCompromissos()
    {
        Assert.assertEquals(4, a.listarTodos().size());
    }

    @Test
    public void removeCompromissos()
    {
        Assert.assertTrue(a.remover(3));
        Assert.assertEquals(3, a.listarTodos().size());
    }

    @Test
    public void compromissosDeHoje()
    {
        Assert.assertEquals(2, a.listarHoje().size());
    }

    @Test
    public void filtroPorAssunto()
    {
        Assert.assertEquals(2, a.listarPorAssunto("Consulta").size());
    }

    @Test
    public void filtroPorPeriodo()
    {
        Assert.assertEquals(
            2,
            a.listarPorPeriodo(LocalDate.of(2017, 1, 29), LocalDate.of(2017, 3, 1)).size()
        );
    }

    @Test
    public void listarProximosCompromissos()
    {
        Assert.assertEquals(2, a.listarProximos(1).size());
    }
}