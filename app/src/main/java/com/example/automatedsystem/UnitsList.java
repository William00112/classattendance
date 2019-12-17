package com.example.automatedsystem;

class UnitsList {
    private final String unitcode;
    private final String unitname;
    private final String lecturer;

    public UnitsList(String unitcode, String unitname, String lecturer) {
        this.unitcode = unitcode;
        this.unitname = unitname;
        this.lecturer = lecturer;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getLecturer() {
        return lecturer;
    }
}