package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DosyaIslemleri {

    static String path;
    static String klasor = "dosyalar";

    public static void dosyayaYaz(ObservableList list, String p) {
        path = System.getProperty("user.dir") + "/" + klasor + "/" + p + ".txt";
        try {
            File file = new File(path);
            if (!file.exists()) {
                File yol = new File(klasor);
                if (!yol.exists()) {
                    yol.mkdir();
                }
                file.createNewFile();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        try (BufferedWriter yazici = new BufferedWriter(new FileWriter(path))) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                yazici.write(list.get(i).toString());
                yazici.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedReader dosyaGetir(String dosya) {
        path = System.getProperty("user.dir") + "/" + klasor + "/" + dosya + ".txt";
        try {
            File file = new File(path);
            if (!file.exists()) {
                File yol = new File(klasor);
                if (!yol.exists()) {
                    yol.mkdir();
                }
                file.createNewFile();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        try {
            return new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList dosyadanOku(String p) {
        path = p;
        ObservableList<Object> geciciList = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String oAnkiSatir;
            while ((oAnkiSatir = br.readLine()) != null) {
                geciciList.add(oAnkiSatir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geciciList;
    }

}
