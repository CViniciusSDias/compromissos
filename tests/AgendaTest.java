package tests;

import compromissos.dados.*;
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

        a.inserir(new Compromisso("", "Descrição", "20/01/2017 15:30", "Consulta"));
        a.inserir(new Compromisso("", "Descrição", "28/02/2017 11:00", "Teste"));
        a.inserir(new Compromisso("", "Descrição", "30/01/2017 12:30", "Outro"));
        a.inserir(new Compromisso("", "Descrição", "20/01/2017 15:30", "Consulta"));
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