import java.util.*;

/*
 * WAR (Card Game) — with both recursion and loop versions of WAR resolution.
 *
 * Why this exists:
 * - War is a nice demo for recursion because "WAR" can trigger another WAR.
 * - Same rules, repeated until it resolves.
 *
 * Why there is also a loop version:
 * - Some instructors want to see both and compare readability.
 * - Spoiler: recursion matches the game rules more naturally.
 *
 */
public class WarGame {

    // Standard War rule: each player puts down 3 cards face-down during a war.
    private static final int FACE_DOWN_WAR_CARDS = 3;

    // War can go on for a weirdly long time depending on shuffle luck.
    // This cap prevents "forever games" and terminal spam.
    private static final int MAX_ROUNDS = 10_000;

    // Toggle this to switch WAR resolution styles:
    // true  -> recursion
    // false -> while-loop
    private static final boolean USE_RECURSION = true;

    public static void main(String[] args) {

        /*
         * Each player deck is a queue:
         * - draw from the front (pollFirst)
         * - add winnings to the back (addLast)
         *
         * ArrayDeque is fast and works great for queue behavior.
         */
        Deque<Card> p1 = new ArrayDeque<>();
        Deque<Card> p2 = new ArrayDeque<>();

        // Build and shuffle a standard deck.
        List<Card> deck = Deck.standard52();
        Collections.shuffle(deck);

        // Deal alternately so players split the deck evenly.
        for (int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) p1.addLast(deck.get(i));
            else p2.addLast(deck.get(i));
        }

        int round = 0;

        // Main game loop: play until someone runs out of cards or we hit the cap.
        while (!p1.isEmpty() && !p2.isEmpty() && round < MAX_ROUNDS) {
            round++;
            playRound(p1, p2, round);
        }

