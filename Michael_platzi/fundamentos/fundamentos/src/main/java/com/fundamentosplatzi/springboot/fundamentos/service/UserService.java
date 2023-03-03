package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Transactional  // Permite hacer un rollback (no guardar ningun dato de la lista si hay por lo menos algun error)
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: " + user))
        .forEach(userRepository::save); // igual a .forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
       userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
