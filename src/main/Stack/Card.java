package main.Stack;

public class Card {

    private Color Color;
    private Suit Suit;
    private Rank Rank;


    public Card(Suit s, Rank r){

        //defining the color of the cards. If spades or clubs, color is black, else it's Red
        Color c = main.Stack.Color.Black;
        if(s.ordinal()==1 || s.ordinal()==3)
            c = main.Stack.Color.Red;

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
