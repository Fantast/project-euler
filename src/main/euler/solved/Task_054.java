package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.StringTokenizer;


//Answer : 376
public class Task_054 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_054());
        Logger.close();
    }

    String hands[] =
            {
            "8C TS KC 9H 4S 7D 2S 5D 3S AC",
            "5C AD 5D AC 9C 7C 5H 8D TD KS",
            "3H 7H 6S KC JS QH TD JC 2D 8S",
            "TH 8H 5C QS TC 9H 4D JC KS JS",
            "7C 5H KC QH JD AS KH 4C AD 4S",
            "5H KS 9C 7D 9H 8D 3S 5D 5C AH",
            "6H 4H 5C 3H 2H 3S QH 5S 6S AS",
            "TD 8C 4H 7C TC KC 4C 3H 7S KS",
            "7C 9C 6D KD 3H 4C QS QC AC KH",
            "JC 6S 5H 2H 2D KD 9D 7C AS JS"
            };
    public void solving() {
        int res = 0;

        for (String hand : hands) {
            if ( isWinning(hand) ) ++res;
        }

        System.out.println(res);
    }

    private boolean isWinning(String hand) {
        Hand h1 = new Hand(hand.substring(0, 15));
        Hand h2 = new Hand(hand.substring(15));

        return h1.compareTo(h2) > 0;
    }

    private class Hand implements Comparable<Hand> {
        private int ns[] = new int[13];
        private int colors[] = new int[4];
        private boolean isFlush = false;
        private boolean isStraight = false;
        private boolean isFour = false;
        private boolean isThree = false;
        private int pairs = 0;

        private int baseval = 0;

        public Hand(String hand) {
            StringTokenizer stok = new StringTokenizer(hand);
            for (int i = 0; i < 5; ++i) {
                String s = stok.nextToken();
                ++ns[ getNumber(s.charAt(0)) ];
                int color = getColor(s.charAt(1));
                ++colors[ color ];
                if (colors[color] == 5) isFlush = true;
            }

            int cons = 0;
            for (int i = 0; i < 13; ++i) {
                if (ns[i] == 1) ++cons;
                else cons = 0;

                if (cons == 5) isStraight = true;

                if (ns[i] == 2) {
                    ++pairs;
                } else if (ns[i] == 3) {
                    isThree = true;
                } else if (ns[i] == 4) {
                    isFour = true;
                }
            }

            baseval = getBaseVal();
        }

        private int getBaseVal() {
            if (isStraight && isFlush) return 0;
            if (isFour) return 1;
            if (isThree && pairs==1) return 2;
            if (isFlush) return 3;
            if (isStraight) return 4;
            if (isThree) return 5;
            if (pairs == 2) return 6;
            if (pairs == 1) return 7;

            return 8;
        }

        private int getColor(char c) {
            if (c == 'C') return 0;
            if (c == 'S') return 1;
            if (c == 'D') return 2;
            if (c == 'H') return 3;
            return -1;
        }

        private int getNumber(char c) {
            if ('2' <= c && c <= '9' ) return c - '2';
            if (c == 'T') return 8;
            if (c == 'J') return 9;
            if (c == 'Q') return 10;
            if (c == 'K') return 11;
            if (c == 'A') return 12;

            return -1;
        }

        public int compareTo(Hand h) {
            if (baseval > h.baseval) return -1;
            if (baseval < h.baseval) return 1;

            if (baseval==0 || baseval==4 || baseval==3 || baseval==8) return compareHighest(h);
            if (baseval==1) return compareFour(h);
            if (baseval==2 || baseval==5) return compareThree(h);
            if (baseval==6) return compareTwoPairs(h);
            if (baseval==7) return comparePair(h);

            return -2;
        }

        private int comparePair(Hand h) {
            for (int i = 12; i >= 0; --i) {
                if (ns[i]==2 && h.ns[i]!=2) return 1;
                if (ns[i]!=2 && h.ns[i]==2) return -1;
            }

            return compareHighest(h);
        }

        private int compareTwoPairs(Hand h) {
            int card1 = -1, card2 = -1;
            for (int i = 12; i >= 0; --i) {
                if (ns[i]==2 && h.ns[i]!=2) return 1;
                if (ns[i]!=2 && h.ns[i]==2) return -1;
                if (ns[i] == 1) card1 = i;
                if (h.ns[i] == 1) card2 = i;
            }

            return Double.compare(card1, card2);
        }

        private int compareThree(Hand h) {
            int card1 = -1, card2 = -1;
            int card12 = -1, card22 = -1;

            for (int i = 12; i >= 0; --i) {
                if (ns[i]==3 && h.ns[i]!=3) return 1;
                if (ns[i]!=3 && h.ns[i]==3) return -1;
                if (ns[i] == 1) {
                    if (card1 == -1) card1 = i;
                    else card12 = i;
                }
                if (ns[i] == 2) {
                    card1 = card12 = i;
                }

                if (h.ns[i] == 1) {
                    if (card2 == -1) card2 = i;
                    else card22 = i;
                }
                if (h.ns[i] == 2) {
                    card2 = card22 = i;
                }
            }

            if (Double.compare( card1, card2 ) != 0) return Double.compare( card1, card2 );
            return Double.compare(card12, card22);
        }

        private int compareFour(Hand h) {
            int card1 = -1, card2 = -1;
            for (int i = 12; i >= 0; --i) {
                if (ns[i]==4 && h.ns[i]!=4) return 1;
                if (ns[i]!=4 && h.ns[i]==4) return -1;
                if (ns[i] == 1) card1 = i;
                if (h.ns[i] == 1) card2 = i;
            }

            return Double.compare(card1, card2);
        }

        private int compareHighest(Hand h) {
            for (int i = 12; i >= 0; --i) {
                if (ns[i]!=0 && h.ns[i]==0) return 1;
                if (ns[i]==0 && h.ns[i]!=0) return -1;
            }
            return 0;
        }
    }
}
