package com.wo.park.api.service;

import com.wo.park.api.entity.Usuario;
import com.wo.park.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if(!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova senha não confere com confirmação de senha");
        }

        Usuario user = buscarPorId(id);
        if(!user.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("Sua senha não confere");
        }

        user.setPassword(novaSenha);
        return usuarioRepository.save(user);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
