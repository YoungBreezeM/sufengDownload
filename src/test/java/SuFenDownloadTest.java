import com.fw.domain.Config;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.concurrent.*;

public class SuFenDownloadTest {

    @Test
    public void testYaml() {
        Yaml yaml = new Yaml();
        try {

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("config.yml");


            Config config = yaml.loadAs(resourceAsStream, Config.class);
            System.out.println(config);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() throws IOException, ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

    }

}
