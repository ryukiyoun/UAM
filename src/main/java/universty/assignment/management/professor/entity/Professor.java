package universty.assignment.management.professor.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Entity
@Builder
@IdClass(ProfessorPK.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {
    @Id
    private String professorId;

    @Id
    private Long universityId;

    private String password;

    private String professorName;

    private String department;
}
