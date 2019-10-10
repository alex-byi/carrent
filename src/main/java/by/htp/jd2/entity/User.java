package by.htp.jd2.entity;

public final class User {
    private String login;
    private String password;
    private String fullName;
    private String passNum;
    private String email;
    private String address;
    private UserType type;
    private int cash;
    private boolean active;
    private int id;

    public User(String login, String password, String fullName, String passNum, String email, String address, int cash,
                UserType type, boolean active, int id) {
        setLogin(login);
        setPassword(password);
        setFullName(fullName);
        setPassNum(passNum);
        setEmail(email);
        setAddress(address);
        setCash(cash);
        setType(type);
        setActive(active);
        setId(id);
    }

    public User(String login, String password, String fullName, String passNum, String email, String address) {
        setLogin(login);
        setPassword(password);
        setFullName(fullName);
        setPassNum(passNum);
        setEmail(email);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setType(UserType type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassNum() {
        return passNum;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public UserType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + cash;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + id;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((passNum == null) ? 0 : passNum.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        User other = (User) obj;
        if (active != other.active)
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (cash != other.cash)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (id != other.id)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (passNum == null) {
            if (other.passNum != null)
                return false;
        } else if (!passNum.equals(other.passNum))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + ", fullName=" + fullName + ", passNum=" + passNum
                + ", email=" + email + ", address=" + address + ", type=" + type + ", cash=" + cash + ", active="
                + active + ", id=" + id + "]";
    }

}
