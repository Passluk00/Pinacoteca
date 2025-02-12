package it.uniromatre.pinaback.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {


    public UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .email(user.getEmail())
                .nome(user.getNome())
                .cognome(user.getCognome())
                .url_pic(user.getUrl_picture())
                .build();

    }

    public UserFront toUserFront(User us){
        return UserFront.builder()
                .id(us.getId())
                .name(us.getNomeReale())
                .cognome(us.getCognome())
                .build();
    }

    public List<UserFront> toUserFront(List<User> us){
        return us.stream().map(this::toUserFront).collect(Collectors.toList());
    }


}
