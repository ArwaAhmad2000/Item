package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.exception.ItemNotFoundException;
import com.example.demo.service.exception.NoContentException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class ItemService {

    private final ItemRepository itemRepository;

    public Item addItem(Item item) {
        if (item != null) {
            return itemRepository.save(item);
        }
        throw new NoContentException("No data provided 😰");
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(int id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item " + id + " Not Found!"));
    }

    public Item replaceItem(int id, Item newItem) {
        if (newItem == null) {
            throw new NoContentException("No data provided 😰");
        }
        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item " + id + " Not Found!"));
        newItem.setId(id);
        return itemRepository.save(newItem);
    }

    public Item updateItem(int id, Item newItem) {
        if (newItem == null) {
            throw new NoContentException("No data provided 😰");
        }
        Item oldItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item " + id + " Not Found!"));
        if (newItem.getName() != null) {
            oldItem.setName(newItem.getName());
        }
        if (newItem.getPrice() != null) {
            oldItem.setPrice(newItem.getPrice());
        }
        return itemRepository.save(oldItem);
    }

    public void deleteItemById(int id) {
        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("This id " + id + " Not Found!"));
        itemRepository.deleteById(id);
    }

}
