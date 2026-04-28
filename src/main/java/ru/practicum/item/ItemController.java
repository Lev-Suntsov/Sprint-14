package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")

public class ItemController {
    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> get(@RequestHeader("X-Later-User-Id") long userId){
        return itemService.getItems(userId);
    }

    @PostMapping
    public Item add(@RequestHeader("X-Sharer-User-Id") Long userId,
                    @RequestBody ItemDto item){
        return itemService.addNewItem(userId, item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader("X-Later-User-Id") long userId,
                           @PathVariable(name="itemId") long itemId){
        itemService.deleteItem(userId, itemId);
    }

    @PatchMapping("/{itemId}")
    public  Item update(@PathVariable(name = "itemId") long itemId,
                        @RequestBody ItemDto itemDto){
        return itemService.updateItem(itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable(name = "itemId") long itemId){
        return itemService.getItem(itemId);
    }

    @GetMapping("/search")
    public List<Item> search(@RequestParam String text){
        return itemService.search(text);
    }
}
