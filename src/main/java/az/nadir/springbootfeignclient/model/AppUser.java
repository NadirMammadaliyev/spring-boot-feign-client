package az.nadir.springbootfeignclient.model;


import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<Role> roles;
}
