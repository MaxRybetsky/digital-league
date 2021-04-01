package org.example.hrsample.service;

import org.example.helpers.CompareHelper;
import org.example.hrsample.dao.LocationsMapper;
import org.example.hrsample.dto.LocationsDto;
import org.example.hrsample.entity.LocationsEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource("classpath:application.yaml")
@RunWith(SpringRunner.class)
public class LocationServiceImplTest {
    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private LocationsMapper locationsMapper;

    @Autowired
    private LocationServiceImpl locationService;

    private LocationsEntity locationsEntity;
    private LocationsDto locationsDto;

    @Before
    public void before() {
        locationsEntity = new LocationsEntity();
        locationsEntity.setLocationId(1);
        locationsEntity.setCity("TEST");
        locationsEntity.setStreetAddress("TE st.");
        locationsDto = modelMapper.map(locationsEntity, LocationsDto.class);
    }

    @Test
    public void whenGetById_thenSuccess() {
        when(locationsMapper.getLocationById(locationsEntity.getLocationId()))
                .thenReturn(Optional.of(locationsEntity));
        LocationsDto result = locationService.getLocationById(locationsEntity.getLocationId());
        assertThat(result.getLocationId())
                .isEqualTo(locationsDto.getLocationId());
        assertThat(result.getStreetAddress())
                .isEqualTo(locationsDto.getStreetAddress());
        assertThat(result.getCity())
                .isEqualTo(locationsDto.getCity());
        verify(locationsMapper).getLocationById(locationsEntity.getLocationId());
    }

    @Test(expected = RuntimeException.class)
    public void whenGetNonExistingElementById_thenException() {
        when(locationsMapper.getLocationById(locationsEntity.getLocationId()))
                .thenReturn(Optional.empty());
        LocationsDto result = locationService.getLocationById(locationsEntity.getLocationId());
    }

    @Test
    public void whenGetAllLocations_thenSuccess() {
        List<LocationsEntity> locationsEntities = new ArrayList<>();
        addSomeLocations(locationsEntities);
        List<LocationsDto> locationsDtos = modelMapper.map(locationsEntities,
                TypeToken.of(List.class).getType());
        when(locationsMapper.getAll())
                .thenReturn(locationsEntities);
        List<LocationsDto> resultDtos = locationService.getAll();
        boolean result = CompareHelper.haveSameElements(locationsDtos, resultDtos);
        assertThat(resultDtos.size()).isEqualTo(3);
        assertThat(result).isTrue();
        verify(locationsMapper).getAll();
    }

    @Test
    public void whenInsertNewLocation_thenGetLocationWithNewId() {
        doNothing().when(locationsMapper).insert(locationsEntity);
        when(locationsMapper.getLocationById(locationsEntity.getLocationId()))
                .thenReturn(Optional.of(locationsEntity));
        LocationsDto result = locationService.insertLocation(locationsDto);
        assertThat(result.getLocationId()).isEqualTo(locationsDto.getLocationId());
        assertThat(result.getStreetAddress()).isEqualTo(locationsDto.getStreetAddress());
        assertThat(result.getCity()).isEqualTo(locationsDto.getCity());
        verify(locationsMapper).insert(locationsEntity);
        verify(locationsMapper).getLocationById(locationsEntity.getLocationId());
    }

    private void addSomeLocations(List<LocationsEntity> locationsEntities) {
        LocationsEntity locationsEntity2 = new LocationsEntity();
        locationsEntity2.setLocationId(2);
        locationsEntity2.setCity("TEST2");
        locationsEntity2.setStreetAddress("TE2 st.");
        LocationsEntity locationsEntity3 = new LocationsEntity();
        locationsEntity3.setLocationId(3);
        locationsEntity3.setCity("TEST3");
        locationsEntity3.setStreetAddress("TE3 st.");
        locationsEntities.add(locationsEntity);
        locationsEntities.add(locationsEntity2);
        locationsEntities.add(locationsEntity3);
    }
}