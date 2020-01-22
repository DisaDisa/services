package com.item;


import com.item.dto.ItemAdditionalParametrsDto;
import com.item.dto.ItemCreationDto;
import com.item.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ItemController {
    private final Items itemController;


    private static Logger log = Logger.getLogger(ItemController.class.getName());

    public ItemController(Items itemController) {
        this.itemController = itemController;
    }

    @GetMapping("api/warehouse/items")
    public List<Item> getItems() {
        return itemController.getItems();
    }

    @GetMapping("api/warehouse/items/{item_id}")
    public Item getItemById(@PathVariable Integer item_id) {
        return itemController.getItem(item_id);
    }

    @PostMapping("api/warehouse/items")
    public void createItem(@RequestBody ItemCreationDto addInfo) {
        itemController.createItem(addInfo);
    }

    @PutMapping("api/warehouse/items/{id}/addition/{amount}")
    public void addExistingItem(@PathVariable Integer id, @PathVariable Integer amount) {
        itemController.updateItem(id, amount);
    }
}
