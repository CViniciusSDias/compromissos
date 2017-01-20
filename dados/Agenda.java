package compromissos.dados;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import compromissos.dados.Compromisso;

public class Agenda
{
    private Collection<Compromisso> compromissos;

    public Agenda()
    {
        compromissos = new ArrayList<Compromisso>();
    }

    public void inserir(Compromisso c)
    {
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

    public Collection<Compromisso> listaPorAssunto(String assunto)
    {
        return filtra(c -> c.getAssunto() == assunto);
    }

    public Collection<Compromisso> listaHoje()
    {
        return filtra(c -> LocalDate.now().equals(c.getData()));
    }

    public Collection<Compromisso> listaPeriodo(LocalDate inicio, LocalDate fim)
    {
        return filtra(c -> c.getData().isAfter(inicio) && c.getData().isBefore(fim));
    }

    public Collection<Compromisso> listaProximos(int numeroDeDias)
    {
        LocalDate hoje = LocalDate.now();

        return filtra(c -> Period.between(hoje, c.getData()).getDays() <= numeroDeDias);
    }

    protected Collection<Compromisso> filtra(Predicate p)
    {
        return compromissos.stream()
            .filter(p)
            .collect(Collectors.toList());
    }
}