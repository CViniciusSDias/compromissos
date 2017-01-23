package compromissos.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper
{
    public static LocalDate textToDate(String data)
    {
        if (!data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            throw new IllegalArgumentException("A data deve estar no formato dd/mm/aaaa");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }
}