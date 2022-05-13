package com.example.demo;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "Payments")
public class Payment{
	private @Id	@GeneratedValue long id;
    @Pattern(regexp="(^LV\\d{2}[A-Z]{4}[A-Z0-9]{13}$)|(^EE\\d{18}$)|(^LT\\d{18}$)")
    private String iban;
    @NotNull
	private float amount;
    @CreationTimestamp
    private Timestamp createdAt;
    private HttpServletRequest CallerIP;

    Payment(){}

    Payment(String Iban, float Amount){
        this.iban = Iban;
        this.amount = Amount;
        this.createdAt = getCreatedAt();
        this.CallerIP = CallerIP;

    }

    public long getID(){
        return this.id;
    }
    public String getIban(){
        return this.iban;
    }
    public float getAmount(){
        return this.amount;
    }
    public Timestamp getCreatedAt(){return this.createdAt; }
    private HttpServletRequest CallerIP(){return this.CallerIP; }

    public void setID(long id){
        this.id = id;
    }
    public void setIban(String Iban){
        this.iban = Iban;
    }
    public void setAmount(float Amount){
        this.amount = Amount;
    }
    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt; }
    public void setCallerIP(HttpServletRequest CallerIP){
        this.CallerIP = CallerIP;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", CallerIP=" + CallerIP +
                '}';
    }
}