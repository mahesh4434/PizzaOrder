package com.pizzaDeliveryApp.pizzaApi.service.IMPL;
import com.pizzaDeliveryApp.pizzaApi.dto.LoginDto;
import com.pizzaDeliveryApp.pizzaApi.dto.UserDto;
import com.pizzaDeliveryApp.pizzaApi.entity.Item;
import com.pizzaDeliveryApp.pizzaApi.entity.User;
import com.pizzaDeliveryApp.pizzaApi.exception.CartException;
import com.pizzaDeliveryApp.pizzaApi.repository.ItemRepository;
import com.pizzaDeliveryApp.pizzaApi.repository.UserRepository;
import com.pizzaDeliveryApp.pizzaApi.response.LoginResponse;
import com.pizzaDeliveryApp.pizzaApi.service.UserService;
import com.pizzaDeliveryApp.pizzaApi.constant.Constants;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TokenGenerator tokenGenerator;


    @Override
    public ResponseEntity<String> addUser(UserDto userDTO) {
        boolean userExists = checkUserExists(userDTO.getEmailId());
        log.info(Constants.Messages.ADD_USER_REQUEST, userDTO);
        if (userExists) {
            log.error(Constants.Errors.USER_ALREADY_EXIST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.Errors.USER_ALREADY_EXIST);
        } else if (userDTO.getusername() == null) {
            log.error(Constants.Errors.USERNAME_REQUIRED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.Errors.USERNAME_REQUIRED);
        }
        else if (userDTO.getContact() == 0) {
            log.error(Constants.Errors.CONTACT_REQUIRED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.Errors.CONTACT_REQUIRED);
        }
        else {
            User user = new User(
                    userDTO.getuserid(),
                    userDTO.getusername(),
                    userDTO.getContact(),
                    userDTO.getEmailId(),
                    passwordEncoder.encode(userDTO.getPassword())
            );
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user.getusername());
        }
    }

    @Override
    public boolean checkUserExists(String emailId) {
        log.info(Constants.Messages.CHECK_USER_REQUEST, emailId);
        User user = userRepository.findByEmailId(emailId);
        return user != null;
    }

    @Override
    public LoginResponse loginUser(@NotNull LoginDto loginDTO) {
        //TODO variable name should be proper (should not be c1,b1 etc)
        User userDetail = userRepository.findByEmailId(loginDTO.getEmailId());

        if (userDetail != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = userDetail.getPassword();
            if (passwordEncoder.matches(password, encodedPassword)) {
                Optional<User> user = userRepository.
                        findOneByEmailIdAndPassword(loginDTO.getEmailId(), encodedPassword);
                if (user.isPresent()) {
                    log.info(Constants.Messages.LOGIN_SUCCESS_MESSAGE);
                    return new LoginResponse(Constants.Messages.LOGIN_SUCCESS_MESSAGE, true, tokenGenerator.generate_Token(userDetail));
                } else {
                    log.error(Constants.Messages.LOGIN_FAILED_MESSAGE);
                    return new LoginResponse(Constants.Messages.LOGIN_FAILED_MESSAGE, false, Constants.Messages.NO_TOKEN_GENERATED_MESSAGE);
                }
            } else {
                log.error(Constants.Messages.PASSWORD_MISMATCH_MESSAGE);
                return new LoginResponse(Constants.Messages.PASSWORD_MISMATCH_MESSAGE, false, Constants.Messages.NO_TOKEN_GENERATED_MESSAGE);
            }
        } else {
            log.error(Constants.Messages.USERNAME_NOT_EXIST_MESSAGE);
            return new LoginResponse(Constants.Messages.USERNAME_NOT_EXIST_MESSAGE, false, Constants.Messages.NO_TOKEN_GENERATED_MESSAGE);
        }
    }

    @Override
    public Item viewOrder(Integer orderId) throws CartException {
        log.info(Constants.Messages.VIEW_ORDER_REQUEST, orderId);
        Optional<Item> opt = itemRepository.findById(orderId);
        if(opt.isPresent()) {
            Item order = opt.get();
            log.info(Constants.Messages.ORDER_FETCH, orderId);
            return order;
        }else {
            log.error(Constants.Messages.NO_ORDER_FOUND_MESSAGE);
            throw new CartException( Constants.Messages.NO_ORDER_FOUND_MESSAGE +orderId);
        }
    }

}