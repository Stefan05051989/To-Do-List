package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserChangePasswordDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.DuplicateEmailException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.IncorrectPasswordException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.SamePasswordException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.UserMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserSummaryDTO create(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.email())) {
            throw new DuplicateEmailException("Email already exists");
        }
        User user = userMapper.toUserEntity(userCreateDTO);
        return userMapper.toUserSummaryDTO(userRepository.save(user));
    }


    public List<UserSummaryDTO> getAll() {
        return userMapper.toUserSummaryDTOList(userRepository.findAll());
    }

    public UserSummaryDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toUserSummaryDTO(user);
    }

    public UserSummaryDTO update(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userMapper.updateUserEntity(user, dto);
        return userMapper.toUserSummaryDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserSummaryDTO changePassword(UserChangePasswordDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getPassword().equals(dto.currentPassword())) {
            throw new IncorrectPasswordException("Current password is incorrect");
        }

        if (dto.currentPassword().equals(dto.newPassword())) {
            throw new SamePasswordException("New password cannot be the same as current password");
        }

        user.setPassword(dto.newPassword());
        return userMapper.toUserSummaryDTO(userRepository.save(user));
    }
}