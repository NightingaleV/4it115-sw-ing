package utils;

/**
 *
 * @author xzenj02
 */
public interface Subject {

    void registerObserver(Observer observer);

    void deleteObserver (Observer observer);

    void notifyAllObservers();

}