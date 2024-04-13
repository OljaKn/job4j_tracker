package ru.job4j.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdTest {
    @Test
    public void whenItemWasFindByIdSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Find item"));
        FindById findById = new FindById(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        findById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявки по id ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasFindByIdFail() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Find item"));
        FindById findById = new FindById(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(0);

        findById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Вывод заявки по id ===" + ln
                        + "Заявка с введенным id: " + 0 + " не найдена." + ln
        );
    }

}