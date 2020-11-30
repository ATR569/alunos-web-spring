package alunos.alunos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import alunos.alunos.dto.JwtResponseDTO;
import alunos.alunos.dto.UsuarioDTO;
import alunos.alunos.security.JwtTokenUtil;
import alunos.alunos.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtService;

    @PostMapping("/api/autenticar")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioDTO authenticationRequest) {
        try {
            final UserDetails userDetails = jwtService.loadUserByUsername(authenticationRequest.getUsername());
            
            if (userDetails == null || !userDetails.getPassword().equals(authenticationRequest.getPassword())) {
                return new ResponseEntity<>("Não autorizado!", HttpStatus.UNAUTHORIZED);
            }

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}