package by.htp.jd2.entity;

import java.io.Serializable;
import java.util.Objects;

public final class Crash implements Serializable {


    private static final long serialVersionUID = 2471287905195877412L;
    private int id;
    private String damage;
    private int amount;
    private int idCar;
    private int idUser;
    private boolean isComplete;

    public Crash() {
    }

    public Crash(int id, String damage, int amount, int idCar, int idUser, boolean isComplete) {
        setId(id);
        setDamage(damage);
        setAmount(amount);
        setIdCar(idCar);
        setIdUser(idUser);
        setComplete(isComplete);
    }

    public Crash(String damage, int amount, int idCar, int idUser) {
        setDamage(damage);
        setAmount(amount);
        setIdCar(idCar);
        setIdUser(idUser);
    }


    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamage() {
        return damage;
    }

    private void setDamage(String damage) {
        this.damage = damage;
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIdCar() {
        return idCar;
    }

    private void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdUser() {
        return idUser;
    }

    private void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crash crash = (Crash) o;
        return id == crash.id &&
                amount == crash.amount &&
                idCar == crash.idCar &&
                idUser == crash.idUser &&
                isComplete == crash.isComplete &&
                Objects.equals(damage, crash.damage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, damage, amount, idCar, idUser, isComplete);
    }

    @Override
    public String toString() {
        return "Crash{" +
                "id=" + id +
                ", damage='" + damage + '\'' +
                ", amount=" + amount +
                ", idCar=" + idCar +
                ", idUser=" + idUser +
                ", isComplete=" + isComplete +
                '}';
    }
}
