package utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {


    @Test
    void testReadCSVFile() {
        List<List<String>> csv = CSVReader.getInstance().readCSVFile("C:\\Users\\satis\\Desktop\\java\\FlightBooking_202_personal\\src\\main\\resources\\input\\Sample.csv");
        Assert.assertEquals(csv.size(),6);

    }
}