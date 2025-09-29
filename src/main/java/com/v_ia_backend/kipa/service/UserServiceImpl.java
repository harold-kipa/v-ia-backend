package com.v_ia_backend.kipa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.exception.listexceptions.ConflictException;
import com.v_ia_backend.kipa.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService {
    @Value("${status.deleted}")
    private Long delete;

    private final MessageSource messageSource;
    private final UsersRepository userRepository;
    public UserServiceImpl(MessageSource messageSource, UsersRepository userRepository) {
        this.messageSource = messageSource;
        this.userRepository = userRepository;
    }
    
    @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Users user = userRepository.findByEmail(username);

      if (user == null){
          throw new UsernameNotFoundException("User not found with username: " + username);
      }

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
              new ArrayList<>());
  }

  public Users createUser(UsersRequest request) {
        // Verificar duplicidad de Email
        Optional<Users> userByEmail = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));
        if (userByEmail.isPresent() && !Objects.equals(userByEmail.get().getStatus().getId(), delete)) {
            throw new ConflictException(messageSource.getMessage("email.exist", null, "", LocaleContextHolder.getLocale()));
        }

        // Verificar duplicidad de Identification solo si tiene valor y no es Customer
        boolean shouldCheckIdentification = request.getIdentification() != null
                && !request.getIdentification().trim().isEmpty()
                && !Objects.equals(request.getRoleId().getId(), customerRole);

        Optional<User> userByIdentification = Optional.empty();
        if (shouldCheckIdentification) {
            userByIdentification = Optional.ofNullable(userRepository.findByIdentification(request.getIdentification()));

            if (userByIdentification.isPresent() && !Objects.equals(userByIdentification.get().getStatus().getId(), delete)) {
                throw new ConflictException(messageSource.getMessage("identification.exist", null, "", LocaleContextHolder.getLocale()));
            }
        }

        // Verifica si existe un usuario borrado que coincida con Email o Identification
        User existingUser = userByEmail.orElse(userByIdentification.orElse(null));
        if (existingUser != null && Objects.equals(existingUser.getStatus().getId(), delete)) {
            createUserDetails(existingUser, request);
            return userRepository.save(existingUser);
        } else {
            User newUser = new User();
            createUserDetails(newUser, request);
            newUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
            return userRepository.save(newUser);
        }
    }

}
