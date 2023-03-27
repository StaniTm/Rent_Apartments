package com.example.myproject.service.user;

import com.example.myproject.domain.entity.UserClientEntity;
import com.example.myproject.domain.model.banding.business.RentForm;
import com.example.myproject.domain.model.banding.user.UserRegisterFormModel;
import com.example.myproject.domain.model.dtoS.user.UserModel;
import com.example.myproject.domain.model.errors.UserNotFoundException;
import com.example.myproject.domain.model.view.UserViewModel;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.userRole.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel register(UserRegisterFormModel userRegister) {
        final UserModel userModel = this.modelMapper.map(userRegister, UserModel.class);

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRole(userRegister.getRole().equals("admin")
                ? List.of(this.userRoleService.findRoleByName("ADMIN"))
                : List.of(this.userRoleService.findRoleByName("USER")));

        UserClientEntity user = this.modelMapper.map(userModel, UserClientEntity.class);
        user.setCreatedOn(new Date());
        this.userRepository.saveAndFlush(user);

        return userModel;
    }

    public boolean checkUsernameAvailability(String username) {
        Optional<UserClientEntity> userToCheck = this.userRepository.findByUsername(username);
        return userToCheck.isPresent();
    }

    public UserModel updateUser(RentForm form, String username) {
        UserClientEntity entity = this.getEntityByUsername(username);
        entity.setEmail(form.getEmail()).
                setFirstName(form.getFirstName()).
                setLastName(form.getLastName()).
                setPhoneNumber(form.getPhoneNumber());

        this.userRepository.saveAndFlush(entity);

        return this.modelMapper.map(entity, UserModel.class);
    }

    public UserModel getModelByUsername(String username) {
        UserClientEntity userClientEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");
        return this.modelMapper.map(userClientEntity, UserModel.class);
    }

    public UserViewModel getViewModelByUsername(String username) {
        UserClientEntity userClientEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");

        UserModel userModel = this.modelMapper.map(userClientEntity, UserModel.class);

        return this.modelMapper.map(userModel, UserViewModel.class);
    }

    public UserClientEntity getEntityByUsername(String username) {
        UserClientEntity userClientEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");
        return userClientEntity;
    }

    public UserClientEntity getEntityById(Long userId) {
        UserClientEntity userClientEntity = this.userRepository.findById(userId).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");
        return userClientEntity;
    }

    public List<UserViewModel> getAllUserViews(String loggedUsername) {
        return this.userRepository.findAll().
                stream().
                map(userClientEntity -> this.modelMapper.map(userClientEntity, UserViewModel.class)).filter(userViewModel -> !userViewModel.getUsername().equals(loggedUsername)).
                toList();
    }

    //adding role is possible only if the user has normal USER role which means we always add ADMIN role!
    public void addUserRole(Long userId) {
        UserClientEntity userClientEntity = this.userRepository.findById(userId).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");

        userClientEntity.
                setRole(this.userRoleService.findAllRoles()).
                setModifiedOn(new Date());

        this.userRepository.saveAndFlush(userClientEntity);
    }

    public void deleteUser(Long userId) {
        UserClientEntity userClientEntity = this.userRepository.findById(userId).orElse(null);
        if (userClientEntity == null) throw new UserNotFoundException("User not found!");

        this.userRepository.deleteById(userId);
    }

}
