package compromissos.dados;

import java.time.*;

public class Compromisso
{
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String assunto;

    public Compromisso(int id, String titulo, String descricao, LocalDateTime dataHora, String assunto)
    {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public int getId()
    {
        return id;
    }

    public LocalDate getData()
    {
        return dataHora.toLocalDate();
    }

    public String getAssunto()
    {
        return assunto;
    }
}