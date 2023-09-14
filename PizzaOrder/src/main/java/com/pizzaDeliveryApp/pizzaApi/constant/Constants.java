package com.pizzaDeliveryApp.pizzaApi.constant;
public class Constants {

        public static class URLs {
                public static final String REQUEST_MAPPING = "/api/user";
                public static final String VIEW_MENU_URL = "/viewMenu";
                public static final String CREATE_ORDER_URL = "/createOrder";
                public static final String TRACK_GROUP_ORDER_URL = "/trackGroupOrder/{groupId}";
                public static final String TRACK_ORDER_URL = "/trackOrder/{orderId}";
                public static final String SIGNUP_URL = "/signup";
                public static final String LOGIN_URL = "/login";
        }

        public static class Messages {
                public static final String ORDER_ID = "Your Order ID: ";
                public static final String MENU_FETCH = "Menu fetched successfully";
                public static final String ORDER_FETCH = "Order fetched successfully";
                public static final String LOGIN_SUCCESS_MESSAGE = "Logged in Successfully";
                public static final String LOGIN_FAILED_MESSAGE = "Login failed";
                public static final String PASSWORD_MISMATCH_MESSAGE = "Password did not match";
                public static final String USERNAME_NOT_EXIST_MESSAGE = "UserId is incorrect";
                public static final String NO_TOKEN_GENERATED_MESSAGE = "No token generated";
                public static final String NO_ORDER_FOUND_MESSAGE = "No Order found with ID: ";
                public static final String SIGN_IN_MESSAGE = "Sign in Successful";
                public static final String ORDER_CREATED = "Order created successful";
                public static final String SAVE_REQUEST ="Received request to saveUser: {}";
                public static final String LOGIN_REQUEST ="Received request to loginUser: {}";
                public static final String GET_MENU_REQUEST = "Received request to getMenu";
                public static final String ADD_ITEM_REQUEST = "Received request to addItems: {}";
                public static final String GET_DETAILS_REQUEST = "Received request to getGroupOrderDetails for groupId: {}";
                public static final String GET_DETAIL_REQUEST = "Received request to getOrderDetails for orderId: {}";
                public static final String ADD_USER_REQUEST = "Received request to addUser: {}";
                public static final String CHECK_USER_REQUEST ="Received request to checkUserExists: {}";
                public static final String VIEW_ORDER_REQUEST = "Received request to viewOrder: {}";
                public static final String ITEM_ADDED ="Item added successfully";
        }

        public static class Paths {

                public static final String VIEW_MENU = "/api/user/viewMenu";
                public static final String HELLO = "/Hello";
                public static final String CREATE_ORDER = "/api/user/createOrder";
                public static final String TRACK_GROUP_ORDER = "/api/user/trackGroupOrder/{groupId}";
        }

        public static class Values {
                public static final String PIZZA = "pizza";
                public static final String SIZE = "sizes";
                public static final String CRUST = "crusts";
                public static final String TOPPINGS = "toppings";
                public static final String TAX = "tax";
                public static final int DEFAULT_TAX = 10;
                public static final String OPTION = "OPTIONS";
                public static final String AUTHORIZATION = "Authorization";
                public static final String BEARER = "Bearer ";
        }

        public static class Errors {
                public static final String INVALID_PIZZA_ID_ERROR = "Invalid PizzaId";
                public static final String INVALID_TOKEN = "Missing or Invalid Token";
                public static final String USER_ALREADY_EXIST = "User already exist";
                public static final String RESPONSE_MESSAGE = "No Response";
                public static final String ITEM_EXIST = "Item already exists..";
                public static final String USERNAME_REQUIRED = "Username is required";
                public static final String CONTACT_REQUIRED ="Contact number is required" ;
                public static final String UNEXPECTED_ERROR ="An unexpected error occurred. Please try again later.";
        }
}