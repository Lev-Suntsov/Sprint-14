package ru.practicum.item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(long userId);

    Item addNewItem(long userId, ItemDto item);

    void deleteItem(long userId, long itemId);

    Item updateItem(long itemId, ItemDto item);

    Item getItem(long itemId);

    List<Item> search(String text);
}
