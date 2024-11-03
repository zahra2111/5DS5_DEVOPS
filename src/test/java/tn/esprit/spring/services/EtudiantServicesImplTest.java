package tn.esprit.tpfoyer.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EtudiantServicesImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServicesImpl etudiantServices;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John");
        etudiant.setPrenomEtudiant("Doe");
        etudiant.setCinEtudiant(12345678L);
        etudiant.setDateNaissance(new Date()); // Set a date as needed
        // Set other properties as needed
    }

    // Tests for retrieveAllEtudiants()

    @Test
    void testRetrieveAllEtudiants_successful() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant));

        // Act
        List<Etudiant> etudiants = etudiantServices.retrieveAllEtudiants();

        // Assert
        assertEquals(1, etudiants.size());
        assertEquals(etudiant.getIdEtudiant(), etudiants.get(0).getIdEtudiant());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveAllEtudiants_empty() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Etudiant> etudiants = etudiantServices.retrieveAllEtudiants();

        // Assert
        assertTrue(etudiants.isEmpty());
        verify(etudiantRepository, times(1)).findAll();
    }

    // Tests for addEtudiant()

    @Test
    void testAddEtudiant_successful() {
        // Arrange
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant savedEtudiant = etudiantServices.addEtudiant(etudiant);

        // Assert
        assertNotNull(savedEtudiant);
        assertEquals(etudiant.getIdEtudiant(), savedEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testAddEtudiant_nullEtudiant() {
        // Arrange
        when(etudiantRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> etudiantServices.addEtudiant(null));
        verify(etudiantRepository, times(1)).save(null);
    }

    // Tests for updateEtudiant()

    @Test
    void testUpdateEtudiant_successful() {
        // Arrange
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant updatedEtudiant = etudiantServices.modifyEtudiant(etudiant);

        // Assert
        assertEquals(etudiant.getIdEtudiant(), updatedEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testUpdateEtudiant_nullEtudiant() {
        // Arrange
        when(etudiantRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> etudiantServices.modifyEtudiant(null));
        verify(etudiantRepository, times(1)).save(null);
    }

    // Tests for retrieveEtudiant()

    @Test
    void testRetrieveEtudiant_found() {
        // Arrange
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant foundEtudiant = etudiantServices.retrieveEtudiant(1L);

        // Assert
        assertNotNull(foundEtudiant);
        assertEquals(etudiant.getIdEtudiant(), foundEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveEtudiant_notFound() {
        // Arrange
        when(etudiantRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        Etudiant foundEtudiant = etudiantServices.retrieveEtudiant(2L);

        // Assert
        assertNull(foundEtudiant);
        verify(etudiantRepository, times(1)).findById(2L);
    }
}
