public class fahrzeugdaten extends DiagBase{

    private short geschwindigkeit;
    private short umdrehungszahl;
    private float boardspannung;
    private String position;

    public void addFahrzeugdaten(float boardspannung, short geschwindigkeit, short umdrehungszahl, String position) {
        //Boardspannung
        if( boardspannung >= 0 && boardspannung <= 48){
            this.boardspannung = boardspannung;
            System.out.println(id + ": Spannung auf " + boardspannung + " gesetzt");
        } else {
            System.out.println(id + ": Fehler bei Bordspannung: " + boardspannung + " - Spannung muss zwischen 0 und 48 liegen");
            this.boardspannung = 0;
            System.out.println(id + ": Spannung auf 0 gesetzt");
            this.error = true;
        }
        //Geschwindigkeit
        if( geschwindigkeit >= 0 && geschwindigkeit <= 500){
            this.geschwindigkeit = geschwindigkeit;
            System.out.println(id + ": Geschwindigkeit auf " + geschwindigkeit + " gesetzt");
        } else {
            System.out.println(id + ": Ungültige Geschwindigkeit: " + geschwindigkeit + " - Geschwindigkeit muss zwischen 0 und 500 liegen");
            this.geschwindigkeit = 0;
            System.out.println(id + ": Geschwindigkeit auf 0 gesetzt");
            this.error = true;
        }
        //Umdrehungszahl
        if( umdrehungszahl >= 0 && umdrehungszahl <= 15000){
            this.umdrehungszahl = umdrehungszahl;
            System.out.println(id + ": Umdrehungszahl auf " + umdrehungszahl + " gesetzt");
        } else {
            System.out.println(id + ": Kritischer Fehler - Umdrehungszahl: " + umdrehungszahl + " - Umdrehungszahl muss zwischen 0 und 15000 liegen");
            this.umdrehungszahl = 0;
            System.out.println(id + ": Umdrehungszahl auf 0 gesetzt");
            this.error = true;
        }
        //Position
        String regexCheck = "-?[0-9]+(\\.[0-9]+)?,\\s*-?[0-9]+(\\.[0-9]+)?";
        if(position.matches(regexCheck)){
            this.position = position;
            System.out.println(id + ": Position auf " + position + " gesetzt");
        } else {
            System.out.println(id + ": Positionsfehler: '" + position + "' - Position muss im Format 'x,y' liegen");
            this.position = "0,0";
            System.out.println(id + ": Position auf '0,0' gesetzt");
            this.error = true;
        }

    }

    public String fahrzeugSummary() {
        if(error){
            System.out.println("Warnung: Fehlerhafte Fahrzeugdaten erkannt - einige Werte wurden auf Standardwerte gesetzt");
        }
        return "Vehicle with the id " + id + " at " + timestamp + " has the following vehicle data: \n " +
                "Speed: " + geschwindigkeit + ", Number of revolutions: " + umdrehungszahl + ", Board Voltage: " + boardspannung + ", Position:" + position;
    }
}