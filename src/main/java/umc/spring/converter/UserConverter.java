package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .memberId(user.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

//        Region region = regionRepository.findByName(request.getName())
//                .orElseThrow(() -> "Region Fail");

        return User.builder()
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .gender(gender)
                .birth(String.format("%d-%d-%d", request.getBirthYear(), request.getBirthMonth(), request.getBirthDay()))
                .name(request.getName())
                .preferCategoryList(new ArrayList<>())
                .build();
    }
}
