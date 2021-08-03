import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;

public class TestClass {

    // load our data provider
    @Test(dataProvider = "networkConditions")
    public void test(int download, int upload, int latency)
            throws IOException {

        // only run if the network is throttled
        if (download > 0 && upload > 0) {
            CommandExecutor executor = driver.getCommandExecutor();

            // create a hashmap of the required network conditions
            Map map = new HashMap();
            // you can even test 'offline' behaviour
            map.put("offline", false);
            map.put("latency", latency);

            map.put("download_throughput", downloadThroughput);
            map.put("upload_throughput", uploadThroughput);

            // execute our code
            Response response = executor.execute(
                    new Command(driver.getSessionId(),
                            "setNetworkConditions",
                            ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
        }

        // Open the website
        driver.get("https://www.toptal.com/");

        // You can then check that elements are loaded etc.
        // Don't forget to use waits!
    }
}