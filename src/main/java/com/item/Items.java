package com.item;

import com.item.dto.ItemCreationDto;
import com.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class Items {
    private final ItemRepository itemRepository;

    private static Logger log = Logger.getLogger(ItemController.class.getName());

    public Items(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        log.info("Items getItems");
        ArrayList <Item> items = new ArrayList<>();
        Iterable<Item> allItems = itemRepository.findAll();
        for (Item curItem: allItems) {
            items.add(curItem);
        }
        return items;
    }

    public Item getItem(Integer id) {
        log.info("Items getItem: " + id);
        Optional <Item> items = itemRepository.findById(id);
        return items.get();
    }

    public Item createItem(ItemCreationDto addInfo) {
        log.info("Items createItem:" + addInfo);
        String name = addInfo.getName();
        Integer amount = addInfo.getAmount();
        Double price = addInfo.getPrice();
        Item add = new Item();
        add.setName(name);
        add.setAmount(amount);
        add.setPrice(price);
        itemRepository.save(add);
        return add;
    }

    public Item updateItem(Integer id, Integer amount) {
        log.info("Items updateItem: " + id + " " + amount);
        Item item = getItem(id);
        item.setAmount(item.getAmount() + amount);
        itemRepository.save(item);
        return item;
    }



}
