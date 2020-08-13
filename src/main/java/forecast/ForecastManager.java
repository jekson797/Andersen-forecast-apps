package forecast;

import org.hibernate.Session;
import org.hibernate.Transaction;
import session_factory.SessionFactorySingleton;

import java.util.List;

public class ForecastManager {

    public void saveForecast(Forecast forecast) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(forecast);

            transaction.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public Forecast selectLastForecastByLocation(String location) {
        Forecast forecast = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Forecast> forecasts =
                    session.createQuery("FROM Forecast WHERE location = :location ORDER BY date, time DESC")
                            .setParameter("location", location)
                            .list();
            if (forecasts.size() > 0) {
                forecast = forecasts.get(0);
            } else {
                System.err.println("Forecast for this location doesn't exist");
            }

            transaction.commit();
        }
        return forecast;
    }
}
