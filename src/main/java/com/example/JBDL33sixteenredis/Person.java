package com.example.JBDL33sixteenredis;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//needs the Serializable interface for checking if payload if serializable or not
public class Person implements Serializable {

    private  long id;
    private  String name;
    private int age;
    private double creditScore;
}
