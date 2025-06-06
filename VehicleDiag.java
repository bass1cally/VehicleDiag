import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class VehicleDiag extends DiagBase {

    private static VehicleDiag instance;
    private static LocalDate creationDate;
    private ArrayList<DiagBase> dataList;
    private ArrayList<String> errorDataList;
    private ArrayList<String> correctDataList;
    private int correctCounter = 0;
    private int errorCounter = 0;

    // Private Konstruktor für Singleton Pattern
    private VehicleDiag() {
        this.dataList = new ArrayList<>();
        creationDate = LocalDate.now();
        this.correctDataList = new ArrayList<>();
        this.errorDataList = new ArrayList<>();
    }

    // Singleton getInstance Methode mit Tageskontrolle
    public static VehicleDiag getInstance() {
        LocalDate today = LocalDate.now();
        // Wenn keine Instanz existiert oder der Tag gewechselt hat, neue Instanz erstellen
        if (instance == null || !creationDate.equals(today)) {
            instance = new VehicleDiag();
            creationDate = today;
            System.out.println("Neue VehicleDiag Instanz für " + creationDate + " erstellt.");
        }
        return instance;
    }

    public String getAllData() {
        return dataList.toString();
    }

    public String getFalseData() {
        String result = "Fehlerhafte Daten:\n";
        // Listen leeren falls sie schon befüllt sind
        errorDataList.clear();
        int errorCounter = 0;
        for (DiagBase data : dataList) {
            if (data.error) {
                errorDataList.add(data.summary());
                errorCounter++;
            }
        }

        result = errorDataList.toString() + "\nAnzahl Fehler: " + errorCounter;
        return result;
    }

    public String getCorrectData() {
        String result = "Korrekte Daten:\n";

        // Listen leeren falls sie schon befüllt sind
        correctDataList.clear();
        for (DiagBase data : dataList) {
            if (!data.error) {
                correctDataList.add(data.summary());
                correctCounter++;
            }
        }
        result = correctDataList.toString() + "\nAnzahl Korrekte: " + correctCounter;
        return result;
    }

    // Methode zum Befüllen der Listen ohne String-Ausgabe
    public void fillLists() {
        errorDataList.clear();
        correctDataList.clear();

        for (DiagBase data : dataList) {
            if (data.error) {
                errorDataList.add(data.id);
            } else {
                correctDataList.add(data.id);
            }
        }
    }

    public void getFalseDataSorted() {
        // Erst die Listen befüllen
        fillLists();
        if (errorDataList.isEmpty()) {
            System.out.println("Keine fehlerhaften Daten gefunden!");
            return;
        }
        System.out.println("Anzahl fehlerhafte Daten: " + errorDataList.size());
        // Nach Typ sortieren
        for (int i = 0; i < errorDataList.size() - 1; i++) {
            for (int j = 0; j < errorDataList.size() - 1 - i; j++) {
                String type1 = getTypeFromId(errorDataList.get(j));
                String type2 = getTypeFromId(errorDataList.get(j + 1));
                if (type1.compareTo(type2) > 0) {
                    // Elemente tauschen
                    String temp = errorDataList.get(j);
                    errorDataList.set(j, errorDataList.get(j + 1));
                    errorDataList.set(j + 1, temp);
                }
            }
        }
        // Gruppiert nach Typ ausgeben
        System.out.println("\nNach Typ sortierte fehlerhafte Daten IDs:");
        String currentType = "";
        for (String id : errorDataList) {
            String dataType = getTypeFromId(id);
            if (!dataType.equals(currentType)) {
                System.out.println("\n--- " + dataType + " ---");
                currentType = dataType;
            }
            System.out.println(id);
        }
    }

    public void getCorrectDataSorted() {
        // Erst die Listen befüllen
        fillLists();

        if (correctDataList.isEmpty()) {
            System.out.println("Keine korrekten Daten gefunden!");
            return;
        }

        System.out.println("Anzahl korrekte Daten: " + correctDataList.size());

        for (int i = 0; i < correctDataList.size() - 1; i++) {
            for (int j = 0; j < correctDataList.size() - 1 - i; j++) {
                String type1 = getTypeFromId(correctDataList.get(j));
                String type2 = getTypeFromId(correctDataList.get(j + 1));
                if (type1.compareTo(type2) > 0) {
                    // Elemente tauschen
                    String temp = correctDataList.get(j);
                    correctDataList.set(j, correctDataList.get(j + 1));
                    correctDataList.set(j + 1, temp);
                }
            }
        }

        // Gruppiert nach Typ ausgeben
        System.out.println("\nNach Typ sortierte korrekte Daten IDs:");
        String currentType = "";
        for (String id : correctDataList) {
            String dataType = getTypeFromId(id);
            if (!dataType.equals(currentType)) {
                System.out.println("\n--- " + dataType + " ---");
                currentType = dataType;
            }
            System.out.println(id);
        }
    }

    // Hilfsmethode um Typ aus ID zu bestimmen
    private String getTypeFromId(String id) {
        if (id.startsWith("env")) return "umwelteinfluesse";
        if (id.startsWith("inter")) return "innenraumdaten";
        if (id.startsWith("veh")) return "fahrzeugdaten";
        if (id.startsWith("vehVerk")) return "verkehr";
        return "unknown";
    }

    // Methode zum Zurücksetzen der Instanz (für Tests oder manuellen Reset)
    public static void resetInstance() {
        instance = null;
        creationDate = null;
        System.out.println("VehicleDiag Instanz wurde zurückgesetzt.");
    }

    public void simulate() {
        System.out.println("-----Start der Simulation-----");
        System.out.println("Instanz Info:");
        System.out.println("VehicleDiag Instanz erstellt am: " + creationDate);
        System.out.println("Anzahl gespeicherter Datensätze: " + dataList.size());
        System.out.println();

        Random random = new Random(); // Einmalige Erstellung des Random-Objekts

        for (int i = 1; i <= 1000; i++) {
            umwelteinfluesse env = new umwelteinfluesse();
            innenraumdaten inter = new innenraumdaten();
            fahrzeugdaten veh = new fahrzeugdaten();
            verkehr vehVerk = new verkehr();

            env.id = "env" + i;
            inter.id = "inter" + i;
            veh.id = "veh" + i;
            vehVerk.id = "vehVerk" + i;

            String currentTimestamp = String.valueOf(LocalDate.now());
            env.timestamp = currentTimestamp;
            inter.timestamp = currentTimestamp;
            veh.timestamp = currentTimestamp;
            vehVerk.timestamp = currentTimestamp;

            if (i % 100 != 0) {
                env.addUmwelteinfluesse(
                        Math.round(random.nextFloat() * 100),
                        random.nextFloat() * 100,
                        "1", "1"
                );
                dataList.add(env);

                inter.addInnenraumdaten(
                        Math.round(random.nextFloat() * 100),
                        "1", "1"
                );
                dataList.add(inter);

                veh.addFahrzeugdaten(
                        Math.round(random.nextFloat() * 48),
                        (short) random.nextInt(500),
                        (short) random.nextInt(15000),
                        "0,0"
                );
                dataList.add(veh);

                vehVerk.addVerkehr(
                        (short) random.nextInt(130),
                        (short) random.nextInt(250),
                        "1"
                );
                dataList.add(vehVerk);

            } else {
                switch (i) {
                    case 100:
                        // Test: Werte knapp über oberen Grenzen
                        env.addUmwelteinfluesse(101, 101, "", "");
                        dataList.add(env);
                        inter.addInnenraumdaten(101, "", "");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(49F, (short) 501, (short) 15001, "");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 130, (short) 251, "");
                        dataList.add(vehVerk);
                        break;

                    case 200:
                        // Test: Werte deutlich über oberen Grenzen
                        env.addUmwelteinfluesse(150, 150, "", "");
                        dataList.add(env);
                        inter.addInnenraumdaten(150, "", "");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(60F, (short) 600, (short) 20000, "");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 131, (short) 300, "");
                        dataList.add(vehVerk);
                        break;

                    case 300:
                        // Test: Negative Werte außerhalb unterer Grenzen
                        env.addUmwelteinfluesse(-101, -1, "0", "0");
                        dataList.add(env);
                        inter.addInnenraumdaten(-101, "0", "0");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(-1, (short) -1, (short) -1, "0");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) -1, (short) -1, "0");
                        dataList.add(vehVerk);
                        break;

                    case 400:
                        // Test: Extrem hohe positive Werte
                        env.addUmwelteinfluesse(500F, 500F, "10", "10");
                        dataList.add(env);
                        inter.addInnenraumdaten(500F, "10", "10");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(200F, (short) 2000, (short) 30000, "999,999");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 500, (short) 1000, "10");
                        dataList.add(vehVerk);
                        break;

                    case 500:
                        // Test: Extrem niedrige negative Werte
                        env.addUmwelteinfluesse(-500F, -100F, "-5", "-5");
                        dataList.add(env);
                        inter.addInnenraumdaten(-500F, "-5", "-5");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(-50F, (short) -500, (short) -5000, "-999,-999");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) -100, (short) -500, "-5");
                        dataList.add(vehVerk);
                        break;

                    case 600:
                        // Test: Ungültige String-Formate (Buchstaben statt Zahlen)
                        env.addUmwelteinfluesse(150F, 150F, "abc", "xyz");
                        dataList.add(env);
                        inter.addInnenraumdaten(150F, "abc", "xyz");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(100F, (short) 1000, (short) 20000, "text,mehr_text");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 200, (short) 500, "abc");
                        dataList.add(vehVerk);
                        break;

                    case 700:
                        // Test: Position ohne Komma (nur eine Koordinate)
                        env.addUmwelteinfluesse(200F, 200F, "99", "99");
                        dataList.add(env);
                        inter.addInnenraumdaten(200F, "99", "99");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(150F, (short) 1500, (short) 25000, "nur_eine_koordinate");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 300, (short) 800, "99");
                        dataList.add(vehVerk);
                        break;

                    case 800:
                        // Test: Position mit zu vielen Koordinaten
                        env.addUmwelteinfluesse(300F, 300F, "15", "15");
                        dataList.add(env);
                        inter.addInnenraumdaten(300F, "15", "15");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(250F, (short) 2500, (short) 35000, "1,2,3,4,5");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 400, (short) 1200, "15");
                        dataList.add(vehVerk);
                        break;

                    case 900:
                        // Test: Leerzeichen und Sonderzeichen
                        env.addUmwelteinfluesse(400F, 400F, " ", "!");
                        dataList.add(env);
                        inter.addInnenraumdaten(400F, " ", "!");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(300F, (short) 3000, (short) 40000, " ; ");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 500, (short) 1500, " ");
                        dataList.add(vehVerk);
                        break;

                    case 1000:
                        // Test: Leere Strings kombiniert mit Grenzüberschreitungen
                        env.addUmwelteinfluesse(600F, 600F, "", "");
                        dataList.add(env);
                        inter.addInnenraumdaten(600F, "", "");
                        dataList.add(inter);
                        veh.addFahrzeugdaten(400F, (short) 4000, (short) 50000, "");
                        dataList.add(veh);
                        vehVerk.addVerkehr((short) 600, (short) 2000, "");
                        dataList.add(vehVerk);
                        break;
                }
            }
        }
        System.out.println("\n-----Simulation beendet-----");
        System.out.println("Simulation beendet. " + dataList.size() + " Daten wurden erstellt.");
        System.out.println("Welche Daten wollen Sie angezeigt bekommen?");
        System.out.println("1 = Alle Daten");
        System.out.println("2 = Falsche Daten");
        System.out.println("3 = Richtige Daten");
        System.out.println("4 = Sortierte Falsche Daten");
        System.out.println("5 = Sortierte Richtige Daten");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n-----Alle Daten-----");
                    System.out.println(getAllData());
                    break;
                case 2:
                    System.out.println("\n-----Falsche Daten-----");
                    System.out.println(getFalseData());
                    break;
                case 3:
                    System.out.println("\n-----Richtige Daten-----");
                    System.out.println(getCorrectData());
                    break;
                case 4:
                    System.out.println("\n-----Sortierte Falsche Daten-----");
                    getFalseDataSorted();
                    break;
                case 5:
                    System.out.println("\n-----Sortierte Richtige Daten-----");
                    getCorrectDataSorted();
                    break;
                default:
                    System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 5 ein!");
                    break;
            }
        }
    resetInstance();
}
    public static void main(String[] args) {
        // Verwende getInstance() statt new VehicleDiag()
        VehicleDiag simulation = VehicleDiag.getInstance();
        simulation.simulate();
    }
}