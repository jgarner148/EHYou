import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Tutor extends Staff{
    private String office;
    private String degree;
    private String[] modulesTeaching;
    private String[] modulesModerating;

    public Tutor(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] modulesTeaching, String[] modulesModerating) throws IOException {
        super(fname, lname, dob, startyr, salary);
        this.office = office;
        this.degree = degree;
        this.modulesTeaching = modulesTeaching;
        this.modulesModerating = modulesModerating;

        String filename = "Tutors/" +this.getStaffID() + ".txt";
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

    public String[] getModulesTeaching() {
        return modulesTeaching;
    }

    public void addToModulesTeaching(String newmodule) {
        this.modulesTeaching = AddToArray.string(this.modulesTeaching, newmodule);
    }

    public String[] getModulesModerating() {
        return modulesModerating;
    }

    public void addToModulesModerating(String newmodule) {
        this.modulesModerating = AddToArray.string(this.modulesModerating, newmodule);
    }
}
