package wantedpreonboarding.backend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wantedpreonboarding.backend.common.code.ErrorCode;
import wantedpreonboarding.backend.common.exception.CustomIllegalStateException;
import wantedpreonboarding.backend.common.util.RedisUtil;
import wantedpreonboarding.backend.jwt.TokenProvider;
import wantedpreonboarding.backend.jwt.TokenResponse;
import wantedpreonboarding.backend.user.domain.User;
import wantedpreonboarding.backend.user.dto.LoginRequest;
import wantedpreonboarding.backend.user.dto.LoginResponse;
import wantedpreonboarding.backend.user.dto.SignupRequest;
import wantedpreonboarding.backend.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {throw new CustomIllegalStateException(ErrorCode.NOT_FOUND_USER);});
    }

    // 회원가입
    public void signup(SignupRequest signupRequest) {
        userRepository.findByEmail(signupRequest.getEmail())
                .ifPresent(u -> {
                    throw new CustomIllegalStateException(ErrorCode.DUPLICATE_EMAIL);
                });

        User signupUser = signupRequest.toEntity();
        signupUser.encodingPassword(encoder.encode(signupRequest.getPassword()));
        userRepository.save(signupUser);
    }


    // 로그인
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authenticate);
        redisUtil.setDataExpire(authenticate.getName(), tokenResponse.getRefreshToken(), 1000 * 60 * 60 * 24 * 7);

        return new LoginResponse(
                tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), tokenResponse.getAccessTokenExpiresIn());
    }
}
