package it.uniromatre.pinaback.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    CURATOR_READ("customer:read"),
    CURATOR_UPDATE("customer:update"),
    CURATOR_DELETE("customer:delete"),
    CURATOR_CREATE("customer:create"),



    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");



    @Getter
    private final String permission;


}
