/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author HP
 */
public class GenericRepository<T extends Task> {

    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return items;
    }

    public T findById(long id) {

        for (T item : items) {

            if (item.getId() == id) {
                return item;
            }
        }

        throw new TaskNotFoundException(
                "Task with ID " + id + " not found"
        );
    }

    public Optional<T> findOptionalById(long id) {

        for (T item : items) {

            if (item.getId() == id) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
