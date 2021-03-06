import javax.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double x;
    private Double y;
    private Double r;
    private Boolean result;

    public Result(Double x, Double y, Double r, Boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public String getX() {
        return String.format("%.2f", x);
    }

    public void setX(Double x) {
        this.x = x;
    }

    public String getY() {
        return String.format("%.2f", y);
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getR() {
        return String.format("%.2f", r);
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getResult() {
        return result ? "Yes" : "No";
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Result() {
        x = 0.;
        y = 0.;
        r = 0.;
        result = true;
    }

    void save() {
        DataBase.em.getTransaction().begin();
        DataBase.em.persist(this);
        DataBase.em.getTransaction().commit();
    }

    static Boolean calculate(Double x, Double y, Double r) {
        if (x >= 0 && y <= 0 && y >= x - r)
            return true;
        else if (x >= 0 && y >= 0 && y <= r/2 && x <= r)
            return true;
        else if (x <= 0 && y >= 0 && x*x + y*y <= r*r/4)
            return true;
        else
            return false;
    }
}
