package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepositoryImpl repository;
    private final ItemMapper mapper;

    @Override
    public List<Item> getItems(long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Item addNewItem(long userId, ItemDto itemDto) {
        Item item = mapper.mapToItem(itemDto);
        item.setUserId(userId);
        return repository.save(item);
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        repository.deleteByUserIdAndItemId(userId, itemId);
    }

    @Override
    public Item updateItem(long itemId, ItemDto item) {
        return repository.updateItem(itemId, mapper.mapToItem(item));
    }

    @Override
    public Item getItem(long itemId){
        return repository.getItem(itemId);
    }

    @Override
    public List<Item> search(String text){
        return repository.search(text);
    }

}
