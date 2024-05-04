package com.socgen.creditcardbackend.enums;

public enum UserRoles {
    CUSTOMER(1),APPROVER(2);

    private final int value;

    UserRoles(int value){this.value=value;}

    public int getValue(){return value;}


}
