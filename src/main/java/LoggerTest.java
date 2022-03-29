import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerTest {
    public static void main(String[] args) {
        Logger log = LogManager.getLogger(LoggerTest.class.getName());
        log.info("Test");
    }
}
