package partTwo.restLesson15.objects;

import lombok.Data;

@Data
public class Note {

    private int id;
    private String name;
    private String content;
    private String color;
    private int priority;
    private boolean archiveFlag;

    public Note generateNote() {
        int numberGenerate =  100 + (int) (Math.random() * 10000);

        Note newNote = new Note();
        newNote.setName("testText" + numberGenerate);
        newNote.setContent("testContent" + numberGenerate);
        newNote.setColor("#d7aefb");
        newNote.setPriority(0);
        return newNote;
    }
}
