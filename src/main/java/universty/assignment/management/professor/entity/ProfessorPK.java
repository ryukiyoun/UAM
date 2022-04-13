package universty.assignment.management.professor.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfessorPK implements Serializable {
    private String professorId;

    private Long universityId;
}
