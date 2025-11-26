package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
    private static class Task {
        private final String description;
        private boolean done;

        public Task(String description) {
            this.description = description;
            this.done = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isDone() {
            return done;
        }

        public void markDone() {
            done = true;
        }

        @Override
        public String toString() {
            return (done ? "[x] " : "[ ] ") + description;
        }
    }

    private final List<Task> items = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(new Task(item));
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public List<String> getAll() {
        return items.stream().map(Task::toString).collect(Collectors.toList());
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public boolean done(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).markDone();
            return true;
        }
        return false;
    }

    public List<String> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String lowerQuery = query.toLowerCase();
        return items.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerQuery))
                .map(Task::toString)
                .collect(Collectors.toList());
    }
}