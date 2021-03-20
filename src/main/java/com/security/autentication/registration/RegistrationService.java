package com.security.autentication.registration;

import com.security.autentication.appuser.AppUser;
import com.security.autentication.appuser.AppUserRole;
import com.security.autentication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }

        return appUserService.signUpUser(
              new AppUser(
                      request.getFirstName(),
                      request.getLastName(),
                      request.getEmail(),
                      request.getPassword(),
                      AppUserRole.USER_ROLE
              )
        );
    }
}
