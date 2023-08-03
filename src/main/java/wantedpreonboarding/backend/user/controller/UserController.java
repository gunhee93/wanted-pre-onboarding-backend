package wantedpreonboarding.backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wantedpreonboarding.backend.common.code.SuccessCode;
import wantedpreonboarding.backend.common.response.ApiResponse;
import wantedpreonboarding.backend.user.dto.LoginRequest;
import wantedpreonboarding.backend.user.dto.LoginResponse;
import wantedpreonboarding.backend.user.dto.SignupRequest;
import wantedpreonboarding.backend.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@Validated @RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return new ResponseEntity(new ApiResponse(SuccessCode.SIGNUP_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }
}
