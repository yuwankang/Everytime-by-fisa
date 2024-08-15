package com.fisa.land.fisaland.common.config;
import org.springframework.format.Formatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        try {
            return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new ParseException("Failed to parse date and time: " + text, 0);
        }
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return DATE_TIME_FORMATTER.format(object);
    }
}
