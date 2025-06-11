package ecommerce.example.ecommerce.service.user;

import ecommerce.example.ecommerce.dto.UserDto;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.request.CreateUserRequest;
import ecommerce.example.ecommerce.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}

