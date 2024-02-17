package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody(required = false) Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(item));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> replaceItem(@PathVariable int id, @RequestBody(required = false) Item newItem) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.replaceItem(id, newItem));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody(required = false) Item newItem) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateItem(id, newItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        itemService.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
