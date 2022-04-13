package universty.assignment.management.student.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Entity
@Builder
@IdClass(StudentPK.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    @Id
    private String studentId;

    @Id
    private Long universityId;

    private String password;

    private String studentName;
}
