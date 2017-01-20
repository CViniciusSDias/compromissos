package compromissos.dados;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;

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

    protected Collection<Compromisso> filtra(Predicate<Compromisso> filtro)
    {
        return compromissos.stream()
            .filter(filtro)
            .collect(Collectors.toList());
    }
}