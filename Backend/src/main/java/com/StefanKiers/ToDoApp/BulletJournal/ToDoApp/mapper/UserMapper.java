package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMapper {

    public User toUserEntity(UserCreateDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    public void updateUserEntity(User user, UserUpdateDTO dto) {
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
    }

    public UserSummaryDTO toUserSummaryDTO(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public List<UserSummaryDTO> toUserSummaryDTOList(List<User> users) {
        return users.stream()
                .map(this::toUserSummaryDTO)
                .toList();
    }
}