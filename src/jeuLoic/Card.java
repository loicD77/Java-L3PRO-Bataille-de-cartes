package jeuLoic;

public class Card {
   // Dans ce cas c'est les deux tableaux définissant toutes les couleurs et valeurs possibles des cartes pour la bataille de cartes
    public static String[] COLORS = {"Coeur", "Trèfle", "Pic", "Carreau"};
    public static String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};

    // Ici ce sont les attributs : 1er attribut qui est la couleur (ici color) et le 2ème attribut qui est la valeur (ici value)
    private String color;
    private int value;

    // Mise en place du constructeur pour créer une carte avec sa propre couleur et sa propre valeur, définient pas des attributs qui leurs sont propres
    public Card(String color, int value) {
        this.color = color;
        this.value = value;
    }

    // Accesseurs (getters)

    // Ce getter permet donc d'obtenir, de retourner la couleur spécifique de chaque carte
    public String getColor() {
        return color;
    }

    //Ce getter permet donc d'obtenir, de retourner la valeur spécifique de chaque carte
    public int getValue() {
        return value;
    }

    // Modificateurs (setters)

    //Permet de modifier la couleur des cartes ("Coeur", "Trèfle", "Pic", "Carreau"}
    public void setColor(String color) {
        this.color = color;
    }


    // Permet de modifier la veleur des cartes ("2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As")
    public void setValue(int value) {
        this.value = value;
    }

    // Méthode pour comparer deux cartes (basée sur la valeur d'une carte et de l'autre)
    public int compare(Card other) {
        return Integer.compare(this.value, other.value);
    }

    // Méthode toString pour afficher la carte
    @Override //La méthode que j'ai définie dans ta classe remplace ou « redéfinit » une méthode héritée.
    public String toString() { // La méthode "toString" retourne donc la valeur ("Coeur", "Trèfle", "Pic", "Carreau") et la couleur ("2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As") de chaque carte
        return VALUES[value] + " de " + color;
    }
}


