package library.demo.configuration;

import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDeta")
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        Optional<User> optional = repository.findById(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new UsernameNotFoundException("Bad Credentials");
        }
        user.getUserRole().forEach(userRole -> {
            authorityList.add(new SimpleGrantedAuthority(userRole.getRole()));
        });

        return new org.springframework.security.core.userdetails.User(username, user.getPassword()
                , user.isEnable(), true, true, true, authorityList);
    }
}
