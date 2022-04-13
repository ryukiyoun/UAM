package universty.assignment.management.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import universty.assignment.management.login.dto.LoginDTO;
import universty.assignment.management.professor.entity.Professor;
import universty.assignment.management.professor.repository.ProfessorRepository;
import universty.assignment.management.student.entity.Student;
import universty.assignment.management.student.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = loadProfessorUser(username);

        if(user != null) return user;

        return loadStudentUser(username);
    }

    private UserDetails loadProfessorUser(String username){
        Professor professor = professorRepository.findAllByProfessorId(username).orElse(null);

        if(professor == null) return null;

        return LoginDTO.builder()
                .name(professor.getProfessorId())
                .password(professor.getPassword())
                .role("ROLE_PROFESSOR")
                .build();
    }

    private UserDetails loadStudentUser(String username){
        Student student = studentRepository.findAllByStudentId(username)
                .orElseThrow(() -> new UsernameNotFoundException("미등록 ID 입니다. 학번 또는 교수번호를 다시 확인해 주시기 바랍니다."));

        return LoginDTO.builder()
                .name(student.getStudentId())
                .password(student.getPassword())
                .role("ROLE_STUDENT")
                .build();
    }
}
