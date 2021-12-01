/**
 * Class file for Tutor object
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Tutor extends Staff{
    /**
     * Academics office location
     */
    private String office;
    /**
     * Academics degree
     */
    private String degree;
    /**
     * String array with module codes for the modules the tutor is teaching
     */
    private String[] modulesTeaching;
    /**
     * String array with module codes for the modules the tutor is moderating
     */
    private String[] modulesModerating;

    /**
     * Constructor for Tutor
     * @param fname Tutor's first name
     * @param lname Tutor's last name
     * @param dob Tutor's Date of birth
     * @param startyr Year tutor started working at the university
     * @param salary Tutor's salary
     * @param office Tutor's office
     * @param degree tutor's degree
     * @param modulesTeaching String array with module codes for the modules the tutor is teaching
     * @param modulesModerating String array with module codes for the modules the tutor is moderating
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Tutor(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] modulesTeaching, String[] modulesModerating) throws IOException, ClassNotFoundException {
        super(fname, lname, dob, startyr, salary);//Calling the constructor of the super classes using the taken in variables
        //Assigning the taken in variables to the appropriate class variable
        this.office = office;
        this.degree = degree;
        this.modulesTeaching = modulesTeaching;
        this.modulesModerating = modulesModerating;

        //adding the tutor's staff ID to each of the modules they are teaching
        boolean isTeachingEmpty = this.modulesTeaching.length == 0;
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

        //adding the tutor's staff ID to each of the modules they are teaching
        boolean isModeratingEmpty = this.modulesModerating.length == 0;
        if(!isModeratingEmpty) {
            for (int i = 0; i < this.modulesModerating.length; i++) {
                Module addingTo = getobject.module(this.modulesModerating[i]);
                String fetchedTeacher = addingTo.getModerator();
                boolean doesexist = fetchedTeacher.equals(this.getStaffID());
                if (!doesexist) {
                    addingTo.setModerator(this.getStaffID());
                    addingTo.updateClassFile();
                }
            }
        }

        //Creating the class file for the newly created Tutor object
        String filename = "Tutors/" +this.getStaffID() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

    }

    /**
     * Getter for office
     * @return Tutor's office
     */
    public String getOffice() {return this.office;}

    /**
     * Setter for office
     * @param office value being assigned to office
     * @throws IOException
     */
    public void setOffice(String office) throws IOException {
        this.office = office;
        this.updateClassFile();
    }

    /**
     * Getter for degree
     * @return degree
     */
    public String getDegree() {return this.degree;}

    /**
     * Setter for degree
     * @param degree item being assigned to degree
     * @throws IOException
     */
    public void setDegree(String degree) throws IOException {
        this.degree = degree;
        this.updateClassFile();
    }

    /**
     * Getter for the modules teaching string array
     * @return modules teaching string array
     */
    public String[] getModulesTeaching() {
        return this.modulesTeaching;
    }

    /**
     * Method to add a module code to the modules teaching array
     * @param newmodule Module code being added to the array
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addToModulesTeaching(String newmodule) throws IOException, ClassNotFoundException {
        this.modulesTeaching = AddToArray.string(this.modulesTeaching, newmodule);
        this.updateClassFile();

        //Updating the module that has just been added
        Module addingTO = getobject.module(newmodule);
        addingTO.addToTeachers(this.getStaffID());
    }


    /**
     * Method to remove module from modules teaching array
     * @param removingModule Module code ot be removed
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeFromModulesTeaching(String removingModule) throws IOException, ClassNotFoundException {
        this.modulesTeaching = quickMethods.removeFromStringArray(removingModule, this.modulesModerating);
        this.updateClassFile();

        //Updating module that has just been removed
        Module removingFrom = getobject.module(removingModule);
        removingFrom.removeFromTeachers(this.getStaffID());
    }

    /**
     * Getter for modules taking array
     * @return Modules taking string array
     */
    public String[] getModulesModerating() {
        return this.modulesModerating;
    }

    /**
     * Method to add a module to modules moderating array
     * @param newmodule Module to be added
     * @throws IOException
     */
    public void addToModulesModerating(String newmodule) throws IOException {
        this.modulesModerating = AddToArray.string(this.modulesModerating, newmodule);
        this.updateClassFile();
    }

    /**
     * Method to remove a module from the modules moderating array
     * @param removingModule
     * @throws IOException
     */
    public void removeFromModulesModerating(String removingModule) throws IOException {
        String[] newModulesArray = quickMethods.removeFromStringArray(removingModule, this.modulesModerating);
        this.modulesModerating = newModulesArray;
        this.updateClassFile();
    }

    /**
     * Method to update class file be deleting the old one and creating a new one
     * @throws IOException
     */
    public void updateClassFile() throws IOException {
        String filename = "Tutors/" +this.getStaffID() + ".txt";

        //Deleting the old file
        File oldFile = new File(filename);
        oldFile.delete();

        //Creating the new file
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
