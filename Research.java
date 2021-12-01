/**
 * Class file for Research object
 */

import java.io.*;
import java.time.YearMonth;

public class Research implements Serializable {
    /**
     * Research code
     */
    private final String ResearchCode;
    /**
     * Title of Research
     */
    private String ResearchTitle;
    /**
     * String array with Staff codes of the academics researching
     */
    private String[] AcademicsResearching;

    /**
     * Constructor for Research
     * @param researchTitle Title of Research
     * @param academicsResearching String array with Staff codes of the academics researching
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Research(String researchTitle, String[] academicsResearching) throws IOException, ClassNotFoundException {
        //Assigning the taken in variables to the appropriate class variable
        this.ResearchTitle = researchTitle;
        this.AcademicsResearching = academicsResearching;

        //Generating the research code
        boolean looping = true;
        String filepath = "codes/researchcodes.csv";
        String genCode = "";
        while(looping){
            int currentyear = YearMonth.now().getYear();
            String yearAsString = String.valueOf(currentyear);
            genCode = yearAsString + "-";
            for(int i=0; i < 4; i++){
                char randomchar = quickMethods.randchar();
                String charAsString = Character.toString(randomchar);
                genCode = genCode + charAsString;
            }

            int randomnumber = quickMethods.randnum(100, 999);

            genCode = genCode+"-"+randomnumber;
            boolean doesExist = quickMethods.checkIfInFile(filepath, genCode);
            if (!doesExist) {
                looping = false;
            }
        }

        this.ResearchCode = genCode;//assigning the generated code to the research code variable

        //Adding the research ID to the academics in the academics researching array
        boolean isAcademicEmpty = this.AcademicsResearching.length == 0;
        if(!isAcademicEmpty) {
            for (int i = 0; i < this.getAcademicsResearching().length; i++) {
                Academic addingTo = getobject.academic(this.AcademicsResearching[i]);
                String[] fetchedResearch = addingTo.getCurrentResearch();
                boolean doesexist = false;
                for (int l = 0; l < fetchedResearch.length; l++) {
                    if (fetchedResearch[l].equals(this.ResearchCode)) {
                        doesexist = true;
                    }
                }
                if (!doesexist) {
                    addingTo.addToCurrentResearch(this.ResearchCode);
                    addingTo.updateClassFile();
                }
            }
        }

        //Creating the class file for the newly created research object
        String filename = "Researches/" +this.getResearchCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

        quickMethods.addStringToCSV(filepath, genCode); //Creating the research code to the overall research code file
    }

    /**
     * Getter for research title
     * @return The research title
     */
    public String getResearchTitle() {return this.ResearchTitle;}

    /**
     * Setter for research title
     * @param researchTitle the value being assigned to research title
     * @throws IOException
     */
    public void setResearchTitle(String researchTitle) throws IOException {
        ResearchTitle = researchTitle;
        this.updateClassFile();
    }

    /**
     * Getter for the academics researching array
     * @return the academics researching array
     */
    public String[] getAcademicsResearching() {return this.AcademicsResearching;}

    /**
     * Method for adding string to the academics researching array
     * @param academicsResearching string to be added to the array
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addToAcademicsResearching(String academicsResearching) throws IOException, ClassNotFoundException {
        this.AcademicsResearching = AddToArray.string(this.AcademicsResearching, academicsResearching);
        this.updateClassFile();

        //Updating the academic that's just been added to the academics researching array
        Academic addingTo = getobject.academic(academicsResearching);
        addingTo.addToCurrentResearch(this.ResearchCode);
    }

    /**
     * Method for removing string from academics researching array
     * @param removingAcademic item to be removed from academics researching array
     */
    public void removeFromAcademicResearching(String removingAcademic) throws IOException, ClassNotFoundException {
        String[] newArray = quickMethods.removeFromStringArray(removingAcademic, this.AcademicsResearching);
        this.AcademicsResearching = newArray;

        //Updating the academics that's just been removed from the academics researching array
        Academic removingFrom = getobject.academic(removingAcademic);
        removingFrom.removeFromCurrentResearch(this.ResearchCode);
    }

    /**
     * Getter for Research code
     * @return research code
     */
    public String getResearchCode() {return this.ResearchCode;}

    /**
     * Method to update class file be deleting the old one and creating a new one
     * @throws IOException
     */
    public void updateClassFile() throws IOException {
        String filename = "Researches/" +this.getResearchCode() + ".txt";

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
