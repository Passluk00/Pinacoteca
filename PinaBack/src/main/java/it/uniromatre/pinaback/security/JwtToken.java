package it.uniromatre.pinaback.security;

import it.uniromatre.pinaback.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JwtToken {


    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true, length = 2048)
    public String token;

    public boolean revoked;

    public boolean expired;

    public Integer userId ;




}
