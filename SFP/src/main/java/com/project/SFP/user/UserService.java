package com.project.SFP.user;

import com.project.SFP.registration.token.ConfirmationToken;
import com.project.SFP.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static  String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(PUser pUser) {
        boolean userExists = userRepository
                .findByEmail(pUser.getEmail())
                .isPresent();

        if(userExists) {
            throw new IllegalStateException("Почта занята(");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(pUser.getPassword());

        pUser.setPassword(encodedPassword);

        userRepository.save(pUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                pUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );


        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
