import java.util.List;

public class Film {
    private String titre;
    private String releaseDate;
    private String distributor;
    private String genre;
    private String budget;
    private String usIncome;
    private String worldIncome;
    private String resume;
    private List<String> realisators;
    private List<String> actors;
    private List<String> productors;

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

    public Film(String title, List<String> dirDist, List<String> acteursDist, List<String> prosucteursDist) {
        this.titre = title;
        this.realisators = dirDist;
        this.actors = acteursDist;
        this.productors = prosucteursDist;
    }

    public Film(String title) {
        this.titre = title;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<String> getRealisators() {
        return realisators;
    }

    public void setRealisators(List<String> realisators) {
        this.realisators = realisators;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getProductors() {
        return productors;
    }

    public void setProductors(List<String> productors) {
        this.productors = productors;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titre='" + titre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", distributor='" + distributor + '\'' +
                ", genre='" + genre + '\'' +
                ", budget='" + budget + '\'' +
                ", usIncome='" + usIncome + '\'' +
                ", worldIncome='" + worldIncome + '\'' +
                ", resume='" + resume + '\'' +
                ", realisators=" + realisators +
                ", actors=" + actors +
                ", productors=" + productors +
                '}';
    }
}
