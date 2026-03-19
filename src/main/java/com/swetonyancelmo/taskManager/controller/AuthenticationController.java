package com.swetonyancelmo.taskManager.controller;

import com.swetonyancelmo.taskManager.dtos.request.AuthenticationDataRequestDTO;
import com.swetonyancelmo.taskManager.dtos.request.CreateUserRequestDTO;
import com.swetonyancelmo.taskManager.dtos.response.DataTokenResponseDTO;
import com.swetonyancelmo.taskManager.dtos.response.UserResponseDTO;
import com.swetonyancelmo.taskManager.mapper.UserMapper;
import com.swetonyancelmo.taskManager.models.User;
import com.swetonyancelmo.taskManager.service.TokenService;
import com.swetonyancelmo.taskManager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints de login e registro de usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Faz o login do usuário cadastrado")
    public ResponseEntity<DataTokenResponseDTO> login(@RequestBody @Valid AuthenticationDataRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenResponseDTO(tokenJWT));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro", description = "Cadastra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o usuário")
    })
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody CreateUserRequestDTO dto) {
        User newUser = userService.create(dto);
        return new ResponseEntity<>(userMapper.toResponseDTO(newUser), HttpStatus.CREATED);
    }
}
