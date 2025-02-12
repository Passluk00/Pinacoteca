package it.uniromatre.pinaback.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    CURATOR_READ("curator:read"),
    CURATOR_UPDATE("curator:update"),
    CURATOR_DELETE("curator:delete"),
    CURATOR_CREATE("curator:create"),



    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");



    @Getter
    private final String permission;


}
