package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenItemsReplaceIsTrue() {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("Item1");
            Item item2 = new Item("Item2");
            tracker.add(item1);
            int id = tracker.findById(item1.getId()).getId();
            tracker.replace(id, item2);
            assertThat(tracker.findById(id).getName()).isEqualTo(item2.getName());
        }
    }

    @Test
    public void whenItemsThenDeleteIsTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Item");
            tracker.add(item);
            int id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id)).isNull();
        }
    }

    @Test
    public void whenFindAllItems() {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("Item1"));
            Item item2 = tracker.add(new Item("Item2"));
            Item item3 = tracker.add(new Item("Item3"));
            assertThat(List.of(item1, item2, item3)).containsExactlyInAnyOrder(item1, item2, item3);
        }
    }

    @Test
    public void whenFindByName() {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("Item1"));
            Item item2 = tracker.add(new Item("Item2"));
            tracker.add(new Item("Item3"));
            tracker.add(new Item("Item2"));
            List<Item> rsl = tracker.findByName(item2.getName());
            assertThat(rsl.get(1).getName()).isEqualTo(item2.getName());
        }
    }

}