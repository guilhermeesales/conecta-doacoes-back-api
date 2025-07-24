package com.br.conecta_doacoes.conectadoacoes.config;

import com.br.conecta_doacoes.conectadoacoes.model.Usuario;
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
            if(!usuarioRepository.existsByUsername("guisales")) {
                Usuario user = new Usuario();
                user.setUsername("guisales");
                user.setPassword(passwordEncoder.encode("guisales"));
                usuarioRepository.save(user);
            }
        };
    }
}
