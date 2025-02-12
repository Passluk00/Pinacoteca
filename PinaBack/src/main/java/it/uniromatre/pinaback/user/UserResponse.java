package it.uniromatre.pinaback.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {


    private String email;

    private String nome;

    private String cognome;

    private String url_pic;


}
