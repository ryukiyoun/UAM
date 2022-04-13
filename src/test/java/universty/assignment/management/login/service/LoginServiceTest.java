package universty.assignment.management.login.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import universty.assignment.management.professor.entity.Professor;
import universty.assignment.management.professor.repository.ProfessorRepository;
import universty.assignment.management.student.entity.Student;
import universty.assignment.management.student.repository.StudentRepository;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    ProfessorRepository professorRepository;

    @Mock
    StudentRepository studentRepository;

    @Spy
    @InjectMocks
    LoginService loginService;

    @Test
    void loadUserByUsername() {
        //given
        given(professorRepository.findAllByProfessorId(anyString()))
                .willReturn(Optional.of(Professor.builder()
                        .professorId("P_15023")
                        .password("pass")
                        .build()));

        //when
        UserDetails result = loginService.loadUserByUsername("P_15023");

        //then
        assertThat(result.getUsername(), is("P_15023"));
        assertThat(result.getPassword(), is("pass"));
        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());
        assertTrue(result.isEnabled());
        assertTrue(result.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFESSOR")));
    }

    @Test
    void loadUserByUsernameProfessorNull() {
        //given
        given(professorRepository.findAllByProfessorId(anyString()))
                .willReturn(Optional.empty());

        given(studentRepository.findAllByStudentId(anyString()))
                .willReturn(Optional.of(Student.builder()
                        .studentId("592302")
                        .password("pass")
                        .build()));

        //when
        UserDetails result = loginService.loadUserByUsername("592302");

        //then
        assertThat(result.getUsername(), is("592302"));
        assertThat(result.getPassword(), is("pass"));
        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());
        assertTrue(result.isEnabled());
        assertTrue(result.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT")));
    }

    @Test
    void loadUserByUsernameException() {
        //given
        given(professorRepository.findAllByProfessorId(anyString()))
                .willReturn(Optional.empty());

        given(studentRepository.findAllByStudentId(anyString()))
                .willReturn(Optional.empty());

        //when, then
        assertThrows(UsernameNotFoundException.class, () -> loginService.loadUserByUsername("13884"));
    }
}