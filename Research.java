import java.io.*;
import java.time.YearMonth;

public class Research implements Serializable {
    private final String ResearchCode;
    private String ResearchTitle;
    private String[] AcademicsResearching;

    public Research(String researchTitle, String[] academicsResearching) throws IOException, ClassNotFoundException {
        this.ResearchTitle = researchTitle;
        this.AcademicsResearching = academicsResearching;

        //section for generating Researchcode
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

        this.ResearchCode = genCode;

        String filename = "Researches/" +this.getResearchCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

        quickMethods.addStringToCSV(filepath, genCode);

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
    }

    public String getResearchTitle() {
        return ResearchTitle;
    }

    public void setResearchTitle(String researchTitle) throws IOException {
        ResearchTitle = researchTitle;
        this.updateClassFile();
    }

    public String[] getAcademicsResearching() {
        return AcademicsResearching;
    }

    public void addToAcademicsResearching(String academicsResearching) throws IOException {
        this.AcademicsResearching = AddToArray.string(this.AcademicsResearching, academicsResearching);
        this.updateClassFile();
    }

    public void removeFromAcademicResearching(String removingAcademic){
        String[] newArray = quickMethods.removeFromStringArray(removingAcademic, this.AcademicsResearching);
        this.AcademicsResearching = newArray;
    }

    public String getResearchCode() {
        return ResearchCode;
    }

    public void updateClassFile() throws IOException {
        String filename = "Researches/" +this.getResearchCode() + ".txt";
        File oldFile = new File(filename);
        oldFile.delete();

        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
