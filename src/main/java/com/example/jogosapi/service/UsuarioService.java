package com.example.jogosapi.service;


import com.example.jogosapi.database.models.Usuario;
import com.example.jogosapi.database.repository.UsuarioRepository;
import com.example.jogosapi.dto.UsuarioDto;
import com.example.jogosapi.dto.UsuarioResponseDto;
import com.example.jogosapi.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UsuarioResponseDto cadastrarUsuario(UsuarioDto usuarioDto){
        boolean usuarioExiste = usuarioRepository.existsByCpf(usuarioDto.cpf());
        if(usuarioExiste){
            throw new RuntimeException("Já existe um usuário cadastrado com esse CPF" + usuarioDto.cpf());

        }
        boolean emailExiste = usuarioRepository.existsByEmail(usuarioDto.email());
        if(emailExiste){
            throw new RuntimeException("Já existe um usuário cadastrado com o e-mail: " + usuarioDto.email());
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.nome());
        usuario.setCpf(usuarioDto.cpf());
        usuario.setDataNascimento(usuarioDto.dataNascimento());
        usuario.setEmail(usuarioDto.email());
        usuario.setRole(UserRole.USER);

        String senhaCriptografada = passwordEncoder.encode(usuarioDto.senha());
        usuario.setSenha(senhaCriptografada);


        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(usuarioSalvo);

    }
    @Transactional
    public List<UsuarioResponseDto> listarUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::new)
                .toList();
    }
}
