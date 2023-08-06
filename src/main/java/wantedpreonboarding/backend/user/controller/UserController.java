package wantedpreonboarding.backend.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wantedpreonboarding.backend.common.code.SuccessCode;
import wantedpreonboarding.backend.common.response.ApiResponse;
import wantedpreonboarding.backend.jwt.TokenResponse;
import wantedpreonboarding.backend.user.dto.LoginRequest;
import wantedpreonboarding.backend.user.dto.LoginResponse;
import wantedpreonboarding.backend.user.dto.SignupRequest;
import wantedpreonboarding.backend.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity signup(@Validated @RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return new ResponseEntity(new ApiResponse(SuccessCode.SIGNUP_SUCCESS), HttpStatus.OK);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(
            @RequestHeader(value = "Authorization") String acTokenRequest,
            @RequestHeader(value = "RefreshToken") String rfTokenRequest
    ) {
        userService.logout(acTokenRequest, rfTokenRequest);

        return new ResponseEntity<>(new ApiResponse(SuccessCode.LOGOUT), HttpStatus.OK);
    }

    @ApiOperation(value = "토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity reissue(
            @RequestHeader(value = "Authorization") String acTokenRequest,
            @RequestHeader(value = "RefreshToken") String rfTokenRequest) {
        TokenResponse tokenResponse = userService.reissue(acTokenRequest, rfTokenRequest);

        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
