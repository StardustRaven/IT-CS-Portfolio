// Name: Summer Isakson
// Lab: Week 6 – Game of War - Recursive
// Date: 02/23/2026
// 
import java.util.*;

/*
 * WAR (Card Game)
 *
 * This program simulates the card game War.
 * Recursion is used specifically to handle repeated "war" situations,
 * where the same rules must be applied again and again until resolved.
 *
 * Written while tired, but still correct.
 */
public class WarGame {

    // Number of face-down cards placed during a war.
    // This is part of the standard War rules.
    private static final int FACE_DOWN_WAR_CARDS = 3;

    // War can take FOREVER depending on shuffle luck.
    // This cap prevents infinite-looking runs and massive console spam.
    private static final int MAX_ROUNDS = 10_000;

    public static void main(String[] args) {

        // Each player's deck is a queue:
        // draw from the front, add winnings to the back.
        Deque<Card> p1 = new ArrayDeque<>();
        Deque<Card> p2 = new ArrayDeque<>();

        // Build and shuffle a standard 52-card deck.
        List<Card> deck = Deck.standard52();
        Collections.shuffle(deck);

        // Deal cards alternately so both players get 26 cards.
        for (int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0)
                p1.addLast(deck.get(i));
            else
                p2.addLast(deck.get(i));
        }

        int round = 0;

        // Game continues while:
        // - both players still have cards
        // - round cap has not been reached
        while (!p1.isEmpty() && !p2.isEmpty() && round < MAX_ROUNDS) {
            round++;
            playRound(p1, p2, round);
        }

        // End-of-game reporting
        System.out.println("\n=== GAME OVER ===");

        if (p1.isEmpty() && p2.isEmpty()) {
            // Extremely rare edge case, but technically possible.
            System.out.println("Somehow both players ran out of cards. Reality is questionable.");
        } else if (p1.isEmpty()) {
            System.out.println("Player 2 wins!");
        } else if (p2.isEmpty()) {
            System.out.println("Player 1 wins!");
        } else {
            // Round cap reached before a winner.
            System.out.println("Round cap reached. Declaring a draw to preserve human sanity.");
            System.out.println("Player 1 cards: " + p1.size());
            System.out.println("Player 2 cards: " + p2.size());
        }
    }

    /*
     * Plays one normal round of War.
     *
     * At minimum:
     * - 1 card from each player enters the pot.
     * - The pot grows if a war occurs.
     * - The winner of the round (or war) takes the entire pot.
     */
    private static void playRound(Deque<Card> p1, Deque<Card> p2, int round) {

        // Pot holds all cards currently being fought over.
        List<Card> pot = new ArrayList<>();

        // Draw top card from each player.
        Card c1 = p1.pollFirst();
        Card c2 = p2.pollFirst();

        // Add drawn cards to the pot.
        pot.add(c1);
        pot.add(c2);

        System.out.printf("Round %d: P1 %s vs P2 %s%n", round, c1, c2);

        // Compare card ranks only (suits are irrelevant in War).
        int cmp = Integer.compare(c1.rankValue(), c2.rankValue());

        if (cmp > 0) {
            // Player 1 wins the round.
            // potSize must be captured BEFORE awardPot clears the pot.
            int potSize = pot.size();
            awardPot(p1, pot);
            System.out.println(" -> Player 1 takes the pot (" + potSize + " cards).");

        } else if (cmp < 0) {
            // Player 2 wins the round.
            int potSize = pot.size();
            awardPot(p2, pot);
            System.out.println(" -> Player 2 takes the pot (" + potSize + " cards).");

        } else {
            // Tie -> WAR (this is where recursion lives)
            System.out.println(" -> WAR!");
            int winner = resolveWar(p1, p2, pot, 1);

            int potSize = pot.size();
            if (winner == 1) {
                awardPot(p1, pot);
                System.out.println(" -> Player 1 wins the war pot (" + potSize + " cards).");
            } else {
                awardPot(p2, pot);
                System.out.println(" -> Player 2 wins the war pot (" + potSize + " cards).");
            }
        }

        // Print occasional status so the console doesn’t melt.
        if (round % 1000 == 0) {
            System.out.println("Status: P1=" + p1.size() + " P2=" + p2.size());
        }
    }

    /*
     * Resolves a WAR using recursion.
     *
     * Why recursion works here:
     * - A war can lead to another war.
     * - The rules do not change.
     * - The problem is the same, just deeper.
     *
     * Base cases:
     * - One player cannot continue.
     * - A non-tie comparison occurs.
     */
    private static int resolveWar(Deque<Card> p1, Deque<Card> p2, List<Card> pot, int depth) {

        // Each player needs 4 cards to perform one war layer.
        int required = FACE_DOWN_WAR_CARDS + 1;

        // If someone cannot continue the war, they lose.
        if (p1.size() < required && p2.size() < required) {
            // If both are short, decide by remaining card count or coin flip.
            if (p1.size() > p2.size()) return 1;
            if (p2.size() > p1.size()) return 2;
            return new Random().nextBoolean() ? 1 : 2;
        }
        if (p1.size() < required) return 2;
        if (p2.size() < required) return 1;

        // Add face-down cards to the pot.
        for (int i = 0; i < FACE_DOWN_WAR_CARDS; i++) {
            pot.add(p1.pollFirst());
            pot.add(p2.pollFirst());
        }

        // Draw face-up cards.
        Card up1 = p1.pollFirst();
        Card up2 = p2.pollFirst();
        pot.add(up1);
        pot.add(up2);

        System.out.printf("    WAR depth %d: P1 %s vs P2 %s%n", depth, up1, up2);

        int cmp = Integer.compare(up1.rankValue(), up2.rankValue());
        if (cmp > 0) return 1;
        if (cmp < 0) return 2;

        // Tie again -> same problem, deeper.
        System.out.println("    -> DOUBLE WAR!");
        return resolveWar(p1, p2, pot, depth + 1);
    }

    /*
     * Gives all cards in the pot to the winner.
     * Clears the pot afterward so it is not reused accidentally.
     */
    private static void awardPot(Deque<Card> winner, List<Card> pot) {
        for (Card c : pot) {
            winner.addLast(c);
        }
        pot.clear();
    }

    // --- Card / Deck helpers below ---