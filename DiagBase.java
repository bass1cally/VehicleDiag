public class DiagBase {
    protected String id;
    protected String timestamp;
    protected boolean error; // Changed from static to instance variable

    public DiagBase() {
        this.id = "";
        this.timestamp = "";
        this.error = false; // Initialize as false
    }

    public DiagBase(boolean error){
        this.error = error;
        this.id = "";
        this.timestamp = "";
    }

    protected String summary() {
        if(error){
            return "Vehicle with the id " + id + " at " + timestamp + " has an error";
        } else {
            return "Vehicle with the id " + id + " at " + timestamp + " has no error";
        }

    }
}