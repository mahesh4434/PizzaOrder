package com.pizzaDeliveryApp.pizzaApi.service.IMPL;
import java.util.Optional;

import com.pizzaDeliveryApp.pizzaApi.constant.Constants;
import com.pizzaDeliveryApp.pizzaApi.entity.Item;
import com.pizzaDeliveryApp.pizzaApi.exception.ItemException;
import com.pizzaDeliveryApp.pizzaApi.repository.ItemRepository;
import com.pizzaDeliveryApp.pizzaApi.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepo;


    @Override
    public Item addItem(Item item) throws ItemException {
        Optional<Item> opt = itemRepo.findById(item.getOrderId());
        if (opt.isPresent()) {
            log.error(Constants.Errors.ITEM_EXIST);
            throw new ItemException(Constants.Errors.ITEM_EXIST);
        } else {
            log.info(Constants.Messages.ITEM_ADDED);
            return itemRepo.save(item);
        }
    }


}