package jeuLoic;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    // Propriétés : nom, paquet (tableau de cartes), et score
    private String nom;
    private List<Card> paquet;
    private int score;

    // Constructeur pour créer un joueur avec un nom
    public Joueur(String nom) {
        this.nom = nom;
        this.paquet = new ArrayList<>();
        this.score = 0;
    }

    // Accesseurs pour obtenir le score
    public int getScore() {
        return score;
    }

    // Méthode pour ajouter une carte au paquet du joueur
    public void ajouterCarte(Card carte) {
        paquet.add(carte);
    }

    // Méthode pour tirer une carte du paquet du joueur
    public Card tirerCarte() {
        if (!paquet.isEmpty()) {
            return paquet.remove(0);
        }
        return null; // Retourne null si plus de cartes
    }

    // Méthode pour augmenter le score du joueur
    public void ajouterPoint() {
        score++;
    }

    // Nouvelle méthode pour vérifier si le joueur a encore des cartes
    public boolean aDesCartes() {
        return !paquet.isEmpty();
    }

    // Méthode pour ajouter plusieurs cartes au paquet du joueur (pour la bataille)
    public void ajouterCartes(List<Card> cartes) {
        paquet.addAll(cartes);
    }

    // Méthode toString pour afficher les informations du joueur
    @Override
    public String toString() {
        return nom + " (score: " + score + ", cartes restantes: " + paquet.size() + ")";
    }
}
