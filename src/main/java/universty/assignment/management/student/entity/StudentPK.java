package universty.assignment.management.student.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentPK implements Serializable {
    private String studentId;

    private Long universityId;
}
