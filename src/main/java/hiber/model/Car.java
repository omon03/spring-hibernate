package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car () { }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car(String model, int series, User user) {
        this(model, series);
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getModel().equals(car.getModel()) && getSeries() == car.getSeries();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getSeries());
    }

    @Override
    public String toString() {
        return "Car{ id: " + id +
                ", model: '" + model + '\'' +
                ", series: " + series +
                " }";
    }
}
