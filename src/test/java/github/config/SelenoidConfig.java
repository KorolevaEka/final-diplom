package github.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/data",
        "system:properties"
})
public interface SelenoidConfig extends Config {

    @Key("selenoid.userName")
    String userName();

    @Key("selenoid.password")
    String password();

    @Key("selenoid.remoteURL")
    String url();
}