package by.htp.jd2.entity;

public final class Crash {

    private int id;
    private String damage;
    private int amount;
    private int idCar;
    private int idUser;
    private boolean isComplete;

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

    private void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((damage == null) ? 0 : damage.hashCode());
        result = prime * result + id;
        result = prime * result + idCar;
        result = prime * result + idUser;
        result = prime * result + (isComplete ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Crash other = (Crash) obj;
        if (amount != other.amount)
            return false;
        if (damage == null) {
            if (other.damage != null)
                return false;
        } else if (!damage.equals(other.damage))
            return false;
        if (id != other.id)
            return false;
        if (idCar != other.idCar)
            return false;
        if (idUser != other.idUser)
            return false;
        if (isComplete != other.isComplete)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Crash [id=" + id + ", damage=" + damage + ", amount=" + amount + ", idCar=" + idCar + ", idUser="
                + idUser + ", isComplete=" + isComplete + "]";
    }


}
