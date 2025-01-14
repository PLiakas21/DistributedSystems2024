package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.Role;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.UserRepository;
import gr.hua.dit.ds.DistributedSystems.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void setAdmin(User user){
        String password= user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.updateUser(user);
    }

    @Transactional
    public Integer saveUser(User user) {
        String password= user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user = userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public Integer updateUser(User user) {
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByUsername(username);

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with username: " + username + " not found !");
        else {
            User user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toSet())
            );
        }
    }

    public void updateOrInsertRole(Role role) {
        roleRepository.updateOrInsert(role);
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUser(Integer user_id) {
        return userRepository.findById(user_id).get();
    }

    @Transactional
    public void deleteUser(Integer user_id) {
        userRepository.deleteById(user_id);
    }
}
