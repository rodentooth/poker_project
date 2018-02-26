package main.Stack;

public class Card {

    private Color Color;
    private Suit Suit;
    private Rank Rank;


    Card(Color c, Suit s, Rank r){
        this.Color =c;
        this.Suit =s;
        this.Rank =r;
    }

    public main.Stack.Color getColor() {
        return this.Color;
    }

    public main.Stack.Suit getSuit() {
        return this.Suit;
    }

    public main.Stack.Rank getRank() {
        return this.Rank;
    }


    @Override
    public String toString(){

        return "It's a "+this.getSuit() + " " + this.getRank();
    }

}
