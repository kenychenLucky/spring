
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "networkConditions")
    public static Object[][] networkConditions() throws Exception {
        return new Object[][] {
                // Upload Speed, Dowload Speed in kb/s and latency in ms.
                { 5000 , 5000, 5 },
                { 10000, 7000, 5 },
                { 15000, 9000, 5 },
                { 20000, 10000, 5 },
                { 0, 0 },
        };
    }
}
