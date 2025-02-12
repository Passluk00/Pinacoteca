package it.uniromatre.pinaback.user;

import it.uniromatre.pinaback.role.Role;
import it.uniromatre.pinaback.security.JwtService;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {


    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;



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

    public boolean authWithToken(String token){

        var to = jwtService.isTokenStillValid(token);
        if(!to){
            throw new RuntimeException("Token Not valid");
        }
        return true;
    }

    public boolean isAdmin(Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());
        User currentUser = userRepository.findUserById(user.getId());
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + user.getId());
        }

        return currentUser.getRoles().equals(Role.ADMIN);
    }


    public Boolean checkIfCurator(Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());
        User currentUser = userRepository.findUserById(user.getId());
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + user.getId());
        }

        var to = currentUser.getRoles().equals(Role.CURATOR) || currentUser.getRoles().equals(Role.ADMIN);


        return to;

    }
}

