package com.pentalog.sc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.UserDAO;
import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.model.Authorities.Authority;
import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;
import com.pentalog.sc.util.Md5Util;

/**
 * implementation of userService interface
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * The user dao
     */
    @Autowired
    private UserDAO userDao;

    /**
     * The authority service
     */
    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    Md5Util md5Util;

    /**
     * @see {@link userService.getUserByUsername}
     */
    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * @see {@link userService.getUserByToken}
     */
    @Override
    public User getUserByToken(String token) {
        return userDao.findByToken(token);
    }

    /**
     * @see {@link UserService.createUser}
     */
    @Override
    public User createUser(WrapperUser wUser) {
        String token = "", userToken = "", username = "", password = "";
        String userSalt = generateUserSalt();
        User newUser = new User();

        username = wUser.getUsername();
        password = wUser.getPassword();

        newUser.setUsername(username);
        token = username.concat(password).concat(userSalt);
        try {
            userToken = md5Util.generateMd5(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newUser.setToken(userToken);
        newUser.setUsersalt(userSalt);

        return registerUser(newUser);
    }

    /**
     * @see {@link UserService.registerWsUser}
     */
    @Override
    public User registerUser(User user) {
        Authorities authorities = new Authorities();
        authorities.setUsername(user.getUsername());
        authorities.setAuthority(Authority.OPERATOR);

        authoritiesService.createAuthority(authorities);
        return userDao.save(user);
    }

    /**
     * @see {@link UserService.authenticate}
     */
    public String authenticate(WrapperUser wUser)
            throws AuthenticationException {
        String username = wUser.getUsername();
        String password = wUser.getPassword();

        String runtimeToken = "";
        User user = getUserByUsername(username);

        if (user != null) {
            String token = username.concat(password).concat(user.getUsersalt());
            try {
                runtimeToken = md5Util.generateMd5(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (runtimeToken.equals(user.getToken())) {
                return runtimeToken;
            } else
                return "Incorrect password!";
        }
        return "Incorrect username!";
    }

    /**
     * generates an userSalt (random)
     * 
     * @return the usersalt
     */
    private String generateUserSalt() {
        StringBuilder userSalt = new StringBuilder();
        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 10; ++idx) {
            int randomInt = randomGenerator.nextInt(9);
            userSalt.append(randomInt);
        }
        return userSalt.toString();
    }

    /**
     * @see {@link UserService.updateUser}
     */
    @Override
    public User updateUser(User user) {
        User userToUpdate = userDao.findOne(user.getId());
        if (userToUpdate != null) {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setToken(user.getToken());
        }
        return userToUpdate;
    }

    @Override
    public List<User> findByAuthority(Authority authority) {

        List<Authorities> authorities = authoritiesService
                .findByAuthority(authority);
        List<User> users = new ArrayList<User>();
        for (Authorities auth : authorities) {
            users.add(userDao.findByUsername(auth.getUsername()));
        }

        return users;

    }

    @Override
    @Transactional
    public User deleteOperator(User user) {
        User userToDelete = new User();
        userToDelete.setId(user.getId());
        userToDelete.setToken(user.getToken());
        userToDelete.setUsername(user.getUsername());
        userToDelete.setUsersalt(user.getUsersalt());
        userDao.delete(userToDelete);
        authoritiesService.delete(authoritiesService
                .getAuthorityByUsername(user.getUsername()));
        return userToDelete;
    }

    @Override
    @Transactional
    public User changePassword(WrapperUser user) {
        User userToUpdate = userDao.findByUsername(user.getUsername());
        if (userToUpdate != null) {
            userToUpdate.setUsername(user.getUsername());
            String token = user.getUsername().concat(user.getPassword())
                    .concat(userToUpdate.getUsersalt());
            try {
                String runtimeToken = md5Util.generateMd5(token);
                userToUpdate.setToken(runtimeToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return userToUpdate;
    }

}
