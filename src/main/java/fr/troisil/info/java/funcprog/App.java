package fr.troisil.info.java.funcprog;

import Model.Personne;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
    public static void main( String[] args ) {
        String cheminFichierCSV = "src/main/resources/personnes.csv";
        try {

            //Exercice 1:
            List<Personne> listePersonnes = Files.lines(Paths.get(cheminFichierCSV))
                    //mon ficher n'as pas d'entête mais si le ficheir a un entête faudra decommenter cette ligne
                   // .skip(1)
                    .map(ligne -> ligne.split(","))
                    .map(parts -> new Personne(parts[0], Integer.valueOf(parts[1].trim())))
                    .collect(Collectors.toList());

            // EXERCICE 08.2 : TRIER LES PERSONNES
            listePersonnes = listePersonnes.stream()
                    .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                    .collect(Collectors.toList());

            //EXERCICE 08.3 : TRIER LES PERSONNES BIS

            Comparator<Personne> comparateur = Comparator
                    .comparingInt(Personne::getAge)
                    .reversed()
                    .thenComparing(Personne::getPrenom);

            // Trier la liste des personnes en utilisant le comparateur
            listePersonnes.sort(comparateur);

            listePersonnes.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
