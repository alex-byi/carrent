package by.htp.jd2.entity;

import java.io.Serializable;


/**
 * Order entity
 *
 * @author alexey
 */
public final class Order implements Serializable {

    private static final long serialVersionUID = 8604506722901962308L;
    private String dateOrder;
    private String startDate;
    private String endDate;
    private boolean isPaid;
    private boolean isCrash;
    private int idCar;
    private int crashBill;
    private int idUser;
    private int dayCol;
    private int amount;
    private int id;
    private boolean isCanceled;
    private boolean isComplete;
    private String rejectReason;


    public Order() {
    }

    public Order(String dateOrder, String startDate, String endDate, boolean isPaid, boolean isCrash, int idCar,
                 int crashBill, int idUser, int dayCol, int amount, int id, boolean isCanceled, boolean isComplete,
                 String rejectReason) {
        setDateOrder(dateOrder);
        setStartDate(startDate);
        setEndDate(endDate);
        setPaid(isPaid);
        setCrash(isCrash);
        setIdCar(idCar);
        setCrashBill(crashBill);
        setIdUser(idUser);
        setDayCol(dayCol);
        setAmount(amount);
        setId(id);
        setCanceled(isCanceled);
        setComplete(isComplete);
        setRejectReason(rejectReason);

    }

    public Order(String dateOrder, String startDate, String endDate, int idCar, int idUser, int amount, int dayCol) {
        setDateOrder(dateOrder);
        setStartDate(startDate);
        setEndDate(endDate);
        setIdCar(idCar);
        setIdUser(idUser);
        setAmount(amount);
        setDayCol(dayCol);
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public boolean isComplete() {
        return isComplete;
    }

    private void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    private void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDayCol() {
        return dayCol;
    }

    private void setDayCol(int dayCol) {
        this.dayCol = dayCol;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    private void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStartDate() {
        return startDate;
    }

    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    private void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    private void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isCrash() {
        return isCrash;
    }

    private void setCrash(boolean isCrash) {
        this.isCrash = isCrash;
    }

    public int getIdCar() {
        return idCar;
    }

    private void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getCrashBill() {
        return crashBill;
    }

    private void setCrashBill(int crashBill) {
        this.crashBill = crashBill;
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
        result = prime * result + crashBill;
        result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
        result = prime * result + dayCol;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + id;
        result = prime * result + idCar;
        result = prime * result + idUser;
        result = prime * result + (isCanceled ? 1231 : 1237);
        result = prime * result + (isComplete ? 1231 : 1237);
        result = prime * result + (isCrash ? 1231 : 1237);
        result = prime * result + (isPaid ? 1231 : 1237);
        result = prime * result + ((rejectReason == null) ? 0 : rejectReason.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        Order other = (Order) obj;
        if (amount != other.amount)
            return false;
        if (crashBill != other.crashBill)
            return false;
        if (dateOrder == null) {
            if (other.dateOrder != null)
                return false;
        } else if (!dateOrder.equals(other.dateOrder))
            return false;
        if (dayCol != other.dayCol)
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (id != other.id)
            return false;
        if (idCar != other.idCar)
            return false;
        if (idUser != other.idUser)
            return false;
        if (isCanceled != other.isCanceled)
            return false;
        if (isComplete != other.isComplete)
            return false;
        if (isCrash != other.isCrash)
            return false;
        if (isPaid != other.isPaid)
            return false;
        if (rejectReason == null) {
            if (other.rejectReason != null)
                return false;
        } else if (!rejectReason.equals(other.rejectReason))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [dateOrder=" + dateOrder + ", startDate=" + startDate + ", endDate=" + endDate + ", isPaid="
                + isPaid + ", isCrash=" + isCrash + ", idCar=" + idCar + ", crashBill=" + crashBill + ", idUser="
                + idUser + ", dayCol=" + dayCol + ", amount=" + amount + ", id=" + id + ", isCanceled=" + isCanceled
                + ", isComplete=" + isComplete + ", rejectReason=" + rejectReason + "]";
    }

}
