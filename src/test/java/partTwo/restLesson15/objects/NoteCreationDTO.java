package partTwo.restLesson15.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteCreationDTO {

    private String name;
    private String content;
    private String color;
    private int priority;

}
