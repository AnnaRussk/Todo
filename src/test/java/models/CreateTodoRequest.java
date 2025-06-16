package models;

public class CreateTodoRequest {
    private final int id;
    private String text;
    private boolean completed;

    public CreateTodoRequest(int id, String text) {
        this.id = id;
        this.text = text;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
