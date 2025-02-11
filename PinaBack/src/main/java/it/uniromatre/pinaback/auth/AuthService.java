package it.uniromatre.pinaback.auth;

import it.uniromatre.pinaback.role.Role;
import it.uniromatre.pinaback.security.JwtService;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor

public class AuthService {


    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public void register(RegistrationRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }


        var user = User.builder()
                .email(request.getEmail())
                .nome(request.getNome())
                .cognome(request.getCognome())
                .codiceFiscale(request.getCodiceFiscale())
                // data e luogo di nascita
                .password(passwordEncoder.encode(request.getPassword()))
                .createdDate(LocalDateTime.now())
                .accountLocked(false)
                .enabled(true)
                .roles(Role.CURATOR)
                .build();

        userRepository.save(user);
    }



    public void registerAdmin(RegistrationRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }


        var user = User.builder()
                .email(request.getEmail())
                .nome(request.getNome())
                .cognome(request.getCognome())
                .codiceFiscale(request.getCodiceFiscale())
                .dataNascita(request.getDataNascita())
                .luogoNascita(request.getLuogoDiNascita())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdDate(LocalDateTime.now())
                .accountLocked(false)
                .enabled(true)
                .roles(Role.ADMIN)
                .build();

        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String , Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("username", user.getUsername());

        // disattivare tutti i token attivi se ci sono al momento dell'autenticazione

        jwtService.revokeAllUserTokens(user);

        var jwtToken = jwtService.generateTokenESalva(claims,user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }


    // controllo se il token è valido

    public boolean authWithToken(String token){
        var to = jwtService.isTokenStillValid(token);
        if(!to){
            throw new RuntimeException("Token Not valid");
        }
        return true;
    }




}
