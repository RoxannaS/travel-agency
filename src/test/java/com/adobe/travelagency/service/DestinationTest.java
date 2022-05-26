package com.adobe.travelagency.service;

import com.adobe.travelagency.domain.AccommodationType;
import com.adobe.travelagency.entity.Accommodation;
import com.adobe.travelagency.entity.Destination;
import com.adobe.travelagency.repository.CustomerRepository;
import com.adobe.travelagency.repository.DestinationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.util.AssertionErrors.assertNull;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DestinationTest {

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DestinationService underTest;

    @Test
    public void test_addCustomer_alreadyExists() {
        // given
        Accommodation accommodation = Accommodation.builder().accommodationType(AccommodationType.HOSTEL)
                .name("Sunflower Rooms")
                .build();
        List<Accommodation> accommodationList = new ArrayList<>();
        accommodationList.add(accommodation);

        Destination destination = Destination.builder().country("Spain").
                city("Barcelona").
                availableAccommodationList(accommodationList).
                build();

        List<Destination> destinationList = new ArrayList<>();
        destinationList.add(destination);

        Mockito.when(destinationRepository.findAllByCountry("Spain")).thenReturn(destinationList);

        // when
        Optional<Destination> result = underTest.save(destination);

        // then
        assertNull("Result should be null", result);
        //verify(destinationRepository, times(1)).findAllByCountry("Spain");
        verifyNoMoreInteractions(destinationRepository);
        verifyNoInteractions(customerRepository);
    }

    
}
