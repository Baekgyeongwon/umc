package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RestaurantCategoryHandler;
import umc.spring.converter.PreferCategoryConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.RestaurantCategory;
import umc.spring.domain.User;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.RestaurantCategoryRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;

    @Override
    public User joinUser(UserRequestDTO.JoinDto request) {
        User newUser = UserConverter.toUser(request);
        List<RestaurantCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return restaurantCategoryRepository.findById(category).orElseThrow(() -> new RestaurantCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<PreferCategory> userPreferList = PreferCategoryConverter.toUserPreferList(newUser, foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
