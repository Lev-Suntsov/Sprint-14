package ru.practicum.item;

import java.util.List;

public interface ItemRepository {

    List<Item> findByUserId(long userId);

    Item save(Item item);

    void deleteByUserIdAndItemId(long userId, long itemId);

    Item updateItem(long itemId, Item item);

    Item getItem(long itemId);

    List<Item> search(String text);
}