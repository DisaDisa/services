package com.item;

import com.item.dto.ItemAdditionalParametrsDto;
import com.item.dto.ItemCreationDto;
import com.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class Items {
    private final ItemRepository itemRepository;

    public Items(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        ArrayList <Item> items = new ArrayList<>();
        Iterable<Item> allItems = itemRepository.findAll();
        for (Item curItem: allItems) {
            items.add(curItem);
        }
        return items;
    }

    public Item getItem(Integer id) {
        Optional <Item> items = itemRepository.findById(id);
        return items.get();
    }

    public void createItem(ItemCreationDto addInfo) {
        String name = addInfo.getName();
        Integer amount = addInfo.getAmount();
        Double price = addInfo.getPrice();
        Item add = new Item();
        add.setName(name);
        add.setAmount(amount);
        add.setPrice(price);
        itemRepository.save(add);
    }

    public void updateItem(Integer id, Integer amount) {
        Item item = getItem(id);
        item.setAmount(amount);
        itemRepository.save(item);
    }



}
