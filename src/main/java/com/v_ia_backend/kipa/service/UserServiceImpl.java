package com.v_ia_backend.kipa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.exception.listexceptions.ConflictException;
import com.v_ia_backend.kipa.repository.UsersRepository;
import com.v_ia_backend.kipa.service.interfaces.RoleService;
import com.v_ia_backend.kipa.service.interfaces.StatusUserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService {
    @Value("${status.deleted}")
    private Long delete;
    @Value("${status.active}")
    private Long active;

    private final MessageSource messageSource;
    private final PasswordEncoder bcryptEncoder;
    private final UsersRepository userRepository;
    private final StatusUserService statusUserService;
    private final RoleService roleService;
    public UserServiceImpl(MessageSource messageSource, PasswordEncoder bcryptEncoder, UsersRepository userRepository, StatusUserService statusUserService, RoleService roleService) {
        this.messageSource = messageSource;
        this.bcryptEncoder = bcryptEncoder;
        this.userRepository = userRepository;
        this.statusUserService = statusUserService;
        this.roleService = roleService;
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
                && !request.getIdentification().toString().isEmpty();
                //&& !Objects.equals(request.getRoleId().getId(), customerRole);

        Optional<Users> userByIdentification = Optional.empty();
        if (shouldCheckIdentification) {
            userByIdentification = Optional.ofNullable(userRepository.findByIdentificationNumber(request.getIdentification()));

            if (userByIdentification.isPresent() && !Objects.equals(userByIdentification.get().getStatus().getId(), delete)) {
                throw new ConflictException(messageSource.getMessage("identification.exist", null, "", LocaleContextHolder.getLocale()));
            }
        }

        // Verifica si existe un usuario borrado que coincida con Email o Identification
        Users existingUser = userByEmail.orElse(userByIdentification.orElse(null));
        if (existingUser != null && Objects.equals(existingUser.getStatus().getId(), delete)) {
            createUserDetails(existingUser, request);
            return userRepository.save(existingUser);
        } else {
            Users newUser = new Users();
            createUserDetails(newUser, request);
            newUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
            return userRepository.save(newUser);
        }
    }
    private void createUserDetails(Users user, UsersRequest request) {
            user.setName(request.getName());
            user.setLastName(request.getLastName());

            user.setIdentificationNumber(request.getIdentification());
            user.setEmail(request.getEmail());
            user.setPassword(bcryptEncoder.encode(request.getPassword()));
            user.setStatus(statusUserService.getStatusById(active));
            user.setRoles(roleService.getRoleById(request.getRoleId().getId()));
            // user.setPushToken(request.getPushToken());
    }
    public Users getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null || Objects.equals(user.getStatus().getId(), delete)) {
            throw new UsernameNotFoundException(messageSource.getMessage("user.notfound", null, "", LocaleContextHolder.getLocale()));
        }
        return user;
    }
}
