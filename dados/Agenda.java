package compromissos.dados;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;
import java.time.temporal.ChronoUnit;
import compromissos.dados.Compromisso;

public class Agenda
{
    private Collection<Compromisso> compromissos;
    private int autoIncrement = 1;

    public Agenda()
    {
        compromissos = new ArrayList<Compromisso>();
    }

    public void inserir(Compromisso c)
    {
        c.setId(autoIncrement++);
        compromissos.add(c);
    }

    public boolean remover(int id)
    {
        for (Compromisso c : compromissos) {
            if (c.getId() == id) {
                compromissos.remove(c);

                return true;
            }
        }

        return false;
    }

    public Collection<Compromisso> listarTodos()
    {
        return Collections.unmodifiableCollection(compromissos);
    }

    public Collection<Compromisso> listarPorAssunto(String assunto)
    {
        return filtra(c -> c.getAssunto().equals(assunto));
    }

    public Collection<Compromisso> listarHoje()
    {
        return filtra(c -> LocalDate.now().equals(c.getData()));
    }

    public Collection<Compromisso> listarPorPeriodo(LocalDate inicio, LocalDate fim)
    {
        return filtra(c -> c.getData().isAfter(inicio) && c.getData().isBefore(fim));
    }

    public Collection<Compromisso> listarProximos(int numeroDeDias)
    {
        LocalDate hoje = LocalDate.now();
        LocalDate proxima = hoje.plusDays(numeroDeDias);

        return filtra(
            c -> c.getData().isAfter(hoje) && c.getData().isBefore(proxima)
        );
    }

    protected Collection<Compromisso> filtra(Predicate<Compromisso> filtro)
    {
        return compromissos.stream()
            .filter(filtro)
            .collect(Collectors.toList());
    }
}