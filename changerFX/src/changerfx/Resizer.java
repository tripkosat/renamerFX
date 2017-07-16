package changerfx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


/**
 * @author Adys
 *
 */
//Προσθήκη subfolders
//standard length
//metatropi selected files only
public class Resizer {

    HashMap<String, String> undo;

    Resizer() {
        undo = new HashMap<>();
    }

    public ArrayList<String> arrayOfFileNames(String foldername) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> ar = new ArrayList<>();
        for (File l : listOfFiles) {
            if (l.isFile() || l.isDirectory()) {
                String s = l.getName();
                ar.add(s);
            }
        }
        return ar;
    }

    public void UpperToLower(String foldername, int category) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                s = s.toLowerCase();
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                s = s.toLowerCase();
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
        }
    }

    public void LowerToUpper(String foldername, int category) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                s = s.toUpperCase();
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                s = s.toUpperCase();
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
        }
    }

    public void Capitalize(String foldername, int category) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();

        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                s = caps(s);
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                s = caps(s);
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
        }
    }

    private String caps(String s) {
        if (s.length() == 0) {
            return s;
        }
        String newString = "";
        String[] words = s.split(" ");
        int i = 1;
        for (String s1 : words) {
            if (i == words.length) {
                newString = newString + s1.substring(0, 1).toUpperCase() + s1.substring(1).toLowerCase();
            } else {
                newString = newString + s1.substring(0, 1).toUpperCase() + s1.substring(1).toLowerCase() + " ";
            }
            i++;
        }
        return newString;
    }

    public void removeAWord(String foldername, String word, String newword, int category) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();

        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                if (s.contains(word)) {
                    s = s.replace(word, newword);
                    updateBackUpList(l.getName(), s);
                    l.renameTo(new File(foldername + "\\" + s));
                }
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                if (s.contains(word)) {
                    s = s.replace(word, newword);
                    updateBackUpList(l.getName(), s);
                    l.renameTo(new File(foldername + "\\" + s));
                }
            }
        }
    }

    /**
     *
     * @param foldername
     * @param word
     * @param x:to the start if true, to the end if false
     * @param category
     */
    public void addAWord(String foldername, String word, boolean x, int category) {
        File folder = new File(foldername);

        File[] listOfFiles = folder.listFiles();

        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                if (x == true) {
                    s = word + s;
                } else {
                    if (l.isFile()) {
                        int i = s.lastIndexOf(".");
                        s = s.substring(0, i) + word + s.substring(i, s.length());
                    } else {
                        s = s + word;
                    }
                }
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                if (x == true) {
                    s = word + s;
                } else {
                    s = s + word;
                }
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
        }
    }

    public void greekToGreeklish(String foldername, int category) {
        File folder = new File(foldername);
        Alphabet al = new Alphabet();
        File[] listOfFiles = folder.listFiles();

        for (File l : listOfFiles) {

            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                s = al.greeklisher(s);

                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                s = al.greeklisher(s);
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }
        }
    }

    private void updateBackUpList(String oldKey, String newKey) {
        if (!oldKey.equals(newKey)) {
            String value = undo.get(oldKey);
            undo.remove(oldKey);
            undo.put(newKey, value);
        }
    }

    public void printUndo() {
        System.out.println("LAST UPDATE");
        for (Entry<String, String> e : undo.entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            System.out.println(key + " -> " + value);
        }
    }

    public void firstBackup(String foldername) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> ar = new ArrayList<>();
        for (File l : listOfFiles) {
            if (l.isFile() || l.isDirectory()) {
                undo.put(l.getName(), l.getName());
            }
        }
    }

    public void undoAll(String foldername, int category) {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        for (File l : listOfFiles) {

            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                s = undo.get(s);
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }

            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                s = undo.get(s);
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
            }

        }
    }

    /**
     *
     * @param foldername
     *
     * @param x:to the start if true, to the end if false
     * @param category
     */
    public void addANumber(String foldername, boolean x, int category) {
        File folder = new File(foldername);

        File[] listOfFiles = folder.listFiles();
        int number = 0;
        for (File l : listOfFiles) {
            if (l.isFile() && (category == 1 || category == 3)) {
                String s = l.getName();
                if (x == true) {
                    s = number + " " + s;
                } else {
                    if (l.isFile()) {
                        int i = s.lastIndexOf(".");
                        s = s.substring(0, i) + " " + number + s.substring(i, s.length());
                    } else {
                        s = s + " " + number;
                    }
                }
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
                number++;
            }
            if (l.isDirectory() && (category == 2 || category == 3)) {
                String s = l.getName();
                if (x == true) {
                    s = number + " " + s;
                } else {
                    s = s + " " + number;
                }
                updateBackUpList(l.getName(), s);
                l.renameTo(new File(foldername + "\\" + s));
                number++;
            }
        }
    }

}
