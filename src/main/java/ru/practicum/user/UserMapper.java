package ru.practicum.user;

public class UserMapper {
    public UserDto mapToUserDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        return dto;
    }
}
