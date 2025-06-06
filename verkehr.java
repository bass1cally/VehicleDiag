public class verkehr extends DiagBase{

    private short LEGV;
    private short abstand;
    private String toterWinkel;

    public void addVerkehr(short LEGV, short abstand, String toterWinkel) {
        //Last recorded speed sign
        if( LEGV >= 0 && LEGV <= 130){
            this.LEGV = LEGV;
            System.out.println(id + ": LEGV auf " + LEGV + " gesetzt");
        } else {
            System.out.println(id + ": Geschwindigkeitserkennung fehlerhaft: " + LEGV + " - LEGV muss zwischen 0 und 130 liegen");
            this.LEGV = 0;
            System.out.println(id + ": LEGV auf 0 gesetzt");
            this.error = true;
        }
        //Abstand
        if(abstand >= 0 && abstand <= 250) {
            this.abstand = abstand;
            System.out.println(id + ": Abstand auf " + abstand + " gesetzt");
        } else {
            System.out.println(id + ": Abstandssensor außerhalb der Spezifikation: " + abstand + " - Abstand muss zwischen 0 und 250 liegen");
            this.abstand = 0;
            System.out.println(id + ": Abstand auf 0 gesetzt");
            this.error = true;
        }
        //Toter Winkel
        switch(toterWinkel){
            case "1":
                this.toterWinkel = "links";
                System.out.println(id + ": Toter Winkel auf 'links' gesetzt");
                break;
            case "2":
                this.toterWinkel = "rechts";
                System.out.println(id + ": Toter Winkel auf 'rechts' gesetzt");
                break;
            case "3":
                this.toterWinkel = "beide";
                System.out.println(id + ": Toter Winkel auf 'beide' gesetzt");
                break;
            case "4":
                this.toterWinkel = "aus";
                System.out.println(id + ": Toter Winkel auf 'aus' gesetzt");
                break;
            default:
                this.toterWinkel = "aus";
                System.out.println(id + ": Toter-Winkel-Assistent Systemfehler: '" + toterWinkel + "' - Toter Winkel muss zwischen 1 und 4 liegen");
                this.error = true;
                System.out.println(id + ": Toter Winkel auf 'aus' gesetzt");
                break;
        }

    }

    public String verkehrSummary() {
        if(error){
            System.out.println("Verkehrsassistenz-Warnung: Sensorwerte teilweise korrigiert");
        }
        return "Vehicle with the id " + id + " at " + timestamp + " has the following traffic data: \n " +
                "Last recorded speed sign: " + LEGV + ", Distance: " + abstand + ", Dead spot:" + toterWinkel;
    }

}