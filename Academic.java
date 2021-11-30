import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Academic extends Staff{
    private String office;
    private String degree;
    private String[] currentResearch;

    public Academic(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] currentResearch) throws IOException, ClassNotFoundException {
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

        boolean isResearchEmpty = this.currentResearch.length == 0;

        if(!isResearchEmpty) {
            for (int i = 0; i < this.currentResearch.length; i++) {
                Research addingTo = getobject.research(this.currentResearch[i]);
                String[] featchedAcademics = addingTo.getAcademicsResearching();
                boolean doesexist = false;
                for (int l = 0; l < featchedAcademics.length; l++) {
                    if (featchedAcademics[l].equals(this.getStaffID())) {
                        doesexist = true;
                    }
                }
                if (!doesexist) {
                    addingTo.addToAcademicsResearching(this.getStaffID());
                    addingTo.updateClassFile();
                }
            }
        }
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) throws IOException {
        this.office = office;
        this.updateClassFile();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) throws IOException {
        this.degree = degree;
        this.updateClassFile();
    }

    public String[] getCurrentResearch() {
        return currentResearch;
    }

    public void addToCurrentResearch(String newresearch) throws IOException {
        this.currentResearch = AddToArray.string(this.currentResearch, newresearch);
        this.updateClassFile();
    }

    public void removeFromCurrentResearch(String removingResearch) throws IOException {
        String[] newArray = quickMethods.removeFromStringArray(removingResearch, this.currentResearch);
        this.currentResearch = newArray;
        this.updateClassFile();
    }

    public void updateClassFile() throws IOException {
        String filename = "Academics/" +this.getStaffID() + ".txt";
        File oldFile = new File(filename);
        oldFile.delete();

        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
