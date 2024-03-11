package pojo;

import lombok.Data;

@Data
public class Note {

    private String name;
    private String content;
    private String color;
    private String priority;


    public static class Builder {
        private Note newNote;

        public Builder() {
            newNote = new Note();
        }

        public Builder withName(String name) {
            newNote.name = name;
            return this;
        }

        public Builder withContent(String content) {
            newNote.content = content;
            return this;
        }

        public Builder withColor(String color) {
            newNote.color = color;
            return this;
        }

        public Builder withPriority(String priority) {
            newNote.priority = priority;
            return this;
        }

        public Note build() {
            return newNote;
        }
    }


}
