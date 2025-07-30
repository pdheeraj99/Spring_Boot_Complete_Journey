package com.security.learn.learn_springwithgoogleoauth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    private Instant expiryDate;

    // *** In the database, only the ID is stored. In your Java code, you get the full User object. ***

    // You then say, "I want to see the details of the student for this entry," by calling .getUser().

    // -> @JoinColumn(...): This annotation specifies the foreign key column that links the two tables.
    // -> name = "user_id": This creates a column in your refresh_token table named user_id.
    // -> referencedColumnName = "id": This specifies that the user_id column will store the value from the id column of the users table.
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}