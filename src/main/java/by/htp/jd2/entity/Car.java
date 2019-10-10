package by.htp.jd2.entity;


public final class Car {

    private int id;
    private String name;
    private int price;
    private String fuel;
    private String color;
    private String body;
    private TransmissionType transmissionType;
    private boolean active;


    public Car(String carname, int price, String carfuel, String carcolor, String carbody, TransmissionType transmissionType, boolean active) {
        setName(carname);
        setPrice(price);
        setFuel(carfuel);
        setColor(carcolor);
        setBody(carbody);
        setTransmissionType(transmissionType);
        setActive(active);

    }

    public Car(String carname, int price, String carfuel, String carcolor, String carbody, TransmissionType transmissionType, boolean active, int id) {
        setName(carname);
        setPrice(price);
        setFuel(carfuel);
        setColor(carcolor);
        setBody(carbody);
        setTransmissionType(transmissionType);
        setActive(active);
        setId(id);

    }


    public int getId() {
        return id;
    }


    private void setId(int id) {
        this.id = id;
    }


    public boolean isActive() {
        return active;
    }


    private void setActive(boolean active) {
        this.active = active;
    }


    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    private void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    private void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + price;
        result = prime * result + ((transmissionType == null) ? 0 : transmissionType.hashCode());
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
        Car other = (Car) obj;
        if (active != other.active)
            return false;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (fuel == null) {
            if (other.fuel != null)
                return false;
        } else if (!fuel.equals(other.fuel))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price != other.price)
            return false;
        if (transmissionType != other.transmissionType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Car [name=" + name + ", price=" + price + ", fuel=" + fuel + ", color=" + color + ", body=" + body
                + ", transmissionType=" + transmissionType + "]";
    }


}
