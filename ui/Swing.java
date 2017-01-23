package compromissos.ui;

import javax.swing.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import compromissos.dados.*;
import compromissos.helpers.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Swing implements ActionListener, Ui
{
    private int botaoSelecionado;
    private List<JButton> botoes;
    private JDialog janela;
    private Agenda agenda;

    public Swing(Agenda a)
    {
        // Sair
        botaoSelecionado = 8;
        botoes = botoes();
        agenda = a;
    }

    public int menu()
    {
        janela = new JDialog();
        janela.setTitle("Menu");
        JPanel painel = new JPanel();

        for (JButton b : botoes) {
            b.addActionListener(this);
            b.setPreferredSize(new Dimension(150, 30));
            painel.add(b);
        }

        janela.setModalityType(ModalityType.APPLICATION_MODAL);
        janela.setSize(200, 350);
        janela.add(painel);
        janela.setVisible(true);

        int botao = botaoSelecionado + 1;

        // Sair
        botaoSelecionado = 8;

        return botao;
    }

    public void actionPerformed(ActionEvent e)
    {
        botaoSelecionado = botoes.indexOf(e.getSource());
        janela.dispose();
    }

    public void listar()
    {
        exibeCompromissos(agenda.listarTodos());
    }

    public void inserir()
    {
        JDialog form = new JDialog();
        form.setTitle("Inserir Compromisso");
        GridLayout layout = new GridLayout(5, 2);
        layout.setHgap(5);
        layout.setVgap(10);
        form.setLayout(layout);

        JTextField titulo = criarCampo("Título", form);
        JTextField descricao = criarCampo("Descrição", form);
        JTextField dataHora = criarCampo("Data/Hora (dd/mm/aaaa hh:mm)", form);
        JTextField assunto = criarCampo("Assunto", form);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Compromisso c = new Compromisso(
                        titulo.getText(),
                        descricao.getText(),
                        dataHora.getText(),
                        assunto.getText()
                    );
                    agenda.inserir(c);

                    form.dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data/Hora devem estar no formato dd/mm/aaaa hh:mm", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        form.add(btSalvar);

        form.setModalityType(ModalityType.APPLICATION_MODAL);
        form.pack();
        form.setVisible(true);
    }

    public void listarPorAssunto()
    {
        String assunto = JOptionPane.showInputDialog(null, "Digite o assunto a buscar:");
        exibeCompromissos(agenda.listarPorAssunto(assunto));
    }

    public void listarPorPeriodo()
    {
        boolean erro;
        // Inicializa as variáveis para passar pelo compilador.
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now();

        do {
            String inicio = JOptionPane.showInputDialog(null, "Digite o inicio do periodo (dd/mm/aaaa):");

            try {
                dataInicio = DateHelper.textToDate(inicio);
                erro = false;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                erro = true;
            }

            if (!erro) {
                String fim = JOptionPane.showInputDialog(null, "Digite o fim do periodo (dd/mm/aaaa):");

                try {
                    dataFim = DateHelper.textToDate(fim);
                    erro = false;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);

        exibeCompromissos(agenda.listarPorPeriodo(dataInicio, dataFim));
    }

    public void listarProximos()
    {
        boolean erro;
        int dias = 0;
        do {
            try {
                dias = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos dias deseja buscar?"));
                erro = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                erro = true;
            }
        } while (erro);

        exibeCompromissos(agenda.listarProximos(dias));
    }

    public void listarDeHoje()
    {
        exibeCompromissos(agenda.listarHoje());
    }

    public void remover()
    {
        boolean erro;
        int id = 0;
        do {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do compromisso a remover:"));
                erro = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                erro = true;
            }
        } while (erro);

        agenda.remover(id);
    }

    public List<JButton> botoes()
    {
        List<JButton> botoes = new ArrayList<>();
        botoes.add(new JButton("Inserir"));
        botoes.add(new JButton("Remover"));
        botoes.add(new JButton("Listar Todos"));
        botoes.add(new JButton("Listar por Assunto"));
        botoes.add(new JButton("Listar por Período"));
        botoes.add(new JButton("Listar de Hoje"));
        botoes.add(new JButton("Listar Próximos"));
        botoes.add(new JButton("Sair"));

        return botoes;
    }

    protected void exibeCompromissos(Collection<Compromisso> compromissos)
    {
        JDialog listagem = new JDialog();
        listagem.setTitle("Compromissos");

        Object[] colunas = {"ID", "Título", "Descrição", "Data e Hora", "Assunto"};
        Object[][] dados = new Object[compromissos.size()][5];
        int i = 0;

        for (Compromisso c : compromissos) {
            dados[i][0] = c.getId();
            dados[i][1] = c.getTitulo();
            dados[i][2] = c.getDescricao();
            dados[i][3] = c.getDataHoraFormatada();
            dados[i][4] = c.getAssunto();

            i++;
        }

        JScrollPane tabela = new JScrollPane(new JTable(dados, colunas));
        listagem.setModalityType(ModalityType.APPLICATION_MODAL);
        listagem.add(tabela);
        listagem.pack();
        listagem.setVisible(true);
    }

    protected JTextField criarCampo(String label, JDialog form)
    {
        form.add(new JLabel(label));
        JTextField txt = new JTextField();
        form.add(txt);

        return txt;
    }
}