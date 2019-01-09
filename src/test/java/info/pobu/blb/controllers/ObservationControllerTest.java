package info.pobu.blb.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import info.pobu.blb.controllers.exceptions.SpeciesNotFoundException;
import info.pobu.blb.controllers.exceptions.UserNotFoundException;
import info.pobu.blb.entities.Observation;
import info.pobu.blb.entities.Species;
import info.pobu.blb.entities.User;
import info.pobu.blb.repositories.IObservationRepository;
import info.pobu.blb.repositories.IUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObservationController.class)
public class ObservationControllerTest {
    
    @MockBean
    private IObservationRepository repository;
    
    @MockBean
    private IUserRepository userRepository;
    
    @Autowired
    private ObservationController controller;
    
    @Mock
    private User user;
    
    @Mock
    Observation observationMock;
    
    final static int EXISTING_ID = 2;
    final static int NOT_EXISTING_ID = -3;
    
    final static String LOCATION = "loc";
    final static LocalDate DATE = LocalDate.now();
    final static String SPECIES = "BLACK_WOODPECKER";
    final static String NOT_EXISTING_SPECIES = "test";
    
    @Before
    public void mock() {
        Mockito.when(userRepository.findById(EXISTING_ID)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(NOT_EXISTING_ID)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(Observation.class))).thenReturn(new Observation(user, Species.valueOf(SPECIES), LOCATION, DATE));
    }

    @Test
    public void shouldAddObservationForMockUser() throws UserNotFoundException, SpeciesNotFoundException {
        
        Observation observation = controller.addNewObservation(EXISTING_ID, SPECIES, LOCATION, DATE);
        
        Assert.assertNotNull(observation);
        Assert.assertEquals(DATE, observation.getDate());
        Assert.assertEquals(LOCATION, observation.getLocation());
        Assert.assertEquals(Species.valueOf(SPECIES), observation.getSpecies());
        Assert.assertEquals(user, observation.getUser());
        
    }
    
    @Test(expected = UserNotFoundException.class)
    public void shouldFindNoUserWhileAddingNewObservation() throws UserNotFoundException, SpeciesNotFoundException {
        controller.addNewObservation(NOT_EXISTING_ID, SPECIES, LOCATION, DATE);
    }
    
    @Test(expected = SpeciesNotFoundException.class)
    public void shouldNotFindSpecies() throws UserNotFoundException, SpeciesNotFoundException {
        controller.addNewObservation(EXISTING_ID, NOT_EXISTING_SPECIES, LOCATION, DATE);
    }

}
