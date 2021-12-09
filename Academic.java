/**
 * Class file for the Academic object
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Academic extends Staff{
    /**
     * Academics office location
     */
    private String office;
    /**
     * Academics degree
     */
    private String degree;
    /**
     * String Array with research codes that corresponds to the research the academic is involved in
     */
    private String[] currentResearch;

    /**
     * Constructor for Academic
     * @param fname Academic's First name
     * @param lname Academic's Last Name
     * @param dob Academic's date of birth
     * @param startyr Year Academic started work at the university
     * @param salary Academic's salary
     * @param office Academic's office location
     * @param degree Academic's degree
     * @param currentResearch String Array with research codes that corresponds to the research the academic is involved in
     * @throws IOException IOError
     * @throws ClassNotFoundException Class not found error
     */
    public Academic(String fname, String lname, String dob, int startyr, int salary, String office, String degree, String[] currentResearch) throws IOException, ClassNotFoundException {
        super(fname, lname, dob, startyr, salary); //Calling the constructor of the super classes using the taken in variables
        //Assigning the taken in variables to the appropriate class variable
        this.office = office;
        this.degree = degree;
        this.currentResearch = currentResearch;

        boolean isResearchEmpty = this.currentResearch.length == 0; //finding out if the current research array is empty

        //adding the academic's staff ID to each the research's academics array
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

        //creating the class file for the newly created academic object
        String filename = "Academics/" +this.getStaffID() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    /**
     * Getter for office
     * @return academic's office variable
     */
    public String getOffice() {return office;}

    /**
     * Setter for office
     * @param office the value being assigned to office
     * @throws IOException IO exception
     */
    public void setOffice(String office) throws IOException {
        this.office = office;
        this.updateClassFile();
    }

    /**
     * getter for degree
     * @return academic's degree variable
     */
    public String getDegree() {return degree;}

    /**
     * Setter for degree
     * @param degree the value being assigned to degree
     * @throws IOException IO exception
     */
    public void setDegree(String degree) throws IOException {
        this.degree = degree;
        this.updateClassFile();
    }

    /**
     * Getter for current research
     * @return academic's current research string array
     */
    public String[] getCurrentResearch() {return currentResearch;}

    /**
     * Method to add string to the current research array
     * @param newresearch item to be added to current research array
     * @throws IOException IO exception
     * @throws ClassNotFoundException CLass not found exception
     */
    public void addToCurrentResearch(String newresearch) throws IOException, ClassNotFoundException {
        this.currentResearch = AddToArray.string(this.currentResearch, newresearch);
        this.updateClassFile();

        //Updating the research class that has just been added to this academic
        Research addingTo = getobject.research(newresearch);
        String[] addingToArray = addingTo.getAcademicsResearching();
        boolean exists = quickMethods.checkIfInStringArray(this.getStaffID(), addingToArray);
        if(!exists){
            addingTo.addToAcademicsResearching(this.getStaffID());
        }
    }

    /**
     * Method to remove string from the current research array
     * @param removingResearch item to be removed from research array
     * @throws IOException IO exception
     * @throws ClassNotFoundException Class not found exception
     */
    public void removeFromCurrentResearch(String removingResearch) throws IOException, ClassNotFoundException {
        this.currentResearch = quickMethods.removeFromStringArray(removingResearch, this.currentResearch);
        this.updateClassFile();

        //Updating the research class that has just been removed from this academic
        Research removingFrom = getobject.research(removingResearch);
        String[] removingFromArray = removingFrom.getAcademicsResearching();
        boolean exists = quickMethods.checkIfInStringArray(this.getStaffID(), removingFromArray);
        if(exists){
            removingFrom.removeFromAcademicResearching(this.getStaffID());
        }
    }

    /**
     * Method to update class file be deleting the old one and creating a new one
     * @throws IOException IO exception
     */
    public void updateClassFile() throws IOException {
        String filename = "Academics/" +this.getStaffID() + ".txt";

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
