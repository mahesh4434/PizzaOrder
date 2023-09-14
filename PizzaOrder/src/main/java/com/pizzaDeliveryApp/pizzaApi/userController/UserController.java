package com.pizzaDeliveryApp.pizzaApi.userController;
import com.pizzaDeliveryApp.pizzaApi.dto.LoginDto;
import com.pizzaDeliveryApp.pizzaApi.dto.UserDto;
import com.pizzaDeliveryApp.pizzaApi.entity.*;
import com.pizzaDeliveryApp.pizzaApi.exception.CartException;
import com.pizzaDeliveryApp.pizzaApi.exception.ItemException;
import com.pizzaDeliveryApp.pizzaApi.repository.*;
import com.pizzaDeliveryApp.pizzaApi.response.LoginResponse;
import com.pizzaDeliveryApp.pizzaApi.response.StatusResponse;
import com.pizzaDeliveryApp.pizzaApi.service.ItemService;
import com.pizzaDeliveryApp.pizzaApi.service.UserService;
import com.pizzaDeliveryApp.pizzaApi.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping(Constants.URLs.REQUEST_MAPPING)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupedOrderRepository groupedOrderRepository;

    @Autowired
    private ViewMenuRepository viewMenuRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CrustRepository crustRepository;

    @Autowired
    private ItemService itemService;


    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ResponseBody
        public ResponseEntity<StatusResponse> handleGenericException(Exception ex) {
            StatusResponse response = new StatusResponse();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(Constants.Errors.UNEXPECTED_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //TODO add hardcoded values in constants
    @PostMapping(path = Constants.URLs.SIGNUP_URL)
    public ResponseEntity<StatusResponse> saveUser(@RequestBody UserDto userDTO) {
        log.info(Constants.Messages.SAVE_REQUEST, userDTO);
        boolean userExists = userService.checkUserExists(userDTO.getEmailId());
        StatusResponse response = new StatusResponse();
        if (userExists) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(Constants.Errors.USER_ALREADY_EXIST);
            return ResponseEntity.badRequest().body(response);

        }
        else if (userDTO.getusername() == null) {
            userService.addUser(userDTO);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(Constants.Errors.USERNAME_REQUIRED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if (userDTO.getContact() == 0) {
            userService.addUser(userDTO);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(Constants.Errors.CONTACT_REQUIRED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            userService.addUser(userDTO);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(Constants.Messages.SIGN_IN_MESSAGE);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }



    @PostMapping(Constants.URLs.LOGIN_URL)
    public ResponseEntity<StatusResponse> loginEmployee(@RequestBody LoginDto loginDTO) {
        log.info(Constants.Messages.LOGIN_REQUEST, loginDTO.getEmailId());
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        StatusResponse response = new StatusResponse();
        response.setMessage(loginResponse.getMessage());
        response.setStatus(HttpStatus.OK.value());
        response.setResponse(loginResponse.getToken());

        return ResponseEntity.ok(response);

    }


    @GetMapping(Constants.URLs.VIEW_MENU_URL)
    public ResponseEntity<StatusResponse> getMenu() {
        log.info(Constants.Messages.GET_MENU_REQUEST);
        List<ViewMenu> pizzas = viewMenuRepository.findAll();
        List<Topping> toppings = toppingRepository.findAll();
        List<Size> sizes = sizeRepository.findAll();
        List<Crust> crusts = crustRepository.findAll();
        Map<String, Object> menu = new HashMap<>();
        menu.put(Constants.Values.PIZZA, pizzas);
        menu.put(Constants.Values.SIZE, sizes);
        menu.put(Constants.Values.CRUST, crusts);
        menu.put(Constants.Values.TOPPINGS, toppings);
        menu.put(Constants.Values.TAX, Constants.Values.DEFAULT_TAX);

        StatusResponse response = new StatusResponse();
        log.info(Constants.Messages.MENU_FETCH);
        response.setMessage(Constants.Messages.MENU_FETCH);
        response.setStatus(HttpStatus.OK.value());
        response.setResponse(menu);
        return ResponseEntity.ok(response);
    }



    private int generateOrderId() {
        // Generate a random UUID and convert it to an integer
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        return (int) (mostSignificantBits & Integer.MAX_VALUE);
    }

    @PostMapping(Constants.URLs.CREATE_ORDER_URL)
    public StatusResponse addItems(@RequestBody List<Item> items) throws ItemException {
        log.info(Constants.Messages.ADD_ITEM_REQUEST, items);
        List<Integer> orderIds = new ArrayList<>();

        for (Item item : items) {
            String pid = item.getPizzaId().getpid();
            Optional<ViewMenu> optionalViewMenu = viewMenuRepository.findById(pid);

            if (optionalViewMenu.isPresent()) {
                ViewMenu viewMenu = optionalViewMenu.get();
                item.setPizzaId(viewMenu);

                Item newItem = itemService.addItem(item);
                int orderId = newItem.getOrderId();
                orderIds.add(orderId);
            } else {
                log.info(Constants.Errors.INVALID_PIZZA_ID_ERROR);
                throw new ItemException(Constants.Errors.INVALID_PIZZA_ID_ERROR);
            }
        }

        GroupedOrder groupedOrder = new GroupedOrder();
        groupedOrder.setOrderIds(orderIds);
        String groupId = String.valueOf(generateOrderId()); // Generate a unique group ID
        groupedOrder.setGroupId(groupId);
        groupedOrderRepository.save(groupedOrder);

        StatusResponse response = new StatusResponse();
        log.info(Constants.Messages.ORDER_CREATED);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(Constants.Messages.ORDER_CREATED);
        response.setResponse(Constants.Messages.ORDER_ID + groupId);
        return response;
    }

    @GetMapping(Constants.URLs.TRACK_GROUP_ORDER_URL)
    public StatusResponse getGroupOrderDetails(@PathVariable("groupId") String groupId) throws CartException {
        log.info(Constants.Messages.GET_DETAILS_REQUEST, groupId);
        Optional<GroupedOrder> optionalGroupedOrder = groupedOrderRepository.findById(groupId);

        if (optionalGroupedOrder.isPresent()) {
            GroupedOrder groupedOrder = optionalGroupedOrder.get();
            List<Integer> orderIds = groupedOrder.getOrderIds();

            List<Item> items = new ArrayList<>();
            for (Integer orderId : orderIds) {
                Item item = userService.viewOrder(orderId);
                items.add(item);
            }

            StatusResponse response = new StatusResponse();
            response.setStatus(HttpStatus.OK.value());
            response.setResponse(items.toString());
            return response;
        } else {
            log.info(Constants.Messages.NO_ORDER_FOUND_MESSAGE + groupId);
            StatusResponse response = new StatusResponse();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(Constants.Messages.NO_ORDER_FOUND_MESSAGE + groupId);
            return response;
        }
    }

    @GetMapping(Constants.URLs.TRACK_ORDER_URL)
    public StatusResponse getOrderDetails(@PathVariable("orderId") Integer orderId) throws CartException {
        log.info(Constants.Messages.GET_DETAIL_REQUEST, orderId);
        Item item = userService.viewOrder(orderId);

        StatusResponse response = new StatusResponse();
        if (item != null) {
            response.setStatus(HttpStatus.FOUND.value());
            response.setMessage(item.toString());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(Constants.Messages.NO_ORDER_FOUND_MESSAGE + orderId);
        }
        return response;
    }

}