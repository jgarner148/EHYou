import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Academic extends Staff{
    private String office;
    private String degree;
    private String[] currentResearch;

    public Academic(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] currentResearch) throws IOException {
        super(fname, lname, dob, startyr, salary);
        this.office = office;
        this.degree = degree;
        this.currentResearch = currentResearch;

        String filename = "Academics/" +this.getStaffID() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String[] getCurrentResearch() {
        return currentResearch;
    }

    public void addToCurrentResearch(String newresearch) {
        this.currentResearch = AddToArray.string(this.currentResearch, newresearch);
    }
}
