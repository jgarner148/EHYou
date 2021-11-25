import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Tutor extends Staff{
    private String office;
    private String degree;
    private String[] modulesTeaching;
    private String[] modulesModerating;

    public Tutor(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] modulesTeaching, String[] modulesModerating) throws IOException, ClassNotFoundException {
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

        boolean isTeachingEmpty = false;
        if(this.modulesTeaching.length == 0){
            isTeachingEmpty = true;
        }

        if(!isTeachingEmpty) {
            for (int i = 0; i < this.modulesTeaching.length; i++) {
                Module addingTo = getobject.module(this.modulesTeaching[i]);
                String[] fetchedTeachers = addingTo.getTeachers();
                boolean doesexist = false;
                for (int l = 0; l < fetchedTeachers.length; l++) {
                    if (fetchedTeachers[l].equals(this.getStaffID())) {
                        doesexist = true;
                    }
                }
                if (!doesexist) {
                    addingTo.addToTeachers(this.getStaffID());
                    addingTo.updateClassFile();
                }
            }
        }

        boolean isModeratingEmpty = false;
        if(this.modulesModerating.length==0){
            isModeratingEmpty = true;
        }

        if(!isModeratingEmpty) {
            for (int i = 0; i < this.modulesModerating.length; i++) {
                Module addingTo = getobject.module(this.modulesModerating[i]);
                String fetchedTeacher = addingTo.getModerator();
                boolean doesexist = false;
                if (fetchedTeacher.equals(this.getStaffID())) {
                    doesexist = true;
                }
                if (!doesexist) {
                    addingTo.setModerator(this.getStaffID());
                    addingTo.updateClassFile();
                }
            }
        }



    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) throws IOException {
        this.office = office;
        this.updateClassFile();
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) throws IOException {
        this.degree = degree;
        this.updateClassFile();
    }

    public String[] getModulesTeaching() {
        return this.modulesTeaching;
    }

    public void addToModulesTeaching(String newmodule) throws IOException {
        this.modulesTeaching = AddToArray.string(this.modulesTeaching, newmodule);
        this.updateClassFile();
    }

    public String[] getModulesModerating() {
        return this.modulesModerating;
    }

    public void addToModulesModerating(String newmodule) throws IOException {
        this.modulesModerating = AddToArray.string(this.modulesModerating, newmodule);
        this.updateClassFile();
    }

    public void updateClassFile() throws IOException {
        String filename = "Tutors/" +this.getStaffID() + ".txt";
        File oldFile = new File(filename);
        oldFile.delete();

        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
