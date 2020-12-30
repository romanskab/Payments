package ua.payments.model.entity;

import ua.payments.model.entity.enums.RequestStatus;

import java.sql.Timestamp;
import java.util.Objects;

public class RequestToUnblock {
    private int id;
    private Account account;
    private RequestStatus requestStatus;
    private Timestamp timeCreated;
    private Timestamp timeDone;

    public RequestToUnblock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Timestamp getTimeDone() {
        return timeDone;
    }

    public void setTimeDone(Timestamp timeDone) {
        this.timeDone = timeDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestToUnblock that = (RequestToUnblock) o;
        return id == that.id &&
                account.equals(that.account) &&
                requestStatus == that.requestStatus &&
                timeCreated.equals(that.timeCreated) &&
                timeDone.equals(that.timeDone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, requestStatus, timeCreated, timeDone);
    }

    @Override
    public String toString() {
        return "RequestToUnblock{" +
                "id=" + id +
                ", account=" + account +
                ", requestStatus=" + requestStatus +
                ", timeCreated=" + timeCreated +
                ", timeDone=" + timeDone +
                '}';
    }
}
