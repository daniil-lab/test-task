package com.test.task.util;

import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class ArticleDateConverter {
    public static Instant getInstantByString(String source) throws ServiceException {
        try {
            Instant date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source).toInstant();

            return date;
        } catch (ParseException e) {
            throw new ServiceException("Can`t parse date from request body", HttpStatus.BAD_REQUEST);
        }
    }

    public static String getStringByInstant(Instant instant) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date.from(instant));
    }
}
