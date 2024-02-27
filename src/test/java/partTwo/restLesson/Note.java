package partTwo.restLesson;

public class Note {

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    private String title;
    private String text;

    public static class Builder {
        private Note newNote;

        public Builder() {
            newNote = new Note();
        }

        public Note.Builder withTitle(String title) {
            newNote.title = title;
            return this;
        }

        public Note.Builder withText(String text) {
            newNote.text = text;
            return this;
        }

        public Note build() {
            return newNote;
        }
    }


}
