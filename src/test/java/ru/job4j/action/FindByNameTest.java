package ru.job4j.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameTest {
    @Test
    public void whenItemWasFindByNameSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Find item"));
        String name = "Find item";
        FindByName findByName = new FindByName(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasFindByNameFail() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Find item name";
        FindByName findByName = new FindByName(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявок по имени ===" + ln
                        + "Заявки с именем: " + name + " не найдены." + ln
        );
    }

}