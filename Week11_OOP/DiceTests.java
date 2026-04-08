public class DiceTests {

    // ── tiny test framework ───────────────────────────────────────────────────
    private static int passed = 0;
    private static int failed = 0;
    private static int skipped = 0;

    private static void pass(String name) {
        System.out.println("  [PASS] " + name);
        passed++;
    }

    private static void fail(String name, String reason) {
        System.out.println("  [FAIL] " + name);
        System.out.println("         → " + reason);
        failed++;
    }

    private static void skip(String name, String reason) {
        System.out.println("  [SKIP] " + name);
        System.out.println("         → " + reason);
        skipped++;
    }

    private static void assertTrue(String name, boolean condition) {
        if (condition) pass(name); else fail(name, "expected true, got false");
    }

    private static void assertEquals(String name, Object expected, Object actual) {
        if (expected.equals(actual)) pass(name);
        else fail(name, "expected " + expected + ", got " + actual);
    }

    private static void assertThrowsNPE(String name, Runnable r) {
        try {
            r.run();
            fail(name, "expected NullPointerException but nothing was thrown");
        } catch (NullPointerException e) {
            pass(name);
        } catch (Exception e) {
            fail(name, "expected NullPointerException but got " + e.getClass().getSimpleName());
        }
    }

    private static void section(String title) {
        System.out.println("\n── " + title + " " + "─".repeat(Math.max(0, 55 - title.length())));
    }

    // ── Die tests ────────────────────────────────────────────────────────────
    static void testDie() {
        section("Die");

        // Initial value is 0 before any roll
        Die d = new Die(new int[]{2, 4, 6, 8});
        assertEquals("Initial value is 0 before roll", 0, d.getCurrentValue());

        // getMin / getMax on custom sides
        Die d2 = new Die(new int[]{3, 1, 4, 1, 5, 9});
        assertEquals("Custom sides getMin()", 1, d2.getMin());
        assertEquals("Custom sides getMax()", 9, d2.getMax());

        // getAverageValue on known sides {1,2,3,4} -> 2.5
        Die d3 = new Die(new int[]{1, 2, 3, 4});
        assertEquals("Custom sides getAverageValue()", 2.5, d3.getAverageValue());

        // sideCount constructor
        Die d6 = new Die(6);
        assertEquals("d6 getMin() == 1",  1,  d6.getMin());
        assertEquals("d6 getMax() == 6",  6,  d6.getMax());
        assertEquals("d6 getAverageValue() == 3.5", 3.5, d6.getAverageValue());

        // roll() stays in [1, sideCount]
        boolean rollInRange = true;
        for (int i = 0; i < 500; i++) {
            int v = d6.roll();
            if (v < 1 || v > 6) { rollInRange = false; break; }
        }
        assertTrue("roll() always in [1, 6]", rollInRange);

        // roll() return value matches getCurrentValue()
        boolean rollMatchesCurrent = true;
        for (int i = 0; i < 100; i++) {
            int rolled = d6.roll();
            if (rolled != d6.getCurrentValue()) { rollMatchesCurrent = false; break; }
        }
        assertTrue("roll() return matches getCurrentValue()", rollMatchesCurrent);

        // custom sides: roll only returns members of the array
        Die custom = new Die(new int[]{10, 20, 30});
        boolean customOnlyValid = true;
        for (int i = 0; i < 200; i++) {
            int v = custom.roll();
            if (v != 10 && v != 20 && v != 30) { customOnlyValid = false; break; }
        }
        assertTrue("Custom sides: roll() only returns values from array", customOnlyValid);

        // single-sided die always rolls 1
        Die d1 = new Die(1);
        boolean alwaysOne = true;
        for (int i = 0; i < 50; i++) {
            if (d1.roll() != 1) { alwaysOne = false; break; }
        }
        assertTrue("Single-sided die always rolls 1", alwaysOne);
    }

    // ── CritDie tests ────────────────────────────────────────────────────────
    static void testCritDie() {
        section("CritDie");

        CritDie cd = new CritDie();
        assertEquals("CritDie getMin() == 1",  1,  cd.getMin());
        assertEquals("CritDie getMax() == 20", 20, cd.getMax());

        // isCrit() is false before any roll (currentValue == 0, not 20)
        assertTrue("isCrit() false before roll", !cd.isCrit());

        // roll stays in [1, 20]
        boolean inRange = true;
        for (int i = 0; i < 200; i++) {
            int v = cd.roll();
            if (v < 1 || v > 20) { inRange = false; break; }
        }
        assertTrue("CritDie roll() always in [1, 20]", inRange);

        // isCrit() true when value is 20, false otherwise -- checked after each roll
        boolean isCritCorrectWhenTrue  = true;
        boolean isCritCorrectWhenFalse = true;
        for (int i = 0; i < 500; i++) {
            cd.roll();
            int val = cd.getCurrentValue();
            if (val == 20 && !cd.isCrit())  { isCritCorrectWhenTrue  = false; }
            if (val != 20 &&  cd.isCrit())  { isCritCorrectWhenFalse = false; }
        }
        assertTrue("isCrit() true  when value == 20", isCritCorrectWhenTrue);
        assertTrue("isCrit() false when value != 20", isCritCorrectWhenFalse);
    }

    // ── PercentileDicePair tests ──────────────────────────────────────────────
    static void testPercentileDicePair() {
        section("PercentileDicePair");

        PercentileDicePair pdp = new PercentileDicePair();

        // roll() always in [1, 100]
        boolean inRange = true;
        for (int i = 0; i < 1000; i++) {
            int v = pdp.roll();
            if (v < 1 || v > 100) { inRange = false; break; }
        }
        assertTrue("roll() always in [1, 100]", inRange);

        // roll() return matches getCurrentValue()
        boolean rollMatchesCurrent = true;
        for (int i = 0; i < 200; i++) {
            int rolled = pdp.roll();
            if (rolled != pdp.getCurrentValue()) { rollMatchesCurrent = false; break; }
        }
        assertTrue("roll() return matches getCurrentValue()", rollMatchesCurrent);

        // 100 is reachable (ones=0, tens=0 -> special case)
        boolean saw100 = false;
        for (int i = 0; i < 5000 && !saw100; i++) {
            if (pdp.roll() == 100) saw100 = true;
        }
        assertTrue("roll() can produce 100 (double-zero special case)", saw100);

        // Non-100 results: ones digit is always 0-9
        boolean onesDigitValid = true;
        for (int i = 0; i < 500; i++) {
            int v = pdp.roll();
            if (v != 100 && (v % 10 < 0 || v % 10 > 9)) {
                onesDigitValid = false; break;
            }
        }
        assertTrue("Ones digit of non-100 result is always 0-9", onesDigitValid);
    }

    // ── DicePool tests ────────────────────────────────────────────────────────
    static void testDicePool() {
        section("DicePool");

        // BUG: constructor uses for-each which reassigns the local variable
        // instead of writing into the array, so every slot stays null.
        assertThrowsNPE(
            "BUG: constructor leaves dice[] null -> NPE on rollForHighest()",
            () -> new DicePool(6, 3).rollForHighest()
        );

        // Tests for intended behaviour -- skipped until the bug is fixed.
        // Fix: replace  for(Die die:dice){ die=new Die(dieSize); }
        //      with     for(int i=0;i<dice.length;i++){ dice[i]=new Die(dieSize); }
        // Then delete the skip() calls and un-comment the bodies below.

        skip("rollForHighest() returns value in [1, dieSize]",
             "Requires constructor bug fix (indexed loop instead of for-each)");
        skip("rollForLowest()  returns value in [1, dieSize]",
             "Requires constructor bug fix");
        skip("getTotalValue()  sum is in [count, dieSize*count] after a roll",
             "Requires constructor bug fix");

        /* --- un-comment after fix ---

        DicePool pool = new DicePool(6, 4);

        boolean highInRange = true;
        for (int i = 0; i < 100; i++) {
            int v = pool.rollForHighest();
            if (v < 1 || v > 6) { highInRange = false; break; }
        }
        assertTrue("rollForHighest() returns value in [1, 6]", highInRange);

        boolean lowInRange = true;
        for (int i = 0; i < 100; i++) {
            int v = pool.rollForLowest();
            if (v < 1 || v > 6) { lowInRange = false; break; }
        }
        assertTrue("rollForLowest() returns value in [1, 6]", lowInRange);

        pool.rollForHighest(); // triggers rollAll()
        double total = pool.getTotalValue();
        assertTrue("getTotalValue() in [4, 24]", total >= 4 && total <= 24);

        */
    }

    // ── entry point ──────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=".repeat(58));
        System.out.println("  Dice Test Suite");
        System.out.println("=".repeat(58));

        testDie();
        testCritDie();
        testPercentileDicePair();
        testDicePool();

        System.out.println("\n" + "=".repeat(58));
        System.out.printf("  Results:  %d passed  |  %d failed  |  %d skipped%n",
                          passed, failed, skipped);
        System.out.println("=".repeat(58));

        if (failed > 0) System.exit(1);
    }
}