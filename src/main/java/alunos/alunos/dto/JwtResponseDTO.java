package alunos.alunos.dto;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String jwttoken;

    public JwtResponseDTO(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getJwtToken() {
        return jwttoken;
    }
}
