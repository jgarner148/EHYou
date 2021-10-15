public class Research {
    private String ResearchTitle;
    private Academic[] AcademicsResearching;

    public Research(String researchTitle, Academic[] academicsResearching) {
        this.ResearchTitle = researchTitle;
        this.AcademicsResearching = academicsResearching;
    }

    public String getResearchTitle() {
        return ResearchTitle;
    }

    public void setResearchTitle(String researchTitle) {
        ResearchTitle = researchTitle;
    }

    public Academic[] getAcademicsResearching() {
        return AcademicsResearching;
    }

    public void addToAcademicsResearching(Academic academicsResearching) {
        this.AcademicsResearching = AddToArray.academic(this.AcademicsResearching, academicsResearching);
    }
}
