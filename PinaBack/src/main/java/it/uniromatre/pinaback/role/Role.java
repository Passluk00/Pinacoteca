package it.uniromatre.pinaback.role;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static it.uniromatre.pinaback.role.Permission.*;

@RequiredArgsConstructor
public enum Role {

    CURATOR(

            Set.of(

                    CURATOR_READ,
                    CURATOR_UPDATE,
                    CURATOR_DELETE,
                    CURATOR_CREATE
            )

    ),

    ADMIN(

            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    CURATOR_READ,
                    CURATOR_UPDATE,
                    CURATOR_DELETE,
                    CURATOR_CREATE
                )
    );


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }



}
