import java.io.*;
import java.time.YearMonth;

public class Research implements Serializable {
    private  String ResearchCode;
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
        quickMethods.addStringToCSV(filepath, genCode);
        this.ResearchCode = genCode;

        String filename = "Researches/" +this.getResearchCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();


        for(int i=0; i<this.getAcademicsResearching().length; i++){
            Academic addingTo = getobject.academic(this.AcademicsResearching[i]);
            String[] fetchedResearch = addingTo.getCurrentResearch();
            boolean doesexist = false;
            for(int l=0; l<fetchedResearch.length;l++){
                if(fetchedResearch[l].equals(this.ResearchCode)){
                    doesexist=true;
                }
            }
            if(!doesexist) {
                addingTo.addToCurrentResearch(this.ResearchCode);
            }
        }
    }

    public String getResearchTitle() {
        return ResearchTitle;
    }

    public void setResearchTitle(String researchTitle) {
        ResearchTitle = researchTitle;
    }

    public String[] getAcademicsResearching() {
        return AcademicsResearching;
    }

    public void addToAcademicsResearching(String academicsResearching) {
        this.AcademicsResearching = AddToArray.string(this.AcademicsResearching, academicsResearching);
    }

    public String getResearchCode() {
        return ResearchCode;
    }

    public void setResearchCode(String researchCode) {
        ResearchCode = researchCode;
    }
}
