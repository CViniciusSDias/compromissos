package compromissos.ui;

import compromissos.dados.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cmd implements Ui
{
    private Agenda agenda;

    public Cmd(Agenda a)
    {
        agenda = a;
    }
    public void listar()
    {
        titulo();
        compromissos(agenda.listarTodos());
    }

    public void listarPorAssunto()
    {
        limpar();
        System.out.println("Digite o assunto a buscar:");
        String assunto = new Scanner(System.in).nextLine();
        titulo();
        compromissos(agenda.listarPorAssunto(assunto));
    }

    public void listarPorPeriodo()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        limpar();
        System.out.println("Digite o inicio do periodo (dd/mm/aaaa):");
        LocalDate inicio = LocalDate.parse(sc.nextLine(), formatter);

        System.out.println("\nDigite o fim do periodo (dd/mm/aaaa):");
        LocalDate fim = LocalDate.parse(sc.nextLine(), formatter);
        titulo();


        compromissos(agenda.listarPorPeriodo(inicio, fim));
    }

    public void inserir()
    {
        limpar();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o titulo do compromisso:");
        String titulo = sc.nextLine();

        System.out.println("\nDigite a descricao do compromisso:");
        String desc = sc.nextLine();

        System.out.println("\nDigite a data e hora (01/01/2001 01:01):");
        String dataHora = sc.nextLine();

        System.out.println("\nDigite o assunto do compromisso:");
        String assunto = sc.nextLine();

        Compromisso c = new Compromisso(titulo, desc, dataHora, assunto);
        agenda.inserir(c);
    }

    public void remover()
    {
        limpar();
        System.out.println("Digite o ID do compromisso a remover:");
        int id = new Scanner(System.in).nextInt();
        agenda.remover(id);
    }

    public int menu()
    {
        limpar();
        System.out.println("1 - Inserir Compromisso");
        System.out.println("2 - Remover Compromisso");
        System.out.println("3 - Listar Todos os Compromissos");
        System.out.println("4 - Listar Compromissos por Assunto");
        System.out.println("5 - Listar Compromissos de Periodo");
        System.out.println("6 - Listar Proximos Compromissos");
        System.out.println("7 - Listar Compromissos de Periodo");
        System.out.println("8 - Sair");

        return new Scanner(System.in).nextInt();
    }

    protected void limpar()
    {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void titulo()
    {
        limpar();

        StringBuilder titulo = new StringBuilder();
            titulo.append("ID").append(" | ")
                .append("Titulo").append(" | ")
                .append("Descricao").append(" | ")
                .append("Data e Hora").append(" | ")
                .append("Assunto");
        System.out.println(titulo.toString());
    }

    protected void compromissos(Collection<Compromisso> compromissos)
    {
        for (Compromisso c : compromissos) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.getId()).append(" | ")
                .append(c.getTitulo()).append(" | ")
                .append(c.getDescricao()).append(" | ")
                .append(c.getDataHoraFormatada()).append(" | ")
                .append(c.getAssunto());
            System.out.println(sb.toString());
        }

        new Scanner(System.in).nextLine();
    }
}