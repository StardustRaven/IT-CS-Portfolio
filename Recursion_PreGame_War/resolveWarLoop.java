// Name: Summer Isakson
// Lab: Week 6 – Game of War - Loops
// Date: 02/23/2026
// 
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

        // Still tied → loop again
        depth++;
        System.out.println("    -> DOUBLE WAR!");
    }
}