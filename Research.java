public class Research {
    private String ResearchTitle;
    private String[] AcademicsResearching;

    public Research(String researchTitle, String[] academicsResearching) {
        this.ResearchTitle = researchTitle;
        this.AcademicsResearching = academicsResearching;
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
}
