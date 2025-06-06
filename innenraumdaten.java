public class innenraumdaten extends DiagBase{

    private float temperatur;
    private String frontscheibenheizung;
    private String heckscheibenheizung;

    public void addInnenraumdaten(float temperatur, String frontscheibenheizung, String heckscheibenheizung) {
        //Temperatur
        if (temperatur >= -100 && temperatur <= 100) {
            this.temperatur = temperatur;
            System.out.println(id + ": Temperatur auf " + temperatur + " gesetzt");
        } else{
            System.out.println(id + ": Temperatur außerhalb des zulässigen Bereichs: " + temperatur + " - Temperatur muss zwischen -100 und 100 liegen");
            this.temperatur = 0;
            System.out.println(id + ": Temperatur auf 0 gesetzt");
            this.error = true;
        }
        //Frontscheibenheizung
        if(frontscheibenheizung.equals("1")){
            this.frontscheibenheizung = "An";
            System.out.println(id + ": Frontscheibenheizung auf 'An' gesetzt");
        } else if(frontscheibenheizung.equals("2")) {
            this.frontscheibenheizung = "Aus";
            System.out.println(id + ": Frontscheibenheizung auf 'Aus' gesetzt");
        } else {
            this.frontscheibenheizung = "Aus";
            System.out.println(id + ": Steuerungsfehler Frontscheibenheizung: '" + frontscheibenheizung + "' - Frontscheibenheizung muss entweder 1 oder 2 sein");
            this.error = true;
            System.out.println(id + ": Frontscheibenheizung auf 'Aus' gesetzt");
        }
        //Heckscheibenheizung
        if (heckscheibenheizung.equals("1")) {
            this.heckscheibenheizung = "An";
            System.out.println(id + ": Heckscheibenheizung auf 'An' gesetzt");
        } else if (heckscheibenheizung.equals("2")){
            this.heckscheibenheizung = "Aus";
            System.out.println(id + ": Heckscheibenheizung auf 'Aus' gesetzt");
        } else {
            this.heckscheibenheizung = "Aus";
            System.out.println(id + ": Heckscheibenheizung Konfigurationsfehler: '" + heckscheibenheizung + "' - Heckscheibenheizung muss entweder 1 oder 2 sein");
            this.error = true;
            System.out.println(id + ": Heckscheibenheizung auf 'Aus' gesetzt");
        }
    }

    public String innenraumSummary() {
        if(error){
            System.out.println("Achtung: Innenraumdaten enthalten Fehler - Standardwerte wurden verwendet");
        }
        return "Vehicle with the id " + id + " at " + timestamp + " has the following cabin data: \n " +
                "Temperature: " + temperatur + ", Front Window: " + frontscheibenheizung + ", Rear Window:" + heckscheibenheizung;
    }
}