        // End-of-game reporting (aka "what just happened?")
        System.out.println("\n=== GAME OVER ===");
        if (p1.isEmpty() && p2.isEmpty()) {
            System.out.println("Both players ran out of cards. Reality is questionable.");
        } else if (p1.isEmpty()) {
            System.out.println("Player 2 wins!");
        } else if (p2.isEmpty()) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Round cap reached. Declaring a draw to preserve human lifespans.");
            System.out.println("Player 1 cards: " + p1.size());
            System.out.println("Player 2 cards: " + p2.size());
        }
    }

    /*
     * Plays one normal round of War.
     *
     * Steps:
     * 1) Each player flips a card into the pot.
     * 2) Compare ranks.
     * 3) Winner takes the pot OR tie triggers WAR.
     */
    private static void playRound(Deque<Card> p1, Deque<Card> p2, int round) {

        // The pot holds every card currently being fought over (including war cards).
        List<Card> pot = new ArrayList<>();

        // Draw top card from each player.
        Card c1 = p1.pollFirst();
        Card c2 = p2.pollFirst();

        // Add them to the pot.
        pot.add(c1);
        pot.add(c2);

        System.out.printf("Round %d: P1 %s vs P2 %s%n", round, c1, c2);

        // Compare ranks only. (Suits don't matter in War.)
        int cmp = Integer.compare(c1.rankValue(), c2.rankValue());

        if (cmp > 0) {
            // Player 1 wins the round.
            // awardPot() clears the pot, so capture size BEFORE awarding.
            int potSize = pot.size();
            awardPot(p1, pot);
            System.out.println(" -> Player 1 takes the pot (" + potSize + " cards).");

        } else if (cmp < 0) {
            // Player 2 wins the round.
            int potSize = pot.size();
            awardPot(p2, pot);
            System.out.println(" -> Player 2 takes the pot (" + potSize + " cards).");

        } else {
            // Tie -> WAR
            System.out.println(" -> WAR!");

            int winner;
            if (USE_RECURSION) {
                // Recursion version: war calls itself if tied again.
                winner = resolveWarRecursive(p1, p2, pot, 1);
            } else {
                // Loop version: keep going in a while-loop until resolved.
                winner = resolveWarLoop(p1, p2, pot);
            }

            // Now award the entire pot to the war winner.
            int potSize = pot.size();
            if (winner == 1) {
                awardPot(p1, pot);
                System.out.println(" -> Player 1 wins the war pot (" + potSize + " cards).");
            } else {
                awardPot(p2, pot);
                System.out.println(" -> Player 2 wins the war pot (" + potSize + " cards).");
            }
        }

        // Occasional status update so you can tell it's still alive.
        if (round % 1000 == 0) {
            System.out.println("Status: P1=" + p1.size() + " P2=" + p2.size());
        }
    }

    /*
     * WAR RESOLUTION (RECURSIVE VERSION)
     *
     * Base cases:
     * - Someone can't continue the war -> they lose.
     * - A non-tie comparison happens -> return winner.
     *
     * Recursive case:
     * - Tie again -> same problem, deeper -> call self with depth+1.
     */
    private static int resolveWarRecursive(Deque<Card> p1, Deque<Card> p2, List<Card> pot, int depth) {

        // Each war layer requires 3 face-down cards + 1 face-up card per player.
        int required = FACE_DOWN_WAR_CARDS + 1;

        // If one player can't supply enough cards, they lose.
        // If BOTH can't, decide by who has more remaining, else coin flip (so program ends).
        if (p1.size() < required && p2.size() < required) {
            if (p1.size() > p2.size()) return 1;
            if (p2.size() > p1.size()) return 2;
            return new Random().nextBoolean() ? 1 : 2;
        }
        if (p1.size() < required) return 2;
        if (p2.size() < required) return 1;

        // Add face-down cards to pot.
        for (int i = 0; i < FACE_DOWN_WAR_CARDS; i++) {
            pot.add(p1.pollFirst());
            pot.add(p2.pollFirst());
        }

        // Flip face-up cards and add them to pot.
        Card up1 = p1.pollFirst();
        Card up2 = p2.pollFirst();
        pot.add(up1);
        pot.add(up2);

        System.out.printf("    WAR depth %d: P1 %s vs P2 %s%n", depth, up1, up2);

        int cmp = Integer.compare(up1.rankValue(), up2.rankValue());
        if (cmp > 0) return 1;
        if (cmp < 0) return 2;

        // Tie again -> recurse (same problem, deeper)
        System.out.println("    -> DOUBLE WAR!");
        return resolveWarRecursive(p1, p2, pot, depth + 1);
    }

    /*
     * WAR RESOLUTION (LOOP VERSION)
     *
     * Same rules as recursion version, but uses a while-loop.
     * Requires manual tracking of depth.
     */
    private static int resolveWarLoop(Deque<Card> p1, Deque<Card> p2, List<Card> pot) {

        int depth = 1;

        while (true) {

            int required = FACE_DOWN_WAR_CARDS + 1;

            if (p1.size() < required && p2.size() < required) {
                if (p1.size() > p2.size()) return 1;
                if (p2.size() > p1.size()) return 2;
                return new Random().nextBoolean() ? 1 : 2;
            }
            if (p1.size() < required) return 2;
            if (p2.size() < required) return 1;

            for (int i = 0; i < FACE_DOWN_WAR_CARDS; i++) {
                pot.add(p1.pollFirst());
                pot.add(p2.pollFirst());
            }

            Card up1 = p1.pollFirst();
            Card up2 = p2.pollFirst();
            pot.add(up1);
            pot.add(up2);

            System.out.printf("    WAR depth %d: P1 %s vs P2 %s%n", depth, up1, up2);

            int cmp = Integer.compare(up1.rankValue(), up2.rankValue());
            if (cmp > 0) return 1;
            if (cmp < 0) return 2;

            System.out.println("    -> DOUBLE WAR!");
            depth++;
        }
    }

    /*
     * Moves all cards from pot to the winner's deck.
     *
     * Important:
     * - pot is cleared here.
     * - that's why we capture potSize BEFORE calling awardPot.
     */
    private static void awardPot(Deque<Card> winner, List<Card> pot) {
        for (Card c : pot) {
            winner.addLast(c);
        }
        pot.clear();
    }

    // ----- Card / Deck stuff (supporting classes) -----

    // Suits exist for display. They don't change rank comparison.
    enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    // Rank values are what we compare for the game.
    enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        final int value;
        Rank(int value) { this.value = value; }
    }

    // Card is just rank + suit. It's immutable so we don't accidentally mutate cards.
    static class Card {
        final Rank rank;
        final Suit suit;

        Card(Rank rank, Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }

        int rankValue() {
            return rank.value;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    // Deck builder: makes a standard 52-card list.
    static class Deck {
        static List<Card> standard52() {
            List<Card> cards = new ArrayList<>(52);
            for (Suit s : Suit.values()) {
                for (Rank r : Rank.values()) {
                    cards.add(new Card(r, s));
                }
            }
            return cards;
        }
    }
}