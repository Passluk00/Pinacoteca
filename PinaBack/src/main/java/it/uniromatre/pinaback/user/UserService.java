package it.uniromatre.pinaback.user;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {


    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public UserResponse getCuratorData(Authentication connectedUser) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }


        return userMapper.toUserResponse(toCheck);

    }

    public List<UserFront> getAllFree() {

        List<User> list = userRepository.getAllFree();
        return userMapper.toUserFront(list);

    }
}
