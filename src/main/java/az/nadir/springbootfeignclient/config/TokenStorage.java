package az.nadir.springbootfeignclient.config;

import az.nadir.springbootfeignclient.util.TokenUtil;
import client.RestApiLoginClient;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class TokenStorage {

    @Value("${credentials.rest-api.username}")
    private String username;

    @Value("${credentials.rest-api.password}")
    private String password;

    private final RestApiLoginClient restApiLoginClient;

    public String generateToken() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return String.valueOf(restApiLoginClient.login(map).get("access_token"));
    }

    public void setToken() {
        TokenUtil.set(generateToken());
    }

}
