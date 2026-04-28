package ru.practicum.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemRepositoryImpl implements ItemRepository{
    private final Map<Long, Item> items = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public List<Item> findByUserId(long userId) {
        return items.values().stream().filter(item -> item.getUserId() == userId).
                collect(Collectors.toList());
    }

    @Override
    public Item save(Item item){
        if(item.getId() == null) {
            item.setId(nextId++);
        }
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId){
        Item item = items.get(itemId);
        if (item != null && item.getUserId() == userId) {
            items.remove(itemId);
        }
    }

    @Override
    public Item updateItem(long itemId, Item item){
        Item oldItem = items.get(itemId);
        if(oldItem != null && oldItem.getUserId() == item.getUserId()){
            if(item.getName() != null && !item.getName().isBlank()){
                oldItem.setName(item.getName());
            }

            if(item.getDescription() != null && !item.getDescription().isBlank()){
                oldItem.setDescription(item.getDescription());
            }

            if(item.getAvailable() != null){
                oldItem.setAvailable(item.getAvailable());
            }
        }
        return oldItem;
    }

    @Override
    public Item getItem(long itemId){
        return items.get(itemId);
    }

    @Override
    public List<Item> search(String text){
        if(text == null || text.isBlank()) {
            return  List.of();
        }

        String query = text.toLowerCase();

        return items.values().stream()
                .filter(Item::isAvailable)
                .filter(item ->
                        containsIgnoreCase(item.getName(), query) ||
                                containsIgnoreCase(item.getDescription(), query))
                .toList();
    }

    private boolean containsIgnoreCase(String source, String query) {
        return source != null && source.toLowerCase().contains(query);
    }
}
