package info.pobu.blb.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import info.pobu.blb.entities.Email;
import info.pobu.blb.entities.Nick;
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
    private UserController userController;
    
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
    public static final Nick USER_A_NICK;
    public static final Nick USER_B_NICK;
    public static final Nick USER_C_NICK;
    static {
        try {
            USER_A_NICK = new Nick("a_test");
            USER_B_NICK = new Nick("b_test");
            USER_C_NICK = new Nick("c_test");
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to create Nick instance in static block.", ex);
        }
    }

    public static final Email USER_A_EMAIL;
    public static final Email USER_B_EMAIL;
    public static final Email USER_C_EMAIL;
    static {
        try {
            USER_A_EMAIL = new Email("a@xyz.abc");
            USER_B_EMAIL = new Email("b@xyz.abc");
            USER_C_EMAIL = new Email("c@xyz.abc");
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to create Email instance in static block.", ex);
        }
    }
    
    
    static final List<Observation> OBSERVATIONS = new ArrayList<Observation>() {
        /** serialization */
        private static final long serialVersionUID = -3369139850782288377L;
        
        {
            add(new Observation(new User(USER_A_NICK, USER_A_EMAIL), Species.valueOf(SPECIES), LOCATION, DATE));
            add(new Observation(new User(USER_B_NICK, USER_B_EMAIL), Species.valueOf(SPECIES), LOCATION, DATE));
            add(new Observation(new User(USER_C_NICK, USER_C_EMAIL), Species.valueOf(SPECIES), LOCATION, DATE));
        }
    };
    
    @Before
    public void mock() {
        Mockito.when(userController.findById(EXISTING_ID)).thenReturn(Optional.of(user));
        Mockito.when(userController.findById(NOT_EXISTING_ID)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(Observation.class))).thenReturn(new Observation(user, Species.valueOf(SPECIES), LOCATION, DATE));
        Mockito.when(repository.findAll()).thenReturn(OBSERVATIONS);
        Mockito.when(user.getNick()).thenReturn(USER_A_NICK);
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
    
    @Test
    public void shouldGetAllObservations() {
        List<Observation> observations = new ArrayList<>();
        Assert.assertNotNull(observations);
        Assert.assertTrue(observations.isEmpty());
        observations = controller.getAllObservations();
        Assert.assertFalse(observations.isEmpty());
        Assert.assertEquals(OBSERVATIONS.size(), observations.size());
    }

}
