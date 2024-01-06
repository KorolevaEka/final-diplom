package github.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/data",
        "system:properties"
})
public interface WebConfig extends Config {

    @DefaultValue("Chrome, 112.0")
    @Key("ui.browserWithVersion")
    String[] browserAndVersion();

    @DefaultValue("1920x1080")
    @Key("ui.browserSize")
    String browserSize();

    @DefaultValue("https://github.com")
    @Key("ui.baseURL")
    String baseUrl();

}