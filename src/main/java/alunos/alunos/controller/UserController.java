package alunos.alunos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import alunos.alunos.dto.UsuarioDTO;
import alunos.alunos.model.Usuario;
import alunos.alunos.repository.UsuarioRepository;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/api/cadastrar")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody UsuarioDTO user) {
        try {
            Usuario userBuscado = userRepository.findByUsername(user.getUsername());

            if (userBuscado != null && (userBuscado.getEmail().equals(user.getEmail())
                    || userBuscado.getUsername().equals(user.getUsername()))) {
                return new ResponseEntity<>("Usuário duplicado!", HttpStatus.CONFLICT);
            }

            return ResponseEntity
                    .ok(userRepository.save(new Usuario(user.getEmail(), user.getUsername(), user.getPassword())));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}