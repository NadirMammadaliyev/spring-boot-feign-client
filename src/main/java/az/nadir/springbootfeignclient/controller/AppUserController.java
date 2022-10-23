package az.nadir.springbootfeignclient.controller;

import az.nadir.springbootfeignclient.model.AppUser;
import az.nadir.springbootfeignclient.model.Auth;
import az.nadir.springbootfeignclient.service.AppUserService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("get")
    public ResponseEntity<List<AppUser>> getAppUser() {
        return ResponseEntity.ok().body(appUserService.getAppUser().getBody());
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, ?>> login(@RequestBody Auth auth){
        return ResponseEntity.ok(appUserService.login(auth));
    }
}
