package hi.verkefni.vinnsla;

/******************************************************************************
 *  Nafn    : Svana Björg Birgisdóttir
 *  T-póstur: sbb51@hi.is
 *
 *  Lýsing  : Vinnsluklasi sem geymir stöðuna á bingóspjaldi. Hver reitur hefur heiltölu. Ef
 *  heiltalan er -1 þá er búið að velja reitinn.
 *
 *
 *****************************************************************************/
public class Bingospjald implements BingospjaldInterface {

    // fastar
    private static final int MAX = 75; // stærsta talan á bingóspjaldi
    private static final int MIN = 1;   // minnsta talan á bingóspjaldi
    private static final int AREIT = -1; // talan hefur verið lesin upp

    private final int[][] spjald;   // táknar bingóspjaldið
    private int[][] originalSpjald;


    /**
     * Smíðar bingóspjald af stærðinni n x m.
     * Bingóspjald er táknað sem tvívítt fylki
     *
     * @param n fjöldi lína
     * @param m fjöldi dálka
     */
    public Bingospjald(int n, int m) {
        spjald = new int[n][m];
        originalSpjald = new int[n][m];
    }

    /**
     * Talan á reit (i,j) hefur verið lesin upp. Reiturinn er merktur sem AREIT (-1) í fylkinu.
     * Forskilyrði - (i,j) er innan marka bingóspjaldsins - óþarfi að tékka sérstaklega
     *
     * @param i lína
     * @param j dálkur
     */
    public void aReit(int i, int j) {
        spjald[i][j] = AREIT;
    }

    /**
     * Frumstillir bingóspjald með tölum af handahófi
     *
     * @return skilar fylkinu með gögnum bingóspjaldsins
     */
    public int[][] nyttSpjald() {
        boolean[] erASpjaldi = new boolean[MAX + 1];
        for (int i = 0; i < spjald.length; i++) {// raðir
            for (int j = 0; j < spjald[0].length; j++) {    //dálkar
                int min = (j * 15) + 1;
                int max = (j + 1) * 15;
                int t;
                do {
                    t = nyTala(min, max);
                } while (erASpjaldi[t]);    // ef talan hefur komið fyrir þurfum við nýja random tölu
                erASpjaldi[t] = true;         // talan hefur komið fyrir
                spjald[i][j] = t;
                originalSpjald[i][j] = t;
            }
        }
        spjald[2][2] = 0;
        originalSpjald[2][2] = 0;
        return spjald;
    }


    /**
     * Ný tala af handahófi á bilinu MIN til og með MAX
     *
     * @return tala af handahófi
     */
    private int nyTala(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * @param i röð
     * @param j dálkur
     * @return ákveðinum takka sem -1
     */
    public boolean getAReit(int i, int j) {
        return spjald[i][j] == -1;
    }

    /**
     * Reseta spjaldið
     */
    public void resetSpjald() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                spjald[i][j] = originalSpjald[i][j];
            }
        }
    }


    /**
     * Athugar hvort það er bingó í hornalínum spjaldsins
     *
     * @return true ef það er bingo annars false
     */
    public boolean erBingo() {
        for (int i = 0; i < spjald[0].length; i++) {// raðir
            if (i == 2) {
                continue;
            }
            if (spjald[i][i] != AREIT)  // getum hætt strax og tala hefur ekki verið valin
                break;
            else if (i == 4) {
                return true;
            }
        }
        for (int i = 0; i < spjald[0].length; i++) {
            int j = 4 - i;
            if (i == 2 && j == 2) {
                continue;
            }
            if (spjald[i][j] != AREIT)  // getum hætt strax og tala hefur ekki verið valin
                break;
            else if (j == 0) {
                return true;
            }
        }
        return false;
    }
}
