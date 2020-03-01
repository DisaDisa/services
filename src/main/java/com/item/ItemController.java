package com.item;


import com.item.dto.ItemCreationDto;
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
    public List<Item> getItems()
    {
        log.info("Controller getItems");
        return itemController.getItems();
    }

    @GetMapping("api/warehouse/items/{item_id}")
    public Item getItemById(@PathVariable Integer item_id) {
        log.info("Controller getItemById: id = " + item_id);
        return itemController.getItem(item_id);
    }

    @PostMapping("api/warehouse/items")
    public Item createItem(@RequestBody ItemCreationDto addInfo) {
        log.info("Controller createItem: " + addInfo);
        return itemController.createItem(addInfo);
    }

    @PutMapping("api/warehouse/items/{id}/addition/{amount}")
    public Item addExistingItem(@PathVariable Integer id, @PathVariable Integer amount) {
        log.info("Controller addExistingItem: " + id + " " + amount);
        return itemController.updateItem(id, amount);
    }
}
