/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changerfx;

/**
 *
 * @author Adys
 */
public class Alphabet {

    public String[][] ar;

    Alphabet() {
        ar = new String[24][2];

        ar[0][0] = "α";
        ar[0][1] = "a";
        ar[1][0] = "β";
        ar[1][1] = "b";
        ar[2][0] = "γ";
        ar[2][1] = "g";
        ar[3][0] = "δ";
        ar[3][1] = "d";
        ar[4][0] = "ε";
        ar[4][1] = "e";
        ar[5][0] = "ζ";
        ar[5][1] = "z";
        ar[6][0] = "η";
        ar[6][1] = "h";
        ar[7][0] = "θ";
        ar[7][1] = "th";
        ar[8][0] = "ι";
        ar[8][1] = "i";
        ar[9][0] = "κ";
        ar[9][1] = "k";
        ar[10][0] = "λ";
        ar[10][1] = "l";
        ar[11][0] = "μ";
        ar[11][1] = "m";
        ar[12][0] = "ν";
        ar[12][1] = "n";
        ar[13][0] = "ξ";
        ar[13][1] = "ks";
        ar[14][0] = "ο";
        ar[14][1] = "o";
        ar[15][0] = "π";
        ar[15][1] = "p";
        ar[16][0] = "ρ";
        ar[16][1] = "r";
        ar[17][0] = "σ";
        ar[17][1] = "s";
        ar[18][0] = "τ";
        ar[18][1] = "t";
        ar[19][0] = "υ";
        ar[19][1] = "u";
        ar[20][0] = "φ";
        ar[20][1] = "f";
        ar[21][0] = "χ";
        ar[21][1] = "x";
        ar[22][0] = "ψ";
        ar[22][1] = "ps";
        ar[23][0] = "ω";
        ar[23][1] = "w";

    }

    public String greeklisher(String s) {

        String s2 = "";
        for (int i = 0; i < s.length(); i++) {
            s2 = s2 + conv(s.substring(i, i + 1));
        }
        return s2;
    }

    private String conv(String a) {
        a = convertor(a);
        for (int i = 0; i < 24; i++) {
            if (ar[i][0] == null ? a == null : ar[i][0].equals(a)) {
                return ar[i][1];
            }
        }

        return "" + a;
    }

    private String convertor(String word) {
        word = word.toUpperCase();
        word = word.toLowerCase();
        word = word.replace("ά", "α");
        word = word.replace("έ", "ε");
        word = word.replace("ή", "η");
        word = word.replace("ί", "ι");
        word = word.replace("ΐ", "ι");
        word = word.replace("ϊ", "ι");
        word = word.replace("ό", "ο");
        word = word.replace("ύ", "υ");
        word = word.replace("ϋ", "υ");
        word = word.replace("ΰ", "υ");
        word = word.replace("ώ", "ω");
        word = word.replace(",", "");
        return word;
    }

}
