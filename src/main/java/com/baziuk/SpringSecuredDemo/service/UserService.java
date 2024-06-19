package com.baziuk.SpringSecuredDemo.service;

import com.baziuk.SpringSecuredDemo.entity.RolesEntity;
import com.baziuk.SpringSecuredDemo.entity.UsersEntity;
import com.baziuk.SpringSecuredDemo.entity.UsersRolesEntity;
import com.baziuk.SpringSecuredDemo.repository.RolesRepository;
import com.baziuk.SpringSecuredDemo.repository.UsersRepository;
import com.baziuk.SpringSecuredDemo.repository.UsersRolesRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    private UsersRolesRepository usersRolesRepository;

    public Optional<UsersEntity> findByUserName(String username) {
        return usersRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Користувач '%s' знайдений ", username)
        ));
        return new User(
                usersEntity.getName(),
                usersEntity.getPassword(),
                usersEntity.getRoles().stream().map(roles ->
                        new SimpleGrantedAuthority(
                                roles.getName()
                        )).collect(Collectors.toList())
        );
    }

    public void create(String name, String password, String email){
        UsersEntity entity = UsersEntity.builder()
                .name(name)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .build();
        usersRepository.save(entity);
        UsersRolesEntity usersRolesEntity = new UsersRolesEntity(entity.getId(), 2);
        usersRolesRepository.save(usersRolesEntity);

    }

    public List<UsersEntity> getAll(){
        return usersRepository.findAll();
    }
}
