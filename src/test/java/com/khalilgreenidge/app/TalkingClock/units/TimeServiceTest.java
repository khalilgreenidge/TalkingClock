package com.khalilgreenidge.app.TalkingClock.units;

import com.khalilgreenidge.app.TalkingClock.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.BadRequestException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class TimeServiceTest {

    @Autowired
    public TimeService service = new TimeService();


    @Test
    public void testTranslationMidnight() throws Exception {
        //arrange
        String actual;
        String expected = "One past twelve";

        //act
        actual = service.translate("00:01");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTranslationPastTheHour() throws Exception {
        //arrange
        String time;
        String actual;
        String expected = "Five past one";

        //act
        actual = service.translate("13:05");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTranslationHalfPast() throws Exception {
        //arrange
        String time;
        String actual;
        String expected = "Half past four";

        //act
        actual = service.translate("16:30");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTranslationToTheHour() throws Exception {
        //arrange
        String actual;
        String expected = "Twenty five to twelve";

        //act
        actual = service.translate("23:35");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTranslationOneOClock() throws Exception {
        //arrange
        String actual;
        String expected = "One o'clock";

        //act
        actual = service.translate("13:00");

        assertEquals(expected, actual);
    }

    @Test
    public void testTranslation12OClock() throws Exception {
        //arrange
        String actual;
        String expected = "Twelve o'clock";

        //act
        actual = service.translate("00:00");

        assertEquals(expected, actual);
    }

    @Test
    public void testTranslationInvalidHours() throws Exception {
        //arrange
        Exception actual;
        String expected = "Invalid hours";

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("24:00");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testTranslationInvalidMins() throws Exception {
        //arrange
        String expected = "Invalid minutes";
        Exception actual;

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("13:60");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testTranslationWithStrangeChars() throws Exception {
        //arrange
        String expected = "Time must contain numbers";
        Exception actual;

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("th:as");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testTranslationWithLongStrangeChars() throws Exception {
        //arrange
        String expected = "Time must be only 5 characters.";
        Exception actual;

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("thwefwefwe:asefwfwefwe");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testTranslationWithoutColon() throws Exception {
        //arrange
        String expected = "Time must contain a :";
        Exception actual;

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("16430");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testTranslationWithDoubleColon() throws Exception {
        //arrange
        String expected = "Time must contain numbers";
        Exception actual;

        //act
        actual = assertThrows(BadRequestException.class, () -> {
            service.translate("16::0");
        });

        //assert
        assertEquals(expected, actual.getMessage());
    }


}
