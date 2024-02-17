package org.example.model;

import lombok.Data;
import java.text.MessageFormat;

@Data
public class Contact implements Comparable<Contact>{
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return MessageFormat.format("{0} | {1} | {2}", fullName, phoneNumber, email);
    }

    @Override
    public int compareTo(Contact o) {
        return fullName.compareTo(o.fullName) + phoneNumber.compareTo(o.phoneNumber) + email.compareTo(o.email);
    }
}
