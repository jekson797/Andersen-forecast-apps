package forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "FORECASTS")
public class Forecast implements Serializable {

    @Id
    @Column(name = "date")
    private Date date;

    @Id
    @Column(name = "time")
    private Time time;

    @Id
    @Column(name = "location")
    private String location;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "status")
    private String status;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(date, forecast.date) &&
                Objects.equals(time, forecast.time) &&
                Objects.equals(location, forecast.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, location);
    }

    @Override
    public String toString() {
        return "Forecast:" + "\n" +
                "Date = " + date + "\n" +
                "Time = " + time + "\n" +
                "Location = " + location + "\n" +
                "Temperature = " + temperature + " C\u00B0\n" +
                "Weather = " + status + "\n";
    }
}
