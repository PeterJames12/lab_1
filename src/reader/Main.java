package reader;


import java.io.*;
import java.util.Arrays;

public class Main {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {

        howManyWord();

    }

    private static void howManyWord() {

        File file = new File("/");

        int howManyWord = 0;

        try {
            StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new FileReader(file)));
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                    howManyWord++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Кількість слів " + howManyWord);
        masCreate(howManyWord,file);
    }

    private static void masCreate(int howManyWord, File file) {

        String mas[] = new String[howManyWord];
        int index = 0;

        try {
           StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new FileReader(file)));
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                    mas[index] = tokenizer.sval.toLowerCase();
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchUnique(mas);
    }

    private static void searchUnique(String[] mas) {

        Arrays.sort(mas);

        int arr[] = new int[mas.length];

        int count = 1;
        int index = 0;
        int indexArr = 0;

        howManyUnique(mas, arr, count, index, indexArr);

        unique(mas,arr);
    }

    private static void howManyUnique(String[] mas, int[] arr, int count, int index, int indexArr) {

        for (int i = 0; i < mas.length; i++) {
            if (mas[index].equals(mas[i])) {
                arr[indexArr] = count;
                count++;
            }
        }
        if (indexArr < mas.length - 1) {
            howManyUnique(mas, arr, count = 1, index + 1, indexArr + 1);
        }
    }

    private static void unique(String[] mas, int[] arr) {

        int countUnique = 0;

        for (int i = mas.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (mas[j].equals(mas[j + 1])) {
                    mas[j] = "the same";
                    arr[j] = 0;
                }
            }
        }

        for (int i = 0; i < mas.length; i++) {
            if (!mas[i].equals("the same")) {
                countUnique++;
            }
        }
        System.out.println("Унікальних з них " + countUnique);

        System.out.println("А саме:");

        for (int i = 0; i < mas.length; i++) {
            if (!mas[i].equals("0") && arr[i] != 0) {
                System.out.println("Слово " + "'" + mas[i] + "'" + " у тексті зустрічається " + arr[i] + " раз(в) ");
            }
        }
    }
}
