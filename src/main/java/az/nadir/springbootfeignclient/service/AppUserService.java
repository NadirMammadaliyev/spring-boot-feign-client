package az.nadir.springbootfeignclient.service;


import az.nadir.springbootfeignclient.model.AppUser;
import az.nadir.springbootfeignclient.model.Auth;
import client.RestApiClient;
import client.RestApiLoginClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final RestApiClient restApiClient;
    private final RestApiLoginClient restApiLoginClient;

    public ResponseEntity<List<AppUser>> getAppUser() {
        return restApiClient.getAppUsers();
    }

    public Map<String, ?> login(Auth auth) {
        Map<String, String> form = new HashMap<>();
        form.put("username", auth.getUsername());
        form.put("password", auth.getPassword());
        return restApiLoginClient.login(form);
    }
}
