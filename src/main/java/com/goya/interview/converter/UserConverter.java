package com.goya.interview.converter;

import com.goya.interview.entity.FlatUserEntity;
import com.goya.interview.model.User;

public class UserConverter {

    public static FlatUserEntity toEntity(User user) {
        FlatUserEntity entity = new FlatUserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setDateOfBirth(user.getDateOfBirth());
        entity.setCityName(user.getCity().getName());
        entity.setCountryName(user.getCity().getCountry().getName());
        return entity;
    }

}
