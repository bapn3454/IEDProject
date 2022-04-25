public class Film {
    private String titre;
    private String releaseDate;
    private String distributor;
    private String genre;
    private String budget;
    private String usIncome;
    private String worldIncome;
    private String resume;

    public Film(String titre, String releaseDate, String distributor, String genre, String budget, String usIncome, String worldIncome, String resume) {
        this.titre = titre;
        this.releaseDate = releaseDate;
        this.distributor = distributor;
        this.genre = genre;
        this.budget = budget;
        this.usIncome = usIncome;
        this.worldIncome = worldIncome;
        this.resume = resume;
    }

    public Film() {

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getUsIncome() {
        return usIncome;
    }

    public void setUsIncome(String usIncome) {
        this.usIncome = usIncome;
    }

    public String getWorldIncome() {
        return worldIncome;
    }

    public void setWorldIncome(String worldIncome) {
        this.worldIncome = worldIncome;
    }
}
