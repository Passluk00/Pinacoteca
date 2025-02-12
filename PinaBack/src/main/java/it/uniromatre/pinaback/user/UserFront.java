package it.uniromatre.pinaback.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserFront {

    private Integer id;

    private String name;

    private String cognome;

}
