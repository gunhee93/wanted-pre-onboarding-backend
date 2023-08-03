package wantedpreonboarding.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wantedpreonboarding.backend.jwt.TokenResponse;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;


}
