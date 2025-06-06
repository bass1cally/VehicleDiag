public class umwelteinfluesse extends DiagBase {

    private float temperatur;
    private float luftfeuchtigkeit;
    private String regensensor;
    private String lichtsensor;

    public void addUmwelteinfluesse(float temperatur, float luftfeuchtigkeit, String regensensor, String lichtsensor) {
        //Temperatur
        if (temperatur >= -100 && temperatur <= 100) {
            this.temperatur = temperatur;
            System.out.println(id + ": Temperatur auf " + temperatur + " gesetzt");
        } else{
            System.out.println(id + ": Umgebungstemperatur nicht plausibel: " + temperatur + " - Temperatur muss zwischen -100 und 100 liegen");
            this.temperatur = 0;
            System.out.println(id + ": Temperatur auf 0 gesetzt");
            this.error = true;
        }
        //Luftfeuchtigkeit
        if(luftfeuchtigkeit >= 0 && luftfeuchtigkeit <= 100){
            this.luftfeuchtigkeit = luftfeuchtigkeit;
            System.out.println(id + ": Luftfeuchtigkeit auf " + luftfeuchtigkeit + " gesetzt");
        } else {
            System.out.println(id + ": Luftfeuchtigkeitssensor defekt: " + luftfeuchtigkeit + " - Luftfeuchtigkeit muss zwischen 0 und 100 liegen");
            this.luftfeuchtigkeit = 0;
            System.out.println(id + ": Luftfeuchtigkeit auf 0 gesetzt");
            this.error = true;
        }
        //Regensensor
        switch(regensensor){
            case "1":
                this.regensensor = "Kein";
                System.out.println(id + ": Regensensor auf 'Kein' gesetzt");
                break;
            case "2":
                this.regensensor = "leicht";
                System.out.println(id + ": Regensensor auf 'leicht' gesetzt");
                break;
            case "3":
                this.regensensor = "mittel";
                System.out.println(id + ": Regensensor auf 'mittel' gesetzt");
                break;
            case "4":
                this.regensensor = "schwer";
                System.out.println(id + ": Regensensor auf 'schwer' gesetzt");
                break;
            default:
                this.regensensor = "Kein";
                System.out.println(id + ": Regensensor Kalibrierungsfehler: '" + regensensor + "' - Regensensor muss zwischen 1 und 4 liegen");
                this.error = true;
                System.out.println(id + ": Regensensor auf 'Kein' gesetzt");
                break;
        }
        //lichtsensor
        if(lichtsensor.equals("1")){
            this.lichtsensor = "Tag";
            System.out.println(id + ": Lichtsensor auf 'Tag' gesetzt");
        }
        else if (lichtsensor.equals("2")){
            this.lichtsensor = "Nacht";
            System.out.println(id + ": Lichtsensor auf 'Nacht' gesetzt");
        } else {
            this.lichtsensor = "Tag";
            System.out.println(id + ": Lichtsensor Auslesefehler: '" + lichtsensor + "' - Lichtsensor muss entweder 1 oder 2 sein");
            this.error = true;
            System.out.println(id + ":Lichtsensor auf 'Tag' gesetzt");
        }
    }

    public String umweltSummary() {
        if(error){
            System.out.println("Hinweis: Umweltsensoren zeigen Anomalien - Daten könnten ungenau sein");
        }
        return "Vehicle with the id " + id + " at " + timestamp + " has the following environment data: \n " +
                "Temperatur: " + temperatur + ", Luftfeuchtigkeit: " + luftfeuchtigkeit + ", Regensensor: " + regensensor + ", Lichtsensor: " + lichtsensor;

    }

}