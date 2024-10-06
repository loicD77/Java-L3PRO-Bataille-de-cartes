package jeuLoic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bataille {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander au joueur de choisir le nombre de cartes
        System.out.println("Voulez-vous jouer avec un jeu de 32 ou 52 cartes ? Entrez 32 ou 52 : ");
        int nombreDeCartes = scanner.nextInt();

        while (nombreDeCartes != 32 && nombreDeCartes != 52) {
            System.out.println("Entrée invalide. Veuillez entrer 32 ou 52 : ");
            nombreDeCartes = scanner.nextInt();
        }

        // Créer le jeu de cartes avec une liste
        List<Card> jeuDeCartes = new ArrayList<>();

        // Limiter les valeurs des cartes en fonction du nombre de cartes choisi
        int startValue = (nombreDeCartes == 32) ? 5 : 0; // Si 32 cartes, on commence à "7" (index 5)

        // Créer les cartes
        for (String color : Card.COLORS) {
            for (int i = startValue; i < Card.VALUES.length; i++) {
                jeuDeCartes.add(new Card(color, i));
            }
        }

        // Mélanger le jeu de cartes avec Math.random
        for (int i = 0; i < jeuDeCartes.size(); i++) {
            int randomIndex = (int) (Math.random() * jeuDeCartes.size());
            Card temp = jeuDeCartes.get(i);
            jeuDeCartes.set(i, jeuDeCartes.get(randomIndex));
            jeuDeCartes.set(randomIndex, temp);
        }

        // Créer deux joueurs
        Joueur joueur1 = new Joueur("Tom");
        Joueur joueur2 = new Joueur("Jerry");

        // Distribuer les cartes
        for (int i = 0; i < jeuDeCartes.size(); i++) {
            if (i % 2 == 0) {
                joueur1.ajouterCarte(jeuDeCartes.get(i));
            } else {
                joueur2.ajouterCarte(jeuDeCartes.get(i));
            }
        }

        // Déroulement du jeu
        while (true) {
            List<Card> cartesJouees = new ArrayList<>();
            cartesJouees.add(joueur1.tirerCarte());
            cartesJouees.add(joueur2.tirerCarte());

            // Si un des joueurs n'a plus de cartes, fin de la partie
            if (cartesJouees.get(0) == null || cartesJouees.get(1) == null) {
                break;
            }

            System.out.println("Tom tire: " + cartesJouees.get(0));
            System.out.println("Jerry tire: " + cartesJouees.get(1));

            // Comparer les cartes tirées
            if (cartesJouees.get(0).compare(cartesJouees.get(1)) > 0) {
                joueur1.ajouterPoint();
                System.out.println("Tom gagne le pli !");
                joueur1.ajouterCartes(cartesJouees);
            } else if (cartesJouees.get(0).compare(cartesJouees.get(1)) < 0) {
                joueur2.ajouterPoint();
                System.out.println("Jerry gagne le pli !");
                joueur2.ajouterCartes(cartesJouees);
            } else {
                // Si égalité, lancer la bataille
                System.out.println("Égalité ! Bataille !");

                while (cartesJouees.get(0).compare(cartesJouees.get(1)) == 0) { // En cas d'égalité
                    // Vérifier si les deux joueurs ont assez de cartes pour continuer la bataille
                    if (joueur1.aDesCartes() && joueur2.aDesCartes()) {
                        cartesJouees.add(joueur1.tirerCarte()); // Carte face cachée
                        cartesJouees.add(joueur2.tirerCarte()); // Carte face cachée

                        // Vérification supplémentaire si les joueurs ont encore assez de cartes pour continuer la bataille
                        if (!joueur1.aDesCartes() || !joueur2.aDesCartes()) {
                            break; // Si l'un des joueurs n'a plus de cartes, on arrête la bataille
                        }

                        cartesJouees.add(joueur1.tirerCarte()); // Carte face visible
                        cartesJouees.add(joueur2.tirerCarte()); // Carte face visible

                        // Afficher les cartes de bataille visibles
                        System.out.println("Tom tire: " + cartesJouees.get(cartesJouees.size() - 2));
                        System.out.println("Jerry tire: " + cartesJouees.get(cartesJouees.size() - 1));

                    } else {
                        // Si un des joueurs n'a plus de cartes pendant une bataille
                        break;
                    }
                }

                // Comparer les cartes après bataille
                if (cartesJouees.get(cartesJouees.size() - 2).compare(cartesJouees.get(cartesJouees.size() - 1)) > 0) {
                    joueur1.ajouterPoint();
                    System.out.println("Tom gagne la bataille !");
                    joueur1.ajouterCartes(cartesJouees);
                } else {
                    joueur2.ajouterPoint();
                    System.out.println("Jerry gagne la bataille !");
                    joueur2.ajouterCartes(cartesJouees);
                }
            }

            // Afficher les scores
            System.out.println("Scores actuels :");
            System.out.println(joueur1);
            System.out.println(joueur2);
            System.out.println("--------------------------------");
        }

        // Afficher le vainqueur
        if (joueur1.getScore() > joueur2.getScore()) {
            System.out.println("Tom remporte la partie !");
        } else if (joueur1.getScore() < joueur2.getScore()) {
            System.out.println("Jerry remporte la partie !");
        } else {
            System.out.println("La partie se termine par une égalité !");
        }

        scanner.close();
    }
}
