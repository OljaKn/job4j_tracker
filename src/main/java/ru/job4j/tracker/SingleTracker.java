package ru.job4j.tracker;

import java.util.List;

public class SingleTracker {
    private static SingleTracker instance = null;
    private MemTracker memtracker = new MemTracker();

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return memtracker.add(item);
    }

    public Item findById(int id) {
        return memtracker.findById(id);
    }

    public List<Item> findAll() {
        return memtracker.findAll();
    }

    public List<Item> findByName(String key) {
        return memtracker.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return memtracker.replace(id, item);
    }

    public void delete(int id) {
        memtracker.delete(id);
    }
}