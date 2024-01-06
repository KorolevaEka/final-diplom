package github.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/test.properties",
        "system:properties"
})
public interface GitHubConfig extends Config {

    @Key("api.token.gitHub")
    String token();

    @Key("login.gitHub")
    String login();

    @Key("password.gitHub")
    String password();

}