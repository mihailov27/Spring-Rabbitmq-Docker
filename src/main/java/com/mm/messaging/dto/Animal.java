package com.mm.messaging.dto;

import java.io.Serializable;

public interface Animal extends Serializable {

    String getName();

    int getAge();

    AnimalType getType();

    int getLegsCount();

}
