package com.bme.syx.middle;

public enum FormatEnum {

    Enum2(2),
    Enum3(3),
    Enum6(6),
    Enum7(7),
    Enum8(8),
    Enum9(9),
    Enum11(11),
    Enum12(12),
    Enum17(17),
    Enum18(18),
    Enum19(19),
    Enum21(21),
    Enum22(22),
    Enum23(23),
    Enum27(27),
    Enum28(28),
    Enum29(29),
    Enum70(70),
    Enum113(113),
    Enum14(14),
    Enum10(10),
    Enum20(20),
    Enum15(15),
    Enum300(300),
    Enum301(301),
    Enum302(302),
    Enum303(303);

    private  int value;

    FormatEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }



}
