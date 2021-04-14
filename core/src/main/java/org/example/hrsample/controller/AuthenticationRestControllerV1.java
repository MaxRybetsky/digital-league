package org.example.hrsample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrsample.activemq.ActiveMQProducerService;
import org.example.hrsample.dto.AuthenticationRequestDto;
import org.example.hrsample.dto.UserDto;
import org.example.hrsample.security.JwtTokenProvider;
import org.example.hrsample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ActiveMQProducerService activeMQProducer;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
        try {
            String login = request.getLogin();
            String password = request.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            UserDto userDto = userService.findByLogin(login);
            String token = jwtTokenProvider.createToken(login, userDto.getRoleEntity().getName());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            activeMQProducer.sendMessageToLog("Authorize person with login " + login);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            activeMQProducer.sendMessageToLog("Can't authorize person!");
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        activeMQProducer.sendMessageToLog("Person logout.");
    }
}
