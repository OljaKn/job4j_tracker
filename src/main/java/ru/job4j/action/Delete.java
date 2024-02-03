package ru.job4j.action;

import ru.job4j.tracker.*;

public class Delete implements UserAction {
    private final Output out;

    public Delete(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Удалить заявку";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Удаление заявки ===");
        int id = input.askInt("Введите id: ");
        Item item = store.findById(id);
        if (item != null) {
            store.delete(id);
            out.println("Заявка удалена успешно.");
        } else {
            out.println("Ошибка удаления заявки.");
        }
        return true;
    }
}
