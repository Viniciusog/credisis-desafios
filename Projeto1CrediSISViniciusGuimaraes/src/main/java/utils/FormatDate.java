package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.TimeZone;

public class FormatDate {

    //Formata nossa data para o padrão: yyyy-MM-dd'T'HH:mm:ss'Z'
    public static Date formatDateTZ(Instant instant) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = (Date) Date.from(instant);
        sdf.format(d);
        return d;
    }
}
