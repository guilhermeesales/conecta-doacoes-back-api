package com.br.conecta_doacoes.conectadoacoes.config;

import com.br.conecta_doacoes.conectadoacoes.model.entity.Usuario;
import com.br.conecta_doacoes.conectadoacoes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoaderConfig {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(!usuarioRepository.existsByEmail("guilhermessaless11@gmail.com")) {
                Usuario user = new Usuario();
                user.setEmail("guilhermessaless11@gmail.com");
                user.setNome("Guilherme Sales");
                user.setPassword(passwordEncoder.encode("guisales"));
                usuarioRepository.save(user);
            }
        };
    }
}
