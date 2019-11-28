package com.goya.interview.service;

import com.goya.interview.converter.UserConverter;
import com.goya.interview.entity.FlatUserEntity;
import com.goya.interview.model.City;
import com.goya.interview.model.Country;
import com.goya.interview.model.User;
import com.goya.interview.repository.FlatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ToolService {
    private static final String DELIMITER = ",";

    private static final int COUNTRY_ID_INDEX = 0;
    private static final int COUNTRY_NAME_INDEX = 1;

    private static final int CITY_ID_INDEX = 0;
    private static final int CITY_NAME_INDEX = 1;
    private static final int CITY_COUNTRY_ID_INDEX = 2;

    private static final int USER_ID_INDEX = 0;
    private static final int USER_NAME_INDEX = 1;
    private static final int USER_DATE_OF_BIRTH_INDEX = 2;
    private static final int USER_CITY_ID_INDEX = 3;

    @Autowired
    private FlatUserRepository repository;

    private static Logger log = Logger.getLogger(ToolService.class.getName());

    public void importData(String userFilePath, String cityFilePath, String countryFilePath) {

        Map<Integer, City> cities =
                loadCities(cityFilePath, loadCountries(countryFilePath));

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath));
            String lineText;
            bufferedReader.readLine();
            while ((lineText = bufferedReader.readLine()) != null) {
                String[] row = lineText.split(DELIMITER);
                Integer id = Integer.valueOf(row[USER_ID_INDEX]);
                String name = row[USER_NAME_INDEX];
                LocalDate dateOfBirth = LocalDate.parse(row[USER_DATE_OF_BIRTH_INDEX]);
                City city = cities.get(Integer.valueOf(row[USER_CITY_ID_INDEX]));
                FlatUserEntity entity = UserConverter.toEntity(new User(id, name, dateOfBirth, city));
                repository.save(entity);
            }
        } catch (FileNotFoundException ex) {
            log.log(Level.SEVERE, "File not found ", ex);

        } catch (IOException e) {
            log.log(Level.SEVERE, "IO Exception ", e);
        }
    }

    private Map<Integer, City> loadCities(String path, Map<Integer, Country> countries) {
        Map<Integer, City> cities = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String lineText;
            bufferedReader.readLine();
            while ((lineText = bufferedReader.readLine()) != null) {
                String[] row = lineText.split(DELIMITER);
                Integer id = Integer.valueOf(row[CITY_ID_INDEX]);
                String name = row[CITY_NAME_INDEX];
                Country country = countries.get(Integer.valueOf(row[CITY_COUNTRY_ID_INDEX]));
                cities.put(id, new City(id, name, country));
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "File not found ", e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "IO Exception ", e);
        }
        return cities;
    }

    private Map<Integer, Country> loadCountries(String path) {
        Map<Integer, Country> countries = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String lineText;
            bufferedReader.readLine();
            while ((lineText = bufferedReader.readLine()) != null) {
                String[] row = lineText.split(DELIMITER);
                Integer id = Integer.valueOf(row[COUNTRY_ID_INDEX]);
                countries.put(id, new Country(id, row[COUNTRY_NAME_INDEX]));
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "File not found ", e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "IO Exception ", e);
        }
        return countries;
    }
}
