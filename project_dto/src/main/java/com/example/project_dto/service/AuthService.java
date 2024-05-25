package com.example.project_dto.service;

import com.example.project_dto.dto.request.LoginRequest;
import com.example.project_dto.dto.response.AuthResponse;
import com.example.project_dto.entity.Role;
import com.example.project_dto.entity.User;
import com.example.project_dto.exception.AppException;
import com.example.project_dto.exception.ErrException;
import com.example.project_dto.responsitory.UserResponsitory;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthService {
    UserResponsitory userResponsitory;
    PasswordEncoder passwordEncoder;
    protected static final String KEY_SINGER="8yFJI7xw2tK5X4dCbUv9pAeDYz3hnRmPTG6sLQoWEqlvZf0taBcVqMjrNKf1Ys8W";
    private String createToken(User user){
        JWSHeader header =new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hungdepzai.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(user))
                .build();
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject=new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(KEY_SINGER.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            log.error("can't create token",e);
            throw new RuntimeException();
        }
    }
    public AuthResponse authenticationReponse(LoginRequest authenticationRequest){
        var user=userResponsitory.getUsersByUsername(authenticationRequest.getUsername())
                .orElseThrow(()->new AppException(ErrException.USER_EXISTED));
        boolean checked=passwordEncoder.matches(authenticationRequest.getPassword(),user.getPassword());
        if (!checked){
            throw new AppException(ErrException.ERR_EXCEPTION);
        }
        var token=createToken(user);
        List<Role> roles=user.getRoles();
        return AuthResponse.builder()
                .token(token)
                .check(true)
                .roles(roles)
                .build();
    }
    private String buildScope(User user){
        StringJoiner stringJoiner=new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            for (Role role : user.getRoles()) {
                stringJoiner.add(role.getName().toString());
            }
        }
        return stringJoiner.toString();
    }
}
