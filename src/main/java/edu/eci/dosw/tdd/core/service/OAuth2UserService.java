package edu.eci.dosw.tdd.core.service;


import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2UserService {

    OAuth2User loadUser(OAuth2UserRequest userRequest);

    String extractEmail(OAuth2User oauth2User);

    String extractName(OAuth2User oauth2User);

    String extractProviderId(OAuth2User oauth2User);
}