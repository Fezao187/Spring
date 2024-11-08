package spring.web.app.example.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRunRepositoryTest {
    InMemoryRunRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRunRepository();
        repository.create(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR,null));

        repository.create(new Run(2,
                "Afternoon run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,
                Location.INDOOR,null));
    }

    @Test
    void shouldFindAllRuns(){
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(), "Should find 2 runs");
    }

    @Test
    void shouldFindRunWithValidId(){
        var run = repository.findById(1).get();
        assertEquals("Monday Morning Run", run.title());
        assertEquals(3, run.miles());
    }

    @Test
    void shouldFindRunWithInvalidId(){
        RunNotFoundException notFoundException = assertThrows(
                RunNotFoundException.class,
                () -> repository.findById(3).get()
        );

        assertEquals("Run not found!", notFoundException.getMessage());
    }

    @Test
    void shouldCreateRun(){
        repository.create(new Run(3,
                "Afternoon run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,
                Location.INDOOR,null));
        List<Run> runs = repository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldUpdateRun(){
        repository.update(new Run(1,
                "Afternoon run@",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,
                Location.INDOOR,null),1);

        var run = repository.findById(1).get();
        assertEquals("Afternoon run@", run.title());
        assertEquals(6, run.miles());
        assertEquals(Location.INDOOR, run.location());
    }

    @Test
    void shouldDeleteRun(){
        repository.delete(3);
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size());
    }
}