package alunos.alunos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import alunos.alunos.repository.UsuarioRepository;
import alunos.alunos.model.Usuario;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);

        if (user.getUsername().equals(username)) {
            return new User(username, user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com username: " + username);
        }
    }
}