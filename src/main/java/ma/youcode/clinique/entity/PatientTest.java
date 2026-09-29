package ma.youcode.clinique.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PatientTest {

    public static void main(String[] args) {

        Patient patient = new Patient();

        patient.setNom("Alami");
        patient.setPrenom("Sara");
        patient.setDateNaissance(LocalDate.of(2000, 5, 10));
        patient.setNumeroSecuriteSociale("123456789");
        patient.setTensionArterielle("120/80");
        patient.setFrequenceCardiaque(75);
        patient.setTemperature(new BigDecimal("36.5"));
        patient.setFrequenceRespiratoire(18);

        System.out.println(patient.getNom());
        System.out.println(patient.getPrenom());
        System.out.println(patient.getDateNaissance());
        System.out.println(patient.getNumeroSecuriteSociale());
        System.out.println(patient.getTensionArterielle());
        System.out.println(patient.getFrequenceCardiaque());
        System.out.println(patient.getTemperature());
        System.out.println(patient.getFrequenceRespiratoire());
    }
}