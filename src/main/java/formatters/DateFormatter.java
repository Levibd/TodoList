package formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

    LocalDateTime date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public LocalDateTime dateFormatter(String dateStr) {
        try {
           return this.date = LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("⚠️ Data inválida! Definindo para hoje + 7 dias.");
            this.date = LocalDateTime.now().plusDays(7);
        }
        return null;
    }
}
