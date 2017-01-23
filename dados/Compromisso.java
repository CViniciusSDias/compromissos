package compromissos.dados;

import java.time.*;
import java.time.format.*;

public class Compromisso
{
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String assunto;

    public Compromisso(String titulo, String descricao, String dataHora, String assunto)
    {
        this.titulo = titulo;
        this.descricao = descricao;
        setDataHora(dataHora);
        this.assunto = assunto;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setDataHora(String dataHora)
    {
        this.dataHora = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"));
    }

    public LocalDate getData()
    {
        return dataHora.toLocalDate();
    }

    public String getAssunto()
    {
        return assunto;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getDataHoraFormatada()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:m");

        return dataHora.format(formatter);
    }
}