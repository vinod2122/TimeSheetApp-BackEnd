package com.shravani.TimeSheetApp.security;
import com.shravani.TimeSheetApp.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService /*implements UserDetailsService*/{

    private UserRepository userRepository;

    public  CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

  /*  @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user!=null)
        {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }*/

    /*private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<? extends GrantedAuthority> mapRoles=roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRoles;
    }*/
}
