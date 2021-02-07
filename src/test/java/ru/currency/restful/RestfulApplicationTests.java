package ru.currency.restful;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.currency.restful.entity.BaseObject;
import ru.currency.restful.entity.Gif;
import ru.currency.restful.feignenabledbackend.Controller;
import ru.currency.restful.feignenabledbackend.GetGif;
import ru.currency.restful.feignenabledbackend.GetCurrency;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestfulApplicationTests {

    @Value("${url-test.good.gif}")
    private String testGoodGif;
    @Value("${url-test.bad.gif}")
    private String testBadGif;

    private static String datePrevious;

    @BeforeAll
    public static void setup() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        datePrevious = format1.format(calendar.getTime());
    }


    @Test
    public void testAnswerBad() {
        GetCurrency getCurrency = mock(GetCurrency.class);
        when(getCurrency.getBaseObjectLatest(any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("AFN", 77.299992);}}));
        when(getCurrency.getBaseObjectHistorical(eq(datePrevious), any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("AFN", 77.426279);}}));
        GetGif getGif = mock(GetGif.class);
        when(getGif.getGifBad(any(), any())).thenReturn(new Gif(new LinkedHashMap<>(){{put("embed_url", "https://giphy.com/embed/Km2YiI2mzRKgw");}}, new LinkedHashMap<>(){{put("s1", "s2");}}));
        Controller controller = new Controller(getCurrency, getGif);
        String result = controller.answer("AFN");

        String expected = testBadGif;
        assertEquals(result, expected);
    }

    @Test
    public void testAnswerGood() {
        GetCurrency getCurrency = mock(GetCurrency.class);
        when(getCurrency.getBaseObjectLatest(any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("ALL", 102.047197);}}));
        when(getCurrency.getBaseObjectHistorical(eq(datePrevious), any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("ALL", 101.70593);}}));
        GetGif getGif = mock(GetGif.class);
        when(getGif.getGifGood(any(), any())).thenReturn(new Gif(new LinkedHashMap<>(){{put("embed_url", "https://giphy.com/embed/la6Ne7z15BXs4");}}, new LinkedHashMap<>(){{put("s1", "s2");}}));
        Controller controller = new Controller(getCurrency, getGif);
        String result = controller.answer("ALL");

        String expected = testGoodGif;
        assertEquals(result, expected);
    }

    @Test
    public void testAnswer() {
        GetCurrency getCurrency = mock(GetCurrency.class);
        when(getCurrency.getBaseObjectLatest(any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("ALL", 102.047197);}}));
        when(getCurrency.getBaseObjectHistorical(eq(datePrevious), any())).thenReturn(new BaseObject("s1", "s2", "s3", "s4", new TreeMap<>(){{put("ALL", 101.70593);}}));
        GetGif getGif = mock(GetGif.class);
        when(getGif.getGifGood(any(), any())).thenReturn(new Gif(new LinkedHashMap<>(){{put("embed_url", "https://giphy.com/embed/la6Ne7z15BXs4");}}, new LinkedHashMap<>(){{put("s1", "s2");}}));
        Controller controller = new Controller(getCurrency, getGif);
        String result = controller.answer("AL");

        String expected = "Страны с таким кодом валюты не существует";
        assertEquals(result, expected);
    }
}
