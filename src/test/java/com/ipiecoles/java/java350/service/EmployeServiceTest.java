package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @Mock
    EmployeRepository employeRepository;

    @InjectMocks
    EmployeService employeService;

    @Test
    public void embaucheEmployeTest() throws EmployeException {
    String nom = "Name";
    String prenom = "Prenom";
    Poste poste = Poste.TECHNICIEN;
    NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
    Double tempsPartiel = 1.0;

    when(employeRepository.findLastMatricule()).thenReturn(null);
    when(employeRepository.findByMatricule("00001")).thenReturn(null);

    employeService.embaucheEmploye(nom, prenom, poste, niveauEtude,tempsPartiel);


    }

    @Test
    public void embaucheTechnicienBTSTempsPleinTest() throws EmployeException {
        String nom = "Name";
        String prenom = "Prenom";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        Employe employe = employeRepository.findAll().get(0);
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
        Assertions.assertThat(employe.getMatricule()).isEqualTo("T00001");
        // 1521.22 * 1.2 = 1825.46
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1825.46);


    }
    }
