package config;

public class TestConfig {

    private TestConfig() {}

    public static final String BASE_URL =
            System.getProperty(
                    "baseUrl",
                    "https://example.com"
            );

    public static final String BROWSER =
            System.getProperty(
                    "browser",
                    "chrome"
            );

    public static final int TIMEOUT =
            Integer.parseInt(
                    System.getProperty(
                            "timeout",
                            "20"
                    )
            );
}